package com.surtidoraoaxaca.punto_venta_surtidora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Ventas;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesProductos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Clientes;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Cajas;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesventasarticulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesUsuarios;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.ICajasService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IClientesService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IDetallesventasarticulosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IReportesProductosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IReportesUsuariosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IVentasService;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = {"http://localhost:8089", "http://localhost:4200"})
@RestController
@RequestMapping("/ventas")
public class VentasController {
    
    @Autowired
    private IVentasService ventasService;
    @Autowired
    private IClientesService clientesService;
    @Autowired
    private ICajasService cajasService;
    @Autowired
    private IDetallesventasarticulosService detallesventasarticulosService;
    @Autowired
    private IReportesProductosService reportesProductosService;
    @Autowired
    private IReportesUsuariosService reportesUsuariosService;
   
    
    ///////////////////////////////////////VENTAS//////////////////////////////////////////////////////////////

    @GetMapping("/ventas/list")
    public ResponseEntity<?> listaVentas(){
        Map<String, Object> response = new HashMap<>();
        List<Ventas> ventas = null;
        try {
            ventas = ventasService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ventas == null){
            response.put("message", "No existen ventas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Ventas>>(ventas, HttpStatus.OK);
    }
    @GetMapping("/ventas/page/{page}")
    public ResponseEntity<?> pageVentas(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 15);
        Map<String, Object> response = new HashMap<>();
        Page<Ventas> ventas = null;
        try {
            ventas = ventasService.findAll(pageable);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ventas == null){
            response.put("message", "No existen ventas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Page<Ventas>>(ventas, HttpStatus.OK);
    }
    
    @GetMapping("/venta/{id}")
    public ResponseEntity<?> showVenta(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Ventas venta = null;
        
        try {
            venta = ventasService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(venta == null){
            response.put("message", "El venta ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ventas>(venta, HttpStatus.OK);
    }
    
    @GetMapping("venta/folio")
    public ResponseEntity<?> ultimoFolio(){
        
        Map<String, Object> response = new HashMap<>();
        String ultimoFolio = "0";
        try{
            ultimoFolio = ventasService.ultimoFolio();
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(ultimoFolio == null){
            response.put("message", "No hay ventas en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(ultimoFolio, HttpStatus.OK);
        
    }
    
    @PostMapping("/venta/create")
    public ResponseEntity<?> createVenta(@Valid @RequestBody Ventas venta, BindingResult result){
        System.out.println(venta.getFecha());
        Map<String, Object> response = new HashMap<>();
        Ventas ventaNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            ventaNuevo = ventasService.save(venta);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El venta ha sido creado con exito");
        response.put("venta", ventaNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/venta/{id}")
    public ResponseEntity<?> updateVenta(@Valid @RequestBody Ventas venta, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Ventas ventaActual = null;
        Ventas ventaUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            ventaActual = ventasService.findById(id);
            
            ventaActual.setFolio(venta.getFolio());
            ventaActual.setUsuario(venta.getUsuario());
            ventaActual.setCliente(venta.getCliente());
            ventaActual.setCaja(venta.getCaja());
            ventaActual.setFecha(venta.getFecha());
            ventaActual.setEnEspera(venta.getEnEspera());
            ventaActual.setTotal(venta.getTotal());


            ventaUpdated = ventasService.save(ventaActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el venta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(ventaActual == null){
            response.put("message", "El venta ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El venta ha sido actualizado con exito");
        response.put("venta", ventaUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/venta/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            ventasService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el venta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El venta ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @GetMapping("ventas/espera")
    public ResponseEntity<?> enEspera(){
        Map<String, Object> response = new HashMap<>();
        List<Ventas> ventas = null;
        try {
            ventas = ventasService.enEspera();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(ventas == null){
            response.put("message", "No existen ventas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Ventas>>(ventas, HttpStatus.OK);
    }
    
    @GetMapping("reporte/productos/{cliente}/{usuario}/{codigo}/{departamento}/{inicio}/{fin}")
    public ResponseEntity<?> reporteProductos(@PathVariable String cliente, @PathVariable String usuario, @PathVariable String codigo, @PathVariable String departamento, @PathVariable Integer inicio, @PathVariable Integer fin){
        if(cliente.equals("**")) {cliente = "";}
        if(usuario.equals("**")) {usuario = "";}
        if(codigo.equals("**")) {codigo = "";}
        if(departamento.equals("**")) {departamento = "";}
        Map<String, Object> response = new HashMap<>();
        List<ReportesProductos> reporte = null;
        try {
            reporte = reportesProductosService.reporteProductos(cliente, usuario, codigo, departamento, inicio, fin);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(reporte == null){
            response.put("message", "No se encontraron registros coincidentes");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<ReportesProductos>>(reporte, HttpStatus.OK);
    }
    @GetMapping("reporte/usuarios/{usuario}/{inicio}/{fin}")
    public ResponseEntity<?> reporteUsuarios(@PathVariable String usuario, @PathVariable Integer inicio, @PathVariable Integer fin ){
        if(usuario.equals("**")) {usuario = "";}
        Map<String, Object> response = new HashMap<>();
        List<ReportesUsuarios> reporte = null;
        try {
            reporte = reportesUsuariosService.reporteUsuarios(usuario, inicio, fin);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(reporte == null){
            response.put("message", "No se encontraron registros coincidentes");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<ReportesUsuarios>>(reporte, HttpStatus.OK);
    }
    
    @GetMapping("/list/fecha/{codigo}/{usuario}/{folio}/{cliente}/{total}/{inicio}/{fin}/{page}")
    public ResponseEntity<?> listaVentassByDate(@PathVariable String codigo,@PathVariable String usuario, @PathVariable String folio, @PathVariable String cliente, @PathVariable String total, @PathVariable Integer inicio, @PathVariable Integer fin, @PathVariable Integer page){
        if(codigo.equals("**")) codigo = "";
        if(usuario.equals("**")) usuario = "";
        if(folio.equals("**")) folio = "";
        if(cliente.equals("**")) cliente = "";
        if(total.equals("**")) total = "";
        Pageable pageable = PageRequest.of(page, 15);
        Map<String, Object> response = new HashMap<>();
        Page<Ventas> compras = null;
        try {
            compras = ventasService.ventasByDate(codigo, usuario, folio, cliente, total, inicio, fin, pageable);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(compras == null){
            response.put("message", "No existen ventas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Page<Ventas>>(compras, HttpStatus.OK);
    }
    
    
    ///////////////////////////////////////CLIENTES//////////////////////////////////////////////////////////////

    @GetMapping("/clientes/list")
    public ResponseEntity<?> listaClientes(){
        Map<String, Object> response = new HashMap<>();
        List<Clientes> clientes = null;
        try {
            clientes = clientesService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(clientes == null){
            response.put("message", "No existen clientes en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Clientes>>(clientes, HttpStatus.OK);
    }
    
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showCliente(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Clientes clientes = null;
        
        try {
            clientes = clientesService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(clientes == null){
            response.put("message", "El cliente ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Clientes>(clientes, HttpStatus.OK);
    }
    
    @PostMapping("/cliente/create")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Clientes cliente, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Clientes clienteNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            clienteNuevo = clientesService.save(cliente);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El cliente ha sido creado con exito");
        response.put("cliente", clienteNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody Clientes cliente, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Clientes clienteActual = null;
        Clientes clienteUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            clienteActual = clientesService.findById(id);
            
            clienteActual.setCodigo(cliente.getCodigo());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setTelefono(cliente.getTelefono());
            clienteActual.setDireccion(cliente.getDireccion());

            clienteUpdated = clientesService.save(clienteActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(clienteActual == null){
            response.put("message", "El cliente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El cliente ha sido actualizado con exito");
        response.put("cliente", clienteUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            clientesService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el cliente en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El cliente ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////CAJAS//////////////////////////////////////////////////////////////

    @GetMapping("/cajas/list")
    public ResponseEntity<?> listaCajas(){
        Map<String, Object> response = new HashMap<>();
        List<Cajas> cajas = null;
        try {
            cajas = cajasService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(cajas == null){
            response.put("message", "No existen cajas en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Cajas>>(cajas, HttpStatus.OK);
    }
    
    @GetMapping("/caja/{id}")
    public ResponseEntity<?> showCaja(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Cajas caja = null;
        
        try {
            caja = cajasService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(caja == null){
            response.put("message", "El caja ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cajas>(caja, HttpStatus.OK);
    }
    
    @PostMapping("/caja/create")
    public ResponseEntity<?> createCaja(@Valid @RequestBody Cajas caja, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Cajas cajaNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            cajaNuevo = cajasService.save(caja);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El caja ha sido creado con exito");
        response.put("caja", cajaNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/caja/{id}")
    public ResponseEntity<?> updateCaja(@Valid @RequestBody Cajas caja, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Cajas cajaActual = null;
        Cajas cajaUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            cajaActual = cajasService.findById(id);
            
            cajaActual.setNombre(caja.getNombre());


            cajaUpdated = cajasService.save(cajaActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el caja en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(cajaActual == null){
            response.put("message", "El caja ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El caja ha sido actualizado con exito");
        response.put("caja", cajaUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/caja/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            cajasService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el caja en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El caja ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////DETALLESVENTASARTICULOS//////////////////////////////////////////////////////////////

    @GetMapping("/ventasarticulos/list")
    public ResponseEntity<?> listaDetallesventasarticulos(){
        Map<String, Object> response = new HashMap<>();
        List<Detallesventasarticulos> detallesventasarticulos = null;
        try {
            detallesventasarticulos = detallesventasarticulosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(detallesventasarticulos == null){
            response.put("message", "No existen detallesventasarticulos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Detallesventasarticulos>>(detallesventasarticulos, HttpStatus.OK);
    }
    
    @GetMapping("/ventasarticulo/{id}")
    public ResponseEntity<?> showDetallesventasarticulo(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Detallesventasarticulos detallesventasarticulo = null;
        
        try {
            detallesventasarticulo = detallesventasarticulosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallesventasarticulo == null){
            response.put("message", "El detallesventasarticulo ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Detallesventasarticulos>(detallesventasarticulo, HttpStatus.OK);
    }
    
    @PostMapping("/ventasarticulo/create")
    public ResponseEntity<?> createDetallesventasarticulo(@Valid @RequestBody Detallesventasarticulos detallesventasarticulo, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Detallesventasarticulos detallesventasarticuloNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            detallesventasarticuloNuevo = detallesventasarticulosService.save(detallesventasarticulo);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El detallesventasarticulo ha sido creado con exito");
        response.put("detallesventasarticulo", detallesventasarticuloNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/ventasarticulo/{id}")
    public ResponseEntity<?> updateDetallesventasarticulo(@Valid @RequestBody Detallesventasarticulos detallesventasarticulo, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Detallesventasarticulos detallesventasarticuloActual = null;
        Detallesventasarticulos detallesventasarticuloUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            detallesventasarticuloActual = detallesventasarticulosService.findById(id);
            
            detallesventasarticuloActual.setArticulo(detallesventasarticulo.getArticulo());
            detallesventasarticuloActual.setVenta(detallesventasarticulo.getVenta());
            detallesventasarticuloActual.setCantidad(detallesventasarticulo.getCantidad());
            detallesventasarticuloActual.setPrecioUnitario(detallesventasarticulo.getPrecioUnitario());
            detallesventasarticuloActual.setImporte(detallesventasarticulo.getImporte());


            detallesventasarticuloUpdated = detallesventasarticulosService.save(detallesventasarticuloActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el detallesventasarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallesventasarticuloActual == null){
            response.put("message", "El detallesventasarticulo ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El detallesventasarticulo ha sido actualizado con exito");
        response.put("detallesventasarticulo", detallesventasarticuloUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/detallesventasarticulo/{id}")
    public ResponseEntity<?> deleteDetallesventasarticulo(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            detallesventasarticulosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el detallesventasarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El articulo ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
}