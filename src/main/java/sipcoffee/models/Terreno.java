package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Terreno")
@NamedQueries({
        @NamedQuery(name = "all-Terreno", query = "SELECT terreno FROM Terreno as terreno"),
        @NamedQuery(name = "findById-Terreno", query = "SELECT terreno FROM Terreno as terreno WHERE terreno.id = :id"),
        @NamedQuery(name = "findByName-Terreno", query = "SELECT terreno FROM Terreno as terreno WHERE terreno.nombre = :nombre")})
public class Terreno {

    public Terreno() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTerreno")
    private int id;

    @JoinColumn(name = "idMunicipio", referencedColumnName = "idMunicipio", nullable = false)
    @OneToOne
    private Municipio municipio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "area")
    private int area;

    @Column(name = "rutaImagen")
    private String rutaImagen;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

    public boolean save() {
        return Conexion.persist(this);
    }

    public Terreno find(int id) {
        Conexion.init();
        return (Terreno) Conexion.manager.createNamedQuery("findById-Terreno")
                .setParameter("id", id).getSingleResult();
    }

    public Terreno find(String nombre) {
        Conexion.init();
        return (Terreno) Conexion.manager.createNamedQuery("findByName-Terreno")
                .setParameter("nombre", nombre).getSingleResult();
    }

    public String all() {
        JSONArray jsonArray = new JSONArray();

        List<Object> list = Conexion.namedQuery("all-Terreno");

        for (Object terreno : list) {
            jsonArray.put(((Terreno) terreno).toJson());
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

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getRutaImagen() {
        return this.rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
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
        json.put("municipio", new JSONObject(this.municipio.toJson()));
        json.put("nombre", this.nombre);
        json.put("direccion", this.direccion);
        json.put("area", this.area);
        //json.put("rutaImagen", this.rutaImagen);
        return json.toString();
    }
}
