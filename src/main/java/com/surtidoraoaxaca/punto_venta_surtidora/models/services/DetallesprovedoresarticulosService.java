package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IDetallesprovedoresarticulosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesprovedoresarticulos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetallesprovedoresarticulosService implements IDetallesprovedoresarticulosService {
    
    @Autowired
    private IDetallesprovedoresarticulosDao detallesprovedoresarticulosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Detallesprovedoresarticulos> findAll() {
        return detallesprovedoresarticulosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Detallesprovedoresarticulos findById(Long id) {
        return detallesprovedoresarticulosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Detallesprovedoresarticulos save(Detallesprovedoresarticulos detallesprovedoresarticulo) {
        return detallesprovedoresarticulosDao.save(detallesprovedoresarticulo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        detallesprovedoresarticulosDao.deleteById(id);
    }
    
}
