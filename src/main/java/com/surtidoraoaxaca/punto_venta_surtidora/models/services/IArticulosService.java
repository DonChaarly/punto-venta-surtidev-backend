package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Articulos;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


public interface IArticulosService {
    
    public List<Articulos> findAll();
    
    public Page<Articulos> findAll(Pageable pageable);
    
    public Articulos findById (Long id);
    
    public Articulos save (Articulos articulo);
    
    public void delete(Long id);
    
    public Page<Articulos> findLikeCodigo(String codigol, String codigou, Pageable pageable);
    public Page<Articulos> findLikeCodigoDepartamento(String codigol, String codigou, int idDepartamentos, Pageable pageable);
    public Page<Articulos> findLikeCodigoDepartamentoCategoria(String codigol, String codigou, int idDepartamentos, int Categorias, Pageable pageable);
    public Page<Articulos> findLikeNombre(String nombrel, String nombreu, Pageable pageable);
    public Page<Articulos> findLikeNombreDepartamento(String nombrel, String nombreu, int idDepartamentos, Pageable pageable);
    public Page<Articulos> findLikeNombreDepartamentoCategoria(String nombrel, String nombreu, int idDepartamentos, int idCategorias, Pageable pageable);
    
    public Page<Articulos> findArticulosByDepartamento(int idDepartamentos, Pageable pageable);
    public Page<Articulos> findArticulosByDepartamentoCategoria(int idDepartamentos, int idCategorias, Pageable pageable);
    
    public Articulos findArticuloByCodigo(String codigol, String codigou);
    
    
}
