package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesventasarticulos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetallesventasarticulosDao extends JpaRepository<Detallesventasarticulos, Long>{
    
}
