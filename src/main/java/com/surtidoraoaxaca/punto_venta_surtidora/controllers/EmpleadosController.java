package com.surtidoraoaxaca.punto_venta_surtidora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Usuarios;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empresas;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Roles;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Empleados;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IEmpleadosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IEmpresasService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IRolesService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IUsuariosService;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = {"http://localhost:8089", "http://localhost:4200"})
@RestController
@RequestMapping("/empleados")
public class EmpleadosController {
    
    @Autowired
    private IUsuariosService usuariosService;
    @Autowired
    private IRolesService rolesService;
    @Autowired
    private IEmpleadosService empleadosService;
    @Autowired
    private IEmpresasService empresasService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
   
    
    ///////////////////////////////////////USUARIOS//////////////////////////////////////////////////////////////

    @GetMapping("/usuarios/list")
    public ResponseEntity<?> listaUsuarios(){
        Map<String, Object> response = new HashMap<>();
        List<Usuarios> usuarios = null;
        try {
            usuarios = usuariosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuarios == null){
            response.put("message", "No existen usuarios en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Usuarios>>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> showUsuario(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Usuarios usuario = null;
        
        try {
            usuario = usuariosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(usuario == null){
            response.put("message", "El usuario ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
    }
    
    @GetMapping("/usuario/nombre/{username}")
    public ResponseEntity<?> showUsuarioByName(@PathVariable String username){
        Map<String, Object> response = new HashMap<>();
        Usuarios usuario = null;
        
        try {
            usuario = usuariosService.findByUsername(username);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(usuario == null){
            response.put("message", "El usuario no existe en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuarios>(usuario, HttpStatus.OK);
    }
    
    @PostMapping("/usuario/create")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuarios usuario, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Usuarios usuarioNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        usuario.setEnabled(true);
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
        try {
            usuarioNuevo = usuariosService.save(usuario);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El usuario ha sido creado con exito");
        response.put("usuario", usuarioNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/usuario/{id}/{modifPassword}")
    public ResponseEntity<?> updateUsuario(@Valid @RequestBody Usuarios usuario, BindingResult result, @PathVariable Long id, @PathVariable Integer modifPassword){
        Map<String, Object> response = new HashMap<>();
        Usuarios usuarioActual = null;
        Usuarios usuarioUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            usuarioActual = usuariosService.findById(id);
            
            usuarioActual.setUsername(usuario.getUsername());
            usuarioActual.setEmpleado(usuario.getEmpleado());
            usuarioActual.setRol(usuario.getRol());
            if(modifPassword == 1){
                usuarioActual.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
            }else{
                usuarioActual.setPassword(usuario.getPassword());
            }
            usuarioActual.setEnabled(usuario.getEnabled());


            usuarioUpdated = usuariosService.save(usuarioActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el usuario en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(usuarioActual == null){
            response.put("message", "El usuario ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El usuario ha sido actualizado con exito");
        response.put("usuario", usuarioUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            usuariosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el usuario en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El usuario ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////ROLES//////////////////////////////////////////////////////////////

    @GetMapping("/roles/list")
    public ResponseEntity<?> listaRoles(){
        Map<String, Object> response = new HashMap<>();
        List<Roles> roles = null;
        try {
            roles = rolesService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(roles == null){
            response.put("message", "No existen roles en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Roles>>(roles, HttpStatus.OK);
    }
    
    @GetMapping("/rol/{id}")
    public ResponseEntity<?> showRol(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Roles rol = null;
        
        try {
            rol = rolesService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(rol == null){
            response.put("message", "El rol ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Roles>(rol, HttpStatus.OK);
    }
    
    @PostMapping("/rol/create")
    public ResponseEntity<?> createRol(@Valid @RequestBody Roles rol, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Roles rolNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            rolNuevo = rolesService.save(rol);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El rol ha sido creado con exito");
        response.put("rol", rolNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/rol/{id}")
    public ResponseEntity<?> updateRol(@Valid @RequestBody Roles rol, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Roles rolActual = null;
        Roles rolUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            rolActual = rolesService.findById(id);
            
            rolActual.setNombre(rol.getNombre());
            rolActual.setSeccionCatalogo(rol.getSeccionCatalogo());
            rolActual.setAgregarArticulo(rol.getAgregarArticulo());
            rolActual.setEliminarArticulo(rol.getEliminarArticulo());
            rolActual.setEditarArticulo(rol.getEditarArticulo());
            rolActual.setExportarArticulos(rol.getExportarArticulos());
            rolActual.setImportarArticulos(rol.getImportarArticulos());
            rolActual.setSeccionConsultas(rol.getSeccionConsultas());
            rolActual.setCambiarFechaConsulta(rol.getCambiarFechaConsulta());
            rolActual.setCancelarVenta(rol.getCancelarVenta());
            rolActual.setCancelarCompra(rol.getCancelarCompra());
            rolActual.setSeccionVentas(rol.getSeccionVentas());
            rolActual.setRealizarVenta(rol.getRealizarVenta());
            rolActual.setCambiarPrecio(rol.getCambiarPrecio());
            rolActual.setPreguntarImprimir(rol.getPreguntarImprimir());
            rolActual.setSeccionReportes(rol.getSeccionReportes());
            rolActual.setCambiarFechaReporte(rol.getCambiarFechaReporte());
            rolActual.setSeccionCompras(rol.getSeccionCompras());
            rolActual.setSeccionConfiguraciones(rol.getSeccionConfiguraciones());
            rolActual.setSeccionOtros(rol.getSeccionOtros());


            rolUpdated = rolesService.save(rolActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el rol en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(rolActual == null){
            response.put("message", "El rol ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El rol ha sido actualizado con exito");
        response.put("rol", rolUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/rol/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            rolesService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el rol en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El rol ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    ///////////////////////////////////////EMPLEADOS//////////////////////////////////////////////////////////////

    @GetMapping("/empleados/list")
    public ResponseEntity<?> listaEmpleados(){
        Map<String, Object> response = new HashMap<>();
        List<Empleados> empleados = null;
        try {
            empleados = empleadosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empleados == null){
            response.put("message", "No existen empleados en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Empleados>>(empleados, HttpStatus.OK);
    }
    
    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> showEmpleado(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Empleados empleados = null;
        
        try {
            empleados = empleadosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(empleados == null){
            response.put("message", "El empleado ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empleados>(empleados, HttpStatus.OK);
    }
    
    @PostMapping("/empleado/create")
    public ResponseEntity<?> createEmpleado(@Valid @RequestBody Empleados empleado, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Empleados empleadoNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            empleadoNuevo = empleadosService.save(empleado);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El empleado ha sido creado con exito");
        response.put("empleado", empleadoNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> updateEmpleado(@Valid @RequestBody Empleados empleado, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Empleados empleadoActual = null;
        Empleados empleadoUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            empleadoActual = empleadosService.findById(id);
            
            empleadoActual.setCodigo(empleado.getCodigo());
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setDireccion(empleado.getDireccion());
            empleadoActual.setTelefono(empleado.getTelefono());
            empleadoActual.setPuesto(empleado.getPuesto());
            empleadoActual.setFechaIngreso(empleado.getFechaIngreso());

            empleadoUpdated = empleadosService.save(empleadoActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el empleado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(empleadoActual == null){
            response.put("message", "El empleado ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El empleado ha sido actualizado con exito");
        response.put("empleado", empleadoUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            empleadosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el empleado en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El empleado ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    
    ///////////////////////////////////////EMPRESAS//////////////////////////////////////////////////////////////

    @GetMapping("/empresas/list")
    public ResponseEntity<?> listaEmpresas(){
        Map<String, Object> response = new HashMap<>();
        List<Empresas> empresas = null;
        try {
            empresas = empresasService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empresas == null){
            response.put("message", "No existen empresas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Empresas>>(empresas, HttpStatus.OK);
    }
    
    @GetMapping("/empresa/{id}")
    public ResponseEntity<?> showEmpresa(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Empresas empresa = null;
        
        try {
            empresa = empresasService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(empresa == null){
            response.put("message", "El empresa ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empresas>(empresa, HttpStatus.OK);
    }
    
    @PostMapping("/empresa/create")
    public ResponseEntity<?> createEmpresa(@Valid @RequestBody Empresas empresa, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Empresas empresaNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            empresaNuevo = empresasService.save(empresa);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El empresa ha sido creado con exito");
        response.put("empresa", empresaNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/empresa/{id}")
    public ResponseEntity<?> updateEmpresa(@Valid @RequestBody Empresas empresa, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Empresas empresaActual = null;
        Empresas empresaUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            empresaActual = empresasService.findById(id);
            
            empresaActual.setNombre(empresa.getNombre());
            empresaActual.setDireccion(empresa.getDireccion());
            empresaActual.setCiudad(empresa.getCiudad());
            empresaActual.setEstado(empresa.getEstado());
            empresaActual.setCodigoPostal(empresa.getCodigoPostal());
            empresaActual.setTelefono(empresa.getTelefono());
            empresaActual.setEmail(empresa.getEmail());


            empresaUpdated = empresasService.save(empresaActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el empresa en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(empresaActual == null){
            response.put("message", "El empresa ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El empresa ha sido actualizado con exito");
        response.put("empresa", empresaUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            empresasService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el empresa en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El empresa ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
}
