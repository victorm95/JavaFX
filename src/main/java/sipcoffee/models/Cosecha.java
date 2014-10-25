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
@Table(name = "Cosechas")
@NamedQueries({
@NamedQuery(name = "all-Cosechas", query = "SELECT cosecha FROM Cosecha as cosecha"),
@NamedQuery(name = "findById-Cosecha", query = "SELECT siembra FROM Cosecha as cosecha WHERE cosecha.id=:id")
})

public class Cosecha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCosecha")
	private int id;

	@JoinColumn(name = "idParcela", referencedColumnName = "idParcela", nullable = true)
	@OneToOne
	private Parcela parcela;
	
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

	public Cosecha find(int id) {
		Conexion.init();
		return (Cosecha) Conexion.manager.createNamedQuery("findById-Cosecha")
				.setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Cosechas");

		for (Object cosecha : list) {
			jsonArray.put(((Cosecha) cosecha).toJson());
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
		json.put("usuario", new JSONObject(this.usuario.toJson()));
		json.put("cantidad", this.cantidad);
		json.put("fechaRegistro", this.fechaRegistro);
		return json.toString();
	}
}
