package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Cafeto;
import sipcoffee.models.Parcela;
import sipcoffee.models.Siembra;
import sipcoffee.models.Usuario;

import java.util.Date;

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
            return new Siembra();
        }
    }

    public Siembra findId(int id) {
        return new Siembra().find(id);
    }

    public String all() {
        return new Siembra().all();
    }

}
