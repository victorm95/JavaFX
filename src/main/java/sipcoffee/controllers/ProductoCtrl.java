package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Producto;

public class ProductoCtrl {

    public ProductoCtrl() {
    }

    public Producto create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Producto producto = new Producto();

            producto.setNombre(json.getString("nombre"));

            return producto;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Producto();
        }
    }

    public Producto findId(int id) {
        return new Producto().find(id);
    }

    public String all() {
        return new Producto().all();
    }

}
