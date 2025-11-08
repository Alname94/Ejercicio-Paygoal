package com.alejo.java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    // Validaciones básicas para el nombre y la descripción en el frontend antes de enviar al backend
    public void validarNombre() {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres");
        }
        
        this.nombre = nombre.trim();
    }

    public void validarDescripcion() {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (descripcion.trim().length() < 5) {
            throw new IllegalArgumentException("La descripción debe tener al menos 5 caracteres");
        }
        
        this.descripcion = descripcion.trim();
    }
}
