package com.alejo.java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    // En este caso los atributos son los mismos que en la entidad Producto pero en un escenario real podrían ocultarse 
    // o modificarse algunos campos según las necesidades de la API
    private long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
}
