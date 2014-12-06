package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Salidas")
@NamedQueries({
        @NamedQuery(name = "all-Salidas", query = "SELECT salida FROM Salida as salida"),
        @NamedQuery(name = "findById-Salida", query = "SELECT salida FROM Salida as salida WHERE salida.id=:id")
})

public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSalida")
    private int id;

    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = true)
    @OneToOne
    private Producto producto;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = true)
    @OneToOne
    private Usuario usuario;

    private int cantidad;
    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;


	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

    public int geId() {
        return this.id;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fecha) {
        this.fechaRegistro = fecha;
    }

}
