package Model.Items;

import Model.BodyParts.BodyPart;
import Model.Game.Dice;
import Model.OutputConsole;

/**
 * Boot is a sub type of class Mis. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Boot extends Mis {

    /**
     * Constructor method for Boot.
     * @param pName Name of the Boot
     * @param pFileName Name of the image file name used for it
     * @author Hamid
     */
    public Boot(String pName, String pFileName) {
        _magicSetter = 0;
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A Boot was just created.");
    }

    /**
     * To set boot name
     */
    public Boot() {
        _magicSetter = 0;
        _name = "Boot";
    }

    /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic() {
        Dice dice = new Dice(1, 2);
        if (dice.roll() == 1) {
            _magicSetter = 1;
        } else {
            _magicSetter = 2;
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
    public boolean validateBodyPart(BodyPart pBodyPart) {

        return true;
    }
}
