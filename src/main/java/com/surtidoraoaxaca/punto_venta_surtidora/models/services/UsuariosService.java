package com.surtidoraoaxaca.punto_venta_surtidora.models.services;

import com.surtidoraoaxaca.punto_venta_surtidora.models.dao.IUsuariosDao;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Usuarios;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuariosService implements IUsuariosService, UserDetailsService{
    
    
    private Logger logger = LoggerFactory.getLogger(UsuariosService.class);
    @Autowired
    private IUsuariosDao usuariosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll() {
        return usuariosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios findById(Long id) {
        return usuariosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuarios save(Usuarios usuario) {
        return usuariosDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuariosDao.deleteById(id);
    }

   @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosDao.findByUsername(username); 


        if(usuario == null) {
                logger.error("Error en el loggin: no existe el usuario'"+username+"' en el sistema!");
                throw new UsernameNotFoundException("Error en el loggin: no existe el usuario'"+username+"' en el sistema!");
        }

        List<GrantedAuthority> authorities = Arrays.asList(usuario.getRol())
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNombre())).
                        peek(authority -> logger.info("Role: " + authority.getAuthority())).
                        collect(Collectors.toList());


        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios findByUsername(String username) {
        return usuariosDao.findByUsername(username);
    }




    


    
}
