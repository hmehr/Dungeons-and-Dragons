/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Character.AbilityScoreGeneration;

/**
 * This is a character builder tank class which implements character builder.
 * @author Sayid
 */
public class CharacterBuilderTank implements CharacterBuilder
{

    Character _tankPlayer;
    AbilityScoreGeneration _asGenerate;
    int[] _abilityScore;

    /**
     * This is a public constructor called character builder tank.
     * Initialize character and ability score generation.
     */
    public CharacterBuilderTank()
    {
        _tankPlayer = new Character();
        _asGenerate = new AbilityScoreGeneration();
    }

    public void buildBodyParts()
    {
        _tankPlayer.createBodyParts();
    }

    public void buildInventoryItems()
    {
        _tankPlayer.createInventoryItems();
    }

    public void buildAvatar(String pName, String pImagePath)
    {
        _tankPlayer.setName(pName);
        _tankPlayer.setAvatar(pImagePath);
        _tankPlayer.setType(CharacterType.TANK);
    }

    public void buildLevel(int pLevel)
    {
        _tankPlayer.setLevel(pLevel);
//        if(pLevel > 0)
//        {
//            for(int i=1; i <= pLevel; i++)
//                _tankPlayer.addLevel();
//        }
//
//        if(pLevel < 0)
//        {
//            for(int i=1; i <= Math.abs(pLevel); i++)
//                _tankPlayer.minusLevel();
//        }
    }

    public void buildAbilityScores()
    {
        _abilityScore = _asGenerate.createAbilityList();

        _tankPlayer.setConstitution(_abilityScore[5]);
        _tankPlayer.setDexterity(_abilityScore[4]);
        _tankPlayer.setStrength(_abilityScore[3]);
        _tankPlayer.setIntelligence(_abilityScore[2]);
        _tankPlayer.setCharisma(_abilityScore[1]);
        _tankPlayer.setWisdom(_abilityScore[0]);
    }

    public Character getCharacter()
    {
        return _tankPlayer;
    }
}
