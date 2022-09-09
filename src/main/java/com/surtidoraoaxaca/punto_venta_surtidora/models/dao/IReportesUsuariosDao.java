
package com.surtidoraoaxaca.punto_venta_surtidora.models.dao;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IReportesUsuariosDao extends JpaRepository<ReportesUsuarios, String> {
    
    
    @Query(nativeQuery = true, value = "SELECT usuarios.username as Usuario, SUM(ventas.total) as Venta \n" +
"                                           FROM usuarios, ventas \n" +
"                                           WHERE ventas.id_usuarios = usuarios.id_usuarios\n" +
"                                           AND ventas.en_espera = 0\n" +
"                                           AND usuarios.username like %:usuario\n" +
"                                           AND (ventas.fecha BETWEEN :inicio AND :fin)\n" +
"                                           GROUP BY username \n" +
"                                           ORDER BY username")
    public List<ReportesUsuarios> reporteUsuarios(@Param("usuario") String usuario,
                                                  @Param("inicio") Integer inicio,
                                                  @Param("fin") Integer fin);

    @Query(nativeQuery = true, value = "SELECT usuarios.username as Usuario, SUM(compras.total) as Venta \n" +
"                                           FROM usuarios, compras \n" +
"                                           WHERE compras.id_usuarios = usuarios.id_usuarios\n" +
"                                           AND compras.en_espera = 0\n" +            
"                                           AND usuarios.username like %:usuario\n" +
"                                           AND (compras.fecha BETWEEN :inicio AND :fin)\n" +
"                                           GROUP BY username \n" +
"                                           ORDER BY username")
    public List<ReportesUsuarios> reporteUsuariosCompras(@Param("usuario") String usuario,
                                                  @Param("inicio") Integer inicio,
                                                  @Param("fin") Integer fin);
    
}
