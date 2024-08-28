package baheanet.schoolApi.domain.aluno;

import baheanet.schoolApi.domain.turma.DadosDetalhamentoTurma;
import baheanet.schoolApi.domain.turma.Turma;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhamentoAluno(
                                     String nome,
                                     int idade,
                                     String matricula,
                                     String turma,
                                     String cpf,
                                     Boolean ativo){

    public DadosDetalhamentoAluno (Aluno dados)
    {
        this(dados.getNome(), dados.getIdade(),dados.getMatricula(),dados.getTurma().getNome(), dados.getCpf(),dados.getAtivo());
    }
}
