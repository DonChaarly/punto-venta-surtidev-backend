package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import java.util.List;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Categorias;

public interface ICategoriasService {

    public List<Categorias> findAll();
    
    public Categorias findById(Long id);
    
    public Categorias save(Categorias categoria);
    
    public void delete(Long id);
    
    public List<Categorias> findAllByDepartamento(int idDepartamento);
    
    public Categorias findCategoriaByNombre(String nombre);
    
}
