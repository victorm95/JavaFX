package sipcoffee;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import sipcoffee.controllers.*;
import sipcoffee.models.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    /* Attrs */
    @FXML
    Button btnLogin;
    @FXML
    Button btnCancel;
    @FXML
    TextField usuarioField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label msgLabel;


    /* Init */
    public void initialize(URL url, ResourceBundle resources) {
        this.passwordField.setOnAction(e -> this.login(e));
        this.btnLogin.setOnAction(e -> this.login(e));
        this.btnCancel.setOnAction(e -> System.exit(0));

        Platform.runLater(  new LoadDB());
    }

    private void login(ActionEvent e) {

        UsuarioCtrl userCtrl = new UsuarioCtrl();

        if (!this.usuarioField.getText().equals("") && !this.passwordField.getText().equals("")) {
            Usuario user = userCtrl.login(this.usuarioField.getText(), this.passwordField.getText());

            if (user != null) {
                if (user.isActivo()) {
                    WebView webView = new WebView();
                    Stage stage = new Stage();
                    WebEngine engine = webView.getEngine();
                    JSObject windowJS = (JSObject) engine.executeScript("window");

                    windowJS.setMember("javaMain", this);
                    engine.executeScript("window.console.log = function(obj){ javaMain.print(obj); };");

			/*-------------------------------  Agragar los modelos al webView --------------------------------*/
                    windowJS.setMember("Rol", new RolCtrl());
                    windowJS.setMember("Usuario", new UsuarioCtrl());
                    windowJS.setMember("Terreno", new TerrenoCtrl());
                    windowJS.setMember("Bloque", new BloqueCtrl());
                    windowJS.setMember("Municipio", new MunicipioCtrl());
                    windowJS.setMember("Departamento", new DepartamentoCtrl());
                    windowJS.setMember("Parcela", new ParcelaCtrl());
                    windowJS.setMember("Cafeto", new CafetoCtrl());
                    windowJS.setMember("Proceso", new ProcesoCtrl());
                    windowJS.setMember("Producto", new ProductoCtrl());
                    windowJS.setMember("Siembra", new SiembraCtrl());
                    windowJS.setMember("Cosecha", new CosechaCtrl());
                    windowJS.setMember("Nomina", new NominaCtrl());
                    windowJS.setMember("Entrada", new EntradaCtrl());
                    windowJS.setMember("Salida", new SalidaCtrl());

			/*------------------------------------------------------------------------------------------------*/

                    engine.load(App.class.getResource("web/views/index.html").toExternalForm());
                    stage.setTitle("Sipcoffee");
                    stage.setScene(new Scene(webView));
                    stage.show();

                    // Close Login
                    ((Stage) (((Node) e.getSource()).getScene().getWindow())).close();
                } else {
                    this.msgLabel.setText("Es usuario no esta activo.");
                }
            } else {
                this.msgLabel.setText("Usuario y/o Contraseña incorrectos.");
            }
        } else {
            this.msgLabel.setText("Todos los campos son obligatorios.");
        }
    }

    public void print(Object obj) {
        System.out.println(obj);
    }


}
