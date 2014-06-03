package sipcoffee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name = "Roles")
public class Rol {
	
	// Constructores
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

	/*-------------------------------------- Acciones DB ---------------------------------------------*/
	
	public Rol create(){
		return new Rol();
	}
	
	public Rol create(String nombre){
		return new Rol(nombre);
	}
	
	public boolean save(){
		return Conexion.persist(this);
	}
	
	public String all(){
		
		return "";
	}
	
	public boolean delete(){
		return Conexion.remove(this);
	}
	
	/*-------------------------------------- Setter / Getters ---------------------------------------------*/
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getId(){
		return this.id;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/
	
	public String toJson(){
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("nombre", this.nombre);
		return json.toString();
	}
	
	@Override
	public String toString() {
		return "[id=" + this.id + ", nombre=" + this.nombre + "]";
	}

}
