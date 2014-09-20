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
import sipcoffee.App;
import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name = "Cafetos")
@NamedQueries({
		@NamedQuery(name = "all-Cafeto", query = "SELECT cafeto FROM Cafeto as cafeto"),
		@NamedQuery(name = "findById-Cafeto", query = "SELECT cafeto FROM Cafeto as user WHERE cafeto.id=:id")
public class Usuario {

	// Constructores
	public Cafeto() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCafeto")
	private int id;

	@Column(name = "nombre", length = 30)
	private String nombre;

	@Column(name = "cantidadAbono", nullable = false)
	private int cantidadAbono;

	@Column(name = "cantidadPesticida", nullable = false)
	private int cantidadPesticida;

	@Column(name = "distanciaSurco", nullable = false)
	private int distanciaSurco;

	@Column(name = "distanciaCafeto", nullable = false)
	private int distanciaCafeto;

	@Column(name = "tiempoSemillero", nullable = false)
	private int tiempoSemillero;

	@Column(name = "tiempoAlmacigo", nullable = false)
	private int tiempoAlmacigo;

	@Column(name = "tiempoGraneo", nullable = false)
	private int tiempoGraneo;

	@Column(name = "tiempoSoca", nullable = false)
	private int tiempoSoca;

	@Column(name = "primeraCosecha", nullable = false)
	private int primeraCosecha;

	@Column(name = "proveedor", length = 30)
	private String proveedor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaRegistro")
	private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

	public boolean save() {
		return Conexion.persist(this);
	}

	public Cafeto find(int id) {
		Conexion.init();
		return (Cafeto) Conexion.manager.createNamedQuery("findById-Cafeto")
				.setParameter("id", id).getSingleResult();
	}

	public String all() {
		JSONArray jsonArray = new JSONArray();
		Conexion.init();
		List<Cafeto> listCafeto = Conexion.manager.createNamedQuery(
				"all-Cafeto", Cafeto.class).getResultList();

		for (Cafeto cafeto : listCafeto) {
			jsonArray.put(cafeto.toJson());
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

	public int getCantidadAbono() {
		return cantidadAbono;
	}

	public void setCantidadAbono(int cantidadAbono) {
		this.cantidadAbono = cantidadAbono;
	}

	public int getCantidadPesticida() {
		return cantidadPesticida;
	}

	public void setCantidadPesticida(int cantidadPesticida) {
		this.cantidadPesticida = cantidadPesticida;
	}

	public int getDistanciaCafeto() {
		return distanciaCafeto;
	}

	public void setDistanciaCafeto(int distanciaCafeto) {
		this.distanciaCafeto = distanciaCafeto;
	}

	public int getDistanciaSurco() {
		return distanciaSurco;
	}

	public void setDistanciaSurco(int distanciaSurco) {
		this.distanciaSurco = distanciaSurco;
	}

	public int getTiempoSemillero() {
		return tiempoSemillero;
	}

	public void setTiempoSemillero(int tiempoSemillero) {
		this.tiempoSemillero = tiempoSemillero;
	}

	public int getTiempoAlmacigo() {
		return tiempoAlmacigo;
	}

	public void setTiempoAlmacigo(int tiempoAlmacigo) {
		this.tiempoAlmacigo = tiempoAlmacigo;
	}
	
	public int getTiempoGraneo() {
		return tiempoGraneo;
	}
	
	public void setTiempoGraneo(int tiempoGraneo) {
		this.tiempoGraneo = tiempoGraneo;
	}

	public int getTiempoSoca() {
		return tiempoSoca;
	}

	public void setTiempoSoca(int tiempoSoca) {
		this.tiempoSoca = tiempoSoca;
	}

	public int getPrimeraCosecha() {
		return primeraCosecha;
	}

	public void setPrimeraCosecha(int primeraCosecha) {
		this.primeraCosecha = primeraCosecha;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro() {
		this.fechaRegistro = new Date();
	}


	/*-------------------------------------- Conversiones ---------------------------------------------*/

	public String toJson() {
		JSONObject json = new JSONObject();

		json.put("id", this.id);
		json.put("nombre", this.nombre);
		json.put("cantidadAbono", this.cantidadAbono);
		json.put("cantidadPesticida", this.cantidadPesticida);
		json.put("distanciaCafeto", this.distanciaCafeto);
		json.put("distanciaSurco", this.distanciaSurco);
		json.put("tiempoSemillero", this.tiempoSemillero);
		json.put("tiempoAlmacigo", this.tiempoAlmacigo);
		json.put("tiempoGraneo", this.tiempoGraneo);
		json.put("tiempoSoca", this.tiempoSoca);
		json.put("primeraCosecha", this.primeraCosecha);
		json.put("proveedor", this.proveedor);
		json.put("fechaRegistro", this.fechaRegistro);

		return json.toString();
	}

}
