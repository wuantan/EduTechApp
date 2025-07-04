package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public CollectionModel<EntityModel<Cursos>> getCursos() {
        List<Cursos> cursos = cursoService.getAllCursos();
        List<EntityModel<Cursos>> cursosConLinks = cursos.stream().map(curso ->
                EntityModel.of(curso,
                        linkTo(methodOn(CursoController.class).getCursoById(curso.getId_curso())).withSelfRel(),
                        linkTo(methodOn(CursoController.class).getCursos()).withRel("todos")
                )
        ).collect(Collectors.toList());

        return CollectionModel.of(cursosConLinks,
                linkTo(methodOn(CursoController.class).getCursos()).withSelfRel());
    }

    @PostMapping
    public String addCurso(@RequestBody Cursos newCurso){
        return cursoService.addCurso(newCurso);
    }

    @GetMapping("/{id}")
    public EntityModel<Cursos> getCursoById(@PathVariable int id){
        Cursos curso = cursoService.getCursoEntityById(id);
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).getCursoById(id)).withSelfRel(),
                linkTo(methodOn(CursoController.class).getCursos()).withRel("todos")
        );
    }

    @DeleteMapping("/{id}")
    public String deleteCursoById(@PathVariable int id){
        return cursoService.deleteCursoById(id);
    }

    @PutMapping("/{id}")
    public String updateCursoById(@PathVariable int id, @RequestBody Cursos newCurso){
        return cursoService.updateCursoById(id, newCurso);
    }
}