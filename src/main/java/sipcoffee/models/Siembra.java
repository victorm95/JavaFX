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
@Table(name = "Siembras")
@NamedQueries({
@NamedQuery(name = "all-Siembras", query = "SELECT siembra FROM Siembra as siembra"),
@NamedQuery(name = "findById-Siembra", query = "SELECT siembra FROM Siembra as siembra WHERE siembra.id=:id")
})

public class Siembra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSiembra")
	private int id;

	@JoinColumn(name = "idParcela", referencedColumnName = "idParcela", nullable = true)
	@OneToOne
	private Parcela parcela;

	@JoinColumn(name = "idCafeto", referencedColumnName = "idCafeto", nullable = true)
	@OneToOne
	private Cafeto cafeto;
	
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = true)
	@OneToOne
	private Usuario usuario;
	
	@Column(name = "cantidad")
	private int cantidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Siembra find(int id) {
		Conexion.init();
		return (Siembra) Conexion.manager.createNamedQuery("findById-Siembra")
				.setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Siembras");

		for (Object siembra : list) {
			jsonArray.put(((Siembra) siembra).toJson());
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

	public Parcela getParcela() {
		return this.parcela;
	}

	public void setParsela(Parsela parcela) {
		this.parsela = parsela;
	}

	public Cafeto getCafeto() {
		return this.cafeto;
	}

	public void setCafeto(Cafeto cafeto) {
		this.cafeto = cafeto;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		json.put("parcela", new JSONObject(this.parcela.toJson()));
		json.put("cafeto", new JSONObject(this.cafeto.toJson()));
		json.put("usuario", new JSONObject(this.usuario.toJson()));
		json.put("cantidad", this.cantidad);
		json.put("fechaRegistro", this.fechaRegistro);
		return json.toString();
	}
}
