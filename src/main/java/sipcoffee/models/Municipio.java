package sipcoffee.models;

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

    private String nombre;

    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento", nullable = false)
    @OneToOne
    private Departamento departamento;


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

}
