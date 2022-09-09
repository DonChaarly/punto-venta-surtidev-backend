package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IDepartamentosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Departamentos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartamentosService implements IDepartamentosService {
    
    @Autowired
    private IDepartamentosDao departamentosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Departamentos> findAll() {
        return departamentosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Departamentos findById(Long id) {
        return departamentosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Departamentos save(Departamentos departamentos) {
        return departamentosDao.save(departamentos);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departamentosDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamentos findDepartamentoByName(String nombre) {
        return departamentosDao.findDepartamentoByName(nombre);
    }
    
}
