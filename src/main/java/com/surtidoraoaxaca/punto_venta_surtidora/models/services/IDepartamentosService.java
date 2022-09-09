package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Departamentos;

import java.util.List;

public interface IDepartamentosService {
    
    public List<Departamentos> findAll();
    
    public Departamentos findById(Long id);
    
    public Departamentos save(Departamentos departamentos);
    
    public void delete(Long id);
    
    public Departamentos findDepartamentoByName(String nombre);
    
}
