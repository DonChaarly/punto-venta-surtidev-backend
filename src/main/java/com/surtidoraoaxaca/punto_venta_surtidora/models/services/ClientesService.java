package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IClientesDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Clientes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientesService implements IClientesService{
    
    @Autowired
    private IClientesDao clientesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Clientes> findAll() {
        return clientesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Clientes findById(Long id) {
        return clientesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Clientes save(Clientes cliente) {
        return clientesDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientesDao.deleteById(id);
    }
    
}
