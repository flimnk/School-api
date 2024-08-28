package baheanet.schoolApi.domain.professor;

public record DadosNomesProfessor (
        String nome
){
    public DadosNomesProfessor(Professor professor){
        this(professor.getNome());
    }
}
