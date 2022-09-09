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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Provedores.findAll", query = "SELECT p FROM Provedores p"),
    @NamedQuery(name = "Provedores.findByIdProvedores", query = "SELECT p FROM Provedores p WHERE p.idProvedores = :idProvedores"),
    @NamedQuery(name = "Provedores.findByCodigo", query = "SELECT p FROM Provedores p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Provedores.findByRfc", query = "SELECT p FROM Provedores p WHERE p.rfc = :rfc"),
    @NamedQuery(name = "Provedores.findByTelefono", query = "SELECT p FROM Provedores p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Provedores.findByEmail", query = "SELECT p FROM Provedores p WHERE p.email = :email")})
public class Provedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_provedores")
    private Long idProvedores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = Messages.MAX_SIZE + "30")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65535, message = Messages.MAX_SIZE + "65535")
    private String nombre;
    @Size(max = 30, message = Messages.MAX_SIZE + "30")
    private String rfc;
    private Long telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    private String email;
    @Size(max = 65535, message = Messages.MAX_SIZE + "65535")
    private String direccion;
    
    

    public Provedores() {
    }

    public Provedores(Long idProvedores) {
        this.idProvedores = idProvedores;
    }

    public Provedores(Long idProvedores, String codigo, String nombre) {
        this.idProvedores = idProvedores;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getIdProvedores() {
        return idProvedores;
    }

    public void setIdProvedores(Long idProvedores) {
        this.idProvedores = idProvedores;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvedores != null ? idProvedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provedores)) {
            return false;
        }
        Provedores other = (Provedores) object;
        if ((this.idProvedores == null && other.idProvedores != null) || (this.idProvedores != null && !this.idProvedores.equals(other.idProvedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idProvedores=" + idProvedores + " ]";
    }
    
}
