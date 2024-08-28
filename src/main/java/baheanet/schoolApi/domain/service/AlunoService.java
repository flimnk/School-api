package baheanet.schoolApi.domain.service;
import baheanet.schoolApi.domain.aluno.*;
import baheanet.schoolApi.domain.turma.Turma;
import baheanet.schoolApi.domain.turma.TurmaRepository;
import baheanet.schoolApi.infra.excepitions.ValidaRegraDeNegocios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    public Page<DadosDetalhamentoAluno> listarAlunos(Pageable pageable) {
        return alunoRepository.findByAtivoTrue(pageable).map(DadosDetalhamentoAluno::new);
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id).
                orElseThrow(() -> new ValidaRegraDeNegocios("Id do aluno inexistente"));
    }

    public Aluno cadastrarAluno(DadosCadastroAluno dados) {
        Turma turma = turmaRepository.findByNome(dados.turma())
                .orElseThrow(() -> new ValidaRegraDeNegocios("Turma com o nome " + dados.turma() + " não encontrada."));

        Aluno novoAluno = new Aluno();

        if(dados.cpf() != null){
            ValidadorCpf.isValidCpf(dados.cpf());
            novoAluno.setCpf(dados.cpf());
        }

        novoAluno.setNome(dados.nome());
        novoAluno.setIdade(dados.idade());
        novoAluno.setTurma(turma);
        novoAluno.setMatricula(dados.matricula());
        novoAluno.setAtivo(true);
        return alunoRepository.save(novoAluno);
    }


    public Aluno atualizarAluno( DadosAtualizaAluno dados) {

        Aluno aluno = alunoRepository.findByNome(dados.nome()).
                 orElseThrow(() -> new ValidaRegraDeNegocios("Aluno com nome " + dados.nome() + " não encontrado."));

        if(!aluno.getAtivo()){
            throw  new ValidaRegraDeNegocios("Aluno com nome " + dados.nome() + "esta inativo");
        }

        if(dados.turma()!= null){
            Turma turma = turmaRepository.findByNome(dados.turma())
                    .orElseThrow(() -> new ValidaRegraDeNegocios("Turma com o nome " + dados.turma() + " não encontrada."));
            aluno.setTurma(turma);
        }

        if(dados.nome() != null){
            aluno.setNome(dados.novoNome());
        }
      return  alunoRepository.save(aluno);



    }


    public void desativarAluno(String matricula) {
        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(()-> new ValidaRegraDeNegocios("Aluno com matrícula " + matricula + " não existe" ));
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }


    public Aluno buscarAlunoPorNome(String nome) {
        return alunoRepository.findByNome(nome).
                orElseThrow(() -> new ValidaRegraDeNegocios("Aluno com " + nome+" não existe"));
    }
}
