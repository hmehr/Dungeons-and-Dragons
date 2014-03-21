/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls.CustomListView;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import Model.Map.MapCell;
import View.Map.MapItemView;

/**
 *ListItemRender Control Class.
 * 
 * @author eric
 */
public class ListItemRender extends DefaultListCellRenderer
{
    /**
     * default constructor
     */
    public ListItemRender(){}
    /**
     * Gets list of renderer component.
     * @param pList The list.
     * @param value The value.
     * @param pIndex The index.
     * @param pIsSelected The flag if it is selected.
     * @param pHasFocus The flag which indicates if the components has focus.
     * @return A component
     */
    public Component getListCellRendererComponent(JList pList,
            Object value,
            int pIndex,
            boolean pIsSelected,
            boolean pHasFocus)
    {
        JLabel label =
                (JLabel) super.getListCellRendererComponent(pList,
                value,
                pIndex,
                pIsSelected,
                pHasFocus);
        if (value instanceof MapCell)
        {
            MapCell itemModel = (MapCell) value;
            java.net.URL imageURL = MapItemView.class.getResource(itemModel.getImageName());
            ImageIcon icon = new ImageIcon(imageURL);
            label.setIcon(icon);
        } else
        {
            // Clear old icon; needed in 1st release of JDK 1.2
            label.setIcon(null);
        }
        return (label);
    }
}
