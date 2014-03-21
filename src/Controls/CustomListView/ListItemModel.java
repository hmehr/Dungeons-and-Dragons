/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls.CustomListView;

 
import Model.Map.MapCell;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This is the custom list view control class.
 * 
 *@author Eric 
 * 
 */
public class ListItemModel implements ListModel
{

    private MapCell[] m_Collection = null;
    /**
     * The constructor for the class.
     * @param pCollection An array to be assigned to m_Collection.
     */
    public ListItemModel(MapCell[] pCollection)
    {
        this.m_Collection = pCollection;
    }
    
    /**
     * Returns the element at the specified index.
     * 
     * @param pIndex The element index.
     * @return <code>Object ret</code>
     */
    @Override
    public Object getElementAt(int pIndex)
    {
        Object ret = null;
        if (pIndex < m_Collection.length)
        {
            ret = m_Collection[pIndex];
        }
        return ret;
    }

    /**
     * Returns the size of the array.
     * @return Size of the array as integer.
     */
    @Override
    public int getSize()
    {
        return (m_Collection.length);
    }

    /**
     * Adds a data listener.
     * @param pListener The listener to add.
     */
    @Override
    public void addListDataListener(ListDataListener pListener)
    {
       
    }

    /**
     * Removes a listener.
     * @param pListener The listener to remove.
     */
    @Override
    public void removeListDataListener(ListDataListener pListener)
    {
       
    }
}
