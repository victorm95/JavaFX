package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import sipcoffee.models.Nomina;
import sipcoffee.models.Proceso;
import sipcoffee.models.Usuario;

import java.util.Date;

public class NominaCtrl {

    public NominaCtrl() {
    }

    public Nomina create(Object obj) {
        JSONObject json;
        try {
            json = new JSONObject(obj.toString());
            Nomina nomina = new Nomina();

            nomina.setHoras(json.getInt("horas"));
            nomina.setValor(json.getInt("valor"));
            nomina.setFechaRegistro(new Date());
            nomina.setProceso(new Proceso().find(json.getInt("proceso")));
            nomina.setUsuario(new Usuario().find(json.getInt("usuario")));

            return nomina;
        } catch (JSONException e) {
            System.out.println(e.toString());
            return new Nomina();
        }
    }

    public Nomina findId(int id) {
        return new Nomina().find(id);
    }

    public String all() {
        return new Nomina().all();
    }

}
