package com.lvlupgamer.carrito.apicarrito.controller;

import com.lvlupgamer.carrito.apicarrito.dto.CarritoItemDTO;
import com.lvlupgamer.carrito.apicarrito.model.Carrito;
import com.lvlupgamer.carrito.apicarrito.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Carrito de Compras", description = "API para gestión del carrito de compras")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping("/{idUsuario}")
    @Operation(
        summary = "Obtener carrito por usuario",
        description = "Retorna el carrito de compras completo de un usuario específico con todos sus items"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Carrito obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Carrito.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public Carrito obtenerCarrito(
        @Parameter(description = "ID del usuario", required = true, example = "1")
        @PathVariable Long idUsuario
    ) {
        return service.obtenerCarritoPorUsuario(idUsuario);
    }

    @PostMapping("/{idUsuario}/productos")
    @Operation(
        summary = "Agregar producto al carrito",
        description = "Agrega un producto al carrito del usuario. Si el producto ya existe, incrementa la cantidad"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto agregado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Carrito.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario o producto no encontrado"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public Carrito agregarProducto(
        @Parameter(description = "ID del usuario", required = true, example = "1")
        @PathVariable Long idUsuario,
        @Parameter(description = "Información del producto a agregar", required = true)
        @RequestBody CarritoItemDTO dto
    ) {
        return service.agregarProducto(idUsuario, dto);
    }

    @DeleteMapping("/{idUsuario}/productos/{idProducto}")
    @Operation(
        summary = "Eliminar producto del carrito",
        description = "Elimina completamente un producto específico del carrito del usuario"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario o producto no encontrado en el carrito"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Void> eliminarProducto(
        @Parameter(description = "ID del usuario", required = true, example = "1")
        @PathVariable Long idUsuario,
        @Parameter(description = "ID del producto a eliminar", required = true, example = "5")
        @PathVariable Long idProducto
    ) {
        service.eliminarProducto(idUsuario, idProducto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUsuario}")
    @Operation(
        summary = "Vaciar carrito",
        description = "Elimina todos los productos del carrito del usuario"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Carrito vaciado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Void> vaciarCarrito(
        @Parameter(description = "ID del usuario", required = true, example = "1")
        @PathVariable Long idUsuario
    ) {
        service.vaciarCarrito(idUsuario);
        return ResponseEntity.ok().build();
    }
}
