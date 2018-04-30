/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;



/*******************************************************************************
 * Třída PrikazZadej implementuje příkaz pro zadání kombinace k trezoru.
 * Tato třída je součástí jednoduché adventury.
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class PrikazZadej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zadej";
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy PrikazZadej
    *  Inicializuje datový atribut plan který tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",
    */    
    public PrikazZadej (HerniPlan plan)
    {
         this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda provádí příkaz 'zadej'. 
     * Použije se k zadání kombinace trezoru a jeho následnému otevření.
     * Hráč zadá jako parametr řetězec a ten se poté porovnává s požadovaným řetězcem.
     * Pokud se shoduje, tak se prostor s trezorem odemkne a pokud se neshoduje, tak vrátí chybovou hlášku.
     * Pokud nemá řetězec požadované parametry, tak vrátí taktéž příslušnou chybovou hlášku.
     *@param parametry - jako  parametr obsahuje kombinaci k trezoru.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved (String... parametry)
    {
        if (parametry.length == 0) {return "Co mám zadat a kam?";}
        String coZadava = parametry[0]; // Co bude hráč zadávat
        if (parametry.length == 1)  {return "Kam to mám zadat nebo co mám zadat?";}
        Prostor aktualni = plan.getAktualniProstor();
        String kamZadava = parametry[1];     // na jaký předmět to hráč použije
        String kombinace = "1888"; // Správná kombininace
        String velkyTrezor = "velký_trezor";
        boolean jeCiselna = true; 
        for (char c : coZadava.toCharArray ()) // zjišťuje, jestli jsou všechny znaky kombinace číselné
        {
            if ( ((int)c < 48) || ((int)c > 57)) {jeCiselna = false; break;}
        }
        if (aktualni.jePritomna (kamZadava) == null) {return "Sem nic zadat nemůžu, protože to tu není.";} 
        if ((coZadava.equals (kombinace)) && (kamZadava.equals (velkyTrezor))) 
        {
                aktualni.vratSousedniProstor ("trezor").setUzamceni (false); 
                aktualni.odeberVec (velkyTrezor);
                return "Kombinace je správná, můžeš vstoupit do trezoru. \n"+ aktualni.kratkyPopis ();
        }
        if ((kamZadava.equals (velkyTrezor)) && (( ! jeCiselna) || (coZadava.length () != 4))) {return "Musíš zadat pouze číselnou čtyřcifernou kombinaci!";}
        if (( ! coZadava.equals (kombinace)) && (kamZadava.equals (velkyTrezor))) {return "Kombinace je bohužel špatná, zkus to znovu.";}
        return "Sem nic zadat bohužel nejde.";
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
