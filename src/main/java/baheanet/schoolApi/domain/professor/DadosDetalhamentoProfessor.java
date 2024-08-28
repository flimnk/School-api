package baheanet.schoolApi.domain.professor;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.turma.Turma;

public record DadosDetalhamentoProfessor(
        String nome,
        int idade,
        String cpf,
        Materia materia,
        Boolean ativo

) {

    public DadosDetalhamentoProfessor(Professor dados){
        this(dados.getNome(), dados.getIdade(), dados.getCpf(),dados.getMateria(),dados.getAtivo());
    }
}
