package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Clientes;
import java.util.List;

public interface IClientesService {
    
    public List<Clientes> findAll();
    
    public Clientes findById(Long id);
    
    public Clientes save(Clientes cliente);
    
    public void delete(Long id);
    
}
