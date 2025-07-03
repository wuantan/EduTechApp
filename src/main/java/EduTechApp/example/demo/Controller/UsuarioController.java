package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Usuario;
import EduTechApp.example.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String registerUser(@RequestBody Usuario user) {
        return usuarioService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        return usuarioService.loginUser(username, password);
    }

    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsers() {
        List<EntityModel<Usuario>> usuarios = usuarioService.getAllUsers().stream()
                .map(usuario -> EntityModel.of(usuario,
                        linkTo(methodOn(UsuarioController.class).getUserById(usuario.getIdUsuario())).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).getAllUsers()).withRel("usuarios")))
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).getAllUsers()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Usuario> getUserById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.getUser(id);
        if (usuario.isEmpty()) {
            throw new RuntimeException("usuario no encontrado");
        }

        return EntityModel.of(usuario.get(),
                linkTo(methodOn(UsuarioController.class).getUserById(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsers()).withRel("usuarios"));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        usuarioService.deleteUser(id);
        return "Usuario eliminado con éxito.";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody Usuario updatedUser) {
        Optional<Usuario> existingUser = usuarioService.getUser(id);
        if (existingUser.isPresent()) {
            updatedUser.setIdUsuario(id);
            usuarioService.updateUser(updatedUser);
            return "Usuario actualizado con exito.";
        } else {
            return "Usuario no encontrado.";
        }
    }
}