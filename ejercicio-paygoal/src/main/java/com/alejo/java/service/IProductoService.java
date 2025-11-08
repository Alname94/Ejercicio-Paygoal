package com.alejo.java.service;

import java.util.List;

import com.alejo.java.dto.ProductoRequestDTO;
import com.alejo.java.dto.ProductoResponseDTO;

// Interfaz del servicio de Producto que define las operaciones CRUD y query methods
public interface IProductoService {

    public List<ProductoResponseDTO> getProductos();

    public ProductoResponseDTO saveProducto(ProductoRequestDTO productoDTO);

    public ProductoResponseDTO getProductoById(Long id);

    public void deleteProducto(Long id);

    public ProductoResponseDTO editProducto(Long id, ProductoRequestDTO productoDTO);

    public List<ProductoResponseDTO> getProductosOrderByPriceAsc();    
}
