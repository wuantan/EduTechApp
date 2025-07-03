package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Repository.CursosRepository;
import EduTechApp.example.demo.Model.Cursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursosRepository cursosRepository;

    public List<Cursos> getAllCursos() {
        return cursosRepository.findAll();
    }

    public Cursos getCursoEntityById(int id) {
        return cursosRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public String addCurso(Cursos newCurso){
        cursosRepository.save(newCurso);
        return "curso agregado con éxito";
    }

    public String deleteCursoById(int id){
        cursosRepository.deleteById(id);
        return "Curso eliminado con éxito";
    }

    public String updateCursoById(int id, Cursos newCurso){
        Cursos curso = cursosRepository.findById(id).orElse(null);
        if(curso == null){
            return "Curso no encontrado";
        }else{
            curso.setNom_cur(newCurso.getNom_cur());
            curso.setDescripcion(newCurso.getDescripcion());
            curso.setInstructor(newCurso.getInstructor());
            curso.setCategoria(newCurso.getCategoria());
            curso.setCosto(newCurso.getCosto());
            cursosRepository.save(curso);
            return "Curso actualizado con éxito";
        }
    }
}