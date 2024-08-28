package baheanet.schoolApi.domain.aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaAluno(


        @NotNull
        String nome,
        String novoNome,
        String turma


)
{
}
