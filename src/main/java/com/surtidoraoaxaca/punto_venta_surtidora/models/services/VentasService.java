package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IVentasDao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Ventas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class VentasService implements IVentasService{
    
    @Autowired
    private IVentasDao ventasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> findAll() {
        return ventasDao.findAll();
    }
    
    @Override
    public Page<Ventas> findAll(Pageable pageable) {
        return ventasDao.findAll(pageable);
    }
    

    @Override
    @Transactional(readOnly = true)
    public Ventas findById(Long id) {
        return ventasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Ventas save(Ventas venta) {
        return ventasDao.save(venta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ventasDao.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public String ultimoFolio(){
        return ventasDao.ultimoFolio();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> enEspera() {
        return ventasDao.ventasEnEspera();
    }

    @Override
    public Page<Ventas> ventasByDate(String codigo, String usuario, String folio, String cliente, String total, Integer inicio, Integer fin, Pageable pageable) {
        return ventasDao.ventasByDate(codigo, usuario, folio, cliente, total, inicio, fin, pageable);
    }

    






    


    
}
