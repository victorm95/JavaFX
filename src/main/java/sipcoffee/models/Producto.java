package sipcoffee.models;

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

    private String nombre;

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

}
