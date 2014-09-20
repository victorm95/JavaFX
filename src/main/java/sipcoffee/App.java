package sipcoffee;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import sipcoffee.controllers.RolCtrl;
import sipcoffee.controllers.UsuarioCtrl;
import sipcoffee.controllers.TerrenoCtrl;
import sipcoffee.controllers.BloqueCtrl;
import sipcoffee.models.Conexion;
import sipcoffee.models.Usuario;
import sipcoffee.models.Rol;

public class App extends Application {

	private WebView webView;
	private WebEngine engine;
	private JSObject windowJS;

	@Override
	public void start(Stage stage) {

		webView = new WebView();
		engine = webView.getEngine();
		windowJS = (JSObject) engine.executeScript("window");

		windowJS.setMember("javaMain", this);
		engine.executeScript("window.console.log = function(obj){ javaMain.print(obj); };");
		engine.executeScript("window.load = function(url){ javaMain.load(url); };");

		/*-------------------------------  Agragar los modelos al webView --------------------------------*/
		windowJS.setMember("Rol", new RolCtrl());
		windowJS.setMember("Usuario", new UsuarioCtrl());
		windowJS.setMember("Terreno", new TerrenoCtrl());
		windowJS.setMember("Bloque", new BloqueCtrl());

		/*------------------------------------------------------------------------------------------------*/

		// engine.load(getClass().getResource("web/views/roles.html").toExternalForm());

		load("web/views/index.html");

		stage.setTitle("Sipcoffee");
		stage.setScene(new Scene(webView));
		stage.show();

		// Iniciarlizar los Objetos para consular la DB
		new Thread( () -> {
            Conexion.init();
            Usuario admin = new Usuario(); 
            
            if( admin.isEmpty() ){
                Rol rolAdmin = new Rol("Admin");
                rolAdmin.save();

                admin.setNombre("Admin");
                admin.setUsuario("root");
                admin.setClave("root");
                admin.setExpedicionDocumento(new Date());
                admin.setCedula(0);
                admin.setDireccion("----");
                admin.setTelefono(0);
				admin.setActivo( true );
                admin.setRol( rolAdmin );

                if( admin.save() )
                    System.out.println("User admin created correctly");
            }
        } ).start();
	}

	public void print(Object obj) {
		System.out.println(obj);
	}

	public void load(String url) {
		engine.load(getClass().getResource(url).toExternalForm());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static String hash(Object text) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(text.toString().getBytes());

			// return new String( md.digest() );

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("HashError: " + e.toString());
			return null;
		}
	}
}
