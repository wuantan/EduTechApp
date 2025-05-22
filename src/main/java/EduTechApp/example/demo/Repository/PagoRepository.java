package EduTechApp.example.demo.Repository;

import EduTechApp.example.demo.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    Optional<Pago> findByMetodoPago(String Metodo);
}