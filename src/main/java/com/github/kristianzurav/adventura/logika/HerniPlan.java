package com.github.kristianzurav.adventura.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan  extends Observable
{
    
    private Prostor aktualniProstor;
    private boolean prohledalKostru = false;
    private boolean zavaleno = false;
    
    
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan () 
    {
        zalozProstoryHry ();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry () 
    {
        // vytvářejí se jednotlivé prostory
        Prostor venku = new Prostor ("před_dolem","před vchodem do dolu", false);
        Prostor dul1 = new Prostor ("první_část_dolu","první části dolu",false);
        Prostor dul2 = new Prostor ("druhá_část_dolu","druhé části dolu",false);
        Prostor zavalenaChodba = new Prostor ("zavalená_chodba","chodby dolu, která je zavalená",false);
        Prostor hala = new Prostor ("hala","haly tajného bunkru. Ocelové dveře vedoucí do dolu \nse bohužel zaklaply a odsuď nejdou otevřít",true);
        Prostor kancelar = new Prostor ("kancelář","kanceláře",true);
        Prostor sklad = new Prostor ("sklad","skladu",false);
        Prostor zasedacka = new Prostor ("zasedačka","zasedací místnosti",false);
        Prostor tajnyVychod = new Prostor ("tajný_východ","",true);
        Prostor trezor= new Prostor ("trezor","velkého nacistického trezoru",true);
        
        
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        venku.setVychod (dul1);
        dul1.setVychod (zavalenaChodba);
        dul1.setVychod (venku);
        dul1.setVychod (dul2);
        zavalenaChodba.setVychod (dul1);
        dul2.setVychod (hala);
        hala.setVychod (kancelar);
        dul2.setVychod (dul1);
        hala.setVychod (sklad);
        hala.setVychod (zasedacka);
        kancelar.setVychod (hala);
        kancelar.setVychod (trezor);
        trezor.setVychod (kancelar);
        sklad.setVychod (hala); 
        zasedacka.setVychod (hala);
        zasedacka.setVychod (tajnyVychod);
        
        
      
                
        aktualniProstor = venku ;  // hra začíná venku před dolem 
        
        Vec oceloveDvere = new Vec ("ocelové_dveře",false);
        Vec kostra = new Vec ("kostra",false);
        Vec zaval = new Vec ("zával",false);
        Vec lopata = new Vec ("lopata",true);
        Vec pacidlo = new Vec ("páčidlo",true);
        Vec obraz = new Vec ("obraz",false);
        Vec dvereTrezoru = new Vec ("velký_trezor",false);
        Vec relikvie = new Vec ("relikvie", true);
        Vec dvereKancelare = new Vec ("dveře_kanceláře",false);
        Vec police = new Vec ("police",false);
        dul2.vlozVec (oceloveDvere);
        dul2.vlozVec (zaval);
        trezor.vlozVec (police);
        zavalenaChodba.vlozVec (kostra); 
        dul2.vlozVec (lopata);
        sklad.vlozVec (pacidlo);
        kancelar.vlozVec (obraz);
        kancelar.vlozVec (dvereTrezoru);     
        trezor.vlozVec (relikvie);
        hala.vlozVec (dvereKancelare);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor () 
    {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor (Prostor prostor) 
    {
       aktualniProstor = prostor;
       this.setChanged();
       this.notifyObservers();
    }
    
    /**
     *  Metoda zjišťuje aktuální stav hry, konkrétně jestli již hráč vyhrál.
     *
     *@return     hodnota = true hráč vyhrál    hodnota = false hráč ještě nevyhrál 
     */
    public boolean jeVyhra()
    {
        return aktualniProstor.getNazev ().equals ("tajný_východ");
    }
    
    /**
     *  Metoda zjišťuje, jestli již byla zavalena chodba 
     *  (aby se nezavalovala po každém vstupu do prostoru).
     *
     *@return     hodnota = true chodba již byla zavalena    
     *hodnota = false chodba ještě zavalena nebyla
     */
    public boolean getZavaleno ()
    {
        return zavaleno;
    }
    
    /**
     *  Metoda nastavuje, že již byla chodba zavalena 
     *  (aby se nezavalovala po každém vstupu do prostoru).
     *  Nemusí mít parametry, protože naopak (přiřazením false) 
     *  se nevyužívá (bylo by to nelogické).
     *
     */
     public void setZavaleno ()
    {
        zavaleno = true;
    }
    
    /**
     *  Metoda zjišťuje, jestli již byla prohledána kostra 
     *  (aby hráč nemohl získat několikrát stejnou věc z kostry).
     *
     *@return     hodnota = true kostra již byla prohledána   
     *hodnota = false kostra ještě prohledána nebyla
     */
     public boolean getProhledalKostru ()
    {
    	 
        return prohledalKostru;
    }
    
    /**
     *  Metoda nastavuje, že již byla kostra prohledána 
     * (aby hráč nemohl získat několikrát stejnou věc z kostry).
     *  Nemusí mít parametry, protože naopak (přiřazením false) 
     *  se nevyužívá (bylo by to nelogické).
     * 
     */
     public void setProhledalKostru ()
    {
        prohledalKostru = true;
        
    }
    
    
}
