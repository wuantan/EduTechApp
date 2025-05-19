package EduTechApp.example.demo.Repository;
import EduTechApp.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findBymail(String email); //buscar usuario por mail
    Optional<User> findByusername(String username); //buscar usuario por username
    Optional<User> findByid_usuario(Integer id_usuario);
}
