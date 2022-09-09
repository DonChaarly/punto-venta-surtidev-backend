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
import javax.persistence.PrePersist;
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
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdRoles", query = "SELECT r FROM Roles r WHERE r.idRoles = :idRoles"),
    @NamedQuery(name = "Roles.findByNombre", query = "SELECT r FROM Roles r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Roles.findBySeccionCatalogo", query = "SELECT r FROM Roles r WHERE r.seccionCatalogo = :seccionCatalogo"),
    @NamedQuery(name = "Roles.findByAgregarArticulo", query = "SELECT r FROM Roles r WHERE r.agregarArticulo = :agregarArticulo"),
    @NamedQuery(name = "Roles.findByEliminarArticulo", query = "SELECT r FROM Roles r WHERE r.eliminarArticulo = :eliminarArticulo"),
    @NamedQuery(name = "Roles.findByEditarArticulo", query = "SELECT r FROM Roles r WHERE r.editarArticulo = :editarArticulo"),
    @NamedQuery(name = "Roles.findByExportarArticulos", query = "SELECT r FROM Roles r WHERE r.exportarArticulos = :exportarArticulos"),
    @NamedQuery(name = "Roles.findByImportarArticulos", query = "SELECT r FROM Roles r WHERE r.importarArticulos = :importarArticulos"),
    @NamedQuery(name = "Roles.findBySeccionConsultas", query = "SELECT r FROM Roles r WHERE r.seccionConsultas = :seccionConsultas"),
    @NamedQuery(name = "Roles.findByCambiarFechaConsulta", query = "SELECT r FROM Roles r WHERE r.cambiarFechaConsulta = :cambiarFechaConsulta"),
    @NamedQuery(name = "Roles.findByCancelarVenta", query = "SELECT r FROM Roles r WHERE r.cancelarVenta = :cancelarVenta"),
    @NamedQuery(name = "Roles.findByCancelarCompra", query = "SELECT r FROM Roles r WHERE r.cancelarCompra = :cancelarCompra"),
    @NamedQuery(name = "Roles.findBySeccionVentas", query = "SELECT r FROM Roles r WHERE r.seccionVentas = :seccionVentas"),
    @NamedQuery(name = "Roles.findByRealizarVenta", query = "SELECT r FROM Roles r WHERE r.realizarVenta = :realizarVenta"),
    @NamedQuery(name = "Roles.findByCambiarPrecio", query = "SELECT r FROM Roles r WHERE r.cambiarPrecio = :cambiarPrecio"),
    @NamedQuery(name = "Roles.findBySeccionReportes", query = "SELECT r FROM Roles r WHERE r.seccionReportes = :seccionReportes"),
    @NamedQuery(name = "Roles.findByCambiarFechaReporte", query = "SELECT r FROM Roles r WHERE r.cambiarFechaReporte = :cambiarFechaReporte"),
    @NamedQuery(name = "Roles.findBySeccionCompras", query = "SELECT r FROM Roles r WHERE r.seccionCompras = :seccionCompras"),
    @NamedQuery(name = "Roles.findBySeccionConfiguraciones", query = "SELECT r FROM Roles r WHERE r.seccionConfiguraciones = :seccionConfiguraciones")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_roles")
    private Long idRoles;
    @NotEmpty(message= Messages.NOT_EMPTY)
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = Messages.MAX_SIZE + "30")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_catalogo")
    private boolean seccionCatalogo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "agregar_articulo")
    private boolean agregarArticulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar_articulo")
    private boolean eliminarArticulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "editar_articulo")
    private boolean editarArticulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "exportar_articulos")
    private boolean exportarArticulos;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "importar_articulos")
    private boolean importarArticulos;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_consultas")
    private boolean seccionConsultas;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambiar_fecha_consulta")
    private boolean cambiarFechaConsulta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancelar_venta")
    private boolean cancelarVenta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancelar_compra")
    private boolean cancelarCompra;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_ventas")
    private boolean seccionVentas;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "realizar_venta")
    private boolean realizarVenta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambiar_precio")
    private boolean cambiarPrecio;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "preguntar_imprimir")
    private boolean preguntarImprimir;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_reportes")
    private boolean seccionReportes;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambiar_fecha_reporte")
    private boolean cambiarFechaReporte;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_compras")
    private boolean seccionCompras;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_configuraciones")
    private boolean seccionConfiguraciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion_otros")
    private boolean seccionOtros;

    public Roles() {
    }

    public Roles(Long idRoles) {
        this.idRoles = idRoles;
    }

    public Roles(Long idRoles, String nombre, boolean seccionCatalogo, boolean agregarArticulo, boolean eliminarArticulo, boolean editarArticulo, boolean exportarArticulos, boolean importarArticulos, boolean seccionConsultas, boolean cambiarFechaConsulta, boolean cancelarVenta, boolean cancelarCompra, boolean seccionVentas, boolean realizarVenta, boolean cambiarPrecio, boolean seccionReportes, boolean cambiarFechaReporte, boolean seccionCompras) {
        this.idRoles = idRoles;
        this.nombre = nombre;
        this.seccionCatalogo = seccionCatalogo;
        this.agregarArticulo = agregarArticulo;
        this.eliminarArticulo = eliminarArticulo;
        this.editarArticulo = editarArticulo;
        this.exportarArticulos = exportarArticulos;
        this.importarArticulos = importarArticulos;
        this.seccionConsultas = seccionConsultas;
        this.cambiarFechaConsulta = cambiarFechaConsulta;
        this.cancelarVenta = cancelarVenta;
        this.cancelarCompra = cancelarCompra;
        this.seccionVentas = seccionVentas;
        this.realizarVenta = realizarVenta;
        this.cambiarPrecio = cambiarPrecio;
        this.seccionReportes = seccionReportes;
        this.cambiarFechaReporte = cambiarFechaReporte;
        this.seccionCompras = seccionCompras;
    }
    
    

    public Long getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Long idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getSeccionCatalogo() {
        return seccionCatalogo;
    }

    public void setSeccionCatalogo(boolean seccionCatalogo) {
        this.seccionCatalogo = seccionCatalogo;
    }

    public boolean getAgregarArticulo() {
        return agregarArticulo;
    }

    public void setAgregarArticulo(boolean agregarArticulo) {
        this.agregarArticulo = agregarArticulo;
    }

    public boolean getEliminarArticulo() {
        return eliminarArticulo;
    }

    public void setEliminarArticulo(boolean eliminarArticulo) {
        this.eliminarArticulo = eliminarArticulo;
    }

    public boolean getEditarArticulo() {
        return editarArticulo;
    }

    public void setEditarArticulo(boolean editarArticulo) {
        this.editarArticulo = editarArticulo;
    }

    public boolean getExportarArticulos() {
        return exportarArticulos;
    }

    public void setExportarArticulos(boolean exportarArticulos) {
        this.exportarArticulos = exportarArticulos;
    }

    public boolean getImportarArticulos() {
        return importarArticulos;
    }

    public void setImportarArticulos(boolean importarArticulos) {
        this.importarArticulos = importarArticulos;
    }

    public boolean getSeccionConsultas() {
        return seccionConsultas;
    }

    public void setSeccionConsultas(boolean seccionConsultas) {
        this.seccionConsultas = seccionConsultas;
    }

    public boolean getCambiarFechaConsulta() {
        return cambiarFechaConsulta;
    }

    public void setCambiarFechaConsulta(boolean cambiarFechaConsulta) {
        this.cambiarFechaConsulta = cambiarFechaConsulta;
    }

    public boolean getCancelarVenta() {
        return cancelarVenta;
    }

    public void setCancelarVenta(boolean cancelarVenta) {
        this.cancelarVenta = cancelarVenta;
    }

    public boolean getCancelarCompra() {
        return cancelarCompra;
    }

    public void setCancelarCompra(boolean cancelarCompra) {
        this.cancelarCompra = cancelarCompra;
    }

    public boolean getSeccionVentas() {
        return seccionVentas;
    }

    public void setSeccionVentas(boolean seccionVentas) {
        this.seccionVentas = seccionVentas;
    }

    public boolean getRealizarVenta() {
        return realizarVenta;
    }

    public void setRealizarVenta(boolean realizarVenta) {
        this.realizarVenta = realizarVenta;
    }

    public boolean getCambiarPrecio() {
        return cambiarPrecio;
    }

    public void setCambiarPrecio(boolean cambiarPrecio) {
        this.cambiarPrecio = cambiarPrecio;
    }

    public boolean getSeccionReportes() {
        return seccionReportes;
    }

    public void setSeccionReportes(boolean seccionReportes) {
        this.seccionReportes = seccionReportes;
    }

    public boolean getCambiarFechaReporte() {
        return cambiarFechaReporte;
    }

    public void setCambiarFechaReporte(boolean cambiarFechaReporte) {
        this.cambiarFechaReporte = cambiarFechaReporte;
    }

    public boolean getSeccionCompras() {
        return seccionCompras;
    }

    public void setSeccionCompras(boolean seccionCompras) {
        this.seccionCompras = seccionCompras;
    }

    public boolean getSeccionConfiguraciones() {
        return seccionConfiguraciones;
    }

    public void setSeccionConfiguraciones(boolean seccionConfiguraciones) {
        this.seccionConfiguraciones = seccionConfiguraciones;
    }

    public boolean getPreguntarImprimir() {
        return preguntarImprimir;
    }

    public void setPreguntarImprimir(boolean preguntarImprimir) {
        this.preguntarImprimir = preguntarImprimir;
    }

    public boolean getSeccionOtros() {
        return seccionOtros;
    }

    public void setSeccionOtros(boolean seccionOtros) {
        this.seccionOtros = seccionOtros;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoles != null ? idRoles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRoles == null && other.idRoles != null) || (this.idRoles != null && !this.idRoles.equals(other.idRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ idRoles=" + idRoles + " ]";
    }
    
}
