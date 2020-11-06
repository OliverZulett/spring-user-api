# First Task

Para la primera tarea de la capacitación de backend relacionada con los servicios web, me gustaría que siguieras la siguiente tarea:

Cree una aplicación Spring usando el inicializador con las siguientes especificaciones:

Package: com.milankas.training
Artifact: product-api
Type: Maven Project
Java Version: 8
Packaging: Jar
Dependencies:
- Spring Web
- Lombok1
- MapStruct

En esta aplicación, debe conservar el objeto Producto con la siguiente estructura:

user:

- id: uuid
- firstName: string
- lastName: string
- email: string (this has to be unique)
- password: Password[] (don’t return this in the DTO)


password:

- hash: string
- salt: string
- status: int|enum

En esta aplicación, debe crear los siguientes puntos finales:

- GET /v1/users/ Obtiene todos los productos
- GET /v1/users/{userId} Obtiene el producto para un userId específico
- POST /v1/users/ Inserta un producto en la colección de productos
- PATCH /v1/users/{userId} Actualiza parcialmente un producto específico
- DELETE /v1/users/{userId} Elimina el producto con ese ID específico


Toda la información del producto se almacenará en una base de datos de Postgres 12 utilizando la API de persistencia de Java.

Para crear su entorno de desarrollo local, utilice Docker para las dependencias externas como postgres y pgadmin.

Para entregar esta tarea, entregue la aplicación que realizó en un contenedor de Docker (utilice Dockerfile)

También haga uso de SCM después de GitFlow y etiquete la primera versión de su código como la versión 0.1.0 que usaremos gitlab para realizar esta tarea.

Como opcional, intente avanzar en las pruebas unitarias y de integración


Fecha de entrega y revisión: 06/10/2020

Hora: 8:30 am (Avísame con anticipación si tendrás problemas para completar esta tarea a tiempo o si terminas antes)

Fecha límite de desarrollo de relaciones públicas: 05/10/2020

Fecha límite de PR: 6:00 pm
