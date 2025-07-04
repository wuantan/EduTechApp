package EduTechApp.example.demo;


import EduTechApp.example.demo.Model.Certificado;
import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Repository.CertificadoRepository;
import EduTechApp.example.demo.Service.CertificadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CertificadoTest {

    @Autowired
    CertificadoRepository certificadoRepository;
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    CertificadoService certificadoServiceMock;

    @Test
    @DisplayName("Generar Certificado Test")
    void TestGenerarCertificado(){
        when(certificadoServiceMock.generarCertificado("Desarrollo Web","Diego Velasquez","Carlos Abarzua")).thenReturn(new Certificado());
        Certificado certificado = certificadoServiceMock.generarCertificado("Desarrollo Web","Diego Velasquez","Carlos Abarzua");
        assertNotNull(certificado);
    }

    @Test
    @DisplayName("FindById")
    void TestFindById(){
        Certificado test = new Certificado();
        test.setId_certificado(1);
        when(certificadoServiceMock.obtenerPorId(1)).thenReturn(test);
        Certificado resultado = certificadoServiceMock.obtenerPorId(1);

        assertNotNull(resultado);

    }

    @Test
    @DisplayName("Obtener todos")
    void TestObtenerTodos(){
        when(certificadoServiceMock.obtenerTodos()).thenReturn(List.of(new Certificado()));
        List<Certificado> certificados=certificadoServiceMock.obtenerTodos();
        assertNotNull(certificados);
        assertEquals(1,certificados.size());
    }
}
