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
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByIdEmpleados", query = "SELECT e FROM Empleados e WHERE e.idEmpleados = :idEmpleados"),
    @NamedQuery(name = "Empleados.findByCodigo", query = "SELECT e FROM Empleados e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleados.findByFechaIngreso", query = "SELECT e FROM Empleados e WHERE e.fechaIngreso = :fechaIngreso")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleados")
    private Long idEmpleados;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = Messages.MAX_SIZE + "30")
    private String codigo;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535, message = Messages.MAX_SIZE + "65535")
    private String nombre;
    @Lob
    @Size(max = 65535, message = Messages.MAX_SIZE + "65535")
    private String direccion;
    private Long telefono;
    
    private String puesto;
    
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    public Empleados() {
    }

    public Empleados(Long idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public Empleados(Long idEmpleados, String codigo, String nombre) {
        this.idEmpleados = idEmpleados;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(Long idEmpleados) {
        this.idEmpleados = idEmpleados;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleados != null ? idEmpleados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleados == null && other.idEmpleados != null) || (this.idEmpleados != null && !this.idEmpleados.equals(other.idEmpleados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idEmpleados=" + idEmpleados + " ]";
    }
    
}
