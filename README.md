# BikeApp

¡Bienvenido a BikeApp!

## Descripción del Proyecto

BikeApp es una aplicación desarrollada en Java 17 y Spring Boot 3.X. 

## Tecnologías Utilizadas

### Java 17

Java es un lenguaje de programación ampliamente utilizado en el desarrollo de aplicaciones empresariales debido a su portabilidad, seguridad y robustez. La versión 17 ofrece características modernas que mejoran la productividad del desarrollador y la eficiencia del código, lo que lo hace ideal para el desarrollo de BikeApp.

### Spring Boot 3.X

Spring Boot es un framework de Java que simplifica el desarrollo de aplicaciones, proporcionando un conjunto de herramientas y convenciones para desarrollar aplicaciones de manera rápida y eficiente. Se eligió Spring Boot debido a su facilidad de configuración, su soporte para la creación de aplicaciones web y su amplia comunidad de desarrolladores.

### PostgreSQL

PostgreSQL es un sistema de gestión de bases de datos relacional de código abierto conocido por su confiabilidad, robustez y capacidad para manejar grandes volúmenes de datos. Se eligió PostgreSQL como base de datos para BikeApp debido a su cumplimiento con el teorema CAP, que prioriza la consistencia y disponibilidad de los datos, características fundamentales para una aplicación de gestión de bicicletas compartidas.

MongoDB es otra opción popular para bases de datos, especialmente en entornos donde se manejan grandes volúmenes de datos no estructurados o semiestructurados. Aunque no se eligió para este proyecto, MongoDB podría haber sido una buena alternativa gracias a su flexibilidad y escalabilidad, especialmente si se necesita almacenar datos con una estructura variable o si se prevé un crecimiento rápido en la cantidad de datos almacenados.

### Redis Cache

Redis es un sistema de almacenamiento en caché de datos en memoria de código abierto que se utiliza para mejorar el rendimiento y la escalabilidad de las aplicaciones. Se utilizó Redis como sistema de caché distribuida en BikeApp para almacenar datos frecuentemente accedidos en memoria, reduciendo así la carga en la base de datos principal y mejorando los tiempos de respuesta de la aplicación.

## Estrategías de desarrollo
### Desarrollo API First

BikeApp sigue una metodología de desarrollo API First, lo que significa que el diseño de la API RESTful se define y documenta antes de comenzar la implementación. Este enfoque permite una mejor comprensión de los requisitos del sistema y promueve la coherencia y la interoperabilidad entre los diferentes servicios y clientes de la aplicación.

#### Ventajas del Desarrollo API First:

- **Claridad en los Requisitos:** Al definir primero la interfaz de la API, se clarifican los requisitos funcionales del sistema y se establece un contrato claro entre el servidor y los clientes.

- **Mejora de la Colaboración:** El diseño de la API proporciona una base común para que los equipos de desarrollo, diseño y pruebas colaboren de manera efectiva en la construcción de la aplicación.

- **Facilita la Iteración y la Evolución:** Al tener una API bien diseñada y documentada, es más fácil realizar cambios y mejoras en la aplicación sin afectar a los clientes existentes.

- **Fomenta la Reutilización de Código:** Una API bien diseñada promueve la reutilización de componentes y servicios en diferentes partes de la aplicación y en otros proyectos.

### Documentación de la API

La documentación de la API de BikeApp está disponible a través de Swagger UI, que proporciona una interfaz interactiva para explorar y probar los endpoints de la API. Puedes acceder a la documentación de la API en tu navegador web visitando la siguiente URL:

http://localhost:8080/swagger-ui/index.html

Desde la interfaz de Swagger UI, puedes explorar los diferentes endpoints de la API, ver los parámetros de entrada y salida, y probar las solicitudes directamente desde tu navegador.


### Bearer auth con JWT
Para un desarrollo rápido pero robusto se ha utilizado un sistema de autenticación - autorización con JWT.

Para probar la aplicación, se incluyen 3 usuarios de prueba en el sistema. Los detalles de inicio de sesión de estos usuarios son los siguientes:

| Usuario | Contraseña |
|---------|------------|
| user    | user       |
| user1   | user1      |
| user2   | user2      |


# Arquitectura hexagonal
Esta aplicación se basa en una arquitectura hexagonal para mejorar la legibilidad, reusabilidad y escalabilidad del código. La aplicación se encuentra modularizada y las distintas capas de la arquitectura hexagonal se ven representadas en los
modulos application, domain y los distintos modulos de infraestructura.

![image](https://github.com/CarlosMartinD/BikeApp/assets/107757538/d22fbcf9-747a-4166-8c35-acafbc8e2c0f)

