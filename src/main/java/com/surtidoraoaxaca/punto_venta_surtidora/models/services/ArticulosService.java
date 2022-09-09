package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IArticulosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Articulos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticulosService implements IArticulosService{

    @Autowired
    private IArticulosDao articulosDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Articulos> findAll() {
        return articulosDao.findAll();
        
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findAll(Pageable pageable) {
        return articulosDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulos findById(Long id) {
        return articulosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Articulos save(Articulos articulo) {
        return articulosDao.save(articulo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        articulosDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeCodigo(String codigol, String codigou, Pageable pageable) {
        return articulosDao.findLikeCodigo(codigol, codigou, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeCodigoDepartamento(String codigol, String codigou, int idDepartamentos, Pageable pageable) {
        return articulosDao.findLikeCodigoDepartamento(codigol, codigou, idDepartamentos, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeCodigoDepartamentoCategoria(String codigol, String codigou, int idDepartamentos, int Categorias, Pageable pageable) {
        return articulosDao.findLikeCodigoDepartamentoCategoria(codigol, codigou, idDepartamentos, Categorias, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeNombre(String nombrel, String nombreu, Pageable pageable) {
        return articulosDao.findLikeNombre(nombrel, nombreu, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeNombreDepartamento(String nombrel, String nombreu, int idDepartamentos, Pageable pageable) {
        return articulosDao.findLikeNombreDepartamento(nombrel, nombreu, idDepartamentos, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Articulos> findLikeNombreDepartamentoCategoria(String nombrel, String nombreu, int idDepartamentos, int idCategorias, Pageable pageable) {
        return articulosDao.findLikeCodigoDepartamentoCategoria(nombrel, nombreu, idDepartamentos, idCategorias, pageable);
    }

    @Override
    public Articulos findArticuloByCodigo(String codigol, String codigou) {
        return articulosDao.findArticuloByCodigo(codigol, codigou);
    }

    @Override
    public Page<Articulos> findArticulosByDepartamento(int idDepartamentos, Pageable pageable) {
        return articulosDao.findArticulosByDepartamento(idDepartamentos, pageable);
    }

    @Override
    public Page<Articulos> findArticulosByDepartamentoCategoria(int idDepartamentos, int idCategorias, Pageable pageable) {
        return articulosDao.findArticulosByDepartamentoCategoria(idDepartamentos, idCategorias, pageable);
    }

    
    
}
