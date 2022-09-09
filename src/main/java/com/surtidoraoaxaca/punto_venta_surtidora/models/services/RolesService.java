package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IRolesDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Roles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesService implements IRolesService{
    
    @Autowired
    private IRolesDao rolesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Roles> findAll() {
        return rolesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findById(Long id) {
        return rolesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Roles save(Roles rol) {
        return rolesDao.save(rol);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolesDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllRoles() {
        return rolesDao.findAllRoles();
    }
    
    


    


    
}
