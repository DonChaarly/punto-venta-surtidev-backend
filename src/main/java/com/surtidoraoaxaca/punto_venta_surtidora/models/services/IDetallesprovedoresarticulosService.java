package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesprovedoresarticulos;
import java.util.List;

public interface IDetallesprovedoresarticulosService {
    
    public List<Detallesprovedoresarticulos> findAll();
    
    public Detallesprovedoresarticulos findById(Long id);
    
    public Detallesprovedoresarticulos save(Detallesprovedoresarticulos detallesprovedoresarticulo);
    
    public void delete(Long id);
    
}
