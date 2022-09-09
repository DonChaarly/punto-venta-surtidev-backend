package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empleados;
import java.util.List;

public interface IEmpleadosService {
    
    
    public List<Empleados> findAll();
    
    public Empleados findById(Long id);

    public Empleados save(Empleados empleado);
    
    public void delete(Long id);
}
