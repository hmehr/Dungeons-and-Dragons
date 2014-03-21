/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Map;

import Controller.Map.MapElementTransferHandler;
import Controller.Map.ElementDraggableMouseListener;
import Controller.Map.MapDropTargetListener;
import Model.Map.Map;
import Model.Map.MapCell;
import Model.Map.MapMessage;
import Model.Map.MapMessage.MapDeleteMessage;
import java.util.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import Model.Map.MapMessage.MapMessageType;
import Model.Map.MapMessage.MapMoveMessage;
import javax.swing.*;

/**
 * This is the class MapView inherit JPanel.
 * And implements the Observe interface.
 * To set the map model with one parameter.
 * Then invoke the initialMap to initialize the map.
 * 
 * @author Xuexiang Hu
 */
public class MapView extends JPanel implements Observer
{
    private Map m_Map = null;
    
    /**
     * Default constructor of MapView class
     */
    public MapView()
    {
      
    }
    
    /**
     * Set the map model of this MapView class
     * @param pMap set the map and cause the MapView to redraw
     */
    public void setMapModel(Map pMap)
    {
        m_Map = pMap;
        this.removeAll();
        initialMap();
    }

/**
 * Invoke the initialMap to initialize the map.
 * The inside for loop is changing the column of the each row.
 * When the inside for loop finished every time, that means one line of the map 
 * is initialized.
 * Then jump to the outside for loop and change the next line. 
 */
    private void initialMap()
    {
        this.setComponentOrientation(ComponentOrientation.UNKNOWN);
        this.setLayout(new GridBagLayout());
       // this.setSize(20* m_Map.getMapRows(), 20*m_Map.getMapCols());
        GridBagConstraints constrains = new GridBagConstraints();
        for (int rows = 0; rows < m_Map.getMapRows(); rows++)
        {
            for (int cols = 0; cols < m_Map.getMapCols(); cols++)
            {
                constrains.weightx = 1.0;
                constrains.weighty = 1.0;
                constrains.gridx = cols;
                constrains.gridy = rows;
                constrains.fill = GridBagConstraints.BOTH;
                MapItemView mapElement = null;
                mapElement = new MapItemView(m_Map.getMapElement(rows, cols),rows,cols);
                mapElement.setTransferHandler(new MapElementTransferHandler());
                mapElement.setDropTarget(new DropTarget(this,new MapDropTargetListener(m_Map, mapElement)));
                mapElement.addMouseListener(new ElementDraggableMouseListener(m_Map));
                this.add(mapElement , constrains);
            }
        }
        this.revalidate();
    }

/**
 * To update the map by using add, update and delete.
 * If the map message is not none, then we can add map, update the old map and 
 * delete the map.
 */
    
    @Override
    public void update(Observable o, Object arg)
    {
        MapMessage mapMessage = (MapMessage)arg;
        if(mapMessage != null)
        {
            if(mapMessage.messageType == MapMessageType.ADD)
            {
                MapCell mapElementModel = (MapCell)mapMessage.messageBody; 
                int index = mapElementModel.GetRow()*m_Map.getMapCols() + mapElementModel.GetCol();
                MapItemView mapElementView = (MapItemView)this.getComponent(index);
                mapElementView.setMapElementModel(mapElementModel);
            }
            else if(mapMessage.messageType == MapMessageType.UPDATE)
            {
                MapMoveMessage mapMoveMessage = (MapMoveMessage)mapMessage.messageBody;
                int oldIndex = mapMoveMessage.oldX*m_Map.getMapCols() + mapMoveMessage.oldY;
                int newIndex = mapMoveMessage.newX*m_Map.getMapCols() + mapMoveMessage.newY;
                MapItemView mapElementViewOld = (MapItemView)this.getComponent(oldIndex);
                MapItemView mapElementViewNew = (MapItemView)this.getComponent(newIndex); 
                mapElementViewNew.setMapElementModel(mapElementViewOld.getMapElementModel());
                mapElementViewOld.setMapElementModel(null);
            }
            else if(mapMessage.messageType == MapMessageType.DELETE)
            {
                MapDeleteMessage mapDeleteMessage = (MapDeleteMessage)mapMessage.messageBody;
                int index = mapDeleteMessage.deleteX*m_Map.getMapCols() + mapDeleteMessage.deleteY;
                MapItemView mapElementViewOld = (MapItemView)this.getComponent(index);
                mapElementViewOld.setMapElementModel(null);
            }
        }
    }
   
}
