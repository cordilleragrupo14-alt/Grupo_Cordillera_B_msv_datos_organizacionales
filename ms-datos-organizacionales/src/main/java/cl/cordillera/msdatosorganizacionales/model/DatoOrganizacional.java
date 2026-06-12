package cl.cordillera.msdatosorganizacionales.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Document(collection = "datos_organizacionales")
public class DatoOrganizacional {
    @Id private String id;
    @NotBlank(message = "El área es obligatoria") private String area;
    @NotBlank(message = "El tipo de dato es obligatorio") private String tipoDato;
    @NotNull(message = "El valor no puede ser nulo") private Double valor;
    private String unidad;
    private String sucursal;
    private String fuente;
    @Builder.Default private LocalDateTime fechaRegistro = LocalDateTime.now();
    private LocalDateTime fechaDato;
}
