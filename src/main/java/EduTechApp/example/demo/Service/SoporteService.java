package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Model.Reporte;
import EduTechApp.example.demo.Repository.SoporteRepository;
import EduTechApp.example.demo.Model.Soporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoporteService {

    @Autowired
    SoporteRepository soporteRepository;

    public List<Soporte> obtenerTodosLosSoportes() {
        return soporteRepository.findAll();
    }

    public Optional<Soporte> obtenerSoporteById(int id) {
        return soporteRepository.findById(id);
    }
}