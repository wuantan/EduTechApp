package EduTechApp.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    private int id_usuario;
    private String nombre_user;
    private String mail;
    private String rol; // estudiante, cliente, profesor, administrativo
    private Date fecha_ultimo_login;
    private String estado; // activo o inactivo
}