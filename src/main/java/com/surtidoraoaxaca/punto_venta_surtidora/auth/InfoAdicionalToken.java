package com.surtidoraoaxaca.punto_venta_surtidora.auth;

import com.surtidoraoaxaca.punto_venta_surtidora.models.entitys.Usuarios;
import com.surtidoraoaxaca.punto_venta_surtidora.models.services.IUsuariosService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
    
    @Autowired
    private IUsuariosService usuariosService;
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        
        Usuarios usuario = usuariosService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        
        
        
        info.put("idUsuario", usuario.getIdUsuarios());
        info.put("username", usuario.getUsername());
        
        info.put("seccionCatalogo", (usuario.getRol().getSeccionCatalogo())?"1":"0");
        info.put("agregarArticulo", (usuario.getRol().getAgregarArticulo())?"1":"0");
        info.put("eliminarArticulo", (usuario.getRol().getEliminarArticulo())?"1":"0");
        info.put("editarArticulo", (usuario.getRol().getEditarArticulo())?"1":"0");
        info.put("exportarArticulos", (usuario.getRol().getExportarArticulos())?"1":"0");
        info.put("importarArticulos", (usuario.getRol().getImportarArticulos())?"1":"0");
        
        info.put("seccionConsultas", (usuario.getRol().getSeccionConsultas())?"1":"0");
        info.put("cambiarFechaConsulta", (usuario.getRol().getCambiarFechaConsulta())?"1":"0");
        info.put("cancelarVenta", (usuario.getRol().getCancelarVenta())?"1":"0");
        info.put("cancelarCompra", (usuario.getRol().getCancelarCompra())?"1":"0");
        
        info.put("seccionVentas", (usuario.getRol().getSeccionVentas())?"1":"0");
        info.put("realizarVenta", (usuario.getRol().getRealizarVenta())?"1":"0");
        info.put("cambiarPrecio", (usuario.getRol().getCambiarPrecio())?"1":"0");
        info.put("preguntarImprimir", (usuario.getRol().getPreguntarImprimir())?"1":"0");
        
        info.put("seccionReportes", (usuario.getRol().getSeccionReportes())?"1":"0");
        info.put("cambiarFechaReporte", (usuario.getRol().getCambiarFechaReporte())?"1":"0");
        
        info.put("seccionCompras", (usuario.getRol().getSeccionCompras())?"1":"0");
        
        info.put("seccionConfiguraciones", (usuario.getRol().getSeccionConfiguraciones())?"1":"0");
        info.put("seccionOtros", (usuario.getRol().getSeccionOtros())?"1":"0");
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
        
    }
    
    
    
}
