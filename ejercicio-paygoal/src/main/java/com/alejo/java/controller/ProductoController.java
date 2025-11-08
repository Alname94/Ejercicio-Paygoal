package com.alejo.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alejo.java.dto.ProductoRequestDTO;
import com.alejo.java.dto.ProductoResponseDTO;
import com.alejo.java.service.IProductoService;

// Controlador REST para gestionar las operaciones CRUD de Producto
// Configurado para permitir solicitudes CORS desde cualquier origen y para los metodos GET, POST, PUT y DELETE
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    // Obtener todos los productos
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponseDTO>> getProductos() {
        try {
            List<ProductoResponseDTO> productos = productoService.getProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo producto
    @PostMapping("/productos/crear")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoRequestDTO productoRequestDTO) {
        try {
            ProductoResponseDTO nuevoProducto = productoService.saveProducto(productoRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    // Obtener un producto por su ID
    @GetMapping("/productos/obtener/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        try {
            ProductoResponseDTO producto = productoService.getProductoById(id);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar un producto por su ID
    @DeleteMapping("/productos/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
        }
    }

    // Editar un producto existente
    @PutMapping("/productos/editar/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO productoRequestDTO) {
        try {
            ProductoResponseDTO productoActualizado = productoService.editProducto(id, productoRequestDTO);
            return ResponseEntity.ok(productoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    // Obtener productos ordenados por precio ascendente
    @GetMapping("/productos/ordenar/precio-asc")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosOrdenadosPorPrecioAsc() {
        try {
            List<ProductoResponseDTO> productos = productoService.getProductosOrderByPriceAsc();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
