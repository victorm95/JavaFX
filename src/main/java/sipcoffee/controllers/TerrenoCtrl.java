package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Terreno;

public class TerrenoCtrl {

	public TerrenoCtrl() {
	}

	
	/*public Terreno create(Object nombre) {
		JSONObject json;
		try {
			json = new JSONObject(nombre.toString());
			return new Terreno(json.getString("nombre"));
		} catch (JSONException e) {
			System.out.println(e.toString());
			return new Terreno(nombre.toString());
		}
	}*/
	
	public Terreno findById(int id){
		return new Terreno().find(id);
	}
	
	public String all(){
		return new Terreno().all();
	}
	
}
