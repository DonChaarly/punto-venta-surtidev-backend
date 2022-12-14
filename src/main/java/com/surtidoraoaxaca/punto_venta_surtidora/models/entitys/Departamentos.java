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
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_departamentos")
    private Long idDepartamentos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = Messages.MAX_SIZE + "30")
    private String nombre;

    public Departamentos() {
    }

    public Departamentos(Long idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public Departamentos(Long idDepartamentos, String nombre) {
        this.idDepartamentos = idDepartamentos;
        this.nombre = nombre;
    }

    public Long getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(Long idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
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
        hash += (idDepartamentos != null ? idDepartamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.idDepartamentos == null && other.idDepartamentos != null) || (this.idDepartamentos != null && !this.idDepartamentos.equals(other.idDepartamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idDepartamentos=" + idDepartamentos + " ]";
    }
    
}
