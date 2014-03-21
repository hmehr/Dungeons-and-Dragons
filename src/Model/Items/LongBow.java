
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.OutputConsole;
/**
 * LongBow is a sub type of class Weapon. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class LongBow extends Weapon
{

    /**
     * Constructor method for LongBow.
     * @param pName Name of the LongBow
     * @param pFileName Name of the image file name used for it
     * @author Hamid
     */
    public LongBow(String pName, String pFileName)
    {
        _magicSetter = 7;
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A LongBow was just created.");
    }

    /**
     * To set the name to longbow.
     */
    public LongBow()
    {
        _magicSetter = 7;
        _name = "LongBow";
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

