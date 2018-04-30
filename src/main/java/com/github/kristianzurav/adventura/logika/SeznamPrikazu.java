package com.github.kristianzurav.adventura.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Class SeznamPrikazu - eviduje seznam přípustných příkazů adventury.
 *  Používá se pro rozpoznávání příkazů
 *  a vrácení odkazu na třídu implementující konkrétní příkaz.
 *  Každý nový příkaz (instance implementující rozhraní Prikaz) se
 *  musí do seznamu přidat metodou vlozPrikaz.
 *
 *  Tato třída je součástí jednoduché adventury.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 *@version    1.00
 */
public class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private  Map<String,IPrikaz> mapaSPrikazy;
    private Map<String,IPrikaz> konecnePrikazy; 
    
   
    
    /**
     * Konstruktor
     */
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }
    
    
    /**
     * Vkládá nový příkaz.
     *
     *@param  prikaz  Instance třídy implementující rozhraní IPrikaz
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(),prikaz);
    }
    
    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     *@param  retezec  klíčové slovo příkazu, který chce hráč zavolat
     *@return          instance třídy, která provede požadovaný příkaz
     */
    public IPrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     *@param  retezec  Řetězec, který se má otestovat, zda je přípustný příkaz
     *@return          Vrací hodnotu true, pokud je zadaný
     *                     řetězec přípustný příkaz
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    /**
     *  Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *  
     *  @return     Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }
    
    
    /**
     *  Vrací seznam přípustných příkazů, 
     *  které budou využity v comboboxu v grafickém UI.
     *  Takto získaný seznam platných příkazů nelze nadále upravovat 
     *  protože z hlediska správného návrhu je to plně
     *  záležitostí třídy SeznamPrikazu.
     *  
     *  @return     Řetězec, který obsahuje seznam 
     *  příkazů pro combobox v GUI
     */
    public Collection<String>  getPrikazy () 
    {
    	konecnePrikazy = new HashMap<> ();
    	
    	for (String prikazy : mapaSPrikazy.keySet()) 
    	{
    	    if (prikazy != "jdi"  
    	    		&& prikazy != "nápověda"  
    	    		&& prikazy != "konec" 
    	    		&& prikazy != "inventář") 
    	    	konecnePrikazy.put(prikazy, mapaSPrikazy.get(prikazy));
    	}
    	
    	
    	return Collections.unmodifiableCollection (konecnePrikazy.keySet());
    }
    
}





