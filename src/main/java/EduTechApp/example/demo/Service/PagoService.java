package EduTechApp.example.demo.Service;

import EduTechApp.example.demo.Repository.PagoRepository;
import EduTechApp.example.demo.Model.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    PagoRepository pagoRepository;

    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPagoPorId(int id) {
        return pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public String eliminarPago(int id) {
        pagoRepository.deleteById(id);
        return "Pago eliminado correctamente";
    }

    public Optional<Pago> obtenerPagoPorMetodo(String metodoPago) {
        return pagoRepository.findByMetodoPago(metodoPago);
    }
}