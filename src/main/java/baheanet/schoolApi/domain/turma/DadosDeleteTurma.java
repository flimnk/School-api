package baheanet.schoolApi.domain.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosDeleteTurma(
        @NotBlank
        String nome

) {
}
