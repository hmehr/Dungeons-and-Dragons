package Model.Items;

import Model.BodyParts.BodyPart;
import java.io.Serializable;

/**
 * This is a super type class to all items a character can put on during the game.
 * @author Hamid
 */
public abstract class Item implements Serializable
{

    /**
     * protected integer magic value.
     */
    protected int _magicValue = 0;//build 3
    
    /**
     * protected integer magic setter.
     */
    protected int _magicSetter = 0;//build 3
 

    /**
     * default constructor
     */
    public Item()
    {
    }
    /**
     * This is the name of the item.
     */
    protected String _name;
    /**
     * Path of the image file.
     */
    protected String _fileName;

  

    /**
     * Returns the _fileName
     * @return The _fileName
     */
    public String getFileName()
    {
        return _fileName;
    }

    /**
     * Returns the _name
     * @return The _name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Sets the _fileName
     * @param pFileName The parameter to set to _fileName .
     */
    public void setFileName(String pFileName)
    {
        this._fileName = pFileName;
    }

    /**
     * Sets the _name
     * @param pName The parameter to set to _name .
     */
    public void setName(String pName)
    {
        this._name = pName;
    }

    /**
     * To get the magic value and return the magic value.
     * @return the integer type value of the magic value.
     */
    public int getMagicValue()
    {
        return _magicValue;
    }

    /**
     * To set the magic value.
     * @param pMagicValue integer type value of the magic value.
     */
    public void setMagicValue(int pMagicValue)
    {
        this._magicValue = pMagicValue;
    }

    /**
     * getter method to get an int value for which ability is modified by item.
     * @return _magicSetter is the value for which ability is modified in character
     * @version 1.0.0
     * @author khush
     */
    public int getMagicSetter()
    {
        return _magicSetter;
    }

    /**
     * setter method for to set an int value for which ability is modified by item.
     * @version 1.0.0
     * @author khush
     * @param _magicSetter _magicSetter is the value for which ability is modified in character
     */
    public void setMagicSetter(int _magicSetter)
    {
        this._magicSetter = _magicSetter;
    }

    /**
     * Validates the body part with the corresponding item
     * 
     * @param pBodyPart The body part to be validated.
     * @return True if the body part is validated.
     * @author Fahim
     */
    public abstract boolean validateBodyPart(BodyPart pBodyPart);



    /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic()
    {
        _magicSetter = 0;
    }

}
