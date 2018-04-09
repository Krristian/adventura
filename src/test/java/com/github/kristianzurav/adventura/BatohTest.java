/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.kristianzurav.adventura;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.kristianzurav.adventura.logika.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Kristian Žurav
 * @version   1.00
 */
public class BatohTest
{
    private Vec pacidlo;
    private Vec lopata;
    private Vec klic;
    private Vec relikvie;
    private Batoh inventar;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp ()
    {
       inventar = new Batoh ();
       relikvie = new Vec ("relikvie",true);
       klic = new Vec ("klíč",true);
       lopata = new Vec ("lopata",true);
       pacidlo = new Vec ("páčidlo",true);
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown ()
    {
        // momentálně nevyužijeme
    }

    //== VLASTNÍ TESTY =========================================================
    
         /********************************************************************
          * Testuje vložení a oodebrání věci do batohu/inventáře a jejich přítomnost tam.
          */
        @Test
        public void testPridejAOdeber ()
         {
             assertEquals (true, inventar.pridejVec (pacidlo));
             assertEquals (true, inventar.pridejVec (klic));
             assertEquals (true, inventar.pridejVec (lopata));
             assertEquals (false, inventar.pridejVec (relikvie)); // již se tam nevejde
             assertFalse (inventar.ukazObsah().contains("relikvie"));
             assertTrue (inventar.ukazObsah().contains("lopata"));
             assertTrue (inventar.ukazObsah().contains("klíč"));
             assertTrue (inventar.ukazObsah().contains("páčidlo"));
             assertEquals (true, inventar.odeberVec (lopata));
             assertEquals (true, inventar.pridejVec (relikvie));
             assertTrue (inventar.ukazObsah().contains("relikvie"));
             assertFalse (inventar.ukazObsah().contains("lopata"));
             assertEquals (false, inventar.odeberVec (lopata)); 
             assertEquals (false, inventar.pridejVec (relikvie));
             
         }
         
        /********************************************************************
        * Testuje správnou funkci metody na vypsání jmen věcí z batohu/inventáře
        */ 
        @Test
        public void testUkazObsah ()
         {
            inventar.pridejVec (pacidlo);
            inventar.pridejVec (relikvie);
            inventar.pridejVec (klic);
            assertFalse (inventar.ukazObsah().contains("lopata"));
            assertTrue (inventar.ukazObsah().contains("klíč"));
            assertTrue (inventar.ukazObsah().contains("páčidlo"));
            assertTrue (inventar.ukazObsah().contains("relikvie"));        
         }
         
         /********************************************************************
          * Testuje záskání odkazu na věc z batohu/inventáře po zadání jména
          */
        @Test
        public void testGetVec ()
         {
            inventar.pridejVec (pacidlo);
            inventar.pridejVec (relikvie);
            inventar.pridejVec (klic);
            assertNotNull (inventar.getVec("páčidlo"));
            assertNotNull (inventar.getVec("relikvie"));
            assertNotNull (inventar.getVec("klíč"));
            assertNull (inventar.getVec("lopata"));
            assertEquals (relikvie, inventar.getVec ("relikvie"));
            
         }
         
         
         
}
