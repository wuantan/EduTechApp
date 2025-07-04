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

import static org.junit.jupiter.api.Assertions.*;
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
    void testFindByIdNotFound() {
        // Configuramos el mock para que lance una excepción cuando se busque el ID 999
        when(cursoServiceMock.getCursoEntityById(999))
                .thenThrow(new RuntimeException("Curso no encontrado"));

        // Verificamos que se lance la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cursoServiceMock.getCursoEntityById(999);
        });

        // Verificamos que el mensaje de la excepción sea el esperado
        assertEquals("Curso no encontrado", exception.getMessage());
    }




    @Test
    @DisplayName("FindById CURSO ENCONTRADO")
    void testFindByIdFound() {

        Cursos cursoEsperado = new Cursos();
        cursoEsperado.setId_curso(1);
        cursoEsperado.setNom_cur("Java Programming");
        cursoEsperado.setDescripcion("Curso de Java");
        cursoEsperado.setInstructor("Juan Pérez");
        cursoEsperado.setCategoria("Programación");
        cursoEsperado.setCosto(100);

        when(cursoServiceMock.getCursoEntityById(1)).thenReturn(cursoEsperado);

        Cursos resultado = cursoServiceMock.getCursoEntityById(1);

        assertNotNull(resultado);
        assertEquals(cursoEsperado.getId_curso(), resultado.getId_curso());
        assertEquals(cursoEsperado.getNom_cur(), resultado.getNom_cur());
        assertEquals(cursoEsperado.getDescripcion(), resultado.getDescripcion());
        assertEquals(cursoEsperado.getInstructor(), resultado.getInstructor());
        assertEquals(cursoEsperado.getCategoria(), resultado.getCategoria());
        assertEquals(cursoEsperado.getCosto(), resultado.getCosto());
    }


}