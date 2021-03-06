package sipcoffee.models;

import org.json.JSONArray;
import org.json.JSONObject;

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
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

    @JoinColumn(name = "idBloque", referencedColumnName = "idBloque", nullable = false)
    @OneToOne
    private Bloque bloque;

	/*-------------------------------------- Acciones DB ---------------------------------------------*/

    public boolean save() {
        return Conexion.persist(this);
    }

    public Parcela find(int id) {
        //Conexion.init();
        return (Parcela) Conexion.manager.createNamedQuery("findById-Parcela")
                .setParameter("id", id).getSingleResult();
    }

    public String all() {
        JSONArray jsonArray = new JSONArray();
        Conexion.init();
        List<Parcela> listParcela = Conexion.manager.createNamedQuery(
                "all-Parcelas", Parcela.class).getResultList();

        for (Parcela parcela : listParcela) {
            jsonArray.put(parcela.toJson());
        }
        return jsonArray.toString();
    }

    public boolean delete() {
        return Conexion.delete(this);
    }

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

    public boolean isEmpty() {
        Conexion.init();
        return Conexion.namedQuery("all-Parcelas").isEmpty();
    }


	/*-------------------------------------- Conversiones ---------------------------------------------*/

    public String toJson() {
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("nombre", this.nombre);
        json.put("area", this.area);
        json.put("bloque", new JSONObject(this.bloque.toJson()));
        json.put("activo", this.activo);
        json.put("fechaRegistro", this.fechaRegistro);

        return json.toString();
    }

    public String getData() {
        try {
            List<Parcela> listParcelas = Conexion.manager.createNamedQuery(
                    "all-Parcelas", Parcela.class).getResultList();
            List<Bloque> listBloques = Conexion.manager.createNamedQuery(
                    "all-Bloques", Bloque.class).getResultList();
            List<Terreno> listTerrenos = Conexion.manager.createNamedQuery(
                    "all-Terreno", Terreno.class).getResultList();

            JSONArray jsonTerrenos = new JSONArray();

            for (Terreno terreno : listTerrenos) {
                JSONObject jsonTerreno = new JSONObject();

                jsonTerreno.put("id", terreno.getId());
                jsonTerreno.put("nombre", terreno.getNombre());
                jsonTerreno.put("size", terreno.getArea());
                jsonTerreno.put("children", new JSONArray());

                for (Bloque bloque : listBloques) {
                    JSONObject jsonBloque = new JSONObject();
                    if (terreno.getId() == bloque.getTerreno().getId()) {

                        jsonBloque.put("id", bloque.getId());
                        jsonBloque.put("nombre", bloque.getNombre());
                        jsonBloque.put("size", bloque.getArea());
                        jsonBloque.put("children", new JSONArray());

                        for (Parcela parcela : listParcelas) {
                            if (parcela.getBloque().getId() == bloque.getId()) {
                                JSONObject jsonParcela = new JSONObject();

                                jsonParcela.put("id", parcela.getId());
                                jsonParcela.put("nombre", parcela.getNombre());
                                jsonParcela.put("size", parcela.getArea());

                                jsonBloque.append("children", jsonParcela);
                            }
                        }
                    }
                    jsonTerreno.append("children", jsonBloque);
                }
                jsonTerrenos.put(jsonTerreno);
            }

            return ((JSONObject) jsonTerrenos.get(0)).toString();
        } catch (Exception e) {
            System.out.println("[Error GetData]: " + e.toString());
            return null;
        }
    }

}
