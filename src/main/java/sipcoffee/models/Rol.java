package sipcoffee.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "Roles")
@NamedQueries({
	@NamedQuery(name = "all-Rol", query = "SELECT rol FROM Rol as rol"),
	@NamedQuery(name = "findById-Rol", query="SELECT rol FROM Rol as rol WHERE rol.id = :id")
})
public class Rol {

	// Constructores
	public Rol() {
	}

	public Rol(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Rol find(int id){
		Conexion.init();
		return (Rol) Conexion.manager.createNamedQuery("findById-Rol").setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Rol");

		for (Object rol : list) {
			jsonArray.put(((Rol) rol).toJson());
		}

		return jsonArray.toString();
	}

	public boolean delete() {
		return Conexion.delete(this);
	}

	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getId() {
		return this.id;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
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
