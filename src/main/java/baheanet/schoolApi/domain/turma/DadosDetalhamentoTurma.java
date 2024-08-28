package baheanet.schoolApi.domain.turma;

import baheanet.schoolApi.domain.professor.Professor;

import java.util.List;

public record DadosDetalhamentoTurma(
        String nome,
        List<Professor> professors,
        Boolean ativo

){
    public  DadosDetalhamentoTurma(Turma turma){
        this(turma.getNome(), turma.getProfessores(),turma.getAtivo());
    }

}
