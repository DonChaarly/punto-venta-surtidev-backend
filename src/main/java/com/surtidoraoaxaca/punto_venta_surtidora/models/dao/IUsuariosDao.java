package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuariosDao extends JpaRepository<Usuarios, Long>{
    
    public Usuarios findByUsername(String username);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Usuarios ORDER BY username")
    public List<Usuarios> findAll();
    
}
