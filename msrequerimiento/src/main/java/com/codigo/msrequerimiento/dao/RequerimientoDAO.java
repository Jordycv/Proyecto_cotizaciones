package com.codigo.msrequerimiento.dao;

import com.codigo.msrequerimiento.entity.Requerimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequerimientoDAO extends JpaRepository<Requerimiento,Integer> {
}
