package Controller;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Charts_controler {

    public WebView tomato_used;
    public WebView tomato_ok;
    public WebView tomato_trade;

    public void tomato_ok() throws IOException {
        WebEngine webEngine = tomato_ok.getEngine();
        webEngine.load("file:/Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_ok_html.html");
//        Runtime.getRuntime().exec("open /Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_ok_html.html");
    }

    public void tomato_trade() throws IOException {
        WebEngine webEngine = tomato_trade.getEngine();
        webEngine.load("http://10.0.117.51:8080/");
//        Runtime.getRuntime().exec("open /Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_trade_html.html");
    }

    public void tomato_used() throws IOException {
        WebEngine webEngine = tomato_used.getEngine();
        webEngine.load("file:/Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_use_html.html");
        Runtime.getRuntime().exec("open /Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_use_html.html");
    }


}
