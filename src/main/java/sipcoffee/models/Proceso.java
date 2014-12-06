package sipcoffee.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Procesos")
@NamedQueries({
        @NamedQuery(name = "all-Procesos", query = "SELECT proceso FROM Proceso as proceso"),
        @NamedQuery(name = "findById-Proceso", query = "SELECT proceso FROM Proceso as proceso WHERE proceso.id = :id"),
        @NamedQuery(name = "findByName-Proceso", query = "SELECT proceso FROM Proceso as proceso WHERE proceso.nombre = :nombre")})
public class Proceso {

    // Constructores
    public Proceso() {
    }

    public Proceso(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProceso", nullable = false)
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
