package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IReportesProductosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesProductos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportesProductosService implements IReportesProductosService {
    
    @Autowired
    private IReportesProductosDao reportesProductosDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<ReportesProductos> reporteProductos(String cliente, String usuario, String codigo, String departamento, Integer inicio, Integer fin) {
        return reportesProductosDao.reporteProductos(cliente, usuario, codigo, departamento, inicio, fin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ReportesProductos> reporteProductosCompras(String cliente, String usuario, String codigo, String departamento, Integer inicio, Integer fin) {
        return reportesProductosDao.reporteProductosCompras(cliente, usuario, codigo, departamento, inicio, fin);
    }
    
}
