package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Departamentos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDepartamentosDao extends JpaRepository<Departamentos, Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM Departamentos WHERE nombre = ?1")
    public Departamentos findDepartamentoByName(String nombre);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Departamentos ORDER BY nombre")
    public List<Departamentos> findAll();
    
}
