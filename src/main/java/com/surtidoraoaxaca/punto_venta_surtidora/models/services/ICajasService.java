package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Cajas;
import java.util.List;

public interface ICajasService {
    
    public List<Cajas> findAll();
    
    public Cajas findById(Long id);
    
    public Cajas save(Cajas caja);
    
    public void delete(Long id);
}
