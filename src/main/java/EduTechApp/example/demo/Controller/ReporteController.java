package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Reporte;
import EduTechApp.example.demo.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public CollectionModel<EntityModel<Reporte>> getAllReportes() {
        List<Reporte> reportes = reporteService.obtenerTodos();

        List<EntityModel<Reporte>> recursos = reportes.stream().map(reporte ->
                EntityModel.of(reporte,
                        linkTo(methodOn(ReporteController.class).getReporteById(reporte.getId_Reporte())).withSelfRel(),
                        linkTo(methodOn(ReporteController.class).getAllReportes()).withRel("todos-los-reportes"))
        ).collect(Collectors.toList());

        return CollectionModel.of(recursos,
                linkTo(methodOn(ReporteController.class).getAllReportes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Reporte> getReporteById(@PathVariable int id) {
        Reporte reporte = reporteService.obtenerPorId(id);
        return EntityModel.of(reporte,
                linkTo(methodOn(ReporteController.class).getReporteById(id)).withSelfRel(),
                linkTo(methodOn(ReporteController.class).getAllReportes()).withRel("todos-los-reportes"));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Reporte>> crearReporte(@RequestBody Reporte reporte) {
        Reporte nuevo = reporteService.guardarReporte(reporte);
        EntityModel<Reporte> entityModel = EntityModel.of(nuevo,
                linkTo(methodOn(ReporteController.class).getReporteById(nuevo.getId_Reporte())).withSelfRel());

        return ResponseEntity
                .created(linkTo(methodOn(ReporteController.class).getReporteById(nuevo.getId_Reporte())).toUri())
                .body(entityModel);
    }

    @GetMapping("/fecha")
    public ResponseEntity<CollectionModel<EntityModel<Reporte>>> getReportesPorFecha(
            @RequestParam("fecha") @DateTimeFormat(pattern = "DD-MM-YYYY") Date fecha) {
        List<Reporte> reportes = reporteService.obtenerReportePorFecha(fecha);
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Reporte>> recursos = reportes.stream().map(reporte ->
                EntityModel.of(reporte,
                        linkTo(methodOn(ReporteController.class).getReporteById(reporte.getId_Reporte())).withSelfRel(),
                        linkTo(methodOn(ReporteController.class).getAllReportes()).withRel("todos-los-reportes"))
        ).collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(recursos,
                        linkTo(methodOn(ReporteController.class).getAllReportes()).withSelfRel()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable int id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}