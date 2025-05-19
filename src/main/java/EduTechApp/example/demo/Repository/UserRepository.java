package EduTechApp.example.demo.Repository;


import EduTechApp.example.demo.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface  UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findBymail(String mail); //buscar usuarios por mail
    Optional<User> findByusername(String username); //buscar usuarios por username
}
