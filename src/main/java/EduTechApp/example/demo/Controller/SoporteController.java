package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Soporte;
import EduTechApp.example.demo.Service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    SoporteService soporteService;

    @GetMapping
    public List<Soporte> listarSoportes() {
        return soporteService.obtenerTodosLosSoportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> getSoportePorId(@PathVariable int id) {
        Optional<Soporte> soporte = soporteService.obtenerSoporteById(id);
        if (soporte.isPresent()) {
            return ResponseEntity.ok(soporte.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
