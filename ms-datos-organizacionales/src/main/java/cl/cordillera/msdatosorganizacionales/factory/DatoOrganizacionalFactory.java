package cl.cordillera.msdatosorganizacionales.factory;
import cl.cordillera.msdatosorganizacionales.dto.DatoOrganizacionalDTO;
import cl.cordillera.msdatosorganizacionales.model.DatoOrganizacional;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/** PATRÓN: Factory Method */
@Component
public class DatoOrganizacionalFactory {
    public DatoOrganizacional crearDesdeDTO(DatoOrganizacionalDTO dto) {
        return DatoOrganizacional.builder()
                .area(dto.getArea()).tipoDato(dto.getTipoDato()).valor(dto.getValor())
                .unidad(dto.getUnidad()).sucursal(dto.getSucursal()).fuente(dto.getFuente())
                .fechaDato(dto.getFechaDato() != null ? dto.getFechaDato() : LocalDateTime.now())
                .fechaRegistro(LocalDateTime.now()).build();
    }
    public DatoOrganizacionalDTO crearDTO(DatoOrganizacional dato) {
        return DatoOrganizacionalDTO.builder()
                .id(dato.getId()).area(dato.getArea()).tipoDato(dato.getTipoDato())
                .valor(dato.getValor()).unidad(dato.getUnidad()).sucursal(dato.getSucursal())
                .fuente(dato.getFuente()).fechaDato(dato.getFechaDato()).build();
    }
}
