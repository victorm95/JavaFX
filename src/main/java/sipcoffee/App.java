package sipcoffee;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class App extends Application{
	
	private WebView webView;
	private WebEngine engine;
	private JSObject windowJS;
	
	@Override
	public void start(Stage stage){
		
		webView = new WebView();
		engine = webView.getEngine();
		windowJS = (JSObject) engine.executeScript("window");
		
		engine.load( getClass().getResource("web/index.html").toExternalForm() );
		
					
		stage.setTitle("Sipcoffee");
		stage.setScene( new Scene(webView) );
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
