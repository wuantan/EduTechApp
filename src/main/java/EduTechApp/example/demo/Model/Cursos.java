package EduTechApp.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;
    private String nom_cur,descripcion,instructor,categoria;
    private int costo;

}
