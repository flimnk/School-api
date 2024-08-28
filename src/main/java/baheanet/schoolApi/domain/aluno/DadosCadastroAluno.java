       package baheanet.schoolApi.domain.aluno;

       import jakarta.validation.constraints.NotBlank;
       import jakarta.validation.constraints.NotNull;
       import jakarta.validation.constraints.Pattern;

       public record DadosCadastroAluno(
              @NotBlank
              String nome,
              @NotNull
              int idade,

              Boolean ativo,

              @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
              String cpf,

              @NotBlank
              String turma,

              @NotBlank
              @Pattern(regexp = "\\d{6}")
              String matricula
       ) {
       }
