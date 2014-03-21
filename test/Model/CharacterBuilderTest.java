/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sayid
 */
public class CharacterBuilderTest
{
    Character _character;    
    CharacterDirector _characterDirector;

    public CharacterBuilderTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
        _character = new Character();
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testBullyCharacter()
    {
        boolean flag = false;
        CharacterBuilder _cBuilder;
        
        //check the ability for all 20 levels
        for(int i = 1; i <= 20; i++)
        {
            _cBuilder = new CharacterBuilderBully();
            _characterDirector = new CharacterDirector(_cBuilder, "Yuki", "", i);
            _characterDirector.constructCharacter();
            _character = _characterDirector.getCharacter();

            if(_character.getStrength() >= _character.getConstitution())
                if(_character.getConstitution() >= _character.getDexterity())
                    if (_character.getDexterity() >= _character.getIntelligence())
                        if(_character.getIntelligence() >= _character.getCharisma())
                            if(_character.getCharisma() >= _character.getWisdom())
                            flag = true;

            assertEquals("Bully Character: ", flag, true);
        }

    }

    @Test
    public void testNimbleCharacter()
    {
        boolean flag = false;
        CharacterBuilder _cBuilder;

        //check the ability for all 20 levels
        for(int i = 1; i <= 20; i++)
        {
            _cBuilder = new CharacterBuilderNimble();
            _characterDirector = new CharacterDirector(_cBuilder, "Yuki", "", i);
            _characterDirector.constructCharacter();
            _character = _characterDirector.getCharacter();

            if(_character.getDexterity() >= _character.getConstitution())
                if(_character.getConstitution() >= _character.getStrength())
                    if (_character.getStrength() >= _character.getIntelligence())
                        if(_character.getIntelligence() >= _character.getCharisma())
                            if(_character.getCharisma() >= _character.getWisdom())
                            flag = true;

            assertEquals("Ordered ability score for Nimble: ", flag, true);
        }

    }

    @Test
    public void testTankCharacter()
    {
        boolean flag = false;
        CharacterBuilder _cBuilder;

        //check the ability for all 20 levels
        for(int i = 1; i <= 20; i++)
        {
            _cBuilder = new CharacterBuilderTank();
            _characterDirector = new CharacterDirector(_cBuilder, "Yuki", "", i);
            _characterDirector.constructCharacter();
            _character = _characterDirector.getCharacter();

            if(_character.getConstitution() >= _character.getDexterity())
                if(_character.getDexterity() >= _character.getStrength())
                    if (_character.getStrength() >= _character.getIntelligence())
                        if(_character.getIntelligence() >= _character.getCharisma())
                            if(_character.getCharisma() >= _character.getWisdom())
                            flag = true;

            assertEquals("Ordered ability score for Tank: ", flag, true);
        }

    }
}
