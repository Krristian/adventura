package com.github.kristianzurav.adventura.logika;

import java.util.*;
//import java.util.stream.Collectors;



/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Kristian Žurav
 * @version pro školní rok 2015/2016 LS
 */
public class Prostor 
{

    private String nazev;
    private String popis;
    private boolean jeUzamceno;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;
    private Set<Prostor> otevreneVychody; 
    private double souradniceX;
    private double souradniceY;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, boolean jeUzamceno) 
    {
        this.nazev = nazev;
        this.popis = popis;
        this.jeUzamceno = jeUzamceno;
        vychody = new HashSet<> ();
        veci = new HashMap<> ();
        souradniceX=-155;
        souradniceY=-15;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod (Prostor vedlejsi)
    {
        vychody.add (vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals (Object o) 
    {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) 
        {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) 
        {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals (this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode () 
    {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode (this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev () 
    {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis () 
    {
        return "Jsi v prostoru " + popis + ".\n"
                + popisVychodu () + "\n"
                + popisVeci ();
    }
    
    /**
     * Vrací "krátký" popis prostoru, který může vypadat následovně: 
     * vychody: chodba bufet ucebna
     * vevi: lopata
     *
     * @return krátký popis prostoru
     */
    public String kratkyPopis ()
    {   
        return popisVychodu () + "\n" + popisVeci ();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() 
    {
        String vracenyText = "Východy:";
        for (Prostor sousedni : vychody) 
        {
            if ( ! sousedni.getUzamceni ()) vracenyText += " " + sousedni.getNazev ();
        }
        return vracenyText;
    }
    
    /**
     * Vrací textový řetězec, který jmenuje věci v aktuálním prostoru například:
     * "veci: lopata ".
     *
     * @return názvy příotmných věcí
     */
    private String popisVeci ()
    {
        String vracenyText = "Věci:";
        for (String nazev : veci.keySet ())
        {
            vracenyText += " " + nazev;
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    
   /** public Prostor vratSousedniProstor (String nazevSouseda) 
    {
        List<Prostor>hledaneProstory = 
            vychody.stream ()
                   .filter (sousedni -> sousedni.getNazev ().equals (nazevSouseda))
                   .collect (Collectors.toList ());
        if(hledaneProstory.isEmpty ()){
            return null;
        }
        else {
            return hledaneProstory.get (0);
        }
    }
    */
    public Prostor vratSousedniProstor(String nazevSouseda) 
    {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;  // prostor nenalezen
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody () 
    {
    	otevreneVychody = new HashSet<> ();
        
        for (Prostor sousedni : vychody) 
        {
            if ( ! sousedni.getUzamceni ()) otevreneVychody.add(sousedni);
        }
        return Collections.unmodifiableCollection (otevreneVychody);
    }
    
    public Collection<Vec>  getVeci () 
    {
    	return Collections.unmodifiableCollection (veci.values());
    }
    
     /**
     * Metoda pro vkládání věcí do prostoru.
     * @param neco věc, kterou chceme vložit
     */
    public void vlozVec (Vec neco)
    {
        veci.put (neco.getNazev (),neco);
    }            
    
     /**
     * Metoda pro odebírání věcí z prostoru.
     * @param nazev Název věci, kterou odebíráme
     * @return odkaz na odebíranou věc
     */
    public Vec odeberVec(String nazev)
    {
        return veci.remove (nazev);
    }
    
     /**
     * Metoda pro zjištění přítomnosti věci v prostoru.
     * @param nazev název věci
     * @return vrátí odkaz na věc, když se tam nachází, jinak null
     */
    public Vec jePritomna (String nazev)
    {
        return veci.get (nazev);
    }
    
     /**
     * Metoda pro nastavení přístupnosti prostoru.
     * @param jeUzamceno nastavíme true nebo false (true je odemčená/přístupná místnost
     */
    public void setUzamceni (boolean jeUzamceno)
    {
        this.jeUzamceno = jeUzamceno;
    }
    
     /**
     * Metoda pro zjištění přístupnosti prostoru.
     * @return true, když je přístupná, jinak false
     */
    public boolean getUzamceni ()
    {
        return jeUzamceno;
    }
    
    @Override
    public String toString() {
    	return getNazev();
    }
    
    public void setSouradnice(double x, double y) {
    	 souradniceX=x;
    	 souradniceY=y;
    }
    
    public double getX() {
   	 	return souradniceX;
   	 
   }
    public double getY() {
      	 return souradniceY;
      	 
      }
    
    
    
}
