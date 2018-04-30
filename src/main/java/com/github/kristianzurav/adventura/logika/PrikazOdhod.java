/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazOdhod implementuje příkaz pro odhození věci z batohu/inventáře.
 *  Tato třída je součástí jednoduché adventury.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
     private static final String NAZEV = "odhoď";
     private Batoh batoh;
     private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy PrikazOdhod
    *  Inicializuje datové atributy plan a batoh, které tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",   
    *  batoh batoh/inventář, kde má hráč uložené věci, které má u sebe
    */    
    public PrikazOdhod (HerniPlan plan,Batoh batoh)
    {
        this.batoh = batoh;
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
    * Metoda provádí příkaz 'odhod'. Jedná se o odhození věci z batohu/inventáře.
    * Pokud není zadán parametr nebo se věc v baothu/inventáři nenachází, vratí chybovou hlášku.
    * Jinak věc odebere z batohu/inventáře, vloží do aktuálního prostoru a vrátí hlíšku o tom, jaká věc byla odhozena.
    *@param parametry - jako  parametr obsahuje název věci, kterou má zahodit
    *@return zpráva, kterou vypíše hra hráči
    */ 
    @Override
    public String proved (String... parametry)
    {
        if (parametry.length == 0) {return "Co mám odhodit?";}
        String nazevOdhazovaneho = parametry [0];
        Prostor aktualni = plan.getAktualniProstor ();
        Vec odhazovana = batoh.getVec (nazevOdhazovaneho);
        if (odhazovana == null) {return "Nic takového v inventáři nemám.";}
            else 
            {
                aktualni.vlozVec (odhazovana); 
                batoh.odeberVec (odhazovana); 
                return "Odhozeno " + nazevOdhazovaneho;
            }
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
