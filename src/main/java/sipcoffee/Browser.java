package sipcoffee;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by victorm on 15/01/15.
 */
public class Browser {

    /* Attrs */
    private WebView webView;
    private WebEngine webEngine;

    /* Construct */
    public Browser() {
        this.webView = new WebView();
        this.webEngine = this.webView.getEngine();
        this.init();
    }

    private void init() {
        this.webEngine.load(App.class.getResource("web/views/login.html").toExternalForm());
    }

    /* Getters */
    public WebView getWebView() { return this.webView; }
    public WebEngine getWebEngine() { return this.webEngine; }

}
