package com.codigo.msproductos.dao;

import com.codigo.msproductos.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosDAO extends JpaRepository<Productos,Integer> {
}
