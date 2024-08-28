package baheanet.schoolApi.domain.turma;


import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.professor.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;

    private String nome;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "turma_professor",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores = new ArrayList<>();

    public String toString() {
        StringBuilder sb = new StringBuilder("Turma { ");
        sb.append("id=").append(id).append(", ");
        sb.append("ativo=").append(ativo).append(", ");
        sb.append("nome='").append(nome).append('\'').append(", ");
        sb.append("professores=[");
        for (int i = 0; i < professores.size(); i++) {
            sb.append(professores.get(i).getNome());
            if (i < professores.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        sb.append(" }");
        return sb.toString();
    }
}
