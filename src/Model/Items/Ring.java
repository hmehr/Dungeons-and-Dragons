package Model.Items;

import Model.BodyParts.BodyPart;
import Model.Game.Dice;
import Model.OutputConsole;

/**
 * Ring is a sub type of class Mis. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Ring extends Mis {

    /**
     * Constructor method for Ring.
     * @param pName Name of the Ring
     * @param pFileName Name of the image file name used for it
     */
    public Ring(String pName, String pFileName) {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A Ring was just created.");
        _magicSetter = 0;
    }

    /**
     * Default constructor.
     */
    public Ring() {
        _magicSetter = 0;
        _name = "Ring";
    }

    /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic() {
        Dice dice = new Dice(1, 4);
        int n = dice.roll();
        if (n == 1) {
            _magicSetter = 1;
        } else if (n == 2) {
            _magicSetter = 3;
        } else if (n == 3) {
            _magicSetter = 4;
        } else {
            _magicSetter = 5;
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
