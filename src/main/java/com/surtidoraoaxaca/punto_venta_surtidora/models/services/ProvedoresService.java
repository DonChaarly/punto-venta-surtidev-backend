package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IProvedoresDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Provedores;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProvedoresService implements IProvedoresService{
    
    @Autowired
    private IProvedoresDao provedoresDao;

    @Override
    @Transactional(readOnly = true)
    public List<Provedores> findAll() {
        return provedoresDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Provedores findById(Long id) {
        return provedoresDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Provedores save(Provedores provedor) {
        return provedoresDao.save(provedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        provedoresDao.deleteById(id);
    }


    
}
