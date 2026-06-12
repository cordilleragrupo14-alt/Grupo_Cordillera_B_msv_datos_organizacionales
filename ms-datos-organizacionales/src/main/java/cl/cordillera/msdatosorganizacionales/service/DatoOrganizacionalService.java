package cl.cordillera.msdatosorganizacionales.service;
import cl.cordillera.msdatosorganizacionales.dto.DatoOrganizacionalDTO;
import cl.cordillera.msdatosorganizacionales.factory.DatoOrganizacionalFactory;
import cl.cordillera.msdatosorganizacionales.model.DatoOrganizacional;
import cl.cordillera.msdatosorganizacionales.repository.DatoOrganizacionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j @Service @RequiredArgsConstructor
public class DatoOrganizacionalService {
    private final DatoOrganizacionalRepository repository;
    private final DatoOrganizacionalFactory factory;

    public List<DatoOrganizacionalDTO> obtenerTodos() {
        return repository.findAll().stream().map(factory::crearDTO).collect(Collectors.toList());
    }
    public DatoOrganizacionalDTO obtenerPorId(String id) {
        return factory.crearDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dato no encontrado con id: " + id)));
    }
    public List<DatoOrganizacionalDTO> obtenerPorArea(String area) {
        return repository.findByArea(area).stream().map(factory::crearDTO).collect(Collectors.toList());
    }
    public List<DatoOrganizacionalDTO> obtenerPorAreaYTipo(String area, String tipoDato) {
        return repository.findByAreaAndTipoDato(area, tipoDato).stream().map(factory::crearDTO).collect(Collectors.toList());
    }
    public List<DatoOrganizacionalDTO> obtenerPorRangoFecha(LocalDateTime desde, LocalDateTime hasta) {
        return repository.findByFechaDatoBetween(desde, hasta).stream().map(factory::crearDTO).collect(Collectors.toList());
    }
    public DatoOrganizacionalDTO crear(DatoOrganizacionalDTO dto) {
        return factory.crearDTO(repository.save(factory.crearDesdeDTO(dto)));
    }
    public DatoOrganizacionalDTO actualizar(String id, DatoOrganizacionalDTO dto) {
        DatoOrganizacional e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dato no encontrado con id: " + id));
        e.setArea(dto.getArea()); e.setTipoDato(dto.getTipoDato()); e.setValor(dto.getValor());
        e.setUnidad(dto.getUnidad()); e.setSucursal(dto.getSucursal()); e.setFuente(dto.getFuente());
        e.setFechaDato(dto.getFechaDato());
        return factory.crearDTO(repository.save(e));
    }
    public void eliminar(String id) {
        if (!repository.existsById(id)) throw new RuntimeException("Dato no encontrado con id: " + id);
        repository.deleteById(id);
    }
}
