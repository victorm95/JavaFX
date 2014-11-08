package sipcoffee;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sipcoffee.models.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by victorm on 12/10/14.
 */
public class Splash implements Initializable {

    /* Attrs */
    @FXML
    private Label textTask;

    /* Init */
    public void initialize(URL url, ResourceBundle resources) {
        //Platform.runLater(new LoadDB());
    }

}
