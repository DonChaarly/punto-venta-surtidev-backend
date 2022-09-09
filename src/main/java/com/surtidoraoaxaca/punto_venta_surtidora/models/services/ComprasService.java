package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Compras;
import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IComprasDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class ComprasService implements IComprasService{
    
    @Autowired
    private IComprasDao comprasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Compras> findAll() {
        return comprasDao.findAll();
    }
    
    @Override
    public Page<Compras> findAll(Pageable pageable) {
        return comprasDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Compras findById(Long id) {
        return comprasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Compras save(Compras compra) {
        return comprasDao.save(compra);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        comprasDao.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public String ultimoFolio(){
        return comprasDao.ultimoFolio();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compras> enEspera() {
        return comprasDao.comprasEnEspera();
    }

    @Override
    public Page<Compras> comprasByDate(String codigo, String usuario, String folio, String provedor, String total, Integer inicio, Integer fin, Pageable pageable) {
        return comprasDao.comprasByDate(codigo, usuario, folio, provedor, total, inicio, fin, pageable);
    }

    
    
}
