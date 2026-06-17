package cl.cordillera.msdatosorganizacionales.service;

import cl.cordillera.msdatosorganizacionales.controller.DatoOrganizacionalController;
import cl.cordillera.msdatosorganizacionales.dto.DatoOrganizacionalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DatoOrganizacionalController.class)
class DatoOrganizacionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatoOrganizacionalService service;

    @Test
    void obtenerTodos_debeRetornarListaYStatusOk() throws Exception {
        DatoOrganizacionalDTO mockDato = DatoOrganizacionalDTO.builder().id("1").area("Ventas").valor(100.0).build();
        when(service.obtenerTodos()).thenReturn(List.of(mockDato));

        mockMvc.perform(get("/api/datos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].area").value("Ventas"));
    }
}