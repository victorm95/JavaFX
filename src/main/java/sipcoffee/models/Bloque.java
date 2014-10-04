package sipcoffee.models;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "Bloque")
@NamedQueries({
@NamedQuery(name = "all-Bloque", query = "SELECT bloque FROM Bloque as bloque"),
@NamedQuery(name = "findByName-Bloque", query = "SELECT bloque FROM Bloque as bloque WHERE bloque.nombre=:nombre"),
@NamedQuery(name = "findById-Bloque", query = "SELECT bloque FROM Bloque as bloque WHERE bloque.id=:id")
})

public class Bloque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBloque")
	private int id;

	@JoinColumn(name = "idTerreno", referencedColumnName = "idTerreno", nullable = true)
	@OneToOne
	private Terreno terreno;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "area")
	private int area;

	@Column(name = "ubicacion")
	private String ubicacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Bloque find(int id) {
		Conexion.init();
		return (Bloque) Conexion.manager.createNamedQuery("findById-Bloque")
				.setParameter("id", id).getSingleResult();
	}
	
	public Bloque find(String nombre) {
		Conexion.init();
		return (Bloque) Conexion.manager.createNamedQuery("findByName-Bloque")
				.setParameter("nombre", nombre).getSingleResult();
	}

	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Bloque");

		for (Object bloque : list) {
			jsonArray.put(((Bloque) bloque).toJson());
		}

		return jsonArray.toString();
	}

	public boolean delete() {
		return Conexion.delete(this);
	}
	
	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

	public int geId() {
		return this.id;
	}

	public Terreno getTerreno() {
		return this.terreno;
	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getArea() {
		return this.area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fecha) {
		this.fechaRegistro = fecha;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);	
		json.put("terreno", new JSONObject(this.terreno.toJson()));
		json.put("nombre", this.nombre);
		json.put("area", this.area);
		json.put("ubicacion", this.ubicacion);
		return json.toString();
	}
}
