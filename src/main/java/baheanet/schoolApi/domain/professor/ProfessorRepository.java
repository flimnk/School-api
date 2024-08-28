package baheanet.schoolApi.domain.professor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  ProfessorRepository  extends JpaRepository<Professor,Long> {
    Page<Professor> findByAtivoTrue(Pageable pageable);


    List<Professor> findByAtivoTrue();

    Optional<Professor> getIdByNome(String nome);

    Optional<Professor> findByNome(String nome);

    Optional<Professor> findByCpf(String cpf);
}

