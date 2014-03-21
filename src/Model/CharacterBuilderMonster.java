/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Character.AbilityScoreGeneration;
import Model.Map.MapCellMonster;

/**
 * This is a public class which implements character builder.
 * @author Sayid
 */
public class CharacterBuilderMonster implements CharacterBuilder
{
    
    Character _monster;
    AbilityScoreGeneration _asGenerate;
    int[] _abilityScore;

    /**
     * default constructor.
     */
    public CharacterBuilderMonster()
    {
        _monster = new MapCellMonster();
        _asGenerate = new AbilityScoreGeneration();
    }

    public void buildBodyParts()
    {
        _monster.createBodyParts();
    }

    public void buildInventoryItems()
    {
        //_monster.createInventoryItems();
    }

    public void buildAvatar(String pName, String pImagePath)
    {
        _monster.setName(pName);
        _monster.setAvatar(pImagePath);
        _monster.setType(CharacterType.MONSTER);
    }

    public void buildLevel(int pLevel)
    {
        _monster.setLevel(pLevel);

//        if(pLevel > 0)
//        {
//            for(int i=1; i <= pLevel; i++)
//                _monster.addLevel();
//        }
//
//        if(pLevel < 0)
//        {
//            for(int i=1; i <= Math.abs(pLevel); i++)
//                _monster.minusLevel();
//        }
    }

    public void buildAbilityScores()
    {
        _abilityScore = _asGenerate.createAbilityList();

        _monster.setDexterity(_abilityScore[0]);
        _monster.setConstitution(_abilityScore[1]);
        _monster.setStrength(_abilityScore[2]);
        _monster.setIntelligence(_abilityScore[3]);
        _monster.setCharisma(_abilityScore[4]);
        _monster.setWisdom(_abilityScore[5]);
    }

    public Character getCharacter()
    {
        return _monster;
    }
}
