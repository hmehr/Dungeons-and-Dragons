
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.Game.Dice;
import Model.OutputConsole;

/**
 * Bracer is a sub type of class Defense. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Bracer extends Mis
{

    /**
     * Constructor method for Bracer.
     * @param pName Name of the Bracer.
     * @param pFileName Name of the image file used for the Bracer.
     */
    public Bracer(String pName, String pFileName)
    {
        
        _fileName = pFileName;
        _name = pName;
        _magicSetter = 0;
        OutputConsole.GetOutputConsoleInstance().Write("A bracer was just created.");
    }

    /**
     * Default constructor.
     */
    public Bracer()
    {
        _magicSetter = 0;
        _name = "Bracer";
    }


    /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic() {
        Dice dice = new Dice(1, 2);
        int n = dice.roll();
        if (n == 1) {
            _magicSetter = 1;
        } else {
            _magicSetter = 3;
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
