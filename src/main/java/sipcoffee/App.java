package sipcoffee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sipcoffee.models.Connection;

public class App extends Application {

    /* Attrs */
    public static final int PORT = 3000;
    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/rest").port(PORT).build();
	 public static final String PERSISTENCE_UNIT = "dev";
    private static HttpServer server;

    @Override
    public void start(Stage stage) throws IOException {
        Parent splash = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(splash));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Sipcoffee");
        stage.show();
    }

    public static void main(String args[]) {
		  Connection.getInstance(Connection.DEV_UNIT);
        App.server = App.startServer();
		  System.out.println("Servicio REST corriendo en: " + BASE_URI);

        launch(args);
    }

    public static String hash(Object text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.toString().getBytes());

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

    public static HttpServer startServer(){
        ResourceConfig config = new ResourceConfig()
                .packages("sipcoffee.rest.resources")
                .register(sipcoffee.rest.Config.class)
                .register(org.glassfish.jersey.moxy.json.MoxyJsonConfig.class)
                .register(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);

        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config);
    }

}
