package baheanet.schoolApi.domain.aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    Page<Aluno> findByAtivoTrue(Pageable pageable);


    Optional<Aluno> findByNome(String nome);

    Optional<Aluno> findByMatricula(String matricula);
}

