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
