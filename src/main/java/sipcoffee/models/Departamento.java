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
@Table(name = "Departamento")
@NamedQueries({
	@NamedQuery(name = "all-Departamento", query = "SELECT Departamento FROM Departamento as Departamento"),
	@NamedQuery(name = "findById-Departamento", query="SELECT Departamento FROM Departamento as Departamento WHERE Departamento.id = :id")
})
public class Departamento {

	// Constructores
	public Departamento() {
	}

	public Departamento(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDepartamento", nullable = false)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Departamento find(int id){
		Conexion.init();
		return (Departamento) Conexion.manager.createNamedQuery("findById-Departamento").setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Departamento");

		for (Object departamento : list) {
			jsonArray.put(((Departamento) departamento).toJson());
		}

		return jsonArray.toString();
	}

	public boolean delete() {
		return Conexion.delete(this);
	}

	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("nombre", this.nombre);
		return json.toString();
	}

}
