package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Soporte;
import EduTechApp.example.demo.Service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    SoporteService soporteService;

    @GetMapping
    public CollectionModel<EntityModel<Soporte>> listarSoportes() {
        List<EntityModel<Soporte>> soportes = soporteService.obtenerTodosLosSoportes().stream()
                .map(soporte -> EntityModel.of(soporte,
                        linkTo(methodOn(SoporteController.class).getSoportePorId(soporte.getId_Reporte())).withSelfRel(),
                        linkTo(methodOn(SoporteController.class).listarSoportes()).withRel("todos")))
                .collect(Collectors.toList());

        return CollectionModel.of(soportes,
                linkTo(methodOn(SoporteController.class).listarSoportes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Soporte> getSoportePorId(@PathVariable int id) {
        Optional<Soporte> soporteOptional = soporteService.obtenerSoporteById(id);
        Soporte soporte = soporteOptional.orElseThrow(() -> new RuntimeException("soporte no encontrado"));

        return EntityModel.of(soporte,
                linkTo(methodOn(SoporteController.class).getSoportePorId(id)).withSelfRel(),
                linkTo(methodOn(SoporteController.class).listarSoportes()).withRel("todos"));
    }
}