package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesventasarticulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IDetallesventasarticulosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetallesventasarticulosService implements IDetallesventasarticulosService {
    
    @Autowired
    private IDetallesventasarticulosDao detallesventasarticulosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Detallesventasarticulos> findAll() {
        return detallesventasarticulosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Detallesventasarticulos findById(Long id) {
        return detallesventasarticulosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Detallesventasarticulos save(Detallesventasarticulos detallesventasarticulo) {
        return detallesventasarticulosDao.save(detallesventasarticulo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        detallesventasarticulosDao.deleteById(id);
    }
    
}
