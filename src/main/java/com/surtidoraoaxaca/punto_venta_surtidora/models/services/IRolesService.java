package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Roles;
import java.util.List;

public interface IRolesService {
    
    public List<Roles> findAll();
    
    public Roles findById(Long id);
    
    public Roles save(Roles rol);
    
    public void delete(Long id);
    
    public List<String> findAllRoles();
    
}
