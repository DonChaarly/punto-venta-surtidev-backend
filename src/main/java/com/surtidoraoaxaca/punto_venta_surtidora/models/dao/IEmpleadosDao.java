package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empleados;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmpleadosDao extends JpaRepository<Empleados, Long>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM Empleados ORDER BY nombre")
    public List<Empleados> findAll();
}
