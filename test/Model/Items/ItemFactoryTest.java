/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Items;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author h_xuexia
 */
public class ItemFactoryTest {

    public ItemFactoryTest() {
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
     * Test of getItemByName method, of class ItemFactory.
     */
    @Test
    public void testGetItemByName() {
        System.out.println("getItemByName");
        String pName = "longsword";
        Item result = ItemFactory.getItemByName(pName);
        assertEquals(LongSword.class, result.getClass());
        pName = "boot";
        result = ItemFactory.getItemByName(pName);
        assertEquals(Boot.class, result.getClass());
        pName = "shield";
        result = ItemFactory.getItemByName(pName);
        assertEquals(Shield.class, result.getClass());
        pName = "healingpotion";
        result = ItemFactory.getItemByName(pName);
        assertEquals(HealingPotion.class, result.getClass());
        pName = "dummy";
        result = ItemFactory.getItemByName(pName);
        assertEquals(null, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}