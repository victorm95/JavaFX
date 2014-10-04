package sipcoffee.controllers;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class CafetoCtrl {

	public CafetoCtrl() {
	}

	public Cafeto create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Cafeto cafeto = new Cafeto();
            
            parcela.setNombre(json.getString("nombre"));
            parcela.setCantidadAbono(json.getInt("cantidadAbono"));
            parcela.setCantidadPesticida(json.getInt("cantidadPesticida"));
            parcela.setDistanciaCafeto(json.getInt("distanciaCafeto"));
            parcela.setDistanciaSurco(json.getInt("distanciaSurco"));
            parcela.setTiempoSemillero(json.getInt("tiempoSemillero"));
            parcela.setTiempoAlmacigo(json.getInt("tiempoAlmacigo"));
            parcela.setTiempoGraneo(json.getInt("tiempoGraneo"));
            parcela.setTiempoSoca(json.getInt("tiempoSoca"));
            parcela.setPrimeraCosecha(json.getInt("primeraCosecha"));
            parcela.setProveedor(json.getString("proveedor"));
            parcela.setFechaRegistro(new Date());

            return cafeto;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Cafeto();
        }
    }
	
	public Cafeto findId(int id){
		return new Cafeto().find(id);
	}
	
	public String all(){
		return new Cafeto().all();
	}
	
}