package sipcoffee.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Departamentos")
@NamedQueries({
        @NamedQuery(name = "all-Departamento", query = "SELECT Departamento FROM Departamento as Departamento"),
        @NamedQuery(name = "findById-Departamento", query = "SELECT Departamento FROM Departamento as Departamento WHERE Departamento.id = :id")
})
public class Departamento {

    // Constructores
    public Departamento() {
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento", nullable = false)
    private int id;

    private String nombre;

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

}
