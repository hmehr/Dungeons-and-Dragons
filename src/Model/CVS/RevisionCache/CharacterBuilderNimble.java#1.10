/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Character.AbilityScoreGeneration;

/**
 * This is a public class called character builder nimble which implements character builder.
 * @author Sayid
 */
public class CharacterBuilderNimble implements CharacterBuilder
{
    Character _nimblePlayer;
    AbilityScoreGeneration _asGenerate;
    int[] _abilityScore;

    /**
     * This is a public constructor called character builder nimble.
     * Initialize character and ability score generation.
     */
    public CharacterBuilderNimble()
    {
        _nimblePlayer = new Character();
        _asGenerate = new AbilityScoreGeneration();
    }

    public void buildBodyParts()
    {
        _nimblePlayer.createBodyParts();
    }

    public void buildInventoryItems()
    {
        _nimblePlayer.createInventoryItems();
    }

    public void buildAvatar(String pName, String pImagePath)
    {
        _nimblePlayer.setName(pName);
        _nimblePlayer.setAvatar(pImagePath);
        _nimblePlayer.setType(CharacterType.NIMBLE);
    }

    public void buildLevel(int pLevel)
    {
        _nimblePlayer.setLevel(pLevel);

//        if(pLevel > 0)
//        {
//            for(int i=1; i <= pLevel; i++)
//                _nimblePlayer.addLevel();
//        }
//
//        if(pLevel < 0)
//        {
//            for(int i=1; i <= Math.abs(pLevel); i++)
//                _nimblePlayer.minusLevel();
//        }
        
    }

    public void buildAbilityScores()
    {
        _abilityScore = _asGenerate.createAbilityList();

        _nimblePlayer.setDexterity(_abilityScore[5]);
        _nimblePlayer.setConstitution(_abilityScore[4]);
        _nimblePlayer.setStrength(_abilityScore[3]);
        _nimblePlayer.setIntelligence(_abilityScore[2]);
        _nimblePlayer.setCharisma(_abilityScore[1]);
        _nimblePlayer.setWisdom(_abilityScore[0]);
    }

    public Character getCharacter()
    {
        return _nimblePlayer;
    }
}
