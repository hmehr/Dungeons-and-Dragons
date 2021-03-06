/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Map;

import Model.Map.Map;
import View.Map.MapItemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;

/**
 * This is a class of ElementDraggable mouse listener inherit mouse adapter
 * This class is responsible for drag elements into the map or delete the 
 * element from the map, and at the meantime, add the deleted item into the menu. 
 * 
 * @author yukywang
 */
public class ElementDraggableMouseListener extends MouseAdapter
{

    private Map _map = null;

    /**
     * Initializes the Map object
     * @param pMap MAP elements for mouse listener.
     */
    public ElementDraggableMouseListener(Map pMap)
    {
        _map = pMap;
    }

    /**
     * This method performs the drag operation to move elements into the map or delete the 
     * element from the map, and at the meantime, add the deleted item into the menu.
     * @param pEvent Takes the argument mouseEvent
     */
    @Override()
    public void mousePressed(MouseEvent pEvent)
    {
        final MapItemView mapElementView = (MapItemView) pEvent.getSource();
        if (mapElementView != null && mapElementView.getMapElementModel() != null)
        {
            if (pEvent.getButton() == MouseEvent.BUTTON1)
            {
                TransferHandler handler = mapElementView.getTransferHandler();
                handler.exportAsDrag(mapElementView, pEvent, TransferHandler.MOVE);
            } 
            else if (pEvent.getButton() == MouseEvent.BUTTON3)
            {
                final JPopupMenu menu = new JPopupMenu();
                JMenuItem item = new JMenuItem("Delete Item");
                menu.add(item);
                menu.show(pEvent.getComponent(), pEvent.getX(), pEvent.getY());
                item.addMouseListener(new MouseAdapter()
                {
                    public void mousePressed(MouseEvent evt)
                    {
                        _map.removeMapElement(mapElementView.getRow(), mapElementView.getCol());
                    }
                });
            }
        }
    }
}
