package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Cosecha;
import sipcoffee.models.Parcela;
import sipcoffee.models.Usuario;

public class CosechaCtrl {

	public CosechaCtrl() {
	}

	public Cosecha create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Cosecha cosecha = new Cosecha();
            
            cosecha.setCantidad(json.getInt("cantidad"));
            cosecha.setFechaRegistro(new Date());
            cosecha.setParcela(new Parcela().find(json.getInt("parcela")));
            cosecha.setUsuario(new Usuario().find(json.getInt("usuario")));
            
            return cosecha;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Cosecha();
        }
    }
	
	public Cosecha findId(int id){
		return new Cosecha().find(id);
	}
	
	public String all(){
		return new Cosecha().all();
	}
	
}
