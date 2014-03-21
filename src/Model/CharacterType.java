/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Enumeration of different CHARACTER types available for fighter. Added new Character types for build 2.
 * @author Fahim 
 * @version 1.1.0
 */
public enum CharacterType
{
    /**
     * This is a player type - bully with high strength.
     */    
    BULLY,
    
    /**
     * This is a player type - nimble with high dexterity.
     */
    NIMBLE,

    /**
     * This is a player type - tank with high constitution.
     */
    TANK,

    /**
     * This is a character type - monster.
     */
    MONSTER;
}
