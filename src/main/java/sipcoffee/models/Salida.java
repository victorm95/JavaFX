package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;

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

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "cometario")
    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

    public boolean save() {
        return Conexion.persist(this);
    }

    public Salida find(int id) {
        Conexion.init();
        return (Salida) Conexion.manager.createNamedQuery("findById-Salida")
                .setParameter("id", id).getSingleResult();
    }

    public String all() {
        JSONArray jsonArray = new JSONArray();

        List<Object> list = Conexion.namedQuery("all-Salidas");

        for (Object salida : list) {
            jsonArray.put(((Salida) salida).toJson());
        }

        return jsonArray.toString();
    }

    public boolean delete() {
        return Conexion.delete(this);
    }

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

	/*-------------------------------------- Conversiones ---------------------------------------------*/

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("producto", new JSONObject(this.producto.toJson()));
        json.put("usuario", new JSONObject(this.usuario.toJson()));
        json.put("cantidad", this.cantidad);
        json.put("comentatio", this.comentario);
        json.put("fechaRegistro", this.fechaRegistro);
        return json.toString();
    }
}
