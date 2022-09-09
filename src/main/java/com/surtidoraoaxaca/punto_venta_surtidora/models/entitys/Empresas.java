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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e"),
    @NamedQuery(name = "Empresas.findByIdEmpresas", query = "SELECT e FROM Empresas e WHERE e.idEmpresas = :idEmpresas"),
    @NamedQuery(name = "Empresas.findByNombre", query = "SELECT e FROM Empresas e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresas.findByDireccion", query = "SELECT e FROM Empresas e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empresas.findByCiudad", query = "SELECT e FROM Empresas e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Empresas.findByEstado", query = "SELECT e FROM Empresas e WHERE e.estado = :estado"),
    @NamedQuery(name = "Empresas.findByCodigoPostal", query = "SELECT e FROM Empresas e WHERE e.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Empresas.findByTelefono", query = "SELECT e FROM Empresas e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresas.findByEmail", query = "SELECT e FROM Empresas e WHERE e.email = :email")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresas")
    private Long idEmpresas;
    @Size(max = 45)
    private String nombre;
    @Size(max = 45)
    private String direccion;
    @Size(max = 45)
    private String ciudad;
    @Size(max = 45)
    private String estado;
    @Column(name = "codigo_postal")
    private Integer codigoPostal;
    private Long telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    private String email;

    public Empresas() {
    }

    public Empresas(Long idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public Long getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(Long idEmpresas) {
        this.idEmpresas = idEmpresas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresas != null ? idEmpresas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idEmpresas == null && other.idEmpresas != null) || (this.idEmpresas != null && !this.idEmpresas.equals(other.idEmpresas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idEmpresas=" + idEmpresas + " ]";
    }
    
}
