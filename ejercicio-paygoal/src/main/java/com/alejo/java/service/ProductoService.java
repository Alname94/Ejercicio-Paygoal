package com.alejo.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejo.java.dto.ProductoMapper;
import com.alejo.java.dto.ProductoRequestDTO;
import com.alejo.java.dto.ProductoResponseDTO;
import com.alejo.java.model.Producto;
import com.alejo.java.repository.IProductoRepository;

// Implementacion de la interfaz del servicio de Producto para definir los metodos que se van a utilizar, podría omitirse si se prefiere usar solo la interfaz
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    //Obtenemos todos los productos desde la base de datos y los convertimos a DTOs antes de retornarlos al controlador para mayor separacion de capas
    @Override
    public List<ProductoResponseDTO> getProductos() {
        List<Producto> productos = productoRepository.findAll();
        return ProductoMapper.toResponseDTOList(productos);
    }

    // Guardamos un nuevo producto en la base de datos despues de validar los datos recibidos en el DTO y convertirlo a entidad. 
    // Retornamos el DTO del producto guardado
    @Override
    public ProductoResponseDTO saveProducto(ProductoRequestDTO productoDTO) {
        productoDTO.validarNombre();
        productoDTO.validarDescripcion();
        Producto producto = ProductoMapper.toEntity(productoDTO);
        productoRepository.save(producto);
        return ProductoMapper.toResponseProducto(producto);
    }

    // Obtener un producto por su ID, si no se encuentra lanzamos una excepcion
    @Override
    public ProductoResponseDTO getProductoById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()) {
            return ProductoMapper.toResponseProducto(producto.get());
        }
        throw new IllegalArgumentException("Producto no encontrado con id: " + id);
    }

    // Eliminar un producto por su ID
    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Editar un producto existente, validando los datos del RequestDTO y actualizando la entidad correspondiente en la base de datos. 
    // Luego retornamos el ResponseDTO actualizado
    @Override
    public ProductoResponseDTO editProducto(Long id, ProductoRequestDTO productoDTO) {
        productoDTO.validarNombre();
        productoDTO.validarDescripcion();
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if(!productoExistente.isPresent()){
            throw new IllegalArgumentException("Producto no encontrado con id: " + id);
        }
        Producto producto = productoExistente.get();
        ProductoMapper.updateEntity(producto, productoDTO);
        productoRepository.save(producto);
        return ProductoMapper.toResponseProducto(producto);
    }

    // Obtener todos los productos ordenados por precio ascendente y convertirlos a DTOs antes de retornarlos
    @Override
    public List<ProductoResponseDTO> getProductosOrderByPriceAsc() {
        List<Producto> productos = productoRepository.findAllByOrderByPrecioAsc();
        return ProductoMapper.toResponseDTOList(productos);
    }
}
