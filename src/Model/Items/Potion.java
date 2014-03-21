
package Model.Items;
/**
 * Armor is a sub type of class Item. One of the items a character can use during the game.
 * @author Fahim
 */
public abstract class Potion extends Item
{
    /**
     * default constructor
     */
    public Potion(){}
    /**
     * This is a public function called drink potion and increase hit point.
     */
    public abstract void drinkPotion();
}
