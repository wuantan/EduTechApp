package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Model.Usuario;
import EduTechApp.example.demo.Repository.CursosRepository;
import EduTechApp.example.demo.Model.Cursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursosRepository cursosRepository;


    public String getCursos(){
        String output = "";
        for(Cursos curso : cursosRepository.findAll()){
            output += curso.getId_curso() + "\n";
            output += curso.getNom_cur() + "\n";
            output += curso.getDescripcion() + "\n";
            output += curso.getInstructor() + "\n";
            output += curso.getCategoria() + "\n";
            output += curso.getCosto() + "\n";
        }
        if (output.isEmpty()) {
            return "Aun no hay cursos";
        } else {
            return output;
        }
    }
    public String addCurso(Cursos newCurso){
        cursosRepository.save(newCurso);
        return "Curso agregado con exito";
    }

    public String getCursoById(int id){
        Cursos curso = cursosRepository.findById(id).orElse(null);
        if(curso == null){
            return "Curso no encontrado";
        }else{
            return curso.toString();
        }
    }
    public String deleteCursoById(int id){
        cursosRepository.deleteById(id);
        return "Curso eliminado con exito";
    }

    public String updateCursoById(int id, Cursos newCurso){
        Cursos curso = cursosRepository.findById(id).orElse(null);
        if(curso == null){
            return "Curso no encontrado";
        }else{
            curso.setNom_cur(newCurso.getNom_cur());
            curso.setDescripcion(newCurso.getDescripcion());
            curso.setInstructor(newCurso.getInstructor());
        }
        return "Curso actualizado con exito";
    }

    public List<Cursos> getAllCursos() {
        return cursosRepository.findAll();
    }

}
