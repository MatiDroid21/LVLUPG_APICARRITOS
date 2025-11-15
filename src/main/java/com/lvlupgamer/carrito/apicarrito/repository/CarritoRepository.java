package com.lvlupgamer.carrito.apicarrito.repository;
import com.lvlupgamer.carrito.apicarrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByIdUsuario(Long idUsuario);

    void deleteByIdUsuario(Long idUsuario);
}
