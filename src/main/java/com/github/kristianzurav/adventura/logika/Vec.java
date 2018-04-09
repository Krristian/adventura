/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Instance třídy Vec představují věci ve hře.
 * Věci mají zadaný název a možnost přenositelnosti.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    
    String nazev; 
    boolean prenositelnost; // Určuje zda je věc přenositelná (hodnota true říká, že ano)
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor vytváří věc a inicializuje její název a přenositelnost.
     *  @param nazev řetězec určující název věci,    prenositelnost hodnota = true je přenositelná   false = není přenositelná 
     */
    public Vec (String nazev, boolean prenositelnost)
    {
        this.nazev= nazev;
        this.prenositelnost= prenositelnost;
    }
    
    /**
     * Metoda vrací název věci.
     * @return řetězec obsahující název věci
     */
    public String getNazev () 
    {
        return nazev;
    }
    
    /**
     * Metoda vrací přenositelnost věci.
     * @return pravda nebo nepravda, jestli je věc přenositelná
     */
    public boolean getPrenositelnost () 
    {   
        return prenositelnost;
    }
    
    /**
     * Metoda nastavuje přenositelnost věci.
     * @param prenositelnost    hodnota = true je přenositelná   false = není přenositelná
     */
    public void setPrenositelnost (boolean prenositelnost)
    {   
        this.prenositelnost= prenositelnost;
    }
    
    

    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
