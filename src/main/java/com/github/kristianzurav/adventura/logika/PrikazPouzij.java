/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazPouzij implementuje příkaz pro použití věci na jinou věc v aktuálním prostoru.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class PrikazPouzij implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "použij";
    private Batoh batoh;
    private HerniPlan plan;

    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy PrikazPouzij
    *  Inicializuje datové atributy plan a batoh, které tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",  
    *  batoh batoh/inventář, kde má hráč uložené věci, které má u sebe
    */    
    public PrikazPouzij (HerniPlan plan, Batoh batoh)
    {
        this.batoh = batoh;
        this.plan = plan;
    }
    
    /**
    * Metoda provádí příkaz 'použij'. Jedná se o použití věci 
    * z batohu/inventáře na věc z aktuálního prostoru.
    * Pokud není zadán parametr, používanou věc nemáme v batohu/inventři nebo
    * se věc, na kterou to hcceme použít, v prostoru nenachází, tak vrátí chybovou hlášku.
    * Pokud je mezi danými věcmi předepsána interakce, tak se provede a vrátí hlášku o úspěšném provedení.
    * Jinak vrátí hlášku, že se zde daná věc nedá použít.
    *@param parametry - jako  parametr obsahuje jméno věci, kterou má použít a jméno věci, na kterou to má použít
    *@return zpráva, kterou vypíše hra hráči
    */ 
    @Override
    public String proved (String... parametry)
    {
        if (parametry.length == 0) {return "Co mám použít a na co to mám použít?";}
        String coPouziva = parametry[0]; // jaký předmět bude hráč používat
        if (batoh.getVec (coPouziva) ==null) {return "Toto nemůžu použít, protože to nemám.";}
        if (parametry.length == 1)  {return "Co s tím mám dělat?";}
        Prostor aktualni = plan.getAktualniProstor();
        String kCemu = parametry[1];     // na jaký předmět to hráč použije
        if (aktualni.jePritomna (kCemu) == null) {return "Na tohle to nemůžu použít, protože to tu není.";} 
        
        if ((coPouziva.equals ("lopata")) && (kCemu.equals ("zával"))) 
        {
            aktualni.vratSousedniProstor ("první_část_dolu").setUzamceni (false); 
            aktualni.odeberVec ("zával"); 
            return "Podařilo se ti prokopat zával a cesta zpět je volná. \n" + aktualni.kratkyPopis ();
        }
            else if ((coPouziva.equals ("klíč")) && (kCemu.equals ("ocelové_dveře"))) 
            {
                aktualni.vratSousedniProstor ("hala").setUzamceni (false); 
                aktualni.odeberVec ("ocelové_dveře");
                return "Odemkl jsi ocelové dveře a cesta dál je volná. \n" + aktualni.kratkyPopis ();
            }
                else if ((coPouziva.equals ("páčidlo")) && (kCemu.equals ("dveře_kanceláře"))) 
                {
                    aktualni.vratSousedniProstor ("kancelář").setUzamceni (false); 
                    aktualni.odeberVec ("dveře_kanceláře");
                    return "Zvládl jsi vypáčit dveře kanceláře. \n" + aktualni.kratkyPopis ();
                }
                    else {return "Tenhle předmět se nedá na tohle použít.";}
        
      
        
        
        
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

    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
