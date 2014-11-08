package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.App;
import sipcoffee.models.Rol;
import sipcoffee.models.Usuario;

import java.util.Date;

public class UsuarioCtrl {

    public UsuarioCtrl() {
    }

    public Usuario create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Usuario usuario = new Usuario();

            usuario.setNombre(json.getString("nombre"));
            usuario.setUsuario(json.getString("usuario"));
            usuario.setClave(json.getString("clave"));
            usuario.setCedula(json.getLong("cedula"));
            usuario.setDireccion(json.getString("direccion"));
            usuario.setTelefono(json.getLong("telefono"));
            usuario.setActivo(json.getBoolean("activo"));
            usuario.setFechaRegistro(new Date());
            usuario.setUltimaSesion(new Date());
            usuario.setExpedicionDocumento(new Date(json.getLong("expedicionDocumento")));
            //usuario.setExpedicionDocumento(new Date());
            usuario.setRol(new Rol().find(json.getString("rol")));
            //usuario.setMunicipio( new Municipio() );


            return usuario;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Usuario();
        }
    }

    public Usuario find(int id) {
        return new Usuario().find(id);
    }

    public Usuario login(String usuario, String clave) {
        return new Usuario().login(usuario, App.hash(clave));
    }

    public String all() {
        return new Usuario().all();
    }

}
