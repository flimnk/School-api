package baheanet.schoolApi.domain.turma;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.aluno.DadosCadastroAluno;
import baheanet.schoolApi.domain.professor.DadosCadastroProfessor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record DadosCadastroTurma(

        @NotBlank(message = "O nome da turma é obrigatório")
        String nome,


        @NotEmpty(message = "A lista de  professores não pode estar vazia")
        List<String>professores

) {
}

