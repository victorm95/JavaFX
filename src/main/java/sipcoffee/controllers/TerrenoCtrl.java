package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Municipio;
import sipcoffee.models.Terreno;

import java.util.Date;

public class TerrenoCtrl {

    public TerrenoCtrl() {
    }


    public Terreno create(Object nombre) {
        JSONObject json;
        Terreno terreno;
        try {
            json = new JSONObject(nombre.toString());
            terreno = new Terreno();

            terreno.setNombre(json.getString("nombre"));
            terreno.setDireccion(json.getString("direccion"));
            terreno.setArea(json.getInt("area"));
            terreno.setMunicipio(new Municipio().find(json.getInt("municipio")));
            terreno.setFechaRegistro(new Date());
            //terreno.setRutaImagen(json.getString("imagen"));

            return terreno;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Terreno();
        }
    }

    public Terreno findById(int id) {
        return new Terreno().find(id);
    }

    public String all() {
        return new Terreno().all();
    }


}
