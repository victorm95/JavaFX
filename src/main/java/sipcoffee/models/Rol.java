package sipcoffee.models;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@NamedQuery(name = "all-Rol", query = "SELECT rol FROM Rol as rol")
public class Rol {

	/* Attrs */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRol")
	private int id;

	private String nombre;

    /* Constructs */
    public Rol() { }

    public Rol(String nombre) { this.nombre = nombre; }

	/*-------------------------------------- Setter & Getters ---------------------------------------------*/

    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNombre() { return this.nombre; }

    public int getId() { return this.id; }

    @Override
    public String toString() {
        return "{id: :id, nombre: ':nombre'}"
			  .replace(":id", String.valueOf(this.id))
			  .replace(":nombre", this.nombre);
    }

}
