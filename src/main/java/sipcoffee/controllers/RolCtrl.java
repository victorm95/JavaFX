package sipcoffee.controllers;

import sipcoffee.models.Rol;

public class RolCtrl {
	
	public RolCtrl(){}
	
	public Rol createUser(String rol){
		return new Rol(rol);
	}

}
