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
@Table(name = "Nomina")
@NamedQueries({
@NamedQuery(name = "all-Nominas", query = "SELECT nomina FROM Nomina as nomina"),
@NamedQuery(name = "findById-Nomina", query = "SELECT nomina FROM Nomina as nomina WHERE nomina.id=:id")
})

public class Nomina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNomina")
	private int id;

	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = true)
	@OneToOne
	private Usuario usuario;

	@JoinColumn(name = "idProceso", referencedColumnName = "idProceso", nullable = true)
	@OneToOne
	private Proceso proceso;

	@Column(name = "horas")
	private int horas;
	
	@Column(name = "valor")
	private int valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Nomina find(int id) {
		Conexion.init();
		return (Nomina) Conexion.manager.createNamedQuery("findById-Nomina")
				.setParameter("id", id).getSingleResult();
	}
	
	public String all() {
		JSONArray jsonArray = new JSONArray();

		List<Object> list = Conexion.namedQuery("all-Nominas");

		for (Object nomina : list) {
			jsonArray.put(((Nomina) nomina).toJson());
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public int getHoras() {
		return this.horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
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
		json.put("usuario", new JSONObject(this.usuario.toJson()));
		json.put("proceso", new JSONObject(this.proceso.toJson()));
		json.put("horas", this.horas);
		json.put("valor", this.valor);
		json.put("fechaRegistro", this.fechaRegistro);
		return json.toString();
	}
}
