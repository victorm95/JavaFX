package sipcoffee;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import sipcoffee.controllers.RolCtrl;

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

		/*------------------------------------------------------------------------------------------------*/

		//engine.load(getClass().getResource("web/views/roles.html").toExternalForm());		

		load("web/views/roles.html");
		
		stage.setTitle("Sipcoffee");
		stage.setScene(new Scene(webView));
		stage.show();
	}

	public void print(Object obj) {
		System.out.println(obj);
	}
	
	public void load(String url){
		System.out.println("Reload: " + url);
		System.out.println("Location: "+engine.getLocation());
		System.out.println("Load: " + getClass().getResource(url).toExternalForm());
		engine.load(getClass().getResource(url).toExternalForm());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
