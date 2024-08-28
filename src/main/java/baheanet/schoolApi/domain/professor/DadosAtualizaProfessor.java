package baheanet.schoolApi.domain.professor;

import jakarta.validation.constraints.NotBlank;


public record DadosAtualizaProfessor(
        @NotBlank
        String nome,

        String novoNome,

        Materia materia

    ){}
