package sipcoffee.controllers;

import sipcoffee.models.Municipio;

public class MunicipioCtrl {

    public MunicipioCtrl() {
    }

    public Municipio find(int id) {
        return new Municipio().find(id);
    }

    public String all() {
        return new Municipio().all();
    }

}
