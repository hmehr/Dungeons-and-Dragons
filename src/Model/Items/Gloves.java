
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.OutputConsole;

/**
 * Gloves is a sub type of class Defense. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Gloves extends Mis
{

    /**
     * Constructor method for gloves.
     * @param pName Name of the gloves.
     * @param pFileName Name of the image file used for the gloves.
     */
    public Gloves(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A Gloves was just created.");
    }

    /**
     * Default constructor.
     */
    public Gloves()
    {
        _name = "Gloves";
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
