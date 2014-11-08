package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Proceso;

public class ProcesoCtrl {

    public ProcesoCtrl() {
    }

    public Proceso create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Proceso proceso = new Proceso();

            proceso.setNombre(json.getString("nombre"));

            return proceso;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Proceso();
        }
    }

    public Proceso findId(int id) {
        return new Proceso().find(id);
    }

    public String all() {
        return new Proceso().all();
    }

}
