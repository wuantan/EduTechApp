package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Reporte;
import EduTechApp.example.demo.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> getAllReportes() {
        return reporteService.obtenerTodos();
    }

    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {
        return reporteService.guardarReporte(reporte);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Reporte>> getReportesPorFecha(
            @RequestParam("fecha")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<Reporte> reportes = reporteService.obtenerReportePorFecha(fecha);
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable int id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}