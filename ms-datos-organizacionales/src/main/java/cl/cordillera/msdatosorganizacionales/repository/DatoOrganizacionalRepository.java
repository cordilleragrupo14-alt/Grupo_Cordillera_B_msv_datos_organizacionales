package cl.cordillera.msdatosorganizacionales.repository;
import cl.cordillera.msdatosorganizacionales.model.DatoOrganizacional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/** PATRÓN: Repository Pattern */
@Repository
public interface DatoOrganizacionalRepository extends MongoRepository<DatoOrganizacional, String> {
    List<DatoOrganizacional> findByArea(String area);
    List<DatoOrganizacional> findByAreaAndTipoDato(String area, String tipoDato);
    List<DatoOrganizacional> findBySucursal(String sucursal);
    List<DatoOrganizacional> findByFechaDatoBetween(LocalDateTime desde, LocalDateTime hasta);
}
