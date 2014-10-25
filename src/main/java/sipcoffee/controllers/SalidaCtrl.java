package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Salida;
import sipcoffee.models.Producto;

public class SalidaCtrl {

	public SalidaCtrl() {
	}

	public Salida create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Salida salida = new Salida();
            
            salida.setCantidad(json.getInt("cantidad"));
            salida.setFechaRegistro(new Date());
            salida.setProducto(new Producto().find(json.getInt("producto")));
            
            return salida;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Salida();
        }
    }
	
	public Salida findId(int id){
		return new Salida().find(id);
	}
	
	public String all(){
		return new Salida().all();
	}
	
}
