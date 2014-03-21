
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.OutputConsole;
/**
 * Belt is a sub type of class Mis. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Belt extends Mis
{

    /**
     * Constructor method for Belt.
     * 
     * @param pName Name of the Belt
     * @param pFileName Name of the image file name used for it
     * @author Hamid
     */
    public Belt(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A Belt was just created.");
        _magicSetter = 3;
    }

    /**
     * This is a public function that set the name belt.
     */
    public Belt()
    {
        _magicSetter = 3;
        _name = "Belt";
    }

    /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic()
    {
        _magicSetter = 3;
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

