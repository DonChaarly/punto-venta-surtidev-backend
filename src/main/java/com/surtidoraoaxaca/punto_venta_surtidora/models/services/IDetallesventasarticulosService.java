package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesventasarticulos;
import java.util.List;

public interface IDetallesventasarticulosService {
    
    public List<Detallesventasarticulos> findAll();
    
    public Detallesventasarticulos findById(Long id);
    
    public Detallesventasarticulos save(Detallesventasarticulos detallesventasarticulos);
    
    public void delete(Long id);
    
}
