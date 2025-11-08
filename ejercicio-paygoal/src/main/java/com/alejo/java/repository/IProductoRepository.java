package com.alejo.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alejo.java.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    
    //Query method para obtener productos ordenados por precio ascendente, podría ser por precio descendente si se cambia Asc por Desc
    List<Producto> findAllByOrderByPrecioAsc();
}
