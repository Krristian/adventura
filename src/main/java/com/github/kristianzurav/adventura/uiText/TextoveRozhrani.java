package com.github.kristianzurav.adventura.uiText;


import java.util.Scanner;
import com.github.kristianzurav.adventura.logika.*;
import java.io.*;

/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 *@version    1.00
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  
     *  Konstruktor, který vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    
    public void hraj() {
        System.out.println("Pokud si nevíš rady, zadej příkaz 'Nápověda'.\n" + hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    /**
     *  Hlavní metoda hry pro hru ze souboru. 
     *  Vypíše úvodní text a pak opakuje čtení ze souboru a zpracování
     *  příkazů až do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     *  
     *  @param nazevSouboru Cesta k souboru, ze kterého budeme číst příkazy
     */
    
    public void hrajZeSouboru(String nazevSouboru) {
        try (BufferedReader ctecka= new BufferedReader(new FileReader(nazevSouboru)))
        {
             System.out.println(hra.vratUvitani());
          
            String radek = ctecka.readLine();
            while (!hra.konecHry() && radek != null) {
                System.out.println("*"+radek+"*");
                System.out.println(hra.zpracujPrikaz(radek));
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
        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
