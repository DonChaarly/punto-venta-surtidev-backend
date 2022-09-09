package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesUsuarios;
import java.util.List;

public interface IReportesUsuariosService {
    
    public List<ReportesUsuarios> reporteUsuarios(String usuario, Integer inicio, Integer fin);
    
    public List<ReportesUsuarios> reporteUsuariosCompras(String usuario, Integer inicio, Integer fin);
    
}
