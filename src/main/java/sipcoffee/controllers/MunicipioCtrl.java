package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Municipio;
import sipcoffee.models.Rol;

public class MunicipioCtrl {
	
	public MunicipioCtrl(){}
	
	public Municipio find(int id){
		return new Municipio().find(id);
	}
	
	public String all(){
		return new Municipio().all();
	}

}
