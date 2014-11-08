package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Municipios")
@NamedQueries({
        @NamedQuery(name = "all-Municipio", query = "SELECT Municipio FROM Municipio as Municipio"),
        @NamedQuery(name = "findById-Municipio", query = "SELECT Municipio FROM Municipio as Municipio WHERE Municipio.id = :id")})
public class Municipio {

    // Constructores
    public Municipio() {
    }

    public Municipio(String nombre, Departamento departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMunicipio", nullable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento", nullable = false)
    @OneToOne
    private Departamento departamento;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

    public boolean save() {
        return Conexion.persist(this);
    }

    public Municipio find(int id) {
        Conexion.init();
        return (Municipio) Conexion.manager
                .createNamedQuery("findById-Municipio").setParameter("id", id)
                .getSingleResult();
    }

    public String all() {
        JSONArray jsonArray = new JSONArray();

        List<Object> list = Conexion.namedQuery("all-Municipio");

        for (Object municipio : list) {
            jsonArray.put(((Municipio) municipio).toJson());
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
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

	/*-------------------------------------- Conversiones ---------------------------------------------*/

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("nombre", this.nombre);
        json.put("departamento", new JSONObject(this.departamento.toJson()));
        return json.toString();
    }

}
