package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Ventas;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVentasDao extends JpaRepository<Ventas, Long>{
    
    @Query(nativeQuery = true, value = "SELECT Ventas.folio from Ventas order by id_ventas DESC limit 1")
    public String ultimoFolio();
    
    @Query(nativeQuery = true, value = "SELECT * FROM Ventas WHERE en_espera = 1 ORDER BY folio DESC")
    public List<Ventas> ventasEnEspera();
    
    @Query(nativeQuery = true, value = "SELECT ventas.* from Ventas, detallesVentasarticulos, articulos, clientes \n" +
"                                       WHERE Ventas.id_ventas = detallesVentasarticulos.id_ventas\n" +
"                                           and detallesVentasarticulos.id_articulos = articulos.id_articulos\n" +
"                                           and Ventas.id_clientes = clientes.id_clientes\n" +
"                                           and Ventas.en_espera = 0\n" +            
"                                           and articulos.codigo like %:codigo\n" +
"                                           and Ventas.id_usuarios like %:usuario\n" +
"                                           and Ventas.folio like %:folio%\n" +
"                                           and clientes.nombre like %:cliente%\n" +
"                                           and Ventas.total like %:total%\n" +
"                                           AND (Ventas.fecha BETWEEN :inicio AND :fin)\n" +
"                                           GROUP BY folio\n" +
"                                           ORDER BY folio DESC")
    public Page<Ventas> ventasByDate(@Param("codigo") String codigo, 
                                       @Param("usuario") String usuario, 
                                       @Param("folio") String folio, 
                                       @Param("cliente") String cliente, 
                                       @Param("total") String total, 
                                       @Param("inicio") Integer inicio, 
                                       @Param("fin") Integer fin, Pageable pageable);
    
}
