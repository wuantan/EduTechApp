package EduTechApp.example.demo.Model;

import jakarta.persistence.*;

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
    private String nom_cur;
    private String descripcion;
    private String instructor;
    private String categoria;
    private int costo;



}
