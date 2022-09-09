package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IReportesUsuariosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesUsuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportesUsuariosService implements IReportesUsuariosService {
    
    @Autowired
    private IReportesUsuariosDao reportesUsuariosDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<ReportesUsuarios> reporteUsuarios(String usuario, Integer inicio, Integer fin) {
        return reportesUsuariosDao.reporteUsuarios(usuario, inicio, fin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ReportesUsuarios> reporteUsuariosCompras(String usuario, Integer inicio, Integer fin) {
        return reportesUsuariosDao.reporteUsuariosCompras(usuario, inicio, fin);
    }
    
}
