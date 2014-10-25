package sipcoffee;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import sipcoffee.controllers.BloqueCtrl;
import sipcoffee.controllers.DepartamentoCtrl;
import sipcoffee.controllers.MunicipioCtrl;
import sipcoffee.controllers.RolCtrl;
import sipcoffee.controllers.TerrenoCtrl;
import sipcoffee.controllers.UsuarioCtrl;
import sipcoffee.controllers.ParcelaCtrl;
import sipcoffee.controllers.SiembraCtrl;
import sipcoffee.controllers.CosechaCtrl;
import sipcoffee.controllers.CafetoCtrl;
import sipcoffee.controllers.EntradaCtrl;
import sipcoffee.controllers.SalidaCtrl;
import sipcoffee.controllers.NominaCtrl;
import sipcoffee.controllers.ProcesoCtrl;
import sipcoffee.controllers.ProductoCtrl;
import sipcoffee.models.Scripts;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class App extends Application {

	private WebView webView;
	private WebEngine engine;
	private JSObject windowJS;

	@Override
	public void start(Stage stage) throws IOException {
		Parent splash = FXMLLoader.load(getClass().getResource("splash.fxml"));
		stage.setScene(new Scene(splash));
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Sipcoffee");
        stage.show();
<<<<<<< HEAD
=======

		/*
		webView = new WebView();
		engine = webView.getEngine();
		windowJS = (JSObject) engine.executeScript("window");

		windowJS.setMember("javaMain", this);
		engine.executeScript("window.console.log = function(obj){ javaMain.print(obj); };");
		engine.executeScript("window.load = function(url){ javaMain.load(url); };");

		/*-------------------------------  Agragar los modelos al webView --------------------------------
		windowJS.setMember("Rol", new RolCtrl());
		windowJS.setMember("Usuario", new UsuarioCtrl());
		windowJS.setMember("Municipio", new MunicipioCtrl());
		windowJS.setMember("Departamento", new DepartamentoCtrl());
		windowJS.setMember("Proceso", new ProcesoCtrl());
		windowJS.setMember("Producto", new ProductoCtrl());
		windowJS.setMember("Cafeto", new CafetoCtrl());
		windowJS.setMember("Terreno", new TerrenoCtrl());
		windowJS.setMember("Bloque", new BloqueCtrl());
		windowJS.setMember("Parcela", new ParcelaCtrl());
		windowJS.setMember("Siembra", new SiembraCtrl());
		windowJS.setMember("Cosecha", new CosechaCtrl());
		windowJS.setMember("Entrada", new EntradaCtrl());
		windowJS.setMember("Salida", new SalidaCtrl());
		windowJS.setMember("Nomina", new NominaCtrl());

		/*------------------------------------------------------------------------------------------------

		// engine.load(getClass().getResource("web/views/roles.html").toExternalForm());

		load("web/views/index.html");

		stage.setTitle("Sipcoffee");
		stage.setScene(new Scene(webView));
		stage.show();

		// Iniciarlizar los Objetos para consular la DB
		new Scripts().start();
		*/
        
		
	}

	public void load(String url) {
		engine.load(getClass().getResource(url).toExternalForm());
>>>>>>> origin/master
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
