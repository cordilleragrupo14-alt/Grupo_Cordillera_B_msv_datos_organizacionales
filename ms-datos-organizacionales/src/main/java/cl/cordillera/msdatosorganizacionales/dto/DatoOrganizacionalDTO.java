package cl.cordillera.msdatosorganizacionales.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DatoOrganizacionalDTO {
    private String id;
    @NotBlank(message = "El área es obligatoria") private String area;
    @NotBlank(message = "El tipo de dato es obligatorio") private String tipoDato;
    @NotNull(message = "El valor no puede ser nulo") private Double valor;
    private String unidad;
    private String sucursal;
    private String fuente;
    private LocalDateTime fechaDato;
}
