package com.github.kristianzurav.adventura.ui;

import com.github.kristianzurav.adventura.ui.HomeController;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Napoveda {
	  

    
    public void start(Stage okno) throws Exception {
    	
    	URL url = getClass().getResource("napoveda.html");
    	WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(url.toExternalForm());
    	VBox napovedaOkno = new VBox();
		napovedaOkno.getChildren().addAll(browser);
		Scene scene  = new Scene(napovedaOkno, 1000, 550);
		okno.setScene(scene);
        okno.show();
    }
}   
