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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CARLOS
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promociones.findAll", query = "SELECT p FROM Promociones p"),
    @NamedQuery(name = "Promociones.findByIdPromociones", query = "SELECT p FROM Promociones p WHERE p.idPromociones = :idPromociones"),
    @NamedQuery(name = "Promociones.findByCodigo", query = "SELECT p FROM Promociones p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Promociones.findByCantidad", query = "SELECT p FROM Promociones p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Promociones.findByPrecio", query = "SELECT p FROM Promociones p WHERE p.precio = :precio")})
public class Promociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_promociones")
    private Long idPromociones;
    
    @Basic (optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulos", referencedColumnName = "id_articulos")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "promociones"})
    private Articulos articulo;
    
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = Messages.MAX_SIZE + "30")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535, message = Messages.MAX_SIZE + "65535")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    private Float cantidad;
    @Basic(optional = false)
    @NotNull
    private Float precio;

    public Promociones() {
    }

    public Promociones(Long idPromociones) {
        this.idPromociones = idPromociones;
    }

    public Promociones(Long idPromociones, String codigo, String nombre, Float cantidad, Float precio) {
        this.idPromociones = idPromociones;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Long getIdPromociones() {
        return idPromociones;
    }

    public void setIdPromociones(Long idPromociones) {
        this.idPromociones = idPromociones;
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

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromociones != null ? idPromociones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promociones)) {
            return false;
        }
        Promociones other = (Promociones) object;
        if ((this.idPromociones == null && other.idPromociones != null) || (this.idPromociones != null && !this.idPromociones.equals(other.idPromociones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idPromociones=" + idPromociones + " ]";
    }
    
}
