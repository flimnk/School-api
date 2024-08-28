package baheanet.schoolApi.domain.aluno;

import jakarta.validation.constraints.NotBlank;

public record DadosDeleteAluno (
        @NotBlank
        String matricula

){
}
