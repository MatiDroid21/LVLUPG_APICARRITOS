package com.lvlupgamer.carrito.apicarrito.repository;
import com.lvlupgamer.carrito.apicarrito.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByCarrito_IdCarrito(Long idCarrito);
    void deleteByCarrito_IdCarrito(Long idCarrito);
    void deleteByCarrito_IdCarritoAndIdProducto(Long idCarrito, Long idProducto);
}
