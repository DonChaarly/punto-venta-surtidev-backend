package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Promociones;
import java.util.List;

public interface IPromocionesService {
    
    public List<Promociones> findAll();
    
    public Promociones findById(Long id);
    
    public Promociones save(Promociones promocion);
    
    public void delete(Long id);
    
}
