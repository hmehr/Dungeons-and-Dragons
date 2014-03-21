
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.OutputConsole;
/**
 * LongSword is a sub type of class Weapon. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class LongSword extends Weapon
{

    /**
     * Constructor method for LongSword.
     * @param pName Name of the LongSword
     * @param pFileName Name of the image file name used for it
     * @author Hamid 
     */
    public LongSword(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        _magicSetter = 7;
        OutputConsole.GetOutputConsoleInstance().Write("A LongSword was just created.");
    }

    /**
     * Default constructor.
     */
    public LongSword()
    {
        _magicSetter = 7;
        _name = "LongSword";
    }


     /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic() {

          _magicSetter = 7;

    }

    /**
     * Validates the body part with the corresponding item
     * 
     * @param pBodyPart The body part to be validated.
     * @return True if the body part is validated.
     * @author Fahim
     */
    @Override
    public boolean validateBodyPart(BodyPart pBodyPart)
    {
    
        return true;
    }
}

