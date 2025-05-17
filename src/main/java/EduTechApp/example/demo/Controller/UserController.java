package EduTechApp.example.demo.Controller;


//import com.example.StudyService.Model.User;
//import com.example.StudyService.Service.UserService;
import EduTechApp.example.demo.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public String addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public String getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}