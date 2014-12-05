package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;
import sipcoffee.App;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@NamedQueries({
        @NamedQuery(name = "all-Usuarios", query = "SELECT user FROM Usuario as user"),
        @NamedQuery(name = "findById-Usuario", query = "SELECT user FROM Usuario as user WHERE user.id=:id"),
        @NamedQuery(name = "login-Usuario", query = "SELECT user FROM Usuario as user WHERE user.usuario=:usuario AND user.clave=:clave")})
public class Usuario {

    // Constructores
    public Usuario() {
        this.activo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "usuario", length = 30, nullable = false, unique = true)
    private String usuario;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "cedula", nullable = false)
    private long cedula;

    private String direccion;
    private long telefono;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Temporal(TemporalType.DATE)
    @Column(name = "expedicionDocumento", nullable = false)
    private Date expedicionDocumento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaSesion;

    @JoinColumn(name = "idRol", referencedColumnName = "idRol", nullable = false)
    @OneToOne
    private Rol rol;

    @JoinColumn(name = "idMunicipio", referencedColumnName = "idMunicipio", nullable = true)
    @OneToOne
    private Municipio municipio;



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

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = App.hash(clave);
    }

    public Date getExpedicionDocumento() {
        return expedicionDocumento;
    }

    public void setExpedicionDocumento(Date expedicionDocumento) {
        this.expedicionDocumento = expedicionDocumento;
    }

    public void setExpedicionDocumento(long expedicionDocumento) {
        this.expedicionDocumento = new Date(expedicionDocumento);
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public boolean isEmpty() {
        Conexion.init();
        return Conexion.namedQuery("all-Usuarios").isEmpty();
    }

}
