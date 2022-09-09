package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Roles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRolesDao extends JpaRepository<Roles, Long>{
    
    @Query("SELECT nombre FROM Roles ")
    public List<String> findAllRoles();
    
    @Query(nativeQuery = true, value = "SELECT * FROM Roles ORDER BY nombre")
    public List<Roles> findAll();
    
    
}
