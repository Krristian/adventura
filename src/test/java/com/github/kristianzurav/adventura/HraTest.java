package com.github.kristianzurav.adventura;

import org.junit.After;
import com.github.kristianzurav.adventura.logika.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Kristian Žurav
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

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
        hra1 = new Hra();
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

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() 
    {
        assertEquals ("před_dolem", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi první_část_dolu");
        assertEquals (false, hra1.konecHry());
        assertEquals ("první_část_dolu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi zavalená_chodba");
        assertEquals (false, hra1.konecHry());
        assertEquals ("zavalená_chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("prohlédni kostra");
        assertNotNull (hra1.getBatoh().getVec("klíč"));
        hra1.zpracujPrikaz ("jdi první_část_dolu");
        assertEquals (false, hra1.konecHry());
        assertEquals ("první_část_dolu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi druhá_část_dolu");
        assertEquals (false, hra1.konecHry());
        assertEquals ("druhá_část_dolu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi první_část_dolu");
        assertEquals ("druhá_část_dolu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("seber lopata");
        assertNotNull (hra1.getBatoh().getVec("lopata"));
        hra1.zpracujPrikaz ("použij lopata zával");
        hra1.zpracujPrikaz ("jdi první_část_dolu");
        assertEquals (false, hra1.konecHry());
        assertEquals ("první_část_dolu", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi druhá_část_dolu");
        assertEquals(hra1.zpracujPrikaz ("seber ocelové_dveře"),"Tohle vzít nemůžeš.");
        assertEquals(hra1.zpracujPrikaz ("jdi hala"),"Tam teď jít nemůžeš, zkus nejdřív použít nějakou věc na dveře či překážku.");
        hra1.zpracujPrikaz ("použij klíč ocelové_dveře");
        hra1.zpracujPrikaz ("jdi hala");
        assertEquals (false, hra1.konecHry());
        assertEquals ("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi zasedačka");
        assertEquals (false, hra1.konecHry());
        assertEquals ("zasedačka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi tajný_východ");
        assertEquals ("zasedačka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals (false, hra1.konecHry());
        hra1.zpracujPrikaz ("jdi hala");
        assertEquals (false, hra1.konecHry());
        assertEquals ("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi sklad");
        assertEquals (false, hra1.konecHry());
        assertEquals ("sklad", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("seber páčidlo");
        assertNotNull (hra1.getBatoh().getVec("páčidlo"));
        hra1.zpracujPrikaz ("jdi hala");
        assertEquals (false, hra1.konecHry());
        assertEquals ("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi kancelář");
        assertEquals ("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals (hra1.zpracujPrikaz ("prohlédni dveře_kanceláře"),"Dveře jsou zamčené, ale nevypadají, že jsou moc silné.");
        hra1.zpracujPrikaz ("použij páčidlo dveře_kanceláře");
        hra1.zpracujPrikaz ("jdi kancelář");
        assertEquals (hra1.zpracujPrikaz ("prohlédni obraz"),"Na obraze je portrét pruského císaře Víléma I. \nDole v rohu je rok vytvoření malby: 1888.");
        assertEquals (hra1.zpracujPrikaz ("prohlédni velký_trezor"),"Na dveřích trezoru je čtyřciferný číselný zámek, \nmusíš zadat správnou kombinaci.");
        assertEquals(hra1.zpracujPrikaz ("seber velký_trezor"),"Tohle vzít nemůžeš.");
        assertEquals (hra1.zpracujPrikaz ("zadej 18s5 velký_trezor"),"Musíš zadat pouze číselnou čtyřcifernou kombinaci!");
        assertEquals (hra1.zpracujPrikaz ("zadej 1111 velký_trezor"),"Kombinace je bohužel špatná, zkus to znovu.");
        assertEquals (hra1.zpracujPrikaz ("zadej 1888 velký_trezor"),"Kombinace je správná, můžeš vstoupit do trezoru. \n"+hra1.getHerniPlan().getAktualniProstor().kratkyPopis());
        assertEquals (hra1.zpracujPrikaz ("zadej 1888 obraz"),"Sem nic zadat bohužel nejde.");
        hra1.zpracujPrikaz ("jdi trezor");
        assertEquals (false, hra1.konecHry());
        assertEquals ("trezor", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("seber relikvie");
        assertNull (hra1.getBatoh().getVec("relikvie"));
        hra1.zpracujPrikaz ("odhoď lopata");
        hra1.zpracujPrikaz ("seber relikvie");
        assertNotNull (hra1.getBatoh().getVec("relikvie"));
        assertEquals (hra1.zpracujPrikaz ("prohlédni police"),"Našel jsi kompletní plány bunkru. Je tam zakreslen \ni tajný východ umístěný v zasedací místnosti za knihovnou.");
        assertEquals (hra1.zpracujPrikaz ("prohlédni kostra"),"Tohle si prohlédnout nemůžu, protože to tu není.");
        assertEquals (false, hra1.konecHry());
        hra1.zpracujPrikaz ("jdi kancelář");
        assertEquals (false, hra1.konecHry());
        assertEquals ("kancelář", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi hala");
        assertEquals (false, hra1.konecHry());
        assertEquals ("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi zasedačka");
        assertEquals (false, hra1.konecHry());
        assertEquals ("zasedačka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz ("jdi tajný_východ");
        assertEquals ("tajný_východ", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals (true, hra1.konecHry());
    }
}
