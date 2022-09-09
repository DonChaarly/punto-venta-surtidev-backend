package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Ventas;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IVentasService {
    
    public List<Ventas> findAll();
    
    public Page<Ventas> findAll(Pageable pageable);
    
    public Ventas findById(Long id);
    
    public Ventas save(Ventas venta);
    
    public void delete(Long id);
    
    public String ultimoFolio();
    
    public List<Ventas> enEspera();
    
    public Page<Ventas> ventasByDate(String codigo, String folio, String cliente, String total, String usuario, Integer inicio, Integer fin, Pageable pageable);
    
}
