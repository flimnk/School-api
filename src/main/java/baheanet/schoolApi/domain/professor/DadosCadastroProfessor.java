package baheanet.schoolApi.domain.professor;

import baheanet.schoolApi.domain.turma.Turma;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroProfessor(
        @NotBlank
        String nome,

        @NotNull
        int idade,

        @NotBlank
        @Pattern(message = "Cpf deve conter 11 digitos",regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull
        Materia materia
){
}
