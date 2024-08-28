package baheanet.schoolApi.domain.service;


import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.professor.*;
import baheanet.schoolApi.domain.turma.Turma;
import baheanet.schoolApi.domain.turma.TurmaRepository;
import baheanet.schoolApi.infra.excepitions.ValidaRegraDeNegocios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfessorService {



    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;


    public Professor cadastrarProfessor(DadosCadastroProfessor dados) {
        ValidadorCpf.isValidCpf(dados.cpf());

        Professor professor = new Professor();
        professor.setNome(dados.nome());
        professor.setAtivo(true);
        professor.setIdade(dados.idade());
        professor.setMateria(dados.materia());
        professor.setCpf(dados.cpf());
        return  professorRepository.save(professor);
    }

    public Professor atualizarProfessor(DadosAtualizaProfessor dados) {
        System.out.println(dados.nome());
        System.out.println(dados.novoNome());
       Professor professor = professorRepository.findByNome(dados.nome()).
                orElseThrow(() -> new ValidaRegraDeNegocios("Professor com nome " + dados.nome() + " não encontrado."));

       if(!professor.isAtivo()){
           throw  new ValidaRegraDeNegocios("Professor com id " +  dados.nome() + "esta inativo");

       }

       if(dados.materia()!= null){
           professor.setMateria(dados.materia());
       }
       if(dados.nome() != null){
           professor.setNome(dados.novoNome());
       }
       return  professorRepository.save(professor);
    }


    public void deletarProfessor(String cpf) {


        Professor professor = professorRepository.findByCpf(cpf).
                orElseThrow(() -> new ValidaRegraDeNegocios("Professor com cpf " + cpf + " não encontrado."));

       var turmas = turmaRepository.findTurmasByProfessorId(professor.getId());

       if(!professor.isAtivo()){
         throw   new ValidaRegraDeNegocios("Professor com cpf " + cpf + " ja está inativo");
       }
       if(turmas.isEmpty()){
           professor.setAtivo(false);
           return;
       }

        for (Turma t : turmas) {
            t.getProfessores().remove(professor);
        }

        professor.setAtivo(false);

    }

    public Professor buscarProfessor(Long id) {
        return professorRepository.findById(id).
                orElseThrow(() -> new ValidaRegraDeNegocios("Professor com id " + id + " não encontrado"));
    }

    public Page<DadosDetalhamentoProfessor> listarProfessores(Pageable pageable) {
        return professorRepository.findByAtivoTrue(pageable).map(DadosDetalhamentoProfessor::new);

    }

    public List<Professor> listarNomeProfessores() {
        return professorRepository.findByAtivoTrue();
    }

    public Professor buscarProfessorPorNome(String nome) {
        return professorRepository.findByNome(nome).
                orElseThrow(() -> new ValidaRegraDeNegocios("Professor com " + nome +" não existe"));
    }
}
