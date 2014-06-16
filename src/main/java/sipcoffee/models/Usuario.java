package sipcoffee.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "Usuarios")
@NamedQuery(name = "all-Usuarios", query = "SELECT user FROM Usuario as user")
public class Usuario {

	// Constructores
	public Usuario() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id;

	@Column(name = "id_rol")
	private int idRol;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cedula")
	private long cedula;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private long telefono;

	@Column(name = "activo")
	private boolean activo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	
	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean save() {
		return Conexion.persist(this);
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

	public int getId() {
		return this.id;
	}

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
		JSONObject json = new JSONObject();

		return json.toString();
	}

}
