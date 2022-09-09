package com.surtidoraoaxaca.punto_venta_surtidora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Compras;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Provedores;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallescomprasarticulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesProductos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.ReportesUsuarios;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IComprasService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IDetallescomprasarticulosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IProvedoresService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IReportesProductosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IReportesUsuariosService;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = {"http://localhost:8089", "http://localhost:4200"})
@RestController
@RequestMapping("/compras")
public class ComprasController {
    
    @Autowired
    private IComprasService comprasService;
    @Autowired
    private IProvedoresService provedoresService;
    @Autowired
    private IDetallescomprasarticulosService detallescomprasarticulosService;
    @Autowired
    private IReportesProductosService reportesProductosService;
    @Autowired
    private IReportesUsuariosService reportesUsuariosService;
    
    ///////////////////////////////////////COMPRAS//////////////////////////////////////////////////////////////

    @GetMapping("/compras/list")
    public ResponseEntity<?> listaCompras(){
        Map<String, Object> response = new HashMap<>();
        List<Compras> compras = null;
        try {
            compras = comprasService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(compras == null){
            response.put("message", "No existen compras en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Compras>>(compras, HttpStatus.OK);
    }
    
    @GetMapping("/compras/page/{page}")
    public ResponseEntity<?> pageCompras(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 15);
        Map<String, Object> response = new HashMap<>();
        Page<Compras> compras = null;
        try {
            compras = comprasService.findAll(pageable);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(compras == null){
            response.put("message", "No existen compras en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Page<Compras>>(compras, HttpStatus.OK);
    }
    
    @GetMapping("/compra/{id}")
    public ResponseEntity<?> showCompra(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Compras compra = null;
        
        try {
            compra = comprasService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(compra == null){
            response.put("message", "El compra ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Compras>(compra, HttpStatus.OK);
    }
    
    @GetMapping("compra/folio")
    public ResponseEntity<?> ultimoFolio(){
        
        Map<String, Object> response = new HashMap<>();
        String ultimoFolio = "0";
        try{
            ultimoFolio = comprasService.ultimoFolio();
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(ultimoFolio == null){
            response.put("message", "No hay compras en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(ultimoFolio, HttpStatus.OK);
        
    }
    
    @PostMapping("/compra/create")
    public ResponseEntity<?> createCompra(@Valid @RequestBody Compras compra, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Compras compraNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            compraNuevo = comprasService.save(compra);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El compra ha sido creado con exito");
        response.put("compra", compraNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/compra/{id}")
    public ResponseEntity<?> updateCompra(@Valid @RequestBody Compras compra, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Compras compraActual = null;
        Compras compraUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            compraActual = comprasService.findById(id);
            
            compraActual.setFolio(compra.getFolio());
            compraActual.setProvedor(compra.getProvedor());
            compraActual.setUsuario(compra.getUsuario());
            compraActual.setCaja(compra.getCaja());
            compraActual.setFecha(compra.getFecha());
            compraActual.setEnEspera(compra.getEnEspera());
            compraActual.setTotal(compra.getTotal());


            compraUpdated = comprasService.save(compraActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el compra en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(compraActual == null){
            response.put("message", "El compra ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El compra ha sido actualizado con exito");
        response.put("compra", compraUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/compra/{id}")
    public ResponseEntity<?> deleteCompra(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            comprasService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el compra en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El compra ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @GetMapping("compras/espera")
    public ResponseEntity<?> enEspera(){
        Map<String, Object> response = new HashMap<>();
        List<Compras> compras = null;
        try {
            compras = comprasService.enEspera();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(compras == null){
            response.put("message", "No existen compras en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Compras>>(compras, HttpStatus.OK);
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
            reporte = reportesProductosService.reporteProductosCompras(cliente, usuario, codigo, departamento, inicio, fin);
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
            reporte = reportesUsuariosService.reporteUsuariosCompras(usuario, inicio, fin);
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
    
    @GetMapping("/list/fecha/{codigo}/{usuario}/{folio}/{provedor}/{total}/{inicio}/{fin}/{page}")
    public ResponseEntity<?> listaComprasByDate(@PathVariable String codigo, @PathVariable String usuario, @PathVariable String folio, @PathVariable String provedor, @PathVariable String total, @PathVariable Integer inicio, @PathVariable Integer fin, @PathVariable Integer page){
        if(codigo.equals("**")) codigo = "";
        if(usuario.equals("**")) usuario = "";
        if(folio.equals("**")) folio = "";
        if(provedor.equals("**")) provedor = "";
        if(total.equals("**")) total = "";
        Pageable pageable = PageRequest.of(page, 15);
        Map<String, Object> response = new HashMap<>();
        Page<Compras> compras = null;
        try {
            compras = comprasService.comprasByDate(codigo, usuario, folio, provedor, total, inicio, fin, pageable);
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(compras == null){
            response.put("message", "No existen compras en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Page<Compras>>(compras, HttpStatus.OK);
    }
    
    
    
    
    ///////////////////////////////////////PROVEDORES//////////////////////////////////////////////////////////////

    @GetMapping("/provedores/list")
    public ResponseEntity<?> listaProvedores(){
        Map<String, Object> response = new HashMap<>();
        List<Provedores> provedores = null;
        try {
            provedores = provedoresService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(provedores == null){
            response.put("message", "No existen provedores en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Provedores>>(provedores, HttpStatus.OK);
    }
    
    @GetMapping("/provedor/{id}")
    public ResponseEntity<?> showProvedor(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Provedores provedores = null;
        
        try {
            provedores = provedoresService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(provedores == null){
            response.put("message", "El provedor ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Provedores>(provedores, HttpStatus.OK);
    }
    
    @PostMapping(value = "/provedor/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createProvedor(@Valid @RequestBody Provedores provedor, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Provedores provedorNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            provedorNuevo = provedoresService.save(provedor);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El provedor ha sido creado con exito");
        response.put("provedor", provedorNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/provedor/{id}")
    public ResponseEntity<?> updateProvedor(@Valid @RequestBody Provedores provedor, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Provedores provedorActual = null;
        Provedores provedorUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            provedorActual = provedoresService.findById(id);
            
            provedorActual.setCodigo(provedor.getCodigo());
            provedorActual.setNombre(provedor.getNombre());
            provedorActual.setRfc(provedor.getRfc());
            provedorActual.setTelefono(provedor.getTelefono());
            provedorActual.setEmail(provedor.getEmail());
            provedorActual.setDireccion(provedor.getDireccion());

            provedorUpdated = provedoresService.save(provedorActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el provedor en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(provedorActual == null){
            response.put("message", "El provedor ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El provedor ha sido actualizado con exito");
        response.put("provedor", provedorUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/provedor/{id}")
    public ResponseEntity<?> deleteProvedor(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            provedoresService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el provedor en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El provedor ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////DETALLESCOMPRASARTICULOS//////////////////////////////////////////////////////////////

    @GetMapping("/comprasarticulos/list")
    public ResponseEntity<?> listaDetallescomprasarticulos(){
        Map<String, Object> response = new HashMap<>();
        List<Detallescomprasarticulos> detallescomprasarticulos = null;
        try {
            detallescomprasarticulos = detallescomprasarticulosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(detallescomprasarticulos == null){
            response.put("message", "No existen detallescomprasarticulos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Detallescomprasarticulos>>(detallescomprasarticulos, HttpStatus.OK);
    }
    
    @GetMapping("/comprasarticulo/{id}")
    public ResponseEntity<?> showDetallescomprasarticulo(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Detallescomprasarticulos detallescomprasarticulo = null;
        
        try {
            detallescomprasarticulo = detallescomprasarticulosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallescomprasarticulo == null){
            response.put("message", "El detallescomprasarticulo ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Detallescomprasarticulos>(detallescomprasarticulo, HttpStatus.OK);
    }
    
    @PostMapping("/comprasarticulo/create")
    public ResponseEntity<?> createDetallescomprasarticulo(@Valid @RequestBody Detallescomprasarticulos detallescomprasarticulo, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Detallescomprasarticulos detallescomprasarticuloNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            detallescomprasarticuloNuevo = detallescomprasarticulosService.save(detallescomprasarticulo);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El detallescomprasarticulo ha sido creado con exito");
        response.put("detallescomprasarticulo", detallescomprasarticuloNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/comprasarticulo/{id}")
    public ResponseEntity<?> updateDetallescomprasarticulo(@Valid @RequestBody Detallescomprasarticulos detallescomprasarticulo, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Detallescomprasarticulos detallescomprasarticuloActual = null;
        Detallescomprasarticulos detallescomprasarticuloUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            detallescomprasarticuloActual = detallescomprasarticulosService.findById(id);
            
            detallescomprasarticuloActual.setArticulo(detallescomprasarticulo.getArticulo());
            detallescomprasarticuloActual.setCompra(detallescomprasarticulo.getCompra());
            detallescomprasarticuloActual.setCantidad(detallescomprasarticulo.getCantidad());
            detallescomprasarticuloActual.setPrecioCompra(detallescomprasarticulo.getPrecioCompra());
            detallescomprasarticuloActual.setPrecio1Venta(detallescomprasarticulo.getPrecio1Venta());
            detallescomprasarticuloActual.setPrecio2Venta(detallescomprasarticulo.getPrecio2Venta());
            detallescomprasarticuloActual.setImporte(detallescomprasarticulo.getImporte());


            detallescomprasarticuloUpdated = detallescomprasarticulosService.save(detallescomprasarticuloActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el detallescomprasarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallescomprasarticuloActual == null){
            response.put("message", "El detallescomprasarticulo ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El detallescomprasarticulo ha sido actualizado con exito");
        response.put("detallescomprasarticulo", detallescomprasarticuloUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/detallescomprasarticulo/{id}")
    public ResponseEntity<?> deleteDetallescomprasarticulo(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            detallescomprasarticulosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el detallescomprasarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El articulo ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
}
