package sipcoffee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Rol {
	
	public Rol(){}
	public Rol(String nombre){
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getId(){
		return this.id;
	}

	@Override
	public String toString() {
		return "[id=" + this.id + ", nombre=" + this.nombre + "]";
	}

}
