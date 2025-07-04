package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Repository.ReporteRepository;
import EduTechApp.example.demo.Model.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> obtenerReportePorFecha(Date fecha) {
        return reporteRepository.findByfechaReporte(fecha);
    }

    public void eliminarReporte(int id) {
        reporteRepository.deleteById(id);
    }
    public Reporte obtenerPorId(int id) {
        return reporteRepository.findById(id).orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }
}
