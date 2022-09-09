package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.ICajasDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Cajas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CajasService implements ICajasService {
    
    @Autowired
    private ICajasDao cajasDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<Cajas> findAll() {
        return cajasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cajas findById(Long id) {
        return cajasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cajas save(Cajas caja) {
        return cajasDao.save(caja);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cajasDao.deleteById(id);
    }
    
}
