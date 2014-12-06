package sipcoffee.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Nomina")
@NamedQueries({
        @NamedQuery(name = "all-Nominas", query = "SELECT nomina FROM Nomina as nomina"),
        @NamedQuery(name = "findById-Nomina", query = "SELECT nomina FROM Nomina as nomina WHERE nomina.id=:id")
})

public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNomina")
    private int id;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = true)
    @OneToOne
    private Usuario usuario;

    @JoinColumn(name = "idProceso", referencedColumnName = "idProceso", nullable = true)
    @OneToOne
    private Proceso proceso;

    private int horas;
    private int valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

	/*-------------------------------------- Setter / Getters ---------------------------------------------*/

    public int geId() {
        return this.id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proceso getProceso() {
        return this.proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public int getHoras() {
        return this.horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fecha) {
        this.fechaRegistro = fecha;
    }

}
