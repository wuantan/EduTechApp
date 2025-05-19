package EduTechApp.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int id_usuario;
    private String username;
    private String nombre;
    private String password;
    private String mail;
    private String rol; // estudiante, cliente, profesor, administrativo
    private Date fecha_ultimo_login;
    private String estado; // activo o inactivo
}