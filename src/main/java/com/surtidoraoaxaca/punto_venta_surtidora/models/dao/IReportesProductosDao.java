
package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesProductos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IReportesProductosDao extends JpaRepository<ReportesProductos, String> {
    
    
    @Query(nativeQuery = true, value = "SELECT ventas.fecha,\n" +
"		usuarios.username,\n" +
"               clientes.nombre as Cliente,\n" +
"		articulos.codigo, \n" +
"               articulos.nombre,\n" +
"               departamentos.nombre as departamento,\n" +
"               categorias.nombre as categoria,\n" +
"		SUM(detallesventasarticulos.cantidad) as cantidad, \n" +
"               detallesventasarticulos.precio_unitario as precio, \n" +
"               SUM(detallesventasarticulos.importe) as importe  \n" +
"           FROM detallesventasarticulos, articulos, ventas, categorias, departamentos, usuarios, clientes \n" +
"           WHERE detallesventasarticulos.id_articulos = articulos.id_articulos\n" +
"               AND ventas.id_usuarios = usuarios.id_usuarios\n" +
"               AND ventas.id_clientes = clientes.id_clientes\n" +
"               AND articulos.id_categorias = categorias.id_categorias\n" +
"               AND categorias.id_departamentos = departamentos.id_departamentos\n" +
"               AND detallesventasarticulos.id_ventas = ventas.id_ventas\n" +
"               AND ventas.en_espera = 0\n" +            
"               and clientes.nombre like %:cliente\n" +
"               and usuarios.username like %:usuario\n" +
"               and articulos.codigo like %:codigo\n" +
"               and departamentos.nombre LIKE %:departamento\n" +
"               and (ventas.fecha BETWEEN :inicio AND :fin)\n" +
"           GROUP BY nombre\n" +
"           ORDER BY codigo")
    public List<ReportesProductos> reporteProductos(@Param("cliente")String cliente, 
                                              @Param("usuario") String usuario, 
                                              @Param("codigo") String codigo, 
                                              @Param("departamento") String departamento, 
                                              @Param("inicio") Integer inicio, 
                                              @Param("fin") Integer fin);
    
        @Query(nativeQuery = true, value = "SELECT compras.fecha,\n" +
"		usuarios.username,\n" +
"               provedores.nombre as Cliente,\n" +
"		articulos.codigo, \n" +
"               articulos.nombre,\n" +
"               departamentos.nombre as departamento,\n" +
"               categorias.nombre as categoria,\n" +
"		SUM(detallescomprasarticulos.cantidad) as cantidad, \n" +
"               SUM(detallescomprasarticulos.importe)/SUM(detallescomprasarticulos.cantidad) as precio, \n" +
"               SUM(detallescomprasarticulos.importe) as importe  \n" +
"           FROM detallescomprasarticulos, articulos, compras, categorias, departamentos, usuarios, provedores \n" +
"           WHERE detallescomprasarticulos.id_articulos = articulos.id_articulos\n" +
"               AND compras.id_usuarios = usuarios.id_usuarios\n" +
"               AND compras.id_provedores = provedores.id_provedores\n" +
"               AND articulos.id_categorias = categorias.id_categorias\n" +
"               AND categorias.id_departamentos = departamentos.id_departamentos\n" +
"               AND detallescomprasarticulos.id_compras = compras.id_compras\n" +
"               AND Compras.en_espera = 0\n" +                
"               and provedores.nombre like %:cliente\n" +
"               and usuarios.username like %:usuario\n" +
"               and articulos.codigo like %:codigo\n" +
"               and departamentos.nombre LIKE %:departamento\n" +
"               and (compras.fecha BETWEEN :inicio AND :fin)\n" +
"           GROUP BY nombre\n" +
"           ORDER BY codigo")
    public List<ReportesProductos> reporteProductosCompras(@Param("cliente")String cliente, 
                                              @Param("usuario") String usuario, 
                                              @Param("codigo") String codigo, 
                                              @Param("departamento") String departamento, 
                                              @Param("inicio") Integer inicio, 
                                              @Param("fin") Integer fin);
    
}
