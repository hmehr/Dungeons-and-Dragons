/**
 * This just a body part.
 * @author Hamid
 */
package Model.BodyParts;

import Model.Items.Item;
import Model.OutputConsole;

/**
 * It's a child class of body part.
 * @author m_niza
 */
public class UpperBody extends BodyPart
{
    /**
     * It takes the file name of the image of a particular body part.
     * @param pFileName Path of the location of the image file.
     */
    public UpperBody(String pFileName)
    {
        _name = "UpperBody";
        _fileName = pFileName;
    }
    /**
     * Default constructor for the feet class. It assign a name for that.
     */
    public UpperBody()
    {
        _name = "UpperBody";
    }

    /**
     * The method wear an item to a particular parts of the body.
     * @param pItem It is the reference of the item to wear.
     */
    @Override
    public void wearItem(Item pItem)
    {
        //super.wearItem(item);
        _item = pItem;
        //OutputConsole.GetOutputConsoleInstance().Write("The player just put on " + _item.getName());
    }

     /**
     * The method takes off an item to a particular parts of the body.
     * @param pItem It is the reference of the item to take off.
     */
    @Override
    public void takeOffItem(Item pItem)
    {
        OutputConsole.GetOutputConsoleInstance().Write("The player just put off " + _item.getName());
        _item = null;
    }

    /**
     * This method checks whether the item is already equiped or not.
     * @return It returns boolean.
     */
    @Override
    public boolean isEquiped()
    {
        //return super.isEquiped();
        if(_item != null)
            return true;
        else
            return false;
    }


}
