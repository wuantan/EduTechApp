package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String getCursos(){
        return cursoService.getCursos();
    }
    @PostMapping
    public String addCurso(@RequestBody Cursos newCurso){
        return cursoService.addCurso(newCurso);
    }
    @GetMapping("/{id}")
    public String getCursoById(@PathVariable int id){
        return cursoService.getCursoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCursoById(@PathVariable int id){
        return cursoService.deleteCursoById(id);
    }
    @PutMapping("/{id}")
    public String updateCursoById(@PathVariable int id,@RequestBody Cursos newCurso){
        return cursoService.updateCursoById(id,newCurso);
    }
}
