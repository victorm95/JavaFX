package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Bloque;
import sipcoffee.models.Rol;
import sipcoffee.models.Terreno;

public class BloqueCtrl {

	public BloqueCtrl() {
	}

	public Bloque create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Bloque bloque = new Bloque();
            
            bloque.setNombre(json.getString("nombre"));
            bloque.setArea(json.getInt("area"));
            bloque.setUbicacion(json.getString("ubicacion"));
            bloque.setFechaRegistro(new Date());
            bloque.setTerreno(new Terreno().find(json.getInt("terreno")));
            
            return bloque;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Bloque();
        }
    }
	
	public Bloque findId(int id){
		return new Bloque().find(id);
	}
	
	public String all(){
		return new Bloque().all();
	}
	
}
