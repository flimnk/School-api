package baheanet.schoolApi.domain.turma;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository  extends JpaRepository<Turma,Long> {
   Optional<Turma> findByNome(String nome);

   @Query("SELECT t FROM Turma t JOIN t.professores p WHERE p.id = :professorId")
   List<Turma> findTurmasByProfessorId(@Param("professorId") Long professorId);

   Page<Turma> findByAtivoTrue(Pageable pageable);
}
