package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesProductos;
import java.util.List;

public interface IReportesProductosService {
    
    public List<ReportesProductos> reporteProductos(String cliente, String usuario, String codigo, String departamento, Integer inicio, Integer fin);
    
    public List<ReportesProductos> reporteProductosCompras(String cliente, String usuario, String codigo, String departamento, Integer inicio, Integer fin);
    
}
