package com.github.kristianzurav.adventura.ui;

import java.awt.Event;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.util.*;

import com.github.kristianzurav.adventura.logika.IHra;
import com.github.kristianzurav.adventura.logika.Prostor;
import com.github.kristianzurav.adventura.logika.Vec;
import com.github.kristianzurav.adventura.main.Start;
import com.github.kristianzurav.adventura.logika.Hra;
import com.github.kristianzurav.adventura.logika.Batoh;

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
import javafx.scene.image.ImageView;


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
	@FXML private ImageView klic;
	@FXML private ImageView pacidlo;
	@FXML private ImageView lopata;
	@FXML private ImageView relikvie;
	@FXML private ImageView figurka;
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
		klic.setVisible(false);
		pacidlo.setVisible(false);
		relikvie.setVisible(false);
		lopata.setVisible(false);
		figurka.setTranslateX(hra.getHerniPlan().getAktualniProstor().getX());
		figurka.setTranslateY(hra.getHerniPlan().getAktualniProstor().getY());
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamVeci.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamVeci.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		klic.setVisible(hra.getBatoh().getVecUi("klíč"));
		pacidlo.setVisible(hra.getBatoh().getVecUi("páčidlo"));
		relikvie.setVisible(hra.getBatoh().getVecUi("relikvie"));
		lopata.setVisible(hra.getBatoh().getVecUi("lopata"));
		figurka.setTranslateX(hra.getHerniPlan().getAktualniProstor().getX());
		figurka.setTranslateY(hra.getHerniPlan().getAktualniProstor().getY());
		
		
	}
	
	

}
