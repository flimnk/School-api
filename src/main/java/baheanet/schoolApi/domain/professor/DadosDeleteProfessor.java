package baheanet.schoolApi.domain.professor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosDeleteProfessor(
        @NotBlank
        @Pattern(message = "Cpf deve conter 11 digitos",regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf
)
        {

}
