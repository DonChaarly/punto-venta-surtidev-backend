package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEmpresasDao extends JpaRepository<Empresas, Long>{
    
}
