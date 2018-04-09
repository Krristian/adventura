/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazInventar implementuje příkaz pro vypsání věcí z inventáře/batohu.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
class PrikazInventar implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "inventář";
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

   /**
    *  Konstruktor třídy PrikazInventar
    *  Inicializuje datový atribut plan který tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",
    */    
    public PrikazInventar (Batoh batoh)
    {
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     * Metoda provádí příkaz 'inventář'. 
     * Vypíše hráči jména věcí, které obsahuje jeho inventář/batoh.
     *@return zpráva, kterou vypíše hra hráči o obsahu inventáře/batohu
     */
    @Override
    public String proved (String... parametry)
    {
        return batoh.ukazObsah (); 
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev () 
    {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
