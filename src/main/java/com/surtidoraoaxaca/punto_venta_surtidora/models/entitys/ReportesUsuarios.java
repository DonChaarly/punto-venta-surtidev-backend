
package com.surtidoraoaxaca.punto_venta_surtidora.models.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportesUsuarios implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Usuario;
    private Float Venta;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public Float getVenta() {
        return Venta;
    }

    public void setVenta(Float Venta) {
        this.Venta = Venta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.Usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportesUsuarios other = (ReportesUsuarios) obj;
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReportesUsuarios{" + "Usuario=" + Usuario + '}';
    }
    
   
    
    
}

