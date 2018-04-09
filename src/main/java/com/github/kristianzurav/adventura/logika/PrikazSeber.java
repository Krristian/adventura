/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazSeber implementuje příkaz pro sebrání věci z prostoru.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

   /**
    *  Konstruktor třídy PrikazSeber
    *  Inicializuje datové atributy plan a batoh, které tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",  
    *  batoh batoh/inventář, kde má hráč uložené věci, které má u sebe
    */    
    public PrikazSeber (HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
        
        
    }
    
    

    //== Nesoukromé metody (instancí i třídy) ======================================
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
    
   /**
    * Metoda provádí příkaz 'seber'. Jedná se o sebrání věci 
    * z aktuálního prostoru a její vložení do batohu/inventáře.
    * Pokud není zadán parametr nebo se věc v prostoru nenachází, tak vrátí chybovou hlášku.
    * Pokud je věc přenositelná (a je místo v batohu), tak jí hráč sebere, 
    * vloží do batohu/inevtáře a vrátí hlášku o jejím sebrání,
    * jinak metoda vrátí určenou chybovou hlášku.
    *@param parametry - jako  parametr obsahuje jméno věci, kterou má sebrat.
    *@return zpráva, kterou vypíše hra hráči
    */ 
   @Override
   public String proved (String...parametry)
   {
        if (parametry.length == 0) {return "Co mám sebrat?";} // Pokud hráč nezadá žádný parametr
        String nazevSbiraneho = parametry [0];
        Prostor aktualni = plan.getAktualniProstor ();
        Vec sbirana = aktualni.odeberVec (nazevSbiraneho);
        if (sbirana == null) {return "Nic takového tu bohužel není.";} // Pokud věc v prostoru není
            else if (sbirana.getPrenositelnost ()) // Pokud je věc přenositelná
            {
                if ( ! batoh.pridejVec (sbirana)) // Pokud je batoh plný
                {
                    aktualni.vlozVec (sbirana); 
                    return "Tohle se ti už do batohu nevejde, nějakou věc musíš nejdříve odhodit.";
                }
                return  "Sebráno " + nazevSbiraneho; // Pokud batoh není plný
            }
            else // VPokud je věc nepřenostielná
            {
                aktualni.vlozVec(sbirana); 
                return "Tohle vzít nemůžeš.";
            }
   }
        
    
    //== Soukromé metody (instancí i třídy) ========================================
    
   
}
