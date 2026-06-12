package cl.cordillera.msdatosorganizacionales.service;
import cl.cordillera.msdatosorganizacionales.dto.DatoOrganizacionalDTO;
import cl.cordillera.msdatosorganizacionales.factory.DatoOrganizacionalFactory;
import cl.cordillera.msdatosorganizacionales.model.DatoOrganizacional;
import cl.cordillera.msdatosorganizacionales.repository.DatoOrganizacionalRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatoOrganizacionalServiceTest {
    @Mock DatoOrganizacionalRepository repository;
    @Mock DatoOrganizacionalFactory factory;
    @InjectMocks DatoOrganizacionalService service;

    private DatoOrganizacional dato;
    private DatoOrganizacionalDTO dto;

    @BeforeEach void setUp() {
        dato = DatoOrganizacional.builder().id("1").area("ventas").tipoDato("ventas_diarias").valor(1500000.0).unidad("CLP").build();
        dto = DatoOrganizacionalDTO.builder().id("1").area("ventas").tipoDato("ventas_diarias").valor(1500000.0).unidad("CLP").build();
    }

    @Test void obtenerTodos_debeRetornarLista() {
        when(repository.findAll()).thenReturn(List.of(dato));
        when(factory.crearDTO(dato)).thenReturn(dto);
        List<DatoOrganizacionalDTO> resultado = service.obtenerTodos();
        assertNotNull(resultado); assertEquals(1, resultado.size());
    }
    @Test void obtenerPorId_cuandoExiste_debeRetornarDTO() {
        when(repository.findById("1")).thenReturn(Optional.of(dato));
        when(factory.crearDTO(dato)).thenReturn(dto);
        assertEquals("1", service.obtenerPorId("1").getId());
    }
    @Test void obtenerPorId_cuandoNoExiste_debeLanzarExcepcion() {
        when(repository.findById("X")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.obtenerPorId("X"));
    }
    @Test void crear_debeGuardarYRetornarDTO() {
        when(factory.crearDesdeDTO(dto)).thenReturn(dato);
        when(repository.save(dato)).thenReturn(dato);
        when(factory.crearDTO(dato)).thenReturn(dto);
        assertNotNull(service.crear(dto));
        verify(repository, times(1)).save(any());
    }
    @Test void eliminar_cuandoExiste_debeEliminar() {
        when(repository.existsById("1")).thenReturn(true);
        doNothing().when(repository).deleteById("1");
        assertDoesNotThrow(() -> service.eliminar("1"));
    }
    @Test void eliminar_cuandoNoExiste_debeLanzarExcepcion() {
        when(repository.existsById("X")).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.eliminar("X"));
    }
}
