/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import Model.Character;
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
public class MapCellTreasureTest {
    
    public MapCellTreasureTest() {
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
     * Test of canOverlap method, of class MapCellTreasure.
     */
    @Test
    public void testCanOverlap() {
        System.out.println("canOverlap");
        MapCellTreasure instance = new MapCellTreasure(1,2,null,null);
        boolean expResult = true;
        boolean result = instance.canOverlap();
        assertEquals(expResult, result);
        
    }
    @Test
    public void testGetItem()
    {
        
    }

 
}
