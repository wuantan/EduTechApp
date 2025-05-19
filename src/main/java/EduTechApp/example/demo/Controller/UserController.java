package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.User;
import EduTechApp.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    // Registro
    @PostMapping("/registro")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "Usuario eliminado con éxito.";
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.getUser(id);
        if (existingUser.isPresent()) {
            updatedUser.setIdUsuario(id); // Asegura que se actualice el usuario correcto
            userService.updateUser(updatedUser);
            return "Usuario actualizado con éxito.";
        } else {
            return "Usuario no encontrado.";
        }
    }
}