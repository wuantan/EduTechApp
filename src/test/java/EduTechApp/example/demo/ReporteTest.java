package EduTechApp.example.demo;


import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Model.Reporte;
import EduTechApp.example.demo.Model.Usuario;
import EduTechApp.example.demo.Repository.ReporteRepository;
import EduTechApp.example.demo.Service.ReporteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReporteTest {

    @Autowired
    ReporteRepository reporteRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ReporteService reporteServiceMock;

    @Test
    @DisplayName("FindAll")
    void FindallTest() {
        when(reporteServiceMock.obtenerTodos()).thenReturn(List.of(new Reporte()));
        List<Reporte> reportes = reporteServiceMock.obtenerTodos();
        assertNotNull(reportes);
        assertEquals(1,reportes.size());
    }

    @Test
    @DisplayName("Test controller")
    void testController()  {
        List<Reporte> reportesList = List.of(new Reporte());
        when(reporteServiceMock.obtenerTodos()).thenReturn(reportesList);

        try{
            mockMvc.perform(get("/reportes"))
                    .andExpect(status().isOk());

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Test eliminar reporte")
    void EliminarReporteTest()  {
        when(reporteServiceMock.obtenerReporte(1)).thenReturn(new Reporte());
        Reporte reporte = reporteServiceMock.obtenerReporte(1);
        assertNotNull(reporte);
        reporteServiceMock.eliminarReporte(1);
        when(reporteServiceMock.obtenerReporte(1)).thenReturn(null);
        assertNull(reporteServiceMock.obtenerReporte(1));
    }

}
