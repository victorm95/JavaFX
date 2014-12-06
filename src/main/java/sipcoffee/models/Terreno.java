package sipcoffee.models;

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

    private String nombre;
    private String direccion;
    private int area;
    private String rutaImagen;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

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

}
