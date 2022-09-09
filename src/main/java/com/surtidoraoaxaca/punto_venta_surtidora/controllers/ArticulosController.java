package com.surtidoraoaxaca.punto_venta_surtidora.controllers;

import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IArticulosService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Articulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Departamentos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Categorias;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Detallesprovedoresarticulos;
import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Promociones;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.ICategoriasService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IDepartamentosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IDetallesprovedoresarticulosService;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IPromocionesService;
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
@RequestMapping("/articulos")
public class ArticulosController {
    
    @Autowired
    private IArticulosService articulosService;
    @Autowired
    private IDepartamentosService departamentosService;
    @Autowired
    private ICategoriasService categoriasService;
    @Autowired
    private IDetallesprovedoresarticulosService detallesprovedoresarticulosService;
    @Autowired
    private IPromocionesService promocionesService;
   
    
    ///////////////////////////////////////ARTICULOS//////////////////////////////////////////////////////////////

    @GetMapping("/articulos/list")
    public ResponseEntity<?> listaArticulos(){
        Map<String, Object> response = new HashMap<>();
        List<Articulos> articulos = null;
        try {
            articulos = articulosService.findAll();
        } catch (Exception e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Articulos>>(articulos, HttpStatus.OK);
    }
    @GetMapping("/articulos/page/{page}")
    public ResponseEntity<?> pageArticulos(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        try {
            articulos = articulosService.findAll(pageable);
        } catch (Exception e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    @GetMapping("/articulos/like-codigo/{codigo}/{page}")
    public ResponseEntity<?> listaArticulosLikeCodigo(@PathVariable String codigo, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(codigo.equals("**")){
                articulos = articulosService.findAll(pageable);
            }else{
                articulos = articulosService.findLikeCodigo(codigo.toLowerCase(),codigo.toUpperCase(), pageable);
            }
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    @GetMapping("/articulos/like-codigo-departamento/{codigo}/{idDepartamentos}/{page}")
    public ResponseEntity<?> listaArticulosLikeCodigoDepartamento(@PathVariable String codigo, @PathVariable int idDepartamentos, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(codigo.equals("**")){
                articulos = articulosService.findArticulosByDepartamento(idDepartamentos, pageable);
            }else{
                articulos = articulosService.findLikeCodigoDepartamento(codigo.toLowerCase(),codigo.toUpperCase(), idDepartamentos, pageable);

            }
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    @GetMapping("/articulos/like-codigo-departamento-categoria/{codigo}/{idDepartamentos}/{idCategorias}/{page}")
    public ResponseEntity<?> listaArticulosLikeCodigoDepartamentoCategoria(@PathVariable String codigo, @PathVariable int idDepartamentos, @PathVariable int idCategorias, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(codigo.equals("**")){
                articulos = articulosService.findArticulosByDepartamentoCategoria(idDepartamentos, idCategorias, pageable);
            }else{
                articulos = articulosService.findLikeCodigoDepartamentoCategoria(codigo.toLowerCase(),codigo.toUpperCase(), idDepartamentos, idCategorias, pageable);
            }
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    
    @GetMapping("/articulos/like-nombre/{nombre}/{page}")
    public ResponseEntity<?> listaArticulosLikeNombre(@PathVariable String nombre, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(nombre.equals("**")){
                articulos = articulosService.findAll(pageable);

            }else{
                articulos = articulosService.findLikeNombre(nombre.toLowerCase(), nombre.toUpperCase(), pageable);
            }
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    
    @GetMapping("/articulos/like-nombre-departamento/{nombre}/{idDepartamentos}/{page}")
    public ResponseEntity<?> listaArticulosLikeNombreDepartamento(@PathVariable String nombre, @PathVariable int idDepartamentos, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(nombre.equals("**")){
                articulos = articulosService.findArticulosByDepartamento(idDepartamentos, pageable);
            }else{
                articulos = articulosService.findLikeNombreDepartamento(nombre.toLowerCase(), nombre.toUpperCase(), idDepartamentos, pageable);
            }
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    @GetMapping("/articulos/like-nombre-departamento-categoria/{nombre}/{idDepartamentos}/{idCategorias}/{page}")
    public ResponseEntity<?> listaArticulosLikeNombreDepartamentoCategoria(@PathVariable String nombre, @PathVariable int idDepartamentos, @PathVariable int idCategorias, @PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 25);
        Map<String, Object> response = new HashMap<>();
        Page<Articulos> articulos = null;
        
        try{
            if(nombre.equals("**")){
                articulos = articulosService.findArticulosByDepartamentoCategoria(idDepartamentos, idCategorias, pageable);
            }else{
                articulos = articulosService.findLikeNombreDepartamentoCategoria(nombre.toLowerCase(), nombre.toUpperCase(), idDepartamentos, idCategorias, pageable);
            }
            
        }catch(Exception e){
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(articulos == null){
            response.put("message", "No existen articulos parecidos a la cadena");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<Articulos>>(articulos, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/articulo/{id}")
    public ResponseEntity<?> showArticulo(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Articulos articulo = null;
        
        try {
            articulo = articulosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(articulo == null){
            response.put("message", "El articulo ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);
    }
    @GetMapping("/articulo/codigo/{codigo}")
    public ResponseEntity<?> showArticuloByCodigo(@PathVariable String codigo){
        
        Map<String, Object> response = new HashMap<>();
        Articulos articulo = null;
        
        try {
            articulo = articulosService.findArticuloByCodigo(codigo.toLowerCase(), codigo.toUpperCase());
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(articulo == null){
            response.put("message", "El articulo :".concat(codigo.concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Articulos>(articulo, HttpStatus.OK);
    }
    
    @PostMapping("/articulo/create")
    public ResponseEntity<?> createArticulo(@Valid @RequestBody Articulos articulo, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Articulos articuloNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            articuloNuevo = articulosService.save(articulo);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El articulo ha sido creado con exito");
        response.put("articulo", articuloNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/articulo/{id}")
    public ResponseEntity<?> updateArticulo(@Valid @RequestBody Articulos articulo, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Articulos articuloActual = null;
        Articulos articuloUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            articuloActual = articulosService.findById(id);
            
            articuloActual.setCodigo(articulo.getCodigo());
            articuloActual.setNombre(articulo.getNombre());
            articuloActual.setInventarioMax(articulo.getInventarioMax());
            articuloActual.setInventarioMin(articulo.getInventarioMin());
            articuloActual.setPrecio1(articulo.getPrecio1());
            articuloActual.setPrecio2(articulo.getPrecio2());
            articuloActual.setExistencias(articulo.getExistencias());
            articuloActual.setUltimoPrecioCompra(articulo.getUltimoPrecioCompra());
            articuloActual.setVecesComprado(articulo.getVecesComprado());
            articuloActual.setModificacionPrecio(articulo.getModificacionPrecio());
            articuloActual.setCategoria(articulo.getCategoria());


            articuloUpdated = articulosService.save(articuloActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el articulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(articuloActual == null){
            response.put("message", "El articulo ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El articulo ha sido actualizado con exito");
        response.put("articulo", articuloUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/articulo/{id}")
    public ResponseEntity<?> deleteArticulo(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            articulosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el articulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El articulo ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    ///////////////////////////////////////DEPARTAMENTOS//////////////////////////////////////////////////////////////

    @GetMapping("/departamentos/list")
    public ResponseEntity<?> listaDepartamentos(){
        Map<String, Object> response = new HashMap<>();
        List<Departamentos> departamentos = null;
        try {
            departamentos = departamentosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(departamentos == null){
            response.put("message", "No existen departamentos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Departamentos>>(departamentos, HttpStatus.OK);
    }
    
    @GetMapping("/departamento/{id}")
    public ResponseEntity<?> showDepartamento(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Departamentos departamentos = null;
        
        try {
            departamentos = departamentosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(departamentos == null){
            response.put("message", "El departamento ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Departamentos>(departamentos, HttpStatus.OK);
    }
    
    @GetMapping("/departamento/nombre/{nombre}")
    public ResponseEntity<?> showDepartamentoByName(@PathVariable String nombre){
        
        Map<String, Object> response = new HashMap<>();
        Departamentos departamento = null;
        
        try {
            departamento = departamentosService.findDepartamentoByName(nombre);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(departamento == null){
            response.put("message", "El departamento: " + nombre + " no existe en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Departamentos>(departamento, HttpStatus.OK);
    }
    
    @PostMapping("/departamento/create")
    public ResponseEntity<?> createDepartamento(@Valid @RequestBody Departamentos departamento, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Departamentos departamentoNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            departamentoNuevo = departamentosService.save(departamento);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El departamento ha sido creado con exito");
        response.put("departamento", departamentoNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/departamento/{id}")
    public ResponseEntity<?> updateDepartamento(@Valid @RequestBody Departamentos departamento, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Departamentos departamentoActual = null;
        Departamentos departamentoUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            departamentoActual = departamentosService.findById(id);
            
            departamentoActual.setNombre(departamento.getNombre());

            departamentoUpdated = departamentosService.save(departamentoActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el departamento en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(departamentoActual == null){
            response.put("message", "El departamento ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El departamento ha sido actualizado con exito");
        response.put("departamento", departamentoUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/departamento/{id}")
    public ResponseEntity<?> deleteDepartamento(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            departamentosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el departamento en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El departamento ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////CATEGORIAS//////////////////////////////////////////////////////////////

    @GetMapping("/categorias/list")
    public ResponseEntity<?> listaCategorias(){
        Map<String, Object> response = new HashMap<>();
        List<Categorias> categorias = null;
        try {
            categorias = categoriasService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(categorias == null){
            response.put("message", "No existen categorias en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Categorias>>(categorias, HttpStatus.OK);
    }
    
    @GetMapping("/categorias/departamento/{idDepartamento}")
    public ResponseEntity<?> listarCategoriasByDepartamento(@PathVariable int idDepartamento){
        Map<String, Object> response = new HashMap<>();
        List<Categorias> categorias = null;
        
        try {
            categorias = categoriasService.findAllByDepartamento(idDepartamento);
        } catch (Exception e) {
            response.put("message", "Error al realiar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(categorias == null){
            response.put("message", "No existen categorias en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Categorias>>(categorias, HttpStatus.OK);
    }
    
    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> showCategoria(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Categorias categoria = null;
        
        try {
            categoria = categoriasService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(categoria == null){
            response.put("message", "El categoria ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Categorias>(categoria, HttpStatus.OK);
    }
    
    @GetMapping("/categoria/nombre/{nombre}")
    public ResponseEntity<?> showCategoriaByName(@PathVariable String nombre){
        Map<String, Object> response = new HashMap<>();
        Categorias categoria = null;
        
        try {
            categoria = categoriasService.findCategoriaByNombre(nombre);
        } catch (Exception e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(categoria == null){
            response.put("message", "La categoria: " + nombre + " no existe en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Categorias>(categoria, HttpStatus.OK);
    }
    
    @PostMapping("/categoria/create")
    public ResponseEntity<?> createCategoria(@Valid @RequestBody Categorias categoria, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Categorias categoriaNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            categoriaNuevo = categoriasService.save(categoria);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El categoria ha sido creado con exito");
        response.put("categoria", categoriaNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@Valid @RequestBody Categorias categoria, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Categorias categoriaActual = null;
        Categorias categoriaUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            categoriaActual = categoriasService.findById(id);
            
            categoriaActual.setNombre(categoria.getNombre());
            categoriaActual.setDepartamento(categoria.getDepartamento());


            categoriaUpdated = categoriasService.save(categoriaActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el categoria en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(categoriaActual == null){
            response.put("message", "El categoria ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El categoria ha sido actualizado con exito");
        response.put("categoria", categoriaUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            categoriasService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el categoria en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El categoria ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////DETALLESPROVEDORESARTICULOS//////////////////////////////////////////////////////////////

    @GetMapping("/provedoresarticulos/list")
    public ResponseEntity<?> listaDetallesprovedoresarticulos(){
        Map<String, Object> response = new HashMap<>();
        List<Detallesprovedoresarticulos> detallesprovedoresarticulos = null;
        try {
            detallesprovedoresarticulos = detallesprovedoresarticulosService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(detallesprovedoresarticulos == null){
            response.put("message", "No existen detallesprovedoresarticulos en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Detallesprovedoresarticulos>>(detallesprovedoresarticulos, HttpStatus.OK);
    }
    
    @GetMapping("/provedoresarticulo/{id}")
    public ResponseEntity<?> showDetallesprovedoresarticulo(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Detallesprovedoresarticulos detallesprovedoresarticulo = null;
        
        try {
            detallesprovedoresarticulo = detallesprovedoresarticulosService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallesprovedoresarticulo == null){
            response.put("message", "El detallesprovedoresarticulo ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Detallesprovedoresarticulos>(detallesprovedoresarticulo, HttpStatus.OK);
    }
    
    @PostMapping("/provedoresarticulo/create")
    public ResponseEntity<?> createDetallesprovedoresarticulo(@Valid @RequestBody Detallesprovedoresarticulos detallesprovedoresarticulo, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Detallesprovedoresarticulos detallesprovedoresarticuloNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            detallesprovedoresarticuloNuevo = detallesprovedoresarticulosService.save(detallesprovedoresarticulo);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El detallesprovedoresarticulo ha sido creado con exito");
        response.put("detallesprovedoresarticulo", detallesprovedoresarticuloNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/provedoresarticulo/{id}")
    public ResponseEntity<?> updateDetallesprovedoresarticulo(@Valid @RequestBody Detallesprovedoresarticulos detallesprovedoresarticulo, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Detallesprovedoresarticulos detallesprovedoresarticuloActual = null;
        Detallesprovedoresarticulos detallesprovedoresarticuloUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            detallesprovedoresarticuloActual = detallesprovedoresarticulosService.findById(id);
            
            detallesprovedoresarticuloActual.setArticulo(detallesprovedoresarticulo.getArticulo());
            detallesprovedoresarticuloActual.setProvedor(detallesprovedoresarticulo.getProvedor());
            detallesprovedoresarticuloActual.setUltPrecio(detallesprovedoresarticulo.getUltPrecio());


            detallesprovedoresarticuloUpdated = detallesprovedoresarticulosService.save(detallesprovedoresarticuloActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el detallesprovedoresarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(detallesprovedoresarticuloActual == null){
            response.put("message", "El detallesprovedoresarticulo ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El detallesprovedoresarticulo ha sido actualizado con exito");
        response.put("detallesprovedoresarticulo", detallesprovedoresarticuloUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/detallesprovedoresarticulo/{id}")
    public ResponseEntity<?> deleteDetallesprovedoresarticulo(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            detallesprovedoresarticulosService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el detallesprovedoresarticulo en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El articulo ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
    ///////////////////////////////////////PROMOCIONES//////////////////////////////////////////////////////////////

    @GetMapping("/promociones/list")
    public ResponseEntity<?> listaPromociones(){
        Map<String, Object> response = new HashMap<>();
        List<Promociones> promociones = null;
        try {
            promociones = promocionesService.findAll();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(promociones == null){
            response.put("message", "No existen promociones en la base de datos todavia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<Promociones>>(promociones, HttpStatus.OK);
    }
    
    @GetMapping("/promocion/{id}")
    public ResponseEntity<?> showPromocion(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();
        Promociones promocion = null;
        
        try {
            promocion = promocionesService.findById(id);
        } catch (Exception e) {
            response.put("message", "Error al reailzar la consulta en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(promocion == null){
            response.put("message", "El promocion ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Promociones>(promocion, HttpStatus.OK);
    }
    
    @PostMapping("/promocion/create")
    public ResponseEntity<?> createPromocion(@Valid @RequestBody Promociones promocion, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();
        Promociones promocionNuevo = null;
        
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        
        
        try {
            promocionNuevo = promocionesService.save(promocion);
        } catch (Exception e) {
            response.put("message", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El promocion ha sido creado con exito");
        response.put("promocion", promocionNuevo);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PutMapping("/promocion/{id}")
    public ResponseEntity<?> updatePromocion(@Valid @RequestBody Promociones promocion, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Promociones promocionActual = null;
        Promociones promocionUpdated = null;
        
        if(result.hasErrors()){
            
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            promocionActual = promocionesService.findById(id);
            
            promocionActual.setArticulo(promocion.getArticulo());
            promocionActual.setCodigo(promocion.getCodigo());
            promocionActual.setNombre(promocion.getNombre());
            promocionActual.setCantidad(promocion.getCantidad());
            promocionActual.setPrecio(promocion.getPrecio());


            promocionUpdated = promocionesService.save(promocionActual);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el promocion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(promocionActual == null){
            response.put("message", "El promocion ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
        response.put("message", "El promocion ha sido actualizado con exito");
        response.put("promocion", promocionUpdated);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/promocion/{id}")
    public ResponseEntity<?> deletePromocion(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        
        try {
            promocionesService.delete(id);
        } catch (Exception e) {
            response.put("message", "Error al eliminar el promocion en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("message", "El promocion ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    
    
}
