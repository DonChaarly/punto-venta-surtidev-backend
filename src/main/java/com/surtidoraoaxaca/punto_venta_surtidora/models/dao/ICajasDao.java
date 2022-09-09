package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Cajas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICajasDao extends JpaRepository<Cajas, Long>{
    
}
