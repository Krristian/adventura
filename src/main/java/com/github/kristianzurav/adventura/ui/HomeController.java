package com.github.kristianzurav.adventura.ui;

import java.awt.Event;
import java.awt.Menu;

import java.io.*;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
	@FXML private ImageView figurka1;
	@FXML private MenuItem konecHryTlacitko;
	@FXML private ComboBox prikaz;

	private IHra hra;
	private Napoveda napoveda = new Napoveda ();
	private boolean zobrazenaNapoveda = false;
	private String [] provedenePrikazy; 
	private int pocetPrikazu;
	//private Planek planek = new Planek ();
	
	
	
	
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String prikazTyp = prikaz.getSelectionModel().getSelectedItem().toString();
		
			String vypis = hra.zpracujPrikaz(prikazTyp+" "+textVstup.getText());
			textVypis.appendText("\n--------\n"+prikazTyp+" "+textVstup.getText()+"\n--------\n");
			provedenePrikazy[pocetPrikazu] = prikazTyp+" "+textVstup.getText();
			pocetPrikazu++;
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
	
	/**
	 * Metoda ukončí hru, ale nevypne aplikaci
	 */
	public void konecHry() {
			
			hra.setKonecHry(true);
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
			konecHryTlacitko.setDisable(true);
			prikaz.setDisable(true);
			
		
	}
	
	/**
	 * Metoda spustí hru od začátku
	 */
	public void novaHra() {
		
		zobrazenaNapoveda = true;
		IHra hra = new Hra();
		inicializuj(hra) ;
	
	}
	
	/**
	 * Metoda spustí hru od začátku
	 */
	public void ulozHru() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("UlozenaHra.txt")))
		{
			
			for (int i=0; i < pocetPrikazu; i++ )
	        {
				bw.write(provedenePrikazy[i]);
		        bw.newLine();
		        bw.flush();      
	        }
			
		}		
			catch (Exception e)
			{
				System.err.println("Do souboru se nepovedlo zapsat.");
			}
		           	
	}
	
	/**
	 * Metoda spustí hru od začátku
	 */
	public void nahrajHru() {
		
		zobrazenaNapoveda = true;
		IHra hra = new Hra();
		inicializuj(hra) ;
		textVstup.setText("");
		try (BufferedReader ctecka= new BufferedReader(new FileReader("UlozenaHra.txt")))
        {
            
          
            String radek = ctecka.readLine();
            while (radek != null) {
            	String vypis = hra.zpracujPrikaz(radek);
    			textVypis.appendText("\n--------\n"+radek+"\n--------\n");
    			provedenePrikazy[pocetPrikazu] = radek;
    			pocetPrikazu++;
    			textVypis.appendText(vypis);			
    			update(null,seznamMistnosti);
    			update(null,seznamVeci);
                radek= ctecka.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
             System.out.println("Soubor nenalezen "+ e);
        }
        catch(IOException e)
        {
             System.out.println("Probém se vstupem "+ e);
        }
 
	
	}
	
	/**
	 * Metoda zobrazí nápovědu v novém okně
	 */
	public void napoveda() throws Exception {
		
		Stage stage = new Stage();
		napoveda.start(stage);
	
	}
	
	/**
	 * Připravovaná metoda pro dialogové
	 * zobrazení plánku s aktuální pozicí
	 */
   /** public void planek() throws Exception {
  		
  		Stage stage = new Stage();
  		planek.start(stage);
  		
  	
  	}*/
    
	/**
	 * Metoda pro přechod do jiné místnosti
	 * přes grafické rozhraní
	 */
    public void jdi() {
    	Prostor prostor = seznamMistnosti.getSelectionModel().getSelectedItem();
		if (prostor != null) {
			String vypis = hra.zpracujPrikaz("jdi "+prostor.toString());
			textVypis.appendText("\n--------\n"+"Jdi "+prostor.getNazev()+"\n--------\n");
			textVypis.appendText(vypis);
			update(null,seznamMistnosti);
			update(null,seznamVeci);
		}
		else {textVypis.appendText("\n--------\n Nemáš vybranou žádnou místnost! \n--------\n");}
		
		provedenePrikazy[pocetPrikazu] = ("jdi "+prostor.toString());
		pocetPrikazu++;
	

	}
	
	
	
	
	
    /**
	 * Metoda pro inicializaci uživatelského
	 * rozhraní a samotného počátku hry
	 */
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
		konecHryTlacitko.setDisable(false);
		prikaz.setDisable(false);
		prikaz.getItems().clear();
		prikaz.getItems().addAll(hra.getPlatnePrikazy().getPrikazy());
		prikaz.getSelectionModel().selectFirst();
		provedenePrikazy = new String [200];
		pocetPrikazu = 0;
		if (! zobrazenaNapoveda ) {
			try {
				napoveda();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * Metoda pro aktualizaci uživatelského 
	 * rozhraní
	 */
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

		/** try {
			planek();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		figurka1.setTranslateX(hra.getHerniPlan().getAktualniProstor().getX());
		figurka1.setTranslateY(hra.getHerniPlan().getAktualniProstor().getY());
		*/
	}
	
	

}
