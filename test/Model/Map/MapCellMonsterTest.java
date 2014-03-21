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
public class MapCellMonsterTest {
    
    public MapCellMonsterTest() {
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
     * Test of canOverlap method, of class MapCellMonster.
     */
    @Test
    public void testCanOverlap() {
        System.out.println("canOverlap");
        MapCellMonster instance = new MapCellMonster(1,2,null,null);
        boolean expResult = false;
        boolean result = instance.canOverlap();
        assertEquals(expResult, result);
       
    }
}
