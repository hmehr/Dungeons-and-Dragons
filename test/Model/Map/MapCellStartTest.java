/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yukywang
 */
public class MapCellStartTest {
    
    public MapCellStartTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of canOverlap method, of class MapCellStart.
     */
    @Test
    public void testCanOverlap() {
        System.out.println("canOverlap");
        MapCellStart instance = new MapCellStart (1,1,null,null);
        boolean expResult = true;
        boolean result = instance.canOverlap();
        assertEquals(expResult, result);
        
    }
}
