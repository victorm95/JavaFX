package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Entradas")
@NamedQueries({
        @NamedQuery(name = "all-Entradas", query = "SELECT entrada FROM Entrada as entrada"),
        @NamedQuery(name = "findById-Entrada", query = "SELECT entrada FROM Entrada as entrada WHERE entrada.id=:id")
})

public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEntrada")
    private int id;

    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = true)
    @OneToOne
    private Producto producto;

    private int cantidad;
    private int valor;
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

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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
