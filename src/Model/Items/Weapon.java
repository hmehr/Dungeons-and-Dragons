
package Model.Items;
/**
 * Weapon is a sub type of class Item. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public abstract class Weapon extends Item
{
    /**
     * default constructor.
     */
    public Weapon(){}
    boolean _isMelee = false;
    boolean _isLongRange = false;

    /**
     * Returns true if the weapon is long range.
     * @return True if the weapon is long range.
     */
    public boolean isIsLongRange()
    {
        return _isLongRange;
    }

     /**
     * Returns true if the weapon is  melee.
     * @return True if the weapon is melee.
     */
    public boolean isIsMelee()
    {
        return _isMelee;
    }

    /**
     * Sets the _isLongRange
     * @param pIsLongRange The boolean value. True if it is long range weapon.
     */
    public void setIsLongRange(boolean pIsLongRange)
    {
        this._isLongRange = pIsLongRange;
    }

     /**
     * Sets the _isMelee
     * @param pIsMelee The boolean value. True if it is melee weapon.
     */
    public void setIsMelee(boolean pIsMelee)
    {
        this._isMelee = pIsMelee;
    }
    
    
}
