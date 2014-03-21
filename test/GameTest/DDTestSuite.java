/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTest;

import Controller.Character.AbilityScoreGenerationTest;
import Model.CharacterBuilderTest;
import Model.CharacterTest;
import Model.Dice;
import Model.Game.GameTest;
import Model.Items.ItemFactoryTest;
import Model.Map.MapCellEndTest;
import Model.Map.MapCellMonsterTest;
import Model.Map.MapCellStartTest;
import Model.Map.MapCellTreasureTest;
import Model.Map.MapTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



/**
 *
 * @author eric
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value ={
    CharacterTest.class, MapCellEndTest.class, MapCellMonsterTest.class,
    AbilityScoreGenerationTest.class, ItemFactoryTest.class, GameTest.class,
   //Dice.class,
    MapCellStartTest.class, MapCellTreasureTest.class, MapTest.class,CharacterBuilderTest.class
})
public class DDTestSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
}
