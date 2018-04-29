package com.github.kristianzurav.adventura.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry,
 *  dále instanci třídy Batoh, která představuje inventář věcí hráče
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra 
{
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan), inventář (prostřednictvím třídy Batoh) a seznam platných příkazů.
     */
    public Hra () 
    {
        herniPlan = new HerniPlan ();
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz (new PrikazNapoveda (platnePrikazy));
        platnePrikazy.vlozPrikaz (new PrikazJdi (herniPlan, batoh));
        platnePrikazy.vlozPrikaz (new PrikazKonec (this));
        platnePrikazy.vlozPrikaz (new PrikazSeber (herniPlan, batoh));
        platnePrikazy.vlozPrikaz (new PrikazInventar (batoh));
        platnePrikazy.vlozPrikaz (new PrikazOdhod (herniPlan, batoh));
        platnePrikazy.vlozPrikaz (new PrikazPouzij (herniPlan, batoh));
        platnePrikazy.vlozPrikaz (new PrikazProhledni (herniPlan, batoh));
        platnePrikazy.vlozPrikaz (new PrikazZadej (herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     *  @return  řetězec s uvítáním a dlouhým popisem prostoru
     */
    public String vratUvitani () 
    {
        return "Vítej!\n" +
               "Student Stanislav Novotný je na své cestě za získáním \ntří starobylých a tajuplných relikvií.\n" +
               "Pomůžeš mu jednu z nich najít někde \nv srdci starého nacistického dolu?\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *  @return  řetězec s epilogem
     */
    public String vratEpilog () 
    {
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     * @return  konecHry  hodnota true= konec hry, false = hra pokračuje
     */
     public boolean konecHry () 
     {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz (String radek) 
     {
        String [] slova = radek.split ("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i = 0 ; i < parametry.length ; i++)
        {
            parametry[i] = slova[i+1];   
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz (slovoPrikazu)) 
        {
            IPrikaz prikaz = platnePrikazy.vratPrikaz (slovoPrikazu);
            textKVypsani = prikaz.proved (parametry);
            if(herniPlan.jeVyhra ()) {konecHry = true;}
        }
        else 
        {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota true= konec hry, false = hra pokračuje
     */
     
     public void setKonecHry (boolean konecHry) 
    {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan ()
     {
        return herniPlan;
     }
     
    /**
     *  Metoda vrátí odkaz na batoh, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává obsah batohu.
     *  
     *  @return     odkaz na batoh
     */
     public Batoh getBatoh ()
     {
         return batoh;
     }
     
     
     /**
      *  Metoda vrátí seznam platných příkazů ve hře
      *  
      *  @return     odkaz na seznam platných příkazů
      */
     public SeznamPrikazu getPlatnePrikazy ()
     {
         return platnePrikazy;
     }
    
}

