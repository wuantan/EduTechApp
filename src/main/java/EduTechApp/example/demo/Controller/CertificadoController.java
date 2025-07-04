package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Certificado;
import EduTechApp.example.demo.Service.CertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;


    @PostMapping("/generar")
    public ResponseEntity<EntityModel<Certificado>> generarCertificado(
            @RequestParam String nombreCurso,
            @RequestParam String nombreEstudiante,
            @RequestParam String nombreProfesor) {

        Certificado certificado = certificadoService.generarCertificado(
                nombreCurso, nombreEstudiante, nombreProfesor);

        EntityModel<Certificado> recurso = EntityModel.of(certificado);

        recurso.add(linkTo(methodOn(CertificadoController.class)
                .obtenerPorId(certificado.getId_certificado())).withSelfRel());

        recurso.add(linkTo(methodOn(CertificadoController.class)
                .listarCertificados()).withRel("todos"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping
    public ResponseEntity<List<Certificado>> listarCertificados() {
        return ResponseEntity.ok(certificadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Certificado>> obtenerPorId(@PathVariable int id) {
        Certificado cert = certificadoService.obtenerPorId(id);
        if (cert == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Certificado> recurso = EntityModel.of(cert);
        recurso.add(linkTo(methodOn(CertificadoController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(CertificadoController.class).listarCertificados()).withRel("todos"));

        return ResponseEntity.ok(recurso);
    }


}