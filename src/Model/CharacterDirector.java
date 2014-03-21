/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Director for the builder classes. It's constructor builds and initializes the
 * necessary components and properties for a character.
 * @author m_niza
 */
public class CharacterDirector
{

    CharacterBuilder _characterBuilder;// = null;
    String _name;
    String _path;
    int _level;
    int[] _scores;

    /**
     * Constructor the the <code>CharacterDirector</code> class, which eventually
     * will create a character with the mentioned properties.
     * @param pCharBuilder It's the concrete builder object, which will be sent to the constructor method.
     * @param pName Name of the character.
     * @param pPath Image path/location for the avatar.
     * @param pLevel Defines the level for the character.
     */
    public CharacterDirector(CharacterBuilder pCharBuilder, String pName, String pPath, int pLevel)
    {
        this._characterBuilder = pCharBuilder;
        this._name = pName;
        this._path = pPath;
        this._level = pLevel;        
    }

    /**
     * This the constructor method for the concrete builders. It will build the character's
     * <p> Avatar
     * <p> Ability scores
     * <p> Level
     */
    public void constructCharacter()
    {
        _characterBuilder.buildAbilityScores();
        _characterBuilder.buildAvatar(_name, _path);
        _characterBuilder.buildLevel(_level);        
        _characterBuilder.buildBodyParts();
        _characterBuilder.buildInventoryItems();
    }

    /**
     * It will return the character object.
     * @return <code>Character</code> based on the concrete builder.
     */
    public Character getCharacter()
    {
        return _characterBuilder.getCharacter();
    }
}
