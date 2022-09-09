/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CARLOS
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallescomprasarticulos.findAll", query = "SELECT d FROM Detallescomprasarticulos d"),
    @NamedQuery(name = "Detallescomprasarticulos.findByIdDetallesComprasArticulos", query = "SELECT d FROM Detallescomprasarticulos d WHERE d.idDetallesComprasArticulos = :idDetallesComprasArticulos"),
    @NamedQuery(name = "Detallescomprasarticulos.findByCantidad", query = "SELECT d FROM Detallescomprasarticulos d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallescomprasarticulos.findByPrecioCompra", query = "SELECT d FROM Detallescomprasarticulos d WHERE d.precioCompra = :precioCompra"),
    @NamedQuery(name = "Detallescomprasarticulos.findByPrecio1Venta", query = "SELECT d FROM Detallescomprasarticulos d WHERE d.precio1Venta = :precio1Venta"),
    @NamedQuery(name = "Detallescomprasarticulos.findByPrecio2Venta", query = "SELECT d FROM Detallescomprasarticulos d WHERE d.precio2Venta = :precio2Venta")})
public class Detallescomprasarticulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalles_compras_articulos")
    private Long idDetallesComprasArticulos;
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulos")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Articulos articulo;
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compras")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "articulos"})
    private Compras compra;

    @Basic(optional = false)
    @NotNull
    private Float cantidad;
    @Basic(optional = false)
    @NotNull
    private Float precioCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio1_venta")
    private Float precio1Venta;
    @Column(name = "precio2_venta")
    private Float precio2Venta;
    
    private Float importe;
    

    public Detallescomprasarticulos() {
    }

    public Detallescomprasarticulos(Long idDetallesComprasArticulos) {
        this.idDetallesComprasArticulos = idDetallesComprasArticulos;
    }

    public Detallescomprasarticulos(Long idDetallesComprasArticulos, Float cantidad, Float precioCompra, Float precio1Venta) {
        this.idDetallesComprasArticulos = idDetallesComprasArticulos;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precio1Venta = precio1Venta;
    }

    public Long getIdDetallesComprasArticulos() {
        return idDetallesComprasArticulos;
    }

    public void setIdDetallesComprasArticulos(Long idDetallesComprasArticulos) {
        this.idDetallesComprasArticulos = idDetallesComprasArticulos;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Float getPrecio1Venta() {
        return precio1Venta;
    }

    public void setPrecio1Venta(Float precio1Venta) {
        this.precio1Venta = precio1Venta;
    }

    public Float getPrecio2Venta() {
        return precio2Venta;
    }

    public void setPrecio2Venta(Float precio2Venta) {
        this.precio2Venta = precio2Venta;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallesComprasArticulos != null ? idDetallesComprasArticulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallescomprasarticulos)) {
            return false;
        }
        Detallescomprasarticulos other = (Detallescomprasarticulos) object;
        if ((this.idDetallesComprasArticulos == null && other.idDetallesComprasArticulos != null) || (this.idDetallesComprasArticulos != null && !this.idDetallesComprasArticulos.equals(other.idDetallesComprasArticulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idDetallesComprasArticulos=" + idDetallesComprasArticulos + " ]";
    }
    
}
