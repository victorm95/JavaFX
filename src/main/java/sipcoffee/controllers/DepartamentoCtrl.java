package sipcoffee.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import sipcoffee.models.Departamento;
import sipcoffee.models.Rol;

public class DepartamentoCtrl {
	
	public DepartamentoCtrl(){}
	
	public Departamento create(Object obj){
		JSONObject json;
		
		try {
			json = new JSONObject(obj.toString());
			return new Departamento(json.getString("nombre"));
		} catch (JSONException e) {
			System.out.println(e.toString());
			return new Departamento(obj.toString());
		}
	}
	
	public Departamento find(int id){
		return new Departamento().find(id);
	}
	
	public String all(){
		return new Departamento().all();
	}

}
