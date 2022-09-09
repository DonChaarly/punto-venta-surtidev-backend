/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Compras.findAll", query = "SELECT c FROM Compras c"),
    @NamedQuery(name = "Compras.findByIdCompras", query = "SELECT c FROM Compras c WHERE c.idCompras = :idCompras"),
    @NamedQuery(name = "Compras.findByFolio", query = "SELECT c FROM Compras c WHERE c.folio = :folio"),
    @NamedQuery(name = "Compras.findByFecha", query = "SELECT c FROM Compras c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Compras.findByEnEspera", query = "SELECT c FROM Compras c WHERE c.enEspera = :enEspera")})
public class Compras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_compras")
    private Long idCompras;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = Messages.MAX_SIZE + "45")
    private String folio; 
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provedores", referencedColumnName = "id_provedores")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Provedores provedor;
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", referencedColumnName = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios usuario;
    
    @Basic(optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cajas", referencedColumnName = "id_cajas")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cajas caja;
    @Basic(optional = false)
    @NotNull
    private LocalDate fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "en_espera")
    private boolean enEspera;
    
    private Float total;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "compra")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Detallescomprasarticulos> articulos;

    public Compras() {
    }

    public Compras(Long idCompras) {
        this.idCompras = idCompras;
    }

    public Compras(Long idCompras, String folio, LocalDate fecha, boolean enEspera) {
        this.idCompras = idCompras;
        this.folio = folio;
        this.fecha = fecha;
        this.enEspera = enEspera;
    }

    public Long getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(Long idCompras) {
        this.idCompras = idCompras;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public boolean getEnEspera() {
        return enEspera;
    }

    public void setEnEspera(boolean enEspera) {
        this.enEspera = enEspera;
    }

    public Provedores getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedores provedor) {
        this.provedor = provedor;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public List<Detallescomprasarticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Detallescomprasarticulos> articulos) {
        this.articulos = articulos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompras != null ? idCompras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.idCompras == null && other.idCompras != null) || (this.idCompras != null && !this.idCompras.equals(other.idCompras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idCompras=" + idCompras + " ]";
    }
    
}
