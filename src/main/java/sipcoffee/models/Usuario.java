package sipcoffee.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
		this.activo = true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id;

	@Column(name = "nombre", length = 30)
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

	@JoinColumn(name = "rol", referencedColumnName = "id_rol", nullable = false)
	@OneToOne
	private Rol rol;

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

	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String toJson() {
		JSONObject json = new JSONObject();

		json.put("id", this.id);
		json.put("nombre", this.nombre);
		json.put("cedula", this.cedula);
		json.put("telefono", this.telefono);
		json.put("rol", this.rol.getNombre());
		json.put("activo", this.activo);
		json.put("fecha_registro", this.fechaRegistro);

		return json.toString();
	}

}
