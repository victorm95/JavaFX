package sipcoffee;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class App extends Application{
	
	/*private WebView webView;
	private WebEngine engine;
	private JSObject windowJS;*/
	
	@Override
	public void start(Stage stage){
		
		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();
		
		engine.load( getClass().getResource("web/index.html").toExternalForm() );
		
		/*
		JSObject windowJS = (JSObject) engine.executeScript("window");		
		engine.executeScript("window.console.log = java.lang.System.out.println");
		engine.executeScript("window.console.debug = java.lang.System.out.println");
		*/
				
		stage.setTitle("Sipcoffee");
		stage.setScene( new Scene(webView) );
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
