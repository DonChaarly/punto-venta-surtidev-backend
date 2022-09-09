package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallescomprasarticulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IDetallescomprasarticulosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetallescomprasarticulosService implements IDetallescomprasarticulosService{
    
    @Autowired
    private IDetallescomprasarticulosDao detallescomprasarticulosDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<Detallescomprasarticulos> findAll() {
        return detallescomprasarticulosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Detallescomprasarticulos findById(Long id) {
        return detallescomprasarticulosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Detallescomprasarticulos save(Detallescomprasarticulos detallescomprasarticulo) {
        return detallescomprasarticulosDao.save(detallescomprasarticulo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        detallescomprasarticulosDao.deleteById(id);
    }
    
}
