
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.Game.Dice;
import Model.OutputConsole;

/**
 * Helmet is a sub type of class Defense. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Helmet extends Defense
{

    /**
     * Constructor method for helmet.
     * @param pName Name of the helmet.
     * @param pFileName Name of the image file used for the helmet.
     */
    public Helmet(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A helmet was just created.");
        _magicSetter = 0;
    }

    /**
     * Default constructor.
     */
    public Helmet()
    {
        _name = "Helmet";
        _magicSetter = 0;
    }

      /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic() {
        Dice dice = new Dice(1, 3);
        int n = dice.roll();
        if (n == 1) {
            _magicSetter = 1;
        } else if (n == 2){
            _magicSetter = 4;
        }
        else
        {
            _magicSetter = 6;
        }
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
