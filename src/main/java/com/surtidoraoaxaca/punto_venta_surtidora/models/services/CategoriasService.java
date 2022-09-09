package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Categorias;
import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.ICategoriasDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriasService implements ICategoriasService{
    
    
    @Autowired
    private ICategoriasDao categoriasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categorias> findAll() {
        return categoriasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categorias findById(Long id) {
        return categoriasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Categorias save(Categorias categoria) {
        return categoriasDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriasDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categorias> findAllByDepartamento(int idDepartamento) {
        return categoriasDao.findAllByDepartamento(idDepartamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Categorias findCategoriaByNombre(String nombre) {
        return categoriasDao.findCategoriaByNombre(nombre);
    }
    
    
    
}
