package com.codigo.msproductos.dao;

import com.codigo.msproductos.entity.Productos;
import com.codigo.msproductos.entity.Requerimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerimientoDAO extends JpaRepository<Requerimiento,Integer> {
}
