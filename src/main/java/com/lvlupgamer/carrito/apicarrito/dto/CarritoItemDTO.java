package com.lvlupgamer.carrito.apicarrito.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemDTO {
    private Long idProducto;
    private int cantidad;
}
