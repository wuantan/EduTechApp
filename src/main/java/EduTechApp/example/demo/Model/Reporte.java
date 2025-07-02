package EduTechApp.example.demo.Model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Reporte;
    private Date fechaReporte;
    private String Nombre_reporte;
    private String Tipo_reporte;
}