package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Repository.UserRepository;
import EduTechApp.example.demo.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    //validacion de que el mail o username no este en uso, y registro de este mismo
    public String registerUser(Usuario user) {
        if (UserRepository.findBymail(user.getMail()).isPresent()) {
            return "Error: El email ya está registrado.";
        }
        if (UserRepository.findByusername(user.getUsername()).isPresent()) {
            return "Error: El nombre de usuario ya está en uso.";
        }
        UserRepository.save(user);
        return "Usuario registrado correctamente.";
    }

    // Login por username y password
    public String loginUser(String username, String password) {
        Optional<Usuario> userOpt = UserRepository.findByusername(username);
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return "Inicio de sesión exitoso.";
            } else {
                return "Error: Contraseña incorrecta.";
            }
        } else {
            return "Error: Usuario no encontrado.";
        }
    }


    public Usuario saveUser(Usuario user) {
        return UserRepository.save(user); //
    }

    public Optional<Usuario> getUser(int idUsuario) {
        return UserRepository.findById(idUsuario);
    }

    public void deleteUser(int idUsuario) {
        UserRepository.deleteById(idUsuario);
    }

    public List<Usuario> getAllUsers() {
        return UserRepository.findAll();
    }

    public Usuario updateUser(Usuario user) {
        return UserRepository.save(user);
    }
}