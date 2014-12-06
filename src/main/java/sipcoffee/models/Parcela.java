package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Parcelas")
@NamedQueries({
        @NamedQuery(name = "all-Parcelas", query = "SELECT parcela FROM Parcela as parcela"),
        @NamedQuery(name = "findById-Parcela", query = "SELECT parcela FROM Parcela as parcela WHERE parcela.id=:id")})
public class Parcela {

    // Constructores
    public Parcela() {
        this.activo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParcela")
    private int id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "area", nullable = false)
    private int area;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @JoinColumn(name = "idBloque", referencedColumnName = "idBloque", nullable = false)
    @OneToOne
    private Bloque bloque;

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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date date) {
        this.fechaRegistro = date;
    }

}
