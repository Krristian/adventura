/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura.logika;
import java.util.*;



/*******************************************************************************
 * Instance třídy Batoh představuje inventář hráče, kam vkládá předměty, které přenáší.
 * Umožňuje věci vkládat do inventáře a také je z něj odebírat.
 * Taktéž umožňuje vrátit řetězec s názvy všech věcí v inventáři nebo vrátit kokrétní věc z inventáře podle zadaného názvu.
 * Tato třída je součástí jednoduché adventury.
 * 
 * @author    Kristian Žurav
 * @version   1.00
 */
public class Batoh extends Observable
{
    //== Datové atributy (statické i instancí)======================================
    private Set<Vec> obsahBatohu; // Reprezentuje inventář hráče
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor vytvoří prázdný obsah batohu (neboli inventář)
     */
    public Batoh ()
    {
        obsahBatohu = new HashSet<> ();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda přidává věci do batohu/inventáře, ale pouze 
     *  pokud je jich již v inventáři méně, jak tři.
     *  Také notifikuje observer o změně (pokud proběhla)
     *  @param vec  věc, kterou chceme do batohu/inventáře přidat
     *  @return vrací hodnotu podle toho, jestli bylo přidání úspěšné   hodnota= true bylo úspěšné hodnota= false  nebylo úspěšné
     */
    public boolean pridejVec (Vec vec)  
    {
    	
        if (obsahBatohu.size () < 3) {this.setChanged();
        this.notifyObservers(); return obsahBatohu.add (vec);}
        return false;
    }
    
    /** 
     * Metoda vypisuje obsah batohu/inventáře (respektive jména všech věcí v batohu).
     * @return řetězec obsahující jména všech věcí v batohu/inventáři (případně 'Inventář je prázdný')
     */
    public String ukazObsah () 
    {
        String odpoved = "";
        if (obsahBatohu.isEmpty ()) {odpoved = "Inventář je prázdný";}
        else 
        {
            for (Vec veci : obsahBatohu)
            {
                odpoved = odpoved + " " + veci.getNazev();
            }
        }
        return odpoved;
    }
    
    /**
     * Metoda odebírá věci z batohu/inventáře 
     * a notifikuje observer o změně
     * @param vec    věc, kterou cheme odebrat z inventáře
     * @return vrací hodnotu úspěšnosti operace     hodnota= true odebrání proběhlo v pořádku    hodnota= false odebrání se nepovedlo
     */
     public boolean odeberVec (Vec vec)  
    {
    	 this.setChanged();
         this.notifyObservers();
        return obsahBatohu.remove (vec);
        
    }
    
    /**
     * Metoda vrací odkaz na věc z batohu/inventáře, kterou jsme hledali podle jejího jména.
     * @param nazev název hledané věci
     * @return vec vrací odkaz na věc, kterou jsme hledali (pokud tam věc není, vrací null)
     */
    public Vec getVec (String nazev) 
    {
        for (Vec hledana : obsahBatohu)
        {
            if (hledana.getNazev ().equals (nazev)) {return hledana;}
        }
        return null;
    }
    
    /**
     * Metoda pro zjištění zda se daná věc nachází v batohu.
     * @param nazev nazev název věci
     * @return boolean vrací hodnotu true, když se věc v batohu nachází a false, když ne
     */
    public boolean getVecUi (String nazev) 
    {
        for (Vec hledana : obsahBatohu)
        {
            if (hledana.getNazev ().equals (nazev)) {return true;}
        }
        return false;
    }
    
    
    //== Soukromé metody (instancí i třídy) ========================================

}
