package com.github.kristianzurav.adventura.ui;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Observable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Planek extends Observable {
	  

    
    public void start(Stage okno) throws Exception {
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../ui/PlanekOkno.fxml"));    	
    	Parent dialog = loader.load();
    	HomeController controller = loader.getController();

    	okno.setScene(new Scene(dialog));
    	okno.show();
    	okno.setTitle("Aktuální pozice hráče");
    }
}   
