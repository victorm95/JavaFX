package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Siembras")
@NamedQueries({
        @NamedQuery(name = "all-Siembras", query = "SELECT siembra FROM Siembra as siembra"),
        @NamedQuery(name = "findById-Siembra", query = "SELECT siembra FROM Siembra as siembra WHERE siembra.id=:id")
})

public class Siembra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSiembra")
    private int id;

    @JoinColumn(name = "idParcela", referencedColumnName = "idParcela", nullable = true)
    @OneToOne
    private Parcela parcela;

    @JoinColumn(name = "idCafeto", referencedColumnName = "idCafeto", nullable = true)
    @OneToOne
    private Cafeto cafeto;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = true)
    @OneToOne
    private Usuario usuario;

    private int cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

    public int geId() {
        return this.id;
    }

    public Parcela getParcela() {
        return this.parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public Cafeto getCafeto() {
        return this.cafeto;
    }

    public void setCafeto(Cafeto cafeto) {
        this.cafeto = cafeto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fecha) {
        this.fechaRegistro = fecha;
    }

}
