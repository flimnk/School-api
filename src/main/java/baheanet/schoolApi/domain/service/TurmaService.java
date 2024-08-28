package baheanet.schoolApi.domain.service;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.professor.Professor;
import baheanet.schoolApi.domain.professor.ProfessorRepository;
import baheanet.schoolApi.domain.turma.*;
import baheanet.schoolApi.infra.excepitions.ValidaRegraDeNegocios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ProfessorRepository professorRepository;


    public Turma cadastrarTurma(DadosCadastroTurma dados) {
        List<Professor> professores = dados.professores().stream().map(p -> {
            return professorRepository.getIdByNome(p)
            .orElseThrow(()-> new ValidaRegraDeNegocios("Profesosr com nome" + p + "não existe"));
        }).toList();


        var profesoresID = professores.stream().map(Professor::getId).toList();
        verificaIds(profesoresID);

//        List<Professor> professores = dados.professores().stream()
//                .map(professorId -> professorRepository.findById(professorId)
//                        .orElseThrow(() -> new ValidaRegraDeNegocios("Professor com ID " + professorId + " não encontrado.")))
//                .collect(Collectors.toList());


        Turma turma = new Turma();
        turma.setNome(dados.nome());
        turma.setProfessores(professores);
        turma.setAtivo(true);
        return  turmaRepository.save(turma);
    }


    public Turma atualizarTurma(DadosAtualizacaoTurma dados) {
        Turma turma  = turmaRepository.findByNome(dados.nome())
                .orElseThrow( () -> new ValidaRegraDeNegocios("Turma com nome " +  dados.nome() + " não existe"));

        if(!turma.getAtivo()){
            throw  new ValidaRegraDeNegocios("Turma com nome " +  dados.nome() + " esta inativa");
        }
        System.out.println(turma);


        if(dados.professores()!= null){
            System.out.println(dados.professores());
            List<Professor> professores = dados.professores().stream().map(p -> {
                return professorRepository.findByNome(p)
                        .orElseThrow(()-> new ValidaRegraDeNegocios("Profesosr com nome" + p + "não existe"));
            }).toList();

            System.out.println(professores);
            var profesoresID = professores.stream().map(Professor::getId).toList();
            verificaIds(profesoresID);
            List<Professor> novaListaProfessores = new ArrayList<>(professores);
            turma.setProfessores(novaListaProfessores);
            turma.setProfessores(novaListaProfessores);



    }

        if(dados.nome() != null){
            turma.setNome(dados.novoNome());
        }

        return  turmaRepository.save(turma);
    }















    public Page<DadosDetalhamentoTurma> listarAlunos(Pageable pageable) {
       return turmaRepository.findByAtivoTrue(pageable).map(DadosDetalhamentoTurma::new);
    }

    public Turma detalharTurma(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(()-> new ValidaRegraDeNegocios("Turma com "+ id + " não existe"));
    }

    public void desativarTurma(String nome) {
        Turma turma= turmaRepository.findByNome(nome)
                .orElseThrow(()-> new ValidaRegraDeNegocios("Turma com "+ nome + " não existe"));
        turma.setAtivo(false);
    }

    public Turma buscarTurmaPorNome(String nome) {
        return turmaRepository.findByNome(nome).
                orElseThrow(() -> new ValidaRegraDeNegocios("Turma com " + nome+" não existe"));
    }








    private void verificaIds(List<Long> dados) {

        dados.forEach(professorID -> {
            Professor professor = professorRepository.findById(professorID)
                    .orElseThrow(() -> new ValidaRegraDeNegocios("Professor com ID " + professorID + " não existe."));
            if (!professor.isAtivo()) {
                throw new ValidaRegraDeNegocios("Professor com ID " + professorID + " não está ativo.");
            }
        });


    }


}
