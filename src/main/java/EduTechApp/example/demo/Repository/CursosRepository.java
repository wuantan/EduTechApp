package EduTechApp.example.demo.Repository;

import EduTechApp.example.demo.Model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CursosRepository extends JpaRepository<Cursos, Integer> {

}
