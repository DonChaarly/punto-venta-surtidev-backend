package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Provedores;
import java.util.List;

public interface IProvedoresService {
    
    public List<Provedores> findAll();
    
    public Provedores findById(Long id);
    
    public Provedores save(Provedores provedor);
    
    public void delete(Long id);
    
}
