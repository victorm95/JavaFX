package sipcoffee.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "Municipio")
@NamedQueries({
		@NamedQuery(name = "all-Municipio", query = "SELECT Municipio FROM Municipio as Municipio"),
		@NamedQuery(name = "findById-Municipio", query = "SELECT Municipio FROM Municipio as Municipio WHERE Municipio.id = :id") })
public class Municipio {

	// Constructores
	public Municipio() {
	}

	public Municipio(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMunicipio", nullable = false)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento", nullable = false)
	@OneToOne
	private Departamento departamento;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Municipio find(int id) {
		Conexion.init();
		return (Municipio) Conexion.manager
				.createNamedQuery("findById-Municipio").setParameter("id", id)
				.getSingleResult();
	}

	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Municipio");

		for (Object municipio : list) {
			jsonArray.put(((Municipio) municipio).toJson());
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

	public Departamento getDepartamento() {
		return this.departamento;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("idMunicipio", this.id);
		json.put("nombre", this.nombre);
		json.put("departamento", this.departamento);
		return json.toString();
	}

}
