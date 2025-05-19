package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Model.Certificado;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class CertificadoService {

    public Certificado generarCertificado(String nombreCurso,
                                          String nombreEstudiante,
                                          String nombreProfesor) {
        Certificado certificado = new Certificado();
        certificado.setNombreCurso(nombreCurso);
        certificado.setNombreEstudiante(nombreEstudiante);
        certificado.setNombreProfesor(nombreProfesor);
        certificado.setFechaCertificado(new Date());
        certificado.setEstadoCurso(true);

        return certificado;
    }
}