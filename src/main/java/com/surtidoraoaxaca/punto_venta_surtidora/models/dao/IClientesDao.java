package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Clientes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IClientesDao extends JpaRepository<Clientes, Long>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM Clientes ORDER BY nombre")
    public List<Clientes> findAll();
    
}
