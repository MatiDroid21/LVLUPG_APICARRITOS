package com.lvlupgamer.carrito.apicarrito.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "carrito_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carrito_items")
    @SequenceGenerator(name = "seq_carrito_items", sequenceName = "seq_carrito_items", allocationSize = 1)
    @Column(name = "id_item")
    private Long idItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Carrito carrito;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;
}
