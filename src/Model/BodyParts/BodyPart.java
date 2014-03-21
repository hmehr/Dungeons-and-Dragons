/**
 *Body part is the super type class and some concrete classes will implement it.
 * We use this class in our character class. 
 * @author Hamid
 */
package Model.BodyParts;

import Model.Items.Item;
import Model.OutputConsole;
import java.io.Serializable;

/**
 * Body part is the parent class of all the parts of the body.
 * @author m_niza
 */
public abstract class BodyPart implements Serializable
{
   /**
     * default constructor
     */
    public BodyPart(){}
    /**
     * It's the name of the body part.
     */
    protected String _name;

    /**
     * It's the path of image file.
     */
    protected String _fileName;
    /**
     * Item object to associate with body part.
     */
    protected Item _item;

    /**
     * The method wear an item to a particular parts of the body.
     * @param item It is the reference of the item to wear.
     */
    public void wearItem(Item item)
    {

        OutputConsole.GetOutputConsoleInstance().Write("The character just put on " + item.getName());
    }

    /**
     * The method takes off an item to a particular parts of the body.
     * @param item It is the reference of the item to take off.
     */
    public void takeOffItem(Item item)
    {

        OutputConsole.GetOutputConsoleInstance().Write("The character just took off" + item.getName());
    }

    /**
     * This method checks whether the item is already equiped or not.
     * @return It returns boolean.
     */
    public boolean isEquiped()
    {
        if (_item == null)
        {
            return false;
        }
        return true;
    }

    /**
     * It provides the name of an item.
     * @return The name of the item.
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Provies the item object.
     * @return It returns an item object reference.
     */
    public Item getItem()
    {
        return _item;
    }    

}
