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
    @NamedQuery(name = "Detallesventasarticulos.findAll", query = "SELECT d FROM Detallesventasarticulos d"),
    @NamedQuery(name = "Detallesventasarticulos.findByIdDetallesVentasArticulos", query = "SELECT d FROM Detallesventasarticulos d WHERE d.idDetallesVentasArticulos = :idDetallesVentasArticulos"),
    @NamedQuery(name = "Detallesventasarticulos.findByCantidad", query = "SELECT d FROM Detallesventasarticulos d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detallesventasarticulos.findByPrecioUnitario", query = "SELECT d FROM Detallesventasarticulos d WHERE d.precioUnitario = :precioUnitario")})
public class Detallesventasarticulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalles_ventas_articulos")
    private Long idDetallesVentasArticulos;
    
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulos")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Articulos articulo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "articulos"})
    private Ventas venta;
    
    @Basic(optional = false)
    @NotNull
    private Float cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private Float precioUnitario;
    
    private Float importe;
    

    public Detallesventasarticulos() {
    }

    public Detallesventasarticulos(Long idDetallesVentasArticulos) {
        this.idDetallesVentasArticulos = idDetallesVentasArticulos;
    }

    public Detallesventasarticulos(Long idDetallesVentasArticulos, Float cantidad, Float precioUnitario) {
        this.idDetallesVentasArticulos = idDetallesVentasArticulos;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getIdDetallesVentasArticulos() {
        return idDetallesVentasArticulos;
    }

    public void setIdDetallesVentasArticulos(Long idDetallesVentasArticulos) {
        this.idDetallesVentasArticulos = idDetallesVentasArticulos;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
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
        hash += (idDetallesVentasArticulos != null ? idDetallesVentasArticulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallesventasarticulos)) {
            return false;
        }
        Detallesventasarticulos other = (Detallesventasarticulos) object;
        if ((this.idDetallesVentasArticulos == null && other.idDetallesVentasArticulos != null) || (this.idDetallesVentasArticulos != null && !this.idDetallesVentasArticulos.equals(other.idDetallesVentasArticulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idDetallesVentasArticulos=" + idDetallesVentasArticulos + " ]";
    }
    
}
