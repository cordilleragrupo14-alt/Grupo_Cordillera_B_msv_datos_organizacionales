# ms-datos-organizacionales
Microservicio de Gestión de Datos Organizacionales — Grupo Cordillera | Puerto: 8081

## Variables de entorno (Render)
| Variable | Valor |
|----------|-------|
| `SPRING_DATA_MONGODB_URI` | `mongodb+srv://tomazamora_db_user:M8uMKBcal26Yzi1K@cluster0.ho4nu6m.mongodb.net/?appName=Cluster0` |
| `SPRING_DATA_MONGODB_DATABASE` | `cordillera_datos` |

## Endpoints
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/datos` | Obtener todos |
| GET | `/api/datos/{id}` | Obtener por ID |
| GET | `/api/datos/area/{area}` | Filtrar por área |
| GET | `/api/datos/area/{area}/tipo/{tipoDato}` | Filtrar por área y tipo |
| GET | `/api/datos/rango?desde=...&hasta=...` | Filtrar por rango de fechas |
| POST | `/api/datos` | Crear |
| PUT | `/api/datos/{id}` | Actualizar |
| DELETE | `/api/datos/{id}` | Eliminar |

## Patrones implementados
- **Repository Pattern**: `DatoOrganizacionalRepository`
- **Factory Method**: `DatoOrganizacionalFactory`

## Ejecución local
```bash
mvn clean install && mvn spring-boot:run
```
