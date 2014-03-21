/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Map;

import Model.Map.Map;
import Model.Map.MapCell;
import View.Map.MapItemView;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

/**
 * This is the class of map drop target listener implementation.
 * To do the drop,drag enter, drag exit, drag over method.
 * 
 * @author yukywang
 */
public class MapDropTargetListener implements DropTargetListener
{

    private Map m_Map = null;
    private MapItemView m_MapElementView = null;

    /**
     * This is the public function map drop target listener.
     * To drop the map target.
     * @param pMapModel the map model of the map.
     * @param pElementView the element view of the map item view.
     * @author yukywang
     */
    public MapDropTargetListener(Map pMapModel, MapItemView pElementView)
    {
        m_Map = pMapModel;
        m_MapElementView = pElementView;
    }

    /**
     * To do the drop method.
     * <p>get the expected flavor, and to see what does the transferable support
     * <p>move the map element in the map item view.
     * <p>change the map element in the map cell in both rows and columns.
     * 
     * @param dtde the command line arguments
     * @author yukywang
     */
    public void drop(DropTargetDropEvent dtde)
    {
        DataFlavor dragAndDropPanelFlavor = null;
        Object transferableObj = null;
        Transferable transferable = null;
        try
        {
            // Grab expected flavor
            dragAndDropPanelFlavor = MapItemView.getDragAndDropPanelDataFlavor();
            transferable = dtde.getTransferable();
            // What does the Transferable support
            if (transferable.isDataFlavorSupported(dragAndDropPanelFlavor))
            {
                transferableObj = transferable.getTransferData(dragAndDropPanelFlavor);
                MapItemView droppedPanel = (MapItemView) transferableObj;
                if (droppedPanel != null)
                {
                    if (droppedPanel.getRow() != m_MapElementView.getRow() || droppedPanel.getCol() != m_MapElementView.getCol())
                    {
                        m_Map.moveMapElement(droppedPanel.getRow(), droppedPanel.getCol(), m_MapElementView.getRow(), m_MapElementView.getCol());
                    }
                }
            }

            dragAndDropPanelFlavor = MapCell.getDragAndDropPanelDataFlavor();
            transferable = dtde.getTransferable();
            if (transferable.isDataFlavorSupported(dragAndDropPanelFlavor))
            {
                transferableObj = transferable.getTransferData(dragAndDropPanelFlavor);
                MapCell droppedData = (MapCell) transferableObj;
                if (droppedData != null)
                {
                   //MapItemModel mapElementMode = new MapItemModel(m_MapElementView.getRow(), m_MapElementView.getCol(), droppedData.getDescription(), droppedData.getImageName());
                   droppedData.SetCol(m_MapElementView.getCol());
                   droppedData.SetRow(m_MapElementView.getRow());
                    m_Map.addMapElement(droppedData);
                }
            }
        } catch (Exception ex)
        { /* nope, not the place */ }
        // If didn't find an item, bail
    }

    /**
     * This method not implemented.
     * @param dtde the command line arguments
     */
    public void dragEnter(DropTargetDragEvent dtde)
    {
    }

    /**
     * This method not implemented.
     * @param dtde the command line arguments
     */
    public void dragOver(DropTargetDragEvent dtde)
    {
    }

    /**
     * This method not implemented.
     * @param dtde the command line arguments
     */
    public void dropActionChanged(DropTargetDragEvent dtde)
    {
    }

    /**
     * This method not implemented.
     * @param dte the command line arguments
     */
    public void dragExit(DropTargetEvent dte)
    {
    }
}
