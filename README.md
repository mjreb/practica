# Users API — Spring Boot (Hexagonal-ready)

Este proyecto fue reorganizado hacia una **arquitectura por capas/puertos (hexagonal-ready)**:

```
com.ejercicio.demo
├── domain               # Reglas de negocio puras
│   ├── model            # Entidades del dominio (sin anotaciones de framework)
│   └── port             # Puertos/Interfaces hacia fuera (repos, gateways)
├── application          # Casos de uso (orquestan el dominio)
│   └── usecase
├── infrastructure       # Adaptadores y frameworks
│   ├── api              # Controladores REST + DTOs
│   ├── persistence      # Adaptadores JPA
│   ├── mapper           # Mapeo Entity ↔ Domain ↔ DTO
│   ├── exception        # Manejo global de errores
│   └── config           # Beans/Configuración
└── util                 # (Legado) JwtConfiguracion (se sugiere migrar a infrastructure/security)
```

## Endpoints
- `POST /login` → devuelve `TokenResponse { token }`
- `POST /consultarUsuarios`
- `POST /crearUsuario`
- `POST /editarUsuario?id={id}`
- `DELETE /eliminarUsuario/{id}`
- `GET /consultarUsuario/{id}`

> **Compatibilidad:** se mantienen los paths originales para no romper clientes existentes.

## Principios aplicados
- **Separación de capas y dependencias**: `api` → `application/usecases` → `domain/port` → `infrastructure` (adapters).
- **Dominio limpio**: clases del dominio sin anotaciones de framework.
- **DTOs/Validación**: `jakarta.validation` en `UserRequest`/`LoginRequestDto` + `@ControllerAdvice` para errores uniformes.
- **Mapper dedicado**: `UserMapper` concentra las transformaciones.
- **Beans explícitos**: `BeanConfig` crea adapters y casos de uso.

## Próximos pasos sugeridos
- Mover `JwtConfiguracion` a `infrastructure/security` y crear `JwtAuthFilter` + `SecurityConfig` con `@PreAuthorize`.
- Incorporar `PasswordEncoder` para almacenar *hashes* de contraseña.
- Añadir pruebas unitarias para `usecases` y de integración para adapters.
- Añadir `Dockerfile` y `docker-compose.yml` para DB y servicio.
- Añadir OpenAPI/Swagger.

## Desarrollo
```bash
# levantar app
./mvnw spring-boot:run
# ejecutar pruebas
./mvnw test
```

