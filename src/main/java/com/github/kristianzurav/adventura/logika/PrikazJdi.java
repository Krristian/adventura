package com.github.kristianzurav.adventura.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi, kterým je možno procházet herním plánem.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Kristian Žurav
 *@version    pro školní rok 2015/2016
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy PrikazSeber
    *  Inicializuje datové atributy plan a batoh, které tato třída využívá.
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit",  
    *  batoh batoh/inventář, kde má hráč uložené věci, které má u sebe
    */    
    public PrikazJdi (HerniPlan plan, Batoh batoh) 
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje a je odemčený (přístupný), vstoupí se do nového prostoru.
     *  Jinak se vypíše příslušné chybové hlášení.
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved (String... parametry) 
    {
        if (parametry.length == 0) 
        {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu.";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor ().vratSousedniProstor (smer);
        
        if (sousedniProstor == null) {return "Tam se odsud jít nedá!";}
        if (sousedniProstor.getUzamceni ()) {return "Tam teď jít nemůžeš, zkus nejdřív použít nějakou věc na dveře či překážku.";} 
        if ((sousedniProstor.getNazev ().equals ("tajný_východ")) && (batoh.getVec ("relikvie") == null)) {return "Zatím nemůžeš odejít, potřebuješ najít a vzít relikvii.";}
                else 
                {
                    plan.setAktualniProstor (sousedniProstor);
                    // Pokud hráč poprvé vstoupí do druhé části dolu, tak ho to tam zavalí (sousedniProstor již obsahuje aktuální prostor)
                    if (sousedniProstor.getNazev().equals ("druhá_část_dolu") && ( ! plan.getZavaleno ())) 
                    {
                        sousedniProstor.vratSousedniProstor ("první_část_dolu").setUzamceni (true); 
                        plan.setZavaleno ();
                        return "Tak tak jsi prošel a důl za tebou se zavalil. " + sousedniProstor.dlouhyPopis ();
                    } 
                    if (sousedniProstor.getNazev ().equals ("tajný_východ")) {return "Vyhrál jsi! Gratuluji.";}
                    return sousedniProstor.dlouhyPopis ();
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

}
