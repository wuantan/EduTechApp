package EduTechApp.example.demo.Repository;
import EduTechApp.example.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findBymail(String email); //buscar usuario por mail
    Optional<Usuario> findByusername(String username); //buscar usuario por username
    Optional<Usuario> findByidUsuario(Integer idUsuario);

}
