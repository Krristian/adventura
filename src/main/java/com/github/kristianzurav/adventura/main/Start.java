/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.main;


import com.github.kristianzurav.adventura.logika.*;
import com.github.kristianzurav.adventura.ui.HomeController;
import com.github.kristianzurav.adventura.uiText.TextoveRozhrani;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková, Kristian Žurav
 * @version   1.00
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     * Pokud parametr zadán není, tak se aplikace spustí v 
     * grafickém rozhraní.
     * Pokud je zadán parametr -text, tak se spustí v textové verzi
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");   
            }
            System.exit(0);
        }	
    	
    }
    
    /**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 * @param Stage Primární okno aplikace
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("MojeOkno.fxml"));    	
    	Parent root = loader.load();

    	HomeController controller = loader.getController();
    	IHra hra = new Hra();
		
    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("Základní adventura");
    	
    	controller.inicializuj(hra);
	}
}
