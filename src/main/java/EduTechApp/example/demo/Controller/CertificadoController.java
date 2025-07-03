package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Certificado;
import EduTechApp.example.demo.Service.CertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;


    @PostMapping("/generar")
    public ResponseEntity<Certificado> generarCertificado(
            @RequestParam String nombreCurso,
            @RequestParam String nombreEstudiante,
            @RequestParam String nombreProfesor) {

        Certificado certificado = certificadoService.generarCertificado(
                nombreCurso, nombreEstudiante, nombreProfesor);

        return ResponseEntity.ok(certificado);
    }


}