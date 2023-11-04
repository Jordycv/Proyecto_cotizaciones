package com.codigo.msordencompra.dao;

import com.codigo.msordencompra.entity.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenCompraDAO extends JpaRepository<OrdenCompra,Long> {
}
