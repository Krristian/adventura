package com.github.kristianzurav.adventura;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.kristianzurav.adventura.logika.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================
    private Prostor prostor1;
    private Prostor prostor2;
    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() 
    {
        prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",false);
        prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",true);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() 
    {
        // momentálně nevyužijeme
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testJeSousedni () 
    {      
        prostor1.setVychod (prostor2);
        prostor2.setVychod (prostor1);
        assertEquals (prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals (null, prostor2.vratSousedniProstor("pokoj"));
        assertEquals (prostor1, prostor2.vratSousedniProstor("hala"));
    }
    
    
    /**
     * Testuje, zda se dají do prostoru vkládat věci, jestli jsou přítomny a jestli se dají odebírat. 
     * Prostory nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testVeciVProstoru () 
    {        
        Vec relikvie = new Vec ("relikvie", true);
        Vec lopata = new Vec ("lopata", true);
        prostor1.vlozVec (lopata);
        prostor2.vlozVec (relikvie);
        assertEquals (lopata, prostor1.jePritomna("lopata"));
        assertEquals (null, prostor1.jePritomna("relikvie"));
        assertEquals (relikvie, prostor2.jePritomna("relikvie"));
        assertEquals (lopata, prostor1.odeberVec("lopata"));
        assertEquals (null, prostor1.jePritomna("lopata"));
        assertEquals (null, prostor1.odeberVec("relikvie"));
    }
    
    /**
     * Testuje, zda je správně nastaveno uzamčení prostorů a jestli správně funguje nastevení uzamčení. 
     * Prostory nemusí odpovídat vlastní hře.
     */
    @Test
    public  void testUzamceni() 
    {      
        assertEquals (false, prostor1.getUzamceni());
        assertEquals (true, prostor2.getUzamceni());
        prostor1.setUzamceni(true);
        prostor2.setUzamceni(false);
        assertEquals (true, prostor1.getUzamceni());
        assertEquals (false, prostor2.getUzamceni());
    }
}
