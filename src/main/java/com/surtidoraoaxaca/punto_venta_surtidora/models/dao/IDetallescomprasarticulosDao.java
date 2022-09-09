package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallescomprasarticulos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetallescomprasarticulosDao extends JpaRepository<Detallescomprasarticulos, Long> {
    
}
