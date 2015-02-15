package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bloque")
@NamedQueries({
        @NamedQuery(name = "all-Bloques", query = "SELECT bloque FROM Bloque as bloque"),
        @NamedQuery(name = "findByName-Bloque", query = "SELECT bloque FROM Bloque as bloque WHERE bloque.nombre=:nombre"),
        @NamedQuery(name = "findById-Bloque", query = "SELECT bloque FROM Bloque as bloque WHERE bloque.id=:id")
})

public class Bloque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBloque")
    private int id;

    @JoinColumn(name = "idTerreno", referencedColumnName = "idTerreno", nullable = true)
    @OneToOne
    private Terreno terreno;

    private String nombre;
    private int area;
    private String ubicacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

    public int getId() {
        return this.id;
    }

    public Terreno getTerreno() {
        return this.terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getArea() {
        return this.area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fecha) {
        this.fechaRegistro = fecha;
    }

}
