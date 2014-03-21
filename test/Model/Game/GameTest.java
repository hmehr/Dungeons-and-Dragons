/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Game;

import Model.Map.MapCell;
import Model.Map.MapCellEnd;
import Model.Map.MapCellStart;
import Model.Character;
import Model.Map.Map;
import Model.Map.MapCellTreasure;
import java.util.ArrayList;
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
public class GameTest {

    private Game _game = null;
    private Map _map = new Map(10, 10);
    private Character _ch = new Character();

    public GameTest() {
        MapCell cellStart = new MapCellStart(2,2,"","");
        MapCell cellEnd = new MapCellStart(8,8,"","");
        _map.addMapElement(cellStart);
        _map.addMapElement(cellEnd);
        _game = new Game(_ch, _map);
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
     * Test of canAttack method, of class Game.
     */
    @Test
    public void testCanAttack() {
        System.out.println("canAttack");
        boolean expResult = true;
        boolean result = _game.canAttack();
        assertEquals(expResult, result);
    }


    /**
     * Test of isPlayerWin method, of class Game.
     */
    @Test
    public void testIsPlayerWin() {
        System.out.println("isPlayerWin");
        boolean expResult = true;
        boolean result = _game.isPlayerWin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of isGameOver method, of class Game.
     */
    @Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        boolean expResult = false;
        boolean result = _game.isGameOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPlayer method, of class Game.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Character expResult = _ch;
        Character result = _game.getPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
