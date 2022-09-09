package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Provedores;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProvedoresDao extends JpaRepository<Provedores, Long>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM Provedores ORDER BY nombre")
    public List<Provedores> findAll();
    
}
