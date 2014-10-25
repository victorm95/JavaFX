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
@Table(name = "Procesos")
@NamedQueries({
		@NamedQuery(name = "all-Procesos", query = "SELECT proceso FROM Proceso as proceso"),
		@NamedQuery(name = "findById-Proceso", query = "SELECT proceso FROM Proceso as proceso WHERE proceso.id = :id"),
		@NamedQuery(name = "findByName-Proceso", query = "SELECT proceso FROM Proceso as proceso WHERE proceso.nombre = :nombre") })
public class Proceso {

	// Constructores
	public Proceso() {
	}

	public Proceso(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProceso", nullable = false)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Proceso find(int id) {
		Conexion.init();
		return (Proceso) Conexion.manager.createNamedQuery("findById-Proceso")
				.setParameter("id", id).getSingleResult();
	}
	
	public Proceso find(String nombre) {
		Conexion.init();
		return (Proceso) Conexion.manager.createNamedQuery("findByName-Proceso")
				.setParameter("nombre", nombre).getSingleResult();
	}

	public String all() {
		JSONArray jsonArray = new JSONArray();

        List<Proceso> listProceso = Conexion.manager.createNamedQuery(
                "all-Procesos", Proceso.class).getResultList();

        for (Proceso proceso : listProceso) {
            jsonArray.put(proceso.toJson());
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
