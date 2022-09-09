/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByIdVentas", query = "SELECT v FROM Ventas v WHERE v.idVentas = :idVentas"),
    @NamedQuery(name = "Ventas.findByFolio", query = "SELECT v FROM Ventas v WHERE v.folio = :folio"),
    @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Ventas.findByEnEspera", query = "SELECT v FROM Ventas v WHERE v.enEspera = :enEspera")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ventas")
    private Long idVentas;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = Messages.MAX_SIZE + "45")
    private String folio;
    
    @Basic (optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios usuario;
    

    @Basic (optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Clientes cliente;
    
    @Basic (optional = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cajas")
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
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "venta")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "venta"})
    private List<Detallesventasarticulos> articulos;
    
    
    public Ventas() {
    }

    public Ventas(Long idVentas) {
        this.idVentas = idVentas;
    }

    public Ventas(Long idVentas, String folio, LocalDate fecha, boolean enEspera) {
        this.idVentas = idVentas;
        this.folio = folio;
        this.fecha = fecha;
        this.enEspera = enEspera;
    }

    public Long getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Long idVentas) {
        this.idVentas = idVentas;
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

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public List<Detallesventasarticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Detallesventasarticulos> articulos) {
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
        hash += (idVentas != null ? idVentas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idVentas == null && other.idVentas != null) || (this.idVentas != null && !this.idVentas.equals(other.idVentas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idVentas=" + idVentas + " ]";
    }
    
}
