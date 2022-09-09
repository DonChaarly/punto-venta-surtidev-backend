/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Cajas.findAll", query = "SELECT c FROM Cajas c"),
    @NamedQuery(name = "Cajas.findByIdCajas", query = "SELECT c FROM Cajas c WHERE c.idCajas = :idCajas"),
    @NamedQuery(name = "Cajas.findByNombre", query = "SELECT c FROM Cajas c WHERE c.nombre = :nombre")})
public class Cajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cajas")
    private Long idCajas;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = Messages.MAX_SIZE + "45")
    @Column(name = "nombre")
    private String nombre;

    public Cajas() {
    }

    public Cajas(Long idCajas) {
        this.idCajas = idCajas;
    }

    public Cajas(Long idCajas, String nombre) {
        this.idCajas = idCajas;
        this.nombre = nombre;
    }

    public Long getIdCajas() {
        return idCajas;
    }

    public void setIdCajas(Long idCajas) {
        this.idCajas = idCajas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCajas != null ? idCajas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajas)) {
            return false;
        }
        Cajas other = (Cajas) object;
        if ((this.idCajas == null && other.idCajas != null) || (this.idCajas != null && !this.idCajas.equals(other.idCajas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idCajas=" + idCajas + " ]";
    }
    
}
