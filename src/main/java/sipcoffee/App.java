package sipcoffee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App extends Application {

    private WebView webView;
    private WebEngine engine;
    private JSObject windowJS;

    @Override
    public void start(Stage stage) throws IOException {
        Parent splash = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(splash));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Sipcoffee");
        stage.show();
    }

    public static void main(String args[]) {
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
