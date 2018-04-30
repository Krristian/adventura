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


/*******************************************************************************
 * Tato třída je pro plánku s aktuální pozicí
 * v samostatném dialogovém okně
 * po každém přechodu do jiné místnosti
 * Tato třída je součástí jednoduché adventury.
 * 
 * @author    Kristian Žurav
 * @version   1.00
 */

public class Planek extends Observable {
	  
	/**
	 * Metoda, ve které se konstruuje okno plánku
	 * 
	 * @param Stage  Okno plánku
	 */
    
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
