package com.alejo.java.dto;

import java.util.List;

import com.alejo.java.model.Producto;

public class ProductoMapper {

    // Metodo para convertir de DTO a entidad
    public static Producto toEntity(ProductoRequestDTO requestDTO) {
        Producto producto = new Producto();
        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setCantidad(requestDTO.getCantidad());
        return producto;
    }

    // Metodo para convertir de entidad a DTO
    public static ProductoResponseDTO toResponseProducto(Producto producto) {
        return new ProductoResponseDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCantidad()
        );
    }

    // Metodo para actualizar una entidad existente con datos de un DTO
    public static void updateEntity(Producto producto, ProductoRequestDTO requestDTO) {
        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setCantidad(requestDTO.getCantidad());
    }

    // Metodo para convertir una lista de entidades a una lista de DTOs para que haya mayor separacion de capas
    public static List<ProductoResponseDTO> toResponseDTOList(List<Producto> productos) {
        return productos.stream()
                .map(ProductoMapper::toResponseProducto)
                .toList();
    }
}
