
package Model.Items;

import Model.BodyParts.BodyPart;

/**
 * HealingPotion is a sub type of class Potion. One of the items a character can use during the game.
 * @author Hamid
 */
public class HealingPotion extends Potion
{
    /**
     * The default constructor.
     * @author Fahim
     */
    public HealingPotion()
    {
        _name = "HealingPotion";
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
    
    

    /**
     * Drink potion method.
     * 
     */
    @Override
    public void drinkPotion()
    {

    }
    
}
