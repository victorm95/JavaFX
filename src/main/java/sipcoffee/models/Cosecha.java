package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cosechas")
@NamedQueries({
        @NamedQuery(name = "all-Cosechas", query = "SELECT cosecha FROM Cosecha as cosecha"),
        @NamedQuery(name = "findById-Cosecha", query = "SELECT cosecha FROM Cosecha as cosecha WHERE cosecha.id=:id")
})

public class Cosecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "idCosecha")
    private int id;

    @JoinColumn(name = "idParcela", referencedColumnName = "idParcela", nullable = true)
    @OneToOne
    private Parcela parcela;

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
