/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;





/**
 *  Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 *@version    1.00
 */
public interface IHra
{
    //== VEŘEJNÉ KONSTANTY =====================================================
    //== DEKLAROVANÉ METODY ====================================================
    /**
     *  Vrátí úvodní zprávu pro hráče.
     *  
     *  @return  vrací se řetězec, který se má vypsat na obrazovku
     */
    public String vratUvitani();
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *  
     *  @return  vrací se řetězec, který se má vypsat na obrazovku
     */
    public String vratEpilog();
    
    /** 
     * Vrací informaci o tom, zda hra již skončila, je jedno zda výhrou, prohrou nebo příkazem konec.
     * 
     * @return   vrací true, pokud hra skončila
     */
     public boolean konecHry();
     
      /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek);
     
     
     /**
      *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
      *  mohou ji použít i další implementace rozhraní Prikaz.
      *  
      *  @param  konecHry  hodnota true= konec hry, false = hra pokračuje
      */
     public void setKonecHry (boolean konecHry); 
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan();
     
     
     /**
      *  Metoda vrátí odkaz na batoh, je využita hlavně v testech,
      *  kde se jejím prostřednictvím získává obsah batohu.
      *  
      *  @return     odkaz na batoh
      */
     public Batoh getBatoh();
     
     /**
      *  Metoda vrátí seznam platných příkazů ve hře
      *  
      *  @return     odkaz na seznam platných příkazů
      */
     public SeznamPrikazu getPlatnePrikazy ();
     
    //== ZDĚDĚNÉ METODY ========================================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
}
