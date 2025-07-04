package EduTechApp.example.demo;


import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Repository.CursosRepository;
import EduTechApp.example.demo.Service.CursoService;
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
public class CursosTest {
    @Autowired
    CursosRepository cursosRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CursoService cursoServiceMock;

    @Test
    @DisplayName("GetAll Test")
    void TestCursoServiceMock(){
        when(cursoServiceMock.getAllCursos()).thenReturn(List.of(new Cursos()));
        List<Cursos> cursos=cursoServiceMock.getAllCursos();
        assertNotNull(cursos);
        assertEquals(1,cursos.size());
    }

    @Test
    @DisplayName("FindById CURSO NO ENCONTRADO")
    void testFindByIdNotFound(){
        when(cursoServiceMock.getCursoById(999)).thenReturn("Curso no encontrado");

        String resultado = cursoServiceMock.getCursoById(999);

        assertEquals("Curso no encontrado", resultado);
    }

    @Test
    @DisplayName("FindById CURSO ENCONTRADO")
    void testFindByIdFound(){
        when(cursoServiceMock.getCursoById(1)).thenReturn("Curso encontrado");
    }

}