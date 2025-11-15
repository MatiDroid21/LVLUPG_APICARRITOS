API Carrito de Compras - LevelUpGamer
Microservicio backend para la gestión de carritos en una tienda online. Permite a cada usuario agregar y quitar productos de su carrito, así como vaciarlo y consultarlo en cualquier momento.

Características
Un carrito activo por usuario.

Permite agregar, eliminar y vaciar productos.

Responde con el estado actualizado del carrito (productos y cantidades).

Basado en Spring Boot 3 y Oracle.

Seguridad mediante API Key simple.

Estructura Principal
Carrito: Entidad que representa el carrito de un usuario.

CarritoItem: Entidad de productos dentro del carrito.

CarritoItemDTO: Objeto transferencia solo con IDs y cantidad (no incluye nombre ni detalles del producto).

Controlador REST: expone los endpoints CRUD.

Servicios y repositorios para lógica de negocio y persistencia.

Endpoints principales (ejemplos)
Método	Endpoint	Descripción
GET	/api/carritos/{idUsuario}	Obtener carrito por usuario
POST	/api/carritos/{idUsuario}/productos	Agregar producto al carrito
DELETE	/api/carritos/{idUsuario}/productos/{idProducto}	Eliminar producto del carrito
DELETE	/api/carritos/{idUsuario}	Vaciar todo el carrito
Todos los endpoints requieren el header:

text
X-API-KEY: lvlupgamer1306
Ejemplo de request para agregar producto
POST /api/carritos/1/productos

Headers:

Content-Type: application/json

X-API-KEY: lvlupgamer1306

Body:

json
{
  "idProducto": 123,
  "cantidad": 2
}
Ejemplo de respuesta
json
{
  "idCarrito": 1,
  "idUsuario": 1,
  "fechaCreacion": "2025-11-15T10:13:30.3489614",
  "items": [
    {
      "idItem": 10,
      "idProducto": 123,
      "cantidad": 2
    }
  ]
}
Importante: Sobre los detalles del producto
El carrito solo conoce el idProducto y cantidad.

El nombre, precio y detalles de producto deben obtenerse desde el microservicio o tabla de productos usando el idProducto.

La API de carrito no incluye detalle completo de los productos (Separación clara de responsabilidades).

Requisitos
Java 21

Maven 3.x

Spring Boot 3.5.x

OracleDB y configuración de credenciales en application.properties

Ejecución
Clonar el repo

Configura tu conexión a Oracle en src/main/resources/application.properties