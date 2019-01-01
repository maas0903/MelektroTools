/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

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
        String result = ExtAPIs.GetIssWhen(ProxyToUse, ProxyPortToUse, lat, lon, count);
        assertTrue(result.length()>0);
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
     * Test of GetToday method, of class ExtAPIs.
     */
    @Test
    public void testGetToday() throws Exception {
        System.out.println("GetToday");
        String ProxyToUse = "";
        String ProxyPortToUse = "";
        String expResult = "";
        String result = ExtAPIs.GetToday(ProxyToUse, ProxyPortToUse);
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
    }
    
}
