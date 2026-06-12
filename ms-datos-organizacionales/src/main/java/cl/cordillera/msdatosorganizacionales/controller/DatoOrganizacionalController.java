package cl.cordillera.msdatosorganizacionales.controller;

import cl.cordillera.msdatosorganizacionales.dto.DatoOrganizacionalDTO;
import cl.cordillera.msdatosorganizacionales.service.DatoOrganizacionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

// NOTA: @CrossOrigin eliminado — CORS gestionado en CorsConfig global
@RestController
@RequestMapping("/api/datos")
@RequiredArgsConstructor
public class DatoOrganizacionalController {

    private final DatoOrganizacionalService service;

    @GetMapping
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatoOrganizacionalDTO> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @GetMapping("/area/{area}")
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerPorArea(@PathVariable String area) {
        return ResponseEntity.ok(service.obtenerPorArea(area));
    }
    @GetMapping("/area/{area}/tipo/{tipoDato}")
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerPorAreaYTipo(
            @PathVariable String area, @PathVariable String tipoDato) {
        return ResponseEntity.ok(service.obtenerPorAreaYTipo(area, tipoDato));
    }
    @GetMapping("/rango")
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerPorRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        return ResponseEntity.ok(service.obtenerPorRangoFecha(desde, hasta));
    }
    @PostMapping
    public ResponseEntity<DatoOrganizacionalDTO> crear(@Valid @RequestBody DatoOrganizacionalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DatoOrganizacionalDTO> actualizar(
            @PathVariable String id, @Valid @RequestBody DatoOrganizacionalDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
