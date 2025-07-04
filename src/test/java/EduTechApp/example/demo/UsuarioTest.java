package EduTechApp.example.demo;

import EduTechApp.example.demo.Model.Usuario;
import EduTechApp.example.demo.Repository.UserRepository;
import EduTechApp.example.demo.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@AutoConfigureMockMvc
class UsuarioTest {

    @MockitoBean
    UserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userServiceMock;

    @Test
    @DisplayName("GetAll Test")
    void TestUserServiceMock(){
        when(userServiceMock.getAllUsers()).thenReturn(List.of(new Usuario()));
        List<Usuario> usuarios = userServiceMock.getAllUsers();
        assertNotNull(usuarios);
        assertEquals(1,usuarios.size());
    }

    @Test
    @DisplayName("FindById Test")
    void TestFindById(){
        when(userServiceMock.getUser(1)).thenReturn(java.util.Optional.of(new Usuario()));
        Usuario usuario = userServiceMock.getUser(1).get();
        assertNotNull(usuario);
    }

    @Test
    @DisplayName("DeleteUser Test")
    void TestDeleteUser(){
        when(userServiceMock.getUser(1)).thenReturn(java.util.Optional.of(new Usuario()));
        Usuario usuario = userServiceMock.getUser(1).get();
        assertNotNull(usuario);
        userServiceMock.deleteUser(1);
        when(userServiceMock.getUser(1)).thenReturn(java.util.Optional.empty());
        assertTrue(userServiceMock.getUser(1).isEmpty());
    }

}
