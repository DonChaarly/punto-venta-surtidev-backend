package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallescomprasarticulos;
import java.util.List;

public interface IDetallescomprasarticulosService {
    
    public List<Detallescomprasarticulos> findAll();
    
    public Detallescomprasarticulos findById(Long id);
    
    public Detallescomprasarticulos save(Detallescomprasarticulos detallescomprasarticulo);
    
    public void delete(Long id);
    
}
