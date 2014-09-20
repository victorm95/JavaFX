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
@Table(name = "Productos")
@NamedQueries({
		@NamedQuery(name = "all-Producto", query = "SELECT producto FROM Producto as producto"),
		@NamedQuery(name = "findById-Producto", query = "SELECT producto FROM Producto as producto WHERE producto.id = :id"),
		@NamedQuery(name = "findByName-Producto", query = "SELECT producto FROM Producto as producto WHERE producto.nombre = :nombre") })
public class Rol {

	// Constructores
	public Producto() {
	}

	public Producto(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Producto find(int id) {
		Conexion.init();
		return (Producto) Conexion.manager.createNamedQuery("findById-Producto")
				.setParameter("id", id).getSingleResult();
	}
	
	public Producto find(String nombre) {
		Conexion.init();
		return (Producto) Conexion.manager.createNamedQuery("findByName-Producto")
				.setParameter("nombre", nombre).getSingleResult();
	}

	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Producto");

		for (Object producto : list) {
			jsonArray.put(((Producto) producto).toJson());
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
