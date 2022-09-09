package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Articulos;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IArticulosDao extends JpaRepository<Articulos, Long>{
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos\n" +
                                        "    AND (Articulos.codigo LIKE %:codigol% OR Articulos.codigo LIKE %:codigou%) ORDER BY LENGTH(Articulos.codigo), Articulos.codigo")
    public Page<Articulos> findLikeCodigoDepartamento(@Param("codigol")String codigol, @Param("codigou") String codigou, @Param("idDepartamentos") int idDepartamentos, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Categorias.id_categorias = :idCategorias \n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos\n" +
                                        "    AND (Articulos.codigo LIKE %:codigol% OR Articulos.codigo LIKE %:codigou%) ORDER BY LENGTH(Articulos.codigo), Articulos.codigo")
    public Page<Articulos> findLikeCodigoDepartamentoCategoria(@Param("codigol")String codigol, @Param("codigou") String codigou, @Param("idDepartamentos") int idDepartamentos, @Param("idCategorias") int idCategorias,  Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Articulos WHERE (Articulos.codigo LIKE %:codigol% OR Articulos.codigo LIKE %:codigou%) ORDER BY LENGTH(Articulos.codigo), Articulos.codigo")
    public Page<Articulos> findLikeCodigo(@Param("codigol")String codigol, @Param("codigou") String codigou, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos\n" +
                                        "    AND (Articulos.nombre LIKE %:nombrel% OR Articulos.nombre LIKE %:nombreu%) ORDER BY LENGTH(Articulos.nombre), Articulos.nombre")
    public Page<Articulos> findLikeNombreDepartamento(@Param("nombrel")String nombrel, @Param("nombreu")String nombreu, @Param("idDepartamentos") int idDepartamentos, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Categorias.id_categorias = :idCategorias \n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos\n" +
                                        "    AND (Articulos.nombre LIKE %:nombrel% OR Articulos.nombre LIKE %:nombreu%) ORDER BY LENGTH(Articulos.nombre), Articulos.nombre")
    public Page<Articulos> findLikeNombreDepartamentoCategoria(@Param("nombrel")String nombrel, @Param("nombreu")String nombreu, @Param("idDepartamentos") int idDepartamentos, @Param("idCategorias") int idCategorias, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT * FROM Articulos WHERE Match(Articulos.nombre) AGAINST(:nombrel) OR Articulos.nombre LIKE %:nombrel% OR Articulos.nombre LIKE %:nombreu% ORDER BY LENGTH(Articulos.nombre), Articulos.nombre")
    public Page<Articulos> findLikeNombre(@Param("nombrel")String nombrel, @Param("nombreu") String nombreu, Pageable pageable);
    
    
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos")
    public Page<Articulos> findArticulosByDepartamento(@Param("idDepartamentos") int idDepartamentos, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT Articulos.* FROM Articulos, Categorias, Departamentos \n" +
                                        "    WHERE Articulos.id_categorias = Categorias.id_categorias\n" +
                                        "    AND Categorias.id_departamentos = Departamentos.id_departamentos\n" +
                                        "    AND Categorias.id_categorias = :idCategorias \n" +
                                        "    AND Departamentos.id_departamentos = :idDepartamentos")
    public Page<Articulos> findArticulosByDepartamentoCategoria(@Param("idDepartamentos") int idDepartamentos, @Param("idCategorias") int idCategorias,  Pageable pageable);
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM Articulos WHERE codigo = :codigol OR codigo = :codigou")
    public Articulos findArticuloByCodigo(@Param("codigol") String codigol, @Param("codigou") String codigou);
    
    
    
    
}
