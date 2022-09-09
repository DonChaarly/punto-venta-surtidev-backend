/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "articulos")
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulos")
    private Long idArticulos;
    
    @NotEmpty(message= Messages.NOT_EMPTY)
    @NotNull
    @Size(min = 1, max = 20, message = Messages.MAX_SIZE + "20")
    @Column(unique = true, name = "codigo")
    private String codigo;  
    @NotEmpty(message= Messages.NOT_EMPTY)
    @NotNull
    @Size(min = 1, max = 65535, message = Messages.MAX_SIZE + "65535")
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "inventario_min")
    private Integer inventarioMin;
    @Column(name = "inventario_max")
    private Integer inventarioMax;

    @NotNull
    @Column(name = "precio1")
    private Float precio1;
    @Column(name = "precio2")
    private Float precio2;
    @Column(name = "existencias")
    private Float existencias;
    @Column(name = "ultimo_precio_compra")
    private Float ultimoPrecioCompra;
    @Column(name = "veces_comprado")
    private Integer vecesComprado;
    @Column(name = "modificacion_precio")
    private Boolean modificacionPrecio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorias", referencedColumnName = "id_categorias")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categorias categoria;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "articulo"})
    private List<Detallesprovedoresarticulos> provedores;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articulo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "articulo"})
    private List<Promociones> promociones;
    

    public Articulos() {
    }

    public Articulos(Long idArticulos) {
        this.idArticulos = idArticulos;
    }

    public Articulos(Long idArticulos, String codigo, String nombre, Float precio1) {
        this.idArticulos = idArticulos;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio1 = precio1;
    }

    public Long getIdArticulos() {
        return idArticulos;
    }

    public void setIdArticulos(Long idArticulos) {
        this.idArticulos = idArticulos;
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

    public Integer getInventarioMin() {
        return inventarioMin;
    }

    public void setInventarioMin(Integer inventarioMin) {
        this.inventarioMin = inventarioMin;
    }

    public Integer getInventarioMax() {
        return inventarioMax;
    }

    public void setInventarioMax(Integer inventarioMax) {
        this.inventarioMax = inventarioMax;
    }

    public Float getPrecio1() {
        return precio1;
    }

    public void setPrecio1(Float precio1) {
        this.precio1 = precio1;
    }

    public Float getPrecio2() {
        return precio2;
    }

    public void setPrecio2(Float precio2) {
        this.precio2 = precio2;
    }

    public Float getExistencias() {
        return existencias;
    }

    public void setExistencias(Float existencias) {
        this.existencias = existencias;
    }

    public Float getUltimoPrecioCompra() {
        return ultimoPrecioCompra;
    }

    public void setUltimoPrecioCompra(Float ultimoPrecioCompra) {
        this.ultimoPrecioCompra = ultimoPrecioCompra;
    }

    public Integer getVecesComprado() {
        return vecesComprado;
    }

    public void setVecesComprado(Integer vecesComprado) {
        this.vecesComprado = vecesComprado;
    }

    public Boolean getModificacionPrecio() {
        return modificacionPrecio;
    }

    public void setModificacionPrecio(Boolean modificacionPrecio) {
        this.modificacionPrecio = modificacionPrecio;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<Detallesprovedoresarticulos> getProvedores() {
        return provedores;
    }

    public void setProvedores(List<Detallesprovedoresarticulos> provedores) {
        this.provedores = provedores;
    }

    public List<Promociones> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promociones> promociones) {
        this.promociones = promociones;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulos != null ? idArticulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.idArticulos == null && other.idArticulos != null) || (this.idArticulos != null && !this.idArticulos.equals(other.idArticulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idArticulos=" + idArticulos + " ]";
    }
    
}
