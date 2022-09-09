package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empresas;
import java.util.List;

public interface IEmpresasService {
    
    public List<Empresas> findAll();
    
    public Empresas findById(Long id);
    
    public Empresas save(Empresas empresa);
    
    public void delete(Long id);
    
}
