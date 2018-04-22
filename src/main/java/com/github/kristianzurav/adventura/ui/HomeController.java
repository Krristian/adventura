package com.github.kristianzurav.adventura.ui;

import java.awt.Event;
import java.awt.Menu;
import java.awt.MenuItem;
import java.util.*;

import com.github.kristianzurav.adventura.logika.IHra;
import com.github.kristianzurav.adventura.logika.Prostor;
import com.github.kristianzurav.adventura.logika.Vec;
import com.github.kristianzurav.adventura.main.Start;
import com.github.kristianzurav.adventura.logika.Hra;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.Observable;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private ListView<Vec> seznamVeci;
	private IHra hra;
	private Napoveda napoveda = new Napoveda ();
	
	
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		update(null,seznamMistnosti);
		update(null,seznamVeci);
		
		
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
			
		}
		
	}
		
	public void konecHry() {
			
			hra.setKonecHry(true);
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		
	}
	
	public void novaHra() {
		
		IHra hra = new Hra();
		inicializuj(hra) ;
	
	}
	
	public void napoveda() throws Exception {
		
		Stage stage = new Stage();
		napoveda.start(stage);
	
	}
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		hra.getHerniPlan().addObserver(this);
		textVstup.setDisable(false);
		odesli.setDisable(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		
	}
	
	

}
