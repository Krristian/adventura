/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.kristianzurav.adventura.logika.*;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class VecTest
{
    private Vec lopata;
    private Vec relikvie;
    private Vec kostra;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
       relikvie = new Vec ("relikvie", true);
       kostra = new Vec ("kostra", false);
       lopata = new Vec ("lopata", true);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
        // momentálně nevyužijeme
    }

    //== VLASTNÍ TESTY =========================================================
    //
         /**
          * Testuje, zda metoda vrací sprvný název
          */
         @Test
         public void testGetNazev ()
         {
             assertEquals ("lopata", lopata.getNazev ());
             assertEquals ("kostra", kostra.getNazev ());
             assertEquals ("relikvie", relikvie.getNazev ());
         }
         
         /**
          * Testuje, zda metoda vrací správně přenositelnost věci
          */
         @Test
         public void testGetPrenositelnost ()
         {
             assertEquals (true, lopata.getPrenositelnost ());
             assertEquals (false, kostra.getPrenositelnost ());
             assertEquals (true, relikvie.getPrenositelnost ());
         }
         
         /**
          * Testuje, zda metoda správně nastavuje přenositelnost věci
          */
         @Test
         public void testSetPrenositelnost ()
         {
             lopata.setPrenositelnost(false);
             kostra.setPrenositelnost(true);
             assertEquals (false, lopata.getPrenositelnost ());
             assertEquals (true, kostra.getPrenositelnost ());
         }
}
