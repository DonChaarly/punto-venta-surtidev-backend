package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IPromocionesDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Promociones;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromocionesService implements IPromocionesService{
    
    @Autowired
    private IPromocionesDao promocionesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Promociones> findAll() {
        return promocionesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Promociones findById(Long id) {
        return promocionesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Promociones save(Promociones promocion) {
        return promocionesDao.save(promocion);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        promocionesDao.deleteById(id);
    }
    
}
