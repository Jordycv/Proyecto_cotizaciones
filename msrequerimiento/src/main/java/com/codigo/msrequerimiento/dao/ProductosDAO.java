package com.codigo.msrequerimiento.dao;

import com.codigo.msrequerimiento.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosDAO extends JpaRepository<Productos,Integer> {
}
