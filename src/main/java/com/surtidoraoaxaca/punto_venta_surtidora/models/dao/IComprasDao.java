package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Compras;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IComprasDao extends JpaRepository<Compras, Long>{
    
    @Query(nativeQuery = true, value = "SELECT Compras.folio from Compras ORDER BY id_compras DESC LIMIT 1")
    public String ultimoFolio();
    
    @Query(nativeQuery = true, value = "SELECT * FROM Compras WHERE en_espera = 1 ORDER BY folio DESC")
    public List<Compras> comprasEnEspera();
    
    @Query(nativeQuery = true, value = "SELECT Compras.* from Compras, detallescomprasarticulos, articulos, provedores \n" +
"                                       WHERE compras.id_compras = detallescomprasarticulos.id_compras\n" +
"                                           and detallescomprasarticulos.id_articulos = articulos.id_articulos\n" +
"                                           and compras.id_provedores = provedores.id_provedores\n" +
"                                           and compras.en_espera = 0\n" +            
"                                           and articulos.codigo like %:codigo\n" +
"                                           and compras.id_usuarios like %:usuario\n" +
"                                           and compras.folio like %:folio%\n" +
"                                           and provedores.nombre like %:provedor%\n" +
"                                           and compras.total like %:total%\n" +
"                                           AND (compras.fecha BETWEEN :inicio AND :fin)\n" +
"                                           GROUP BY folio\n" +
"                                           ORDER BY folio DESC")
    public Page<Compras> comprasByDate(@Param("codigo") String codigo, 
                                       @Param("usuario") String usuario, 
                                       @Param("folio") String folio, 
                                       @Param("provedor") String provedor, 
                                       @Param("total") String total, 
                                       @Param("inicio") Integer inicio, 
                                       @Param("fin") Integer fin, Pageable pageable);
    
}
