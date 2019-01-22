/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import static com.melektro.Tools.ExtAPIs.GetDatePartWithFormat;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marius
 */
public class ExtAPIsIT {
    
    public ExtAPIsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of GetPublicIp method, of class ExtAPIs.
     */
    @Test
    public void testGetPublicIp() throws Exception {
        System.out.println("GetPublicIp");
        String ProxyToUse = "";
        String ProxyPortToUse = "";
        String expResult = "";
        String result = ExtAPIs.GetPublicIp(ProxyToUse, ProxyPortToUse);
        assertTrue(result.length()>0);
    }

    /**
     * Test of GetIss method, of class ExtAPIs.
     */
    @Test
    public void testGetIss() throws Exception {
        System.out.println("GetIss");
        String ProxyToUse = "";
        String ProxyPortToUse = "";
        String expResult = "";
        String result = ExtAPIs.GetIss(ProxyToUse, ProxyPortToUse);
        assertTrue(result.length()>0);
    }

    /**
     * Test of GetIssWhen method, of class ExtAPIs.
     */
    @Test
    public void testGetIssWhen() throws Exception {
        System.out.println("GetIssWhen");
        String ProxyToUse = "";
        String ProxyPortToUse = "";
        String lat = "50.918681";
        String lon = "4.698870";
        String count = "5";
        String expResult = "";
        String result = ExtAPIs.GetIssWhen(ProxyToUse, ProxyPortToUse, lat, lon, count, 1);
        assertTrue(result.length()>0);
        assertTrue(result.contains("success"));
        assertTrue(result.contains("altitude"));
        assertTrue(result.contains("datetime"));
        assertTrue(result.contains("latitude"));
        assertTrue(result.contains("longitude"));
        assertTrue(result.contains("altitude"));
    }

    /**
     * Test of GetDatePartWithFormat method, of class ExtAPIs.
     */
    @Test
    public void testGetDatePartWithFormat() {
        System.out.println("GetDatePartWithFormat");
        String format = "MMMM";
        String expResult = "";
        String result = ExtAPIs.GetDatePartWithFormat(format);
        assertTrue(result.length()>0);
    }

    /**
     * Test of GetAday method, of class ExtAPIs.
     */
    @Test
    public void testGetToday() throws Exception {
        System.out.println("GetToday");
        String ProxyToUse = "";
        String ProxyPortToUse = "";
        String expResult = "";
        String dateToday = GetDatePartWithFormat("MMMM") + "_" + GetDatePartWithFormat("d");
        String result = ExtAPIs.GetAday(dateToday, ProxyToUse, ProxyPortToUse);
        assertTrue(result.length()>0);
    }

    /**
     * Test of GetToday_Formatted method, of class ExtAPIs.
     */
    @Test
    public void testGetToday_Formatted() throws Exception {
        System.out.println("GetToday_Formatted");
        String result = ExtAPIs.GetToday_Formatted();
        assertTrue(result.length()>0);
        assertFalse(result.contains("Indexes not calculated"));
        assertFalse(result.contains("Cannot Parse"));
        assertTrue(result.contains("date"));
        assertTrue(result.contains("data"));
        assertTrue(result.contains("Events"));
        assertTrue(result.contains("Births"));
        assertTrue(result.contains("Deaths"));
    }
    
    /**
     * Test of GetADay_Formatted method, of class ExtAPIs.
     */
    @Test
    public void GetADay_FormattedOK() throws Exception {
        System.out.println("GetADay_Formatted");
        String result = ExtAPIs.GetADay_Formatted("december_9");
        assertTrue(result.length()>0);
        assertFalse(result.contains("Indexes not calculated"));
        assertFalse(result.contains("Cannot Parse"));
        assertTrue(result.contains("date"));
        assertTrue(result.contains("data"));
        assertTrue(result.contains("Events"));
        assertTrue(result.contains("Births"));
        assertTrue(result.contains("Deaths"));    }
    
    @Test
    public void GetADay_FormattedKO() throws Exception {
        System.out.println("GetADay_Formatted");
        String result = ExtAPIs.GetADay_Formatted("octember_5");
        assertTrue(result.length()>0);
        assertTrue(result.contains("is not a valid date."));
    }
    
    @Test
    public void GetExchangeRate() throws IOException, InterruptedException{
        System.out.println("GetExchangeRate");
        String result = ExtAPIs.GetExchangeRate("EUR", "ZAR");
        assertTrue(result.length()>0);
        assertTrue(result.contains("EUR_ZAR"));
    }
    
}
