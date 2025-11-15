package com.lvlupgamer.carrito.apicarrito.controller;

import com.lvlupgamer.carrito.apicarrito.dto.CarritoItemDTO;
import com.lvlupgamer.carrito.apicarrito.model.Carrito;
import com.lvlupgamer.carrito.apicarrito.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping("/{idUsuario}")
    public Carrito obtenerCarrito(@PathVariable Long idUsuario) {
        return service.obtenerCarritoPorUsuario(idUsuario);
    }

    @PostMapping("/{idUsuario}/productos")
    public Carrito agregarProducto(@PathVariable Long idUsuario, @RequestBody CarritoItemDTO dto) {
        return service.agregarProducto(idUsuario, dto);
    }

    @DeleteMapping("/{idUsuario}/productos/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idUsuario, @PathVariable Long idProducto) {
        service.eliminarProducto(idUsuario, idProducto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long idUsuario) {
        service.vaciarCarrito(idUsuario);
        return ResponseEntity.ok().build();
    }
}
