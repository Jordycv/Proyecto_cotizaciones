package com.codigo.msrespuestacot.dao;

import com.codigo.msrespuestacot.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosDAO extends JpaRepository<Productos,Long> {
}
