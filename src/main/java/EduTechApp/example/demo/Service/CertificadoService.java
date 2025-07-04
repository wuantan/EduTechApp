package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Model.Certificado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CertificadoService {

    private List<Certificado> certificados = new ArrayList<>();
    private AtomicInteger idGenerator = new AtomicInteger(1);

    public Certificado generarCertificado(String nombreCurso,
                                          String nombreEstudiante,
                                          String nombreProfesor) {
        Certificado certificado = new Certificado();
        certificado.setId_certificado(idGenerator.getAndIncrement());
        certificado.setNombreCurso(nombreCurso);
        certificado.setNombreEstudiante(nombreEstudiante);
        certificado.setNombreProfesor(nombreProfesor);
        certificado.setFechaCertificado(new Date());
        certificado.setEstadoCurso(true);

        certificados.add(certificado);

        return certificado;
    }

    public List<Certificado> obtenerTodos() {
        return certificados;
    }

    public Certificado obtenerPorId(int id) {
        return certificados.stream()
                .filter(c -> c.getId_certificado() == id)
                .findFirst()
                .orElse(null);
    }
}