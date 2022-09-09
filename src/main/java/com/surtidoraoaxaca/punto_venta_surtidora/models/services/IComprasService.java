package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Compras;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IComprasService {

    public List<Compras> findAll();
    
    public Page<Compras> findAll(Pageable pageable);
    
    public Compras findById(Long id);
    
    public Compras save(Compras compra);
    
    public void delete(Long id);
    
    public String ultimoFolio();
    
    public List<Compras> enEspera();
    
    public Page<Compras> comprasByDate(String codigo, String folio, String provedor, String total, String usuario, Integer inicio, Integer fin, Pageable pageable);
    
}
