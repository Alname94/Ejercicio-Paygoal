package com.alejo.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Producto {
    //No especificaba tipo de id, se asume Long auto incremental para fines practicos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    //Setters con validaciones basicas para precio y cantidad
    public void setPrecio(double precio) {
        if(precio<0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        if(cantidad<0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }
}
