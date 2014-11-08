package sipcoffee.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sipcoffee.App;
import sipcoffee.models.*;

import java.util.Date;

/**
 * Created by SABADO on 08/11/2014.
 */
/* Hilo Interno Para Cargar la Base de Datos */
public class LoadDB implements Runnable {
    @Override
    public void run() {
        Usuario usuario;
        Departamento departamento;
        String listDepartamentos[];
        String municipios[];

        //Splash.this.textTask.setText("Conectando Base de Datos.");
        Conexion.init();

        usuario = new Usuario();
        if (usuario.isEmpty()) {
            //Splash.this.textTask.setText("Creando Usuario 'ROOT'");

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

            /*if (usuario.save())
                Splash.this.textTask.setText("'ROOT' Creado Correctamente.");*/
        }

        departamento = new Departamento();
        if (departamento.isEmpty()) {
            listDepartamentos = new String[]{"Antioquia", "Caldas",
                    "Risaralda", "Bogota", "Atlantico", "Bolivar", "Cordoba",
                    "San Andres y Providencia", "Sucre", "Arauca", "Boyaca",
                    "Casanare", "Amazonas", "Caqueta", "Cauca", "Choco",
                    "Guainia", "Guaviare", "Narino", "Putumayo", "Quindio",
                    "Valle del Cauca", "Vaupes"};

            for (String d : listDepartamentos) {
                Departamento temp = new Departamento(d);

                //Splash.this.textTask.setText("Guardando Departamento: " + temp.getNombre());
                if (temp.save()) {
                    System.out.println("[save]: " + d);

                    if (d.equals("Antioquia")) {
                        new Municipio("Andes", temp).save();
                        new Municipio("Medellin", temp).save();
                    }
                }
            }
        }

        System.out.println(new Parcela().getData());

        /*try {
            // Open Login
            Parent splash = FXMLLoader.load(App.class.getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(splash));
            stage.setTitle("Sipcoffee");
            stage.show();

            // Close Splah
            //((Stage) Splash.this.textTask.getScene().getWindow()).close();
        } catch (Exception e) {
            System.out.println("[Error Open Login]: " + e.toString());
        }*/
    }

}