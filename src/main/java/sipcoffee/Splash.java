package sipcoffee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import sipcoffee.models.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by victorm on 12/10/14.
 */
public class Splash implements Initializable {

    @FXML private Label textTask;

    /* Init */
    public void initialize(URL url, ResourceBundle resources){
        this.initDB();
    }

    private void initDB(){
        Usuario usuario;
        Departamento departamento;
        String listDepartamentos[];
        String municipios[];

        this.textTask.setText("Conectando Base de Datos.");
        Conexion.init();

        usuario = new Usuario();
        if (usuario.isEmpty()) {
            this.textTask.setText("Creando Usuario 'ROOT'");

            Rol rolusuario = new Rol("Admin");
            rolusuario.save();

            usuario.setNombre("usuario");
            usuario.setUsuario("root");
            usuario.setClave("root");
            usuario.setExpedicionDocumento(new Date());
            usuario.setCedula(0);
            usuario.setDireccion("----");
            usuario.setTelefono(0);
            usuario.setActivo(true);
            usuario.setRol(rolusuario);

            if (usuario.save())
                this.textTask.setText("'ROOT' Creado Correctamente.");
        }

        departamento = new Departamento();
        if (departamento.isEmpty()) {
            listDepartamentos = new String[] { "Antioquia", "Caldas",
                    "Risaralda", "Bogota", "Atlantico", "Bolivar", "Cordoba",
                    "San Andres y Providencia", "Sucre", "Arauca", "Boyaca",
                    "Casanare", "Amazonas", "Caqueta", "Cauca", "Choco",
                    "Guainia", "Guaviare", "Narino", "Putumayo", "Quindio",
                    "Valle del Cauca", "Vaupes" };

            for(String d : listDepartamentos){
                Departamento temp = new Departamento(d);

                this.textTask.setText("Guardando Departamento: " + temp.getNombre());
                if( temp.save() ){
                    System.out.println("[save]: " + d);

                    if(d.equals("Antioquia")){
                        new Municipio("Andes", temp).save();
                        new Municipio("Medellin", temp).save();
                    }
                }
            }
        }

        System.out.println(new Parcela().getData());

        try {
            // Open Login
            Parent splash = FXMLLoader.load(App.class.getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(splash));
            stage.setTitle("Sipcoffee");
            stage.show();
        }catch(Exception e ){
            System.out.println("[Error Open Login]:" + e.toString());
        }

        // Close Splash
        try {
            ((Stage) this.textTask.getScene().getWindow()).close();
        }catch(Exception e ){
            System.out.println("[Error Close Splash]: " + e.toString());
        }
    }

}
