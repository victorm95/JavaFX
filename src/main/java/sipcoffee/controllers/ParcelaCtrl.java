package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Bloque;
import sipcoffee.models.Parcela;

public class ParcelaCtrl {

	public ParcelaCtrl() {
	}

	public Parcela create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Parcela parcela = new Parcela();
            
            parcela.setNombre(json.getString("nombre"));
            parcela.setArea(json.getInt("area"));
            parcela.setFechaRegistro(new Date());
            parcela.setBloque(new Bloque().find(json.getInt("bloque")) );
            parcela.setActivo(json.getBoolean("activo"));
         
            return parcela;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Parcela();
        }
    }
	
	public Parcela findId(int id){
		return new Parcela().find(id);
	}
	
	public String all(){
		return new Parcela().all();
	}
	
}
