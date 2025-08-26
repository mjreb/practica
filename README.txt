Este proyecto implementa una API REST en Spring Boot que gestiona usuarios mediante operaciones CRUD (crear, leer, actualizar, eliminar). La aplicación sigue una arquitectura en capas y utiliza JPA/Hibernate para la persistencia de datos.

Tecnologías utilizadas
  Java 17
  Spring Boot (Web, Data JPA)
  Maven como gestor de dependencias  
  MySQL como base de datos (dependiendo de la configuración)
  JUnit 5 y Mockito para pruebas unitarias 
  JWT para autenticación y autorización básica
  Postman para pruebas manuales de la API

Estructura del proyecto
  El código se organizó en tres capas principales
    Capa de Persistencia (capapersistencia): contiene el repositorio (UsuarioRepository) que interactúa con la base de datos.
    Capa de Negocio (capanegocio): contiene la lógica de negocio en el servicio (UsuarioService). Aquí se implementan las validaciones y transformaciones necesarias, así como las llamadas al repositorio.
    Capa de Controlador (controlador): expone los endpoints REST que permiten realizar las peticiones HTTP.

  Adicionalmente, se creó la carpeta "util" donde se encuentra la clase que gestiona la generación, validación y extracción de información del token con jwt, así como el sql de la base de datos.

Funcionalidades principales

  Crear usuario (createUser)
  Consultar todos los usuarios (consultarUsuarios)
  Consultar usuario por ID (consultarUsuarioPorId)
  Actualizar usuario (updateUser)
  Eliminar usuario (deleteUser)
  Validar contraseña (validarContrasena)

Pruebas unitarias

  Se implementaron pruebas unitarias a nivel de servicio para todos los métodos, usando JUnit y Mockito.

Pruebas con Postman

  Además de las pruebas unitarias, se realizaron pruebas manuales en Postman para verificar el correcto funcionamiento de los endpoints:

    Envío de peticiones POST para crear usuarios, actualizar información, consultar usuarios y acceder al login.
    Peticiones DELETE para eliminar usuarios.
    Prueba de endpoints protegidos usando el token JWT en los encabezados (Authorization: Bearer <token>).
