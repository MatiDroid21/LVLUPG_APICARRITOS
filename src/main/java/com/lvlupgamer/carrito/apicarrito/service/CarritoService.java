package com.lvlupgamer.carrito.apicarrito.service;

import com.lvlupgamer.carrito.apicarrito.dto.CarritoItemDTO;
import com.lvlupgamer.carrito.apicarrito.model.Carrito;
import com.lvlupgamer.carrito.apicarrito.model.CarritoItem;
import com.lvlupgamer.carrito.apicarrito.repository.CarritoItemRepository;
import com.lvlupgamer.carrito.apicarrito.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepo;
    private final CarritoItemRepository itemRepo;

    public CarritoService(CarritoRepository carritoRepo, CarritoItemRepository itemRepo) {
        this.carritoRepo = carritoRepo;
        this.itemRepo = itemRepo;
    }

    @Transactional
    public Carrito obtenerCarritoPorUsuario(Long idUsuario) {
        return carritoRepo.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    Carrito carrito = new Carrito();
                    carrito.setIdUsuario(idUsuario);
                    carrito.setFechaCreacion(LocalDateTime.now());
                    carrito.setItems(new ArrayList<>());
                    return carritoRepo.save(carrito);
                });
    }

    @Transactional
    public Carrito agregarProducto(Long idUsuario, CarritoItemDTO dto) {
        Carrito carrito = obtenerCarritoPorUsuario(idUsuario);

        if (carrito.getItems() == null) {
            carrito.setItems(new ArrayList<>());
        }

        Optional<CarritoItem> itemOpt = carrito.getItems().stream()
            .filter(i -> i.getIdProducto().equals(dto.getIdProducto()))
            .findFirst();

        if (itemOpt.isPresent()) {
            itemOpt.get().setCantidad(itemOpt.get().getCantidad() + dto.getCantidad());
        } else {
            CarritoItem item = new CarritoItem();
            item.setCarrito(carrito);
            item.setIdProducto(dto.getIdProducto());
            item.setCantidad(dto.getCantidad());
            carrito.getItems().add(item);
        }
        return carritoRepo.save(carrito);
    }

    @Transactional
    public void eliminarProducto(Long idUsuario, Long idProducto) {
        Carrito carrito = obtenerCarritoPorUsuario(idUsuario);
        carrito.getItems().removeIf(i -> i.getIdProducto().equals(idProducto));
        carritoRepo.save(carrito);
    }

    @Transactional
    public void vaciarCarrito(Long idUsuario) {
        Carrito carrito = obtenerCarritoPorUsuario(idUsuario);
        carrito.getItems().clear();
        carritoRepo.save(carrito);
    }
}
