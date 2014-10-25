package sipcoffee;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import sipcoffee.controllers.*;
import sipcoffee.models.Usuario;

public class Login implements Initializable {

	/* Attrs */
	@FXML Button btnLogin;
	@FXML Button btnCancel;
	@FXML TextField usuarioField;
	@FXML PasswordField passwordField;
    @FXML Label msgLabel;
	

	/* Init */
	public void initialize(URL url, ResourceBundle resources){

        this.passwordField.setOnAction(e -> this.login(e));
		this.btnLogin.setOnAction(e -> this.login(e));
		this.btnCancel.setOnAction( e -> System.exit(0) );

    }

    private void login(ActionEvent e){

		UsuarioCtrl userCtrl = new UsuarioCtrl();

        if(!this.usuarioField.getText().equals("") && !this.passwordField.getText().equals("")){
            Usuario user = userCtrl.login(this.usuarioField.getText(), this.passwordField.getText());

            if( user != null ){
                if(user.isActivo()){
                    WebView webView = new WebView();
                    Stage stage = new Stage();
                    WebEngine engine = webView.getEngine();
                    JSObject windowJS = (JSObject) engine.executeScript("window");

                    windowJS.setMember("javaMain", this);
                    engine.executeScript("window.console.log = function(obj){ javaMain.print(obj); };");
                    //engine.executeScript("window.load = function(url){ javaMain.load(url); };");

                    //System.out.println("[Session:] " + "sessionStorage.setItem('usuario', "+ user.toJson() +");");
                    //engine.executeScript("sessionStorage.setItem('usuario', "+ user.toJson() +");");

			/*-------------------------------  Agragar los modelos al webView --------------------------------*/
                    windowJS.setMember("Rol", new RolCtrl());
                    windowJS.setMember("Usuario", new UsuarioCtrl());
                    windowJS.setMember("Terreno", new TerrenoCtrl());
                    windowJS.setMember("Bloque", new BloqueCtrl());
                    windowJS.setMember("Municipio", new MunicipioCtrl());
                    windowJS.setMember("Departamento", new DepartamentoCtrl());
                    windowJS.setMember("Parcela", new ParcelaCtrl());
                    windowJS.setMember("Cafeto", new CafetoCtrl());

			/*------------------------------------------------------------------------------------------------*/

                    engine.load(App.class.getResource("web/views/index.html").toExternalForm());
                    stage.setTitle("Sipcoffee");
                    stage.setScene(new Scene(webView));
                    stage.show();

                    // Close Login
                    ((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
                }else{
                    this.msgLabel.setText("Es usuario no esta activo.");
                }
            }else{
                this.msgLabel.setText("Usuario y/o Contraseña incorrectos.");
            }
        }else{
            this.msgLabel.setText("Todos los campos son obligatorios.");
        }
    }

    public void print(Object obj) {
        System.out.println(obj);
    }
	

}
