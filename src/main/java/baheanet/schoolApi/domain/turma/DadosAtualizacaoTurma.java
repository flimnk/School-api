package baheanet.schoolApi.domain.turma;

import baheanet.schoolApi.domain.professor.Professor;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizacaoTurma(
        @NotBlank
        String nome,
        String novoNome,


        @NotEmpty(message = "A lista de  professores não pode estar vazia")
        List<String>professores
) {
}
