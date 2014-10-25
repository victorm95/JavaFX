package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Siembra;
import sipcoffee.models.Parcela;
import sipcoffee.models.Cafeto;
import sipcoffee.models.Usuario;

public class SiembraCtrl {

	public SiembraCtrl() {
	}

	public Siembra create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Siembra siembra = new Siembra();
            
            siembra.setCantidad(json.getInt("cantidad"));
            siembra.setFechaRegistro(new Date());
            siembra.setParcela(new Parcela().find(json.getInt("parcela")));
            siembra.setCafeto(new Cafeto().find(json.getInt("cafeto")));
            siembra.setUsuario(new Usuario().find(json.getInt("usuario")));
            
            return siembra;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Entrada();
        }
    }
	
	public Siembra findId(int id){
		return new Siembra().find(id);
	}
	
	public String all(){
		return new Siembra().all();
	}
	
}
