package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Categorias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoriasDao extends JpaRepository<Categorias, Long>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM Categorias WHERE id_departamentos = ?1")
    public List<Categorias> findAllByDepartamento(int idDepartamento);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Categorias WHERE nombre = ?1")
    public Categorias findCategoriaByNombre(String nombre);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Categorias ORDER BY nombre")
    public List<Categorias> findAll();
    
}
