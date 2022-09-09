
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportesProductos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private LocalDate fecha;
    private String username;
    private String cliente;
    private String codigo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nombre;
    private String departamento;
    private String categoria;
    private Float cantidad;
    private Float precio;
    private Float importe;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportesProductos other = (ReportesProductos) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResporteProductos{" + "nombre=" + nombre + '}';
    }
    
   
    
    
}


/*  SELECT ventas.fecha,
        usuarios.username,
        articulos.codigo as codigos, 
        articulos.nombre,
        departamentos.nombre as departamento,
        categorias.nombre as categoria,
        SUM(detallesventasarticulos.cantidad) as cantidad, 
        detallesventasarticulos.precio_unitario as precio, 
        SUM(detallesventasarticulos.importe) as importe  
	from detallesventasarticulos, articulos, ventas, categorias, departamentos, usuarios 
    WHERE detallesventasarticulos.id_articulos = articulos.id_articulos
    AND ventas.id_usuarios = usuarios.id_usuarios
    AND articulos.id_categorias = categorias.id_categorias
    AND categorias.id_departamentos = departamentos.id_departamentos
    AND detallesventasarticulos.id_ventas = ventas.id_ventas
    and usuarios.username like '%'
    and articulos.codigo like '%'
    and departamentos.nombre LIKE '%'
    and (ventas.fecha > 2022-08-19 or ventas.fecha < 2022-08-19)

    GROUP BY nombre
    ORDER BY codigo;
*/