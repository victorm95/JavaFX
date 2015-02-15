package sipcoffee.models;

import sipcoffee.App;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@NamedQueries({
        @NamedQuery(name = "all-Usuario", query = "SELECT user FROM Usuario as user"),
        @NamedQuery(name = "login-Usuario", query = "SELECT user FROM Usuario as user WHERE user.usuario=:usuario AND user.clave=:clave") })
public class Usuario {

    // Constructores
    public Usuario() {
        this.activo = true;
        this.fechaRegistro = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
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
    @Column(name = "expedicionDocumento", nullable = true)
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

	 public void setId(int id) {
		 this.id = id;
	 }

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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

	 public boolean getActivo() {
		 return this.activo;
	 }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = App.hash(clave);
    }

    public Date getExpedicionDocumento() {
        return this.expedicionDocumento;
    }

    public void setExpedicionDocumento(Date expedicionDocumento) {
        this.expedicionDocumento = expedicionDocumento;
    }

    public void setExpedicionDocumento(long expedicionDocumento) {
        this.expedicionDocumento = new Date(expedicionDocumento);
    }

    public Date getUltimaSesion() {
        return this.ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }


    @Override
    public String toString() {
        return "{'nombre': ':nombre', 'usuario': ':usuario', 'clave': ':clave', 'cedula', :cedula, 'direccion': ':direccion', 'telefono': :telefono, 'activo': :activo, 'fechaRegistro': ':fechaRegistro', 'expedicionDocumento': ':expedicionDocumento', 'ultimaSesion': ':ultimaSesion', 'rol': ':rol'}"
                .replace(":nombre", this.nombre)
                .replace(":usuario", this.usuario)
                .replace(":clave", this.clave)
                .replace(":cedula", String.valueOf(this.cedula))
                .replace(":direccion", this.direccion)
                .replace(":telefono", String.valueOf(this.telefono))
                .replace(":activo", String.valueOf(this.activo))
                .replace(":fechaRegistro", String.valueOf(this.fechaRegistro))
                .replace(":expedicionDocumento", String.valueOf(this.expedicionDocumento))
                .replace(":ultimaSesion", String.valueOf(this.ultimaSesion))
                .replace(":rol", String.valueOf(this.rol));
    }


}
