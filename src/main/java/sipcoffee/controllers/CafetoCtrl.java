package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Cafeto;

public class CafetoCtrl {

    public CafetoCtrl() {
    }

    public Cafeto create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Cafeto cafeto = new Cafeto();

            cafeto.setNombre(json.getString("nombre"));
            cafeto.setCantidadAbono(json.getInt("cantidadAbono"));
            cafeto.setCantidadPesticida(json.getInt("cantidadPesticida"));
            cafeto.setDistanciaCafeto(json.getInt("distanciaCafeto"));
            cafeto.setDistanciaSurco(json.getInt("distanciaSurco"));
            cafeto.setTiempoSemillero(json.getInt("tiempoSemillero"));
            cafeto.setTiempoAlmacigo(json.getInt("tiempoAlmacigo"));
            cafeto.setTiempoGraneo(json.getInt("tiempoGraneo"));
            cafeto.setTiempoSoca(json.getInt("tiempoSoca"));
            cafeto.setPrimeraCosecha(json.getInt("primeraCosecha"));
            cafeto.setProveedor(json.getString("proveedor"));
            cafeto.setFechaRegistro();

            return cafeto;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Cafeto();
        }
    }

    public Cafeto findId(int id) {
        return new Cafeto().find(id);
    }

    public String all() {
        return new Cafeto().all();
    }

}