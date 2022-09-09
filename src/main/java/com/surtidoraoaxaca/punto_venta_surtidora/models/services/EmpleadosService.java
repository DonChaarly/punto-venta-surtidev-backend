package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IEmpleadosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empleados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadosService implements IEmpleadosService {
    
    @Autowired
    private IEmpleadosDao empleadosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Empleados> findAll() {
        return empleadosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleados findById(Long id) {
        return empleadosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Empleados save(Empleados empleado) {
        return empleadosDao.save(empleado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        empleadosDao.deleteById(id);
    }
    
}
