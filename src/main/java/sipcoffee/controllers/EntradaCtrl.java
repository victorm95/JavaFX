package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Entrada;
import sipcoffee.models.Producto;

import java.util.Date;

public class EntradaCtrl {

    public EntradaCtrl() {
    }

    public Entrada create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Entrada entrada = new Entrada();

            entrada.setCantidad(json.getInt("cantidad"));
            entrada.setValor(json.getInt("valor"));
            entrada.setComentario(json.getString("comentario"));
            entrada.setFechaRegistro(new Date());
            entrada.setProducto(new Producto().find(json.getInt("producto")));

            return entrada;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Entrada();
        }
    }

    public Entrada findId(int id) {
        return new Entrada().find(id);
    }

    public String all() {
        return new Entrada().all();
    }

}
