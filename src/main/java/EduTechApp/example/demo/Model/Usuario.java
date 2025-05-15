package EduTechApp.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {

    private String id_usuario,nombre,correo,rol;
    private Date fecha_ultimo_login;
    private Boolean estado;
}