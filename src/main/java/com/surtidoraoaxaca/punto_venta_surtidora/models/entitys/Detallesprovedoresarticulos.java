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
    @NamedQuery(name = "Detallesprovedoresarticulos.findAll", query = "SELECT d FROM Detallesprovedoresarticulos d"),
    @NamedQuery(name = "Detallesprovedoresarticulos.findByIdDetallesProvedoresArticulos", query = "SELECT d FROM Detallesprovedoresarticulos d WHERE d.idDetallesProvedoresArticulos = :idDetallesProvedoresArticulos"),
    @NamedQuery(name = "Detallesprovedoresarticulos.findByUltPrecio", query = "SELECT d FROM Detallesprovedoresarticulos d WHERE d.ultPrecio = :ultPrecio")})
public class Detallesprovedoresarticulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalles_provedores_articulos")
    private Long idDetallesProvedoresArticulos;
    
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulos")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "provedores"})
    private Articulos articulo;
    
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provedores")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Provedores provedor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ult_precio")
    private Integer ultPrecio;

    public Detallesprovedoresarticulos() {
    }

    public Detallesprovedoresarticulos(Long idDetallesProvedoresArticulos) {
        this.idDetallesProvedoresArticulos = idDetallesProvedoresArticulos;
    }

    public Detallesprovedoresarticulos(Long idDetallesProvedoresArticulos, Integer ultPrecio) {
        this.idDetallesProvedoresArticulos = idDetallesProvedoresArticulos;
        this.ultPrecio = ultPrecio;
    }

    public Long getIdDetallesProvedoresArticulos() {
        return idDetallesProvedoresArticulos;
    }

    public void setIdDetallesProvedoresArticulos(Long idDetallesProvedoresArticulos) {
        this.idDetallesProvedoresArticulos = idDetallesProvedoresArticulos;
    }

    public Integer getUltPrecio() {
        return ultPrecio;
    }

    public void setUltPrecio(Integer ultPrecio) {
        this.ultPrecio = ultPrecio;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public Provedores getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedores provedor) {
        this.provedor = provedor;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallesProvedoresArticulos != null ? idDetallesProvedoresArticulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallesprovedoresarticulos)) {
            return false;
        }
        Detallesprovedoresarticulos other = (Detallesprovedoresarticulos) object;
        if ((this.idDetallesProvedoresArticulos == null && other.idDetallesProvedoresArticulos != null) || (this.idDetallesProvedoresArticulos != null && !this.idDetallesProvedoresArticulos.equals(other.idDetallesProvedoresArticulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idDetallesProvedoresArticulos=" + idDetallesProvedoresArticulos + " ]";
    }
    
}
