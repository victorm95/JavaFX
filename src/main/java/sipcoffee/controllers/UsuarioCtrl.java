package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Municipio;
import sipcoffee.models.Rol;
import sipcoffee.models.Usuario;

public class UsuarioCtrl {
	
	public UsuarioCtrl(){}

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
			//usuario.setExpedicionDocumento(new Date(json.getLong("expedicionDocumento")));
			usuario.setExpedicionDocumento(new Date());
			usuario.setRol( new Rol().find(json.getString("rol")) );
			//usuario.setMunicipio( new Municipio() );
			
			
			
			return usuario;
		} catch (JSONException e) {
			System.out.println(e.toString());
			return new Usuario();
		}
	}
	
	public Usuario find(int id){
		return new Usuario().find(id);
	}
	
	public String all(){
		return new Usuario().all();
	}
	
}
