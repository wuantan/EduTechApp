package EduTechApp.example.demo.Controller;

import EduTechApp.example.demo.Model.Pago;
import EduTechApp.example.demo.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.guardarPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping
    public CollectionModel<EntityModel<Pago>> listarPagos() {
        List<EntityModel<Pago>> pagos = pagoService.obtenerTodosLosPagos().stream()
                .map(pago -> EntityModel.of(pago,
                        linkTo(methodOn(PagoController.class).getPago(pago.getId_Pagos())).withSelfRel(),
                        linkTo(methodOn(PagoController.class).listarPagos()).withRel("todos")))
                .collect(Collectors.toList());

        return CollectionModel.of(pagos,
                linkTo(methodOn(PagoController.class).listarPagos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pago>> getPago(@PathVariable int id) {
        try {
            Pago pago = pagoService.obtenerPagoPorId(id);
            EntityModel<Pago> pagoResource = EntityModel.of(pago,
                    linkTo(methodOn(PagoController.class).getPago(id)).withSelfRel(),
                    linkTo(methodOn(PagoController.class).listarPagos()).withRel("todos"));

            return ResponseEntity.ok(pagoResource);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable int id) {
        try {
            pagoService.obtenerPagoPorId(id);
            pagoService.eliminarPago(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/metodo")
    public ResponseEntity<EntityModel<Pago>> buscarPorMetodoPago(@RequestParam String metodo) {
        Optional<Pago> pago = pagoService.obtenerPagoPorMetodo(metodo);
        return pago.map(p -> {
            EntityModel<Pago> pagoResource = EntityModel.of(p,
                    linkTo(methodOn(PagoController.class).getPago(p.getId_Pagos())).withSelfRel(),
                    linkTo(methodOn(PagoController.class).listarPagos()).withRel("todos"));
            return ResponseEntity.ok(pagoResource);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}