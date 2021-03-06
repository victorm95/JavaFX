package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Productos")
@NamedQueries({
        @NamedQuery(name = "all-Productos", query = "SELECT producto FROM Producto as producto"),
        @NamedQuery(name = "findById-Producto", query = "SELECT producto FROM Producto as producto WHERE producto.id = :id"),
        @NamedQuery(name = "findByName-Producto", query = "SELECT producto FROM Producto as producto WHERE producto.nombre = :nombre")})
public class Producto {

    // Constructores
    public Producto() {
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

    public boolean save() {
        return Conexion.persist(this);
    }

    public Producto find(int id) {
        Conexion.init();
        return (Producto) Conexion.manager.createNamedQuery("findById-Producto")
                .setParameter("id", id).getSingleResult();
    }

    public Producto find(String nombre) {
        Conexion.init();
        return (Producto) Conexion.manager.createNamedQuery("findByName-Producto")
                .setParameter("nombre", nombre).getSingleResult();
    }

    public String all() {
        JSONArray jsonArray = new JSONArray();

        List<Object> list = Conexion.namedQuery("all-Productos");

        for (Object producto : list) {
            jsonArray.put(((Producto) producto).toJson());
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
