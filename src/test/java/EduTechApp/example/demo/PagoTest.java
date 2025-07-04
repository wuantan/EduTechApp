package EduTechApp.example.demo;


import EduTechApp.example.demo.Model.Pago;
import EduTechApp.example.demo.Repository.PagoRepository;
import EduTechApp.example.demo.Service.PagoService;
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

@SpringBootTest
@AutoConfigureMockMvc
public class PagoTest {

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PagoService pagoServiceMock;

    @Test
    @DisplayName("Findall Test")
    void TestPagoServiceMock(){
     when (pagoServiceMock.obtenerTodosLosPagos()).thenReturn(List.of(new Pago()));
     List<Pago>pagos = pagoServiceMock.obtenerTodosLosPagos();
     assertNotNull(pagos);
     assertEquals(1,pagos.size());
    }

    @Test
    @DisplayName("FindById Test")
    void TestFindById(){
        Pago pago = new Pago();
        when(pagoServiceMock.obtenerPagoPorId(1)).thenReturn(pago);
        Pago test = pagoServiceMock.obtenerPagoPorId(1);
        assertNotNull(test);
        assertEquals(pago,test);
    }

    @Test
    @DisplayName("DeleteById Test")
    void TestDeleteById(){
        Pago pago = new Pago();
        when(pagoServiceMock.obtenerPagoPorId(1)).thenReturn(pago);
        Pago test = pagoServiceMock.obtenerPagoPorId(1);
        assertNotNull(test);
        assertEquals(pago,test);
        pagoServiceMock.eliminarPago(1);
        when(pagoServiceMock.obtenerPagoPorId(1)).thenThrow(new RuntimeException("Pago no encontrado"));
        assertThrows(RuntimeException.class, () -> pagoServiceMock.obtenerPagoPorId(1));
    }

}
