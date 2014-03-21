/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * This is the builder interface for the characters. Concrete builder classes will
 * implement those methods.
 * @author m_niza
 */
public interface CharacterBuilder
{
    /**
     * <code> buildBodyParts() </code> method will create the parts of the body.
     */
    public void buildBodyParts();

    /** 
     * <code> buildInventoryItems() </code> creates some items and initializes the
     * inventory for the character.
     */
    public void buildInventoryItems();

    /**
     * This will set the name and image of the characters.
     * @param pName It's the name of character.
     * @param pImagePath Image path/location of the character.
     */
    public void buildAvatar(String pName, String pImagePath);

    /**
     * Generates the ability score according to the rules and assigns it to the respective
     * properties in respective order for different types of character like bully, nimble etc.
     */
    public void buildAbilityScores();

    /**
     * Sets the level of the character ,which is the basis of several other properties.
     * @param pLevel It's the level of the player or monster.
     */
    public void buildLevel(int pLevel);

    /**
     * It will return the character object.
     * @return <code>Character</code> object which is created by the builder.
     */
    public Character getCharacter();
}
