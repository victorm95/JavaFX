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
@Table(name = "Entradas")
@NamedQueries({
@NamedQuery(name = "all-Entradas", query = "SELECT entrada FROM Entrada as entrada"),
@NamedQuery(name = "findById-Entrada", query = "SELECT entrada FROM Entrada as entrada WHERE entrada.id=:id")
})

public class Entrada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEntrada")
	private int id;

	@JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = true)
	@OneToOne
	private Producto producto;

	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "valor")
	private int valor;

	@Column(name = "cometario")
	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Entrada find(int id) {
		Conexion.init();
		return (Entrada) Conexion.manager.createNamedQuery("findById-Entrada")
				.setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Entradas");

		for (Object entrada : list) {
			jsonArray.put(((Entrada) entrada).toJson());
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

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
		json.put("producto", new JSONObject(this.producto.toJson()));
		json.put("cantidad", this.cantidad);
		json.put("valor", this.valor);
		json.put("comentario", this.comentario);
		json.put("fechaRegistro", this.fechaRegistro);
		return json.toString();
	}
}
