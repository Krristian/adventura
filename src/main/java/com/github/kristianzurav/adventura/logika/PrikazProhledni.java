/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazProhledni implementuje příkaz pro prohledání věci z prostoru.
 * Tato třída je součástí jednoduché adventury.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class PrikazProhledni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prohlédni";
    HerniPlan plan;
    Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy PrikazProhledni
    *  Inicializuje datové atributy plan a batoh, které tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",   batoh batoh/inventář, kde má hráč uložené věci, které má u sebe
    */    
    public PrikazProhledni (HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
   /**
    * Metoda provádí příkaz 'prohlédni'. Jedná se o prohlédnutí věci 
    * z aktuálního prostoru a případnou další interakci s ní.
    * Pokud není zadán parametr nebo se věc v prostoru nenachází, tak vrátí chybovou hlášku.
    * Pokud je s danou věcí předepsána interakce, tak se daná interakce 
    * provede a vrátí řetězec s informacemi o akci, jinak vrátí hlášku, 
    * že nic zajímavého nebylo nalezeno.
    *@param parametry - jako  parametr obsahuje jméno věci, kterou má prohlédnout.
    *@return zpráva, kterou vypíše hra hráči
    */ 
    @Override
    public String proved (String... parametry)
    {
        if (parametry.length == 0) {return "Co si mám prohlédnout?";} // Pokud hráč nezadá žádný parametr
        Prostor aktualni = plan.getAktualniProstor ();
        String coProhlizim = parametry[0];     // jaký předmět bude hráč prohlížet
        if (aktualni.jePritomna (coProhlizim) == null) {return "Tohle si prohlédnout nemůžu, protože to tu není.";}  // Pokud věc v prostoru není
        if (coProhlizim.equals ("kostra") && ( ! plan.getProhledalKostru ())) // Pokud prohlíží kostru (dále v kódu ekvivalentně s dalšími věcmi)
            {
                plan.setProhledalKostru (); 
                Vec klic = new Vec ("klíč",true);
                batoh.pridejVec (klic); 
                return "Prohlédl jsi starou kostru a našel si \njí v kapse klíč, který jsi sebral.";
            }
                else if (coProhlizim.equals ("ocelové_dveře")) {return "Těžké ocelové dveře. Jsou zamčené, ale je v nich patrný starý zámek na klíč. ";}
                    else if (coProhlizim.equals ("dveře_kanceláře")) {return "Dveře jsou zamčené, ale nevypadají, že jsou moc silné.";}    
                        else if (coProhlizim.equals ("obraz")) {return "Na obraze je portrét pruského císaře Víléma I. \nDole v rohu je rok vytvoření malby: 1888.";}
                            else if (coProhlizim.equals ("velký_trezor")) {return "Na dveřích trezoru je čtyřciferný číselný zámek, \nmusíš zadat správnou kombinaci.";}   
                                else if (coProhlizim.equals ("police")) 
                                {
                                    aktualni.vratSousedniProstor ("kancelář").vratSousedniProstor ("hala").vratSousedniProstor ("zasedačka").vratSousedniProstor ("tajný_východ").setUzamceni (false);
                                    return "Našel jsi kompletní plány bunkru. Je tam zakreslen \ni tajný východ umístěný v zasedací místnosti za knihovnou.";
                                }
                                    else {return "Při prohlédnutí jsi nic zajímavého nenašel.";} // Není zadána určitá interakce
        
      
        
        
        
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
