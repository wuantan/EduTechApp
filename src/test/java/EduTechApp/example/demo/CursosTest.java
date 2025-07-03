package EduTechApp.example.demo;


import EduTechApp.example.demo.Model.Cursos;
import EduTechApp.example.demo.Model.Usuario;
import EduTechApp.example.demo.Repository.CursosRepository;
import EduTechApp.example.demo.Service.CursoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CursosTest {
    @MockitoBean
    CursosRepository cursosRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CursoService cursoServiceMock;

    @Test
    @DisplayName("GetAll Test")
    void TestCursoServiceMock(){
        when(cursoServiceMock.getCursos()).thenReturn(String.join(new Cursos()));
        String Cursos =cursoServiceMock.getCursos();
        assertNotNull(Cursos);
    }
    
}
