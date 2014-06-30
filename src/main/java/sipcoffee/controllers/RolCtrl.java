package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Rol;

public class RolCtrl {

	public RolCtrl() {
	}

	
	public Rol create(Object nombre) {
		JSONObject json;
		try {
			json = new JSONObject(nombre.toString());
			return new Rol(json.getString("nombre"));
		} catch (JSONException e) {
			System.out.println(e.toString());
			return new Rol(nombre.toString());
		}
	}
	
	public Rol findById(int id){
		return new Rol().find(id);
	}
	
	public String all(){
		return new Rol().all();
	}
	
}
