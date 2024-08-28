package baheanet.schoolApi.domain.professor;

import baheanet.schoolApi.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;

    private String nome;

    private int idade;

    private String cpf;

    @Enumerated(EnumType.STRING)

    private Materia materia;

    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", materia='" + materia + '\'' +
                '}';
    }

    public boolean isAtivo() {
        return this.ativo;
    }
}

