package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Promociones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromocionesDao extends JpaRepository<Promociones, Long>{
    
}
