package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IEmpresasDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empresas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresasService implements IEmpresasService {
    
    @Autowired
    private IEmpresasDao empresasDao;
    

    @Override
    public List<Empresas> findAll() {
        return empresasDao.findAll();
    }

    @Override
    public Empresas findById(Long id) {
        return empresasDao.findById(id).orElse(null);
    }

    @Override
    public Empresas save(Empresas empresa) {
        return empresasDao.save(empresa);
    }

    @Override
    public void delete(Long id) {
        empresasDao.deleteById(id);
    }
    
}
