/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Map;

import Model.Map.MapCell;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.JComponent;



/**
 * This is the map item view inherit JComponent.
 * This is the map item view implementation.
 * 
 * @author yukywang
 */
public class MapItemView extends JComponent implements Transferable,Serializable 
{

    private static DataFlavor m_dragAndDropPanelDataFlavor = null;
    private  MapCell m_MapElement = null;
    private int m_Row = 0;
    private int m_Col = 0;
   
    /**
     * Constructor of MapItemView
     * @param pMapElement the map element showed in the map.
     * @param pRow the row of the map.
     * @param pCol the column of the map.
     * @author yukywang
     */
    public MapItemView(MapCell pMapElement, int pRow, int pCol)
    {
        m_MapElement = pMapElement;
        m_Row = pRow;
        m_Col = pCol;
        this.setDoubleBuffered(true);

    }
    
    /**
     * set the m_MapElement value and do the repaint.
     * @param pMapElementModel the arguments of the element
     * @author yukywang
     */
    public void setMapElementModel(MapCell pMapElementModel)
    {
       m_MapElement = pMapElementModel;
        this.repaint();
    }
    
     /**
     * get MVC design pattern
     * @return the MapElementModel
     */
    public MapCell getMapElementModel()
    {
        return m_MapElement;
    }

     /**
     * get map row
     * @return the row number of map matrix
     */
    public int getRow()
    {
        return m_Row;
    }

    /**
     * get map column
     * @return the column number of map matrix
     */
    public int getCol()
    {
        return m_Col;
    }

    /**
     * This method paint a particular cell of map
     * @param pGraphics drawing device of screen
     */
    @Override
    public void paintComponent(Graphics pGraphics)
    {
        Graphics2D graphics2d = (Graphics2D) pGraphics;
        Rectangle rec = new Rectangle(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        graphics2d.setPaint(Color.blue);
        graphics2d.draw(rec);
        if (m_MapElement != null)
        {
            try
            {
                java.net.URL imageURL = MapItemView.class.getResource(m_MapElement.getImageName());
                BufferedImage img = ImageIO.read(imageURL);
                graphics2d.drawImage(img, 3, 3, this.getWidth() - 6, this.getHeight() - 6, this);
            } catch (IOException e)
            {
            }
        }
        
        //graphics2d.fill(new Rectangle());
        //graphics.fillRect(WIDTH, WIDTH, WIDTH, WIDTH);
        //graphics.draw
    }

    /**
     * <p>Returns (creating, if necessary) the DataFlavor representing RandomDragAndDropPanel</p>
     * @return <code>DataFlavor</code> object.
     * @throws Exception  
     */
    public static DataFlavor getDragAndDropPanelDataFlavor() throws Exception
    {
        // Lazy load/create the flavor
        if (m_dragAndDropPanelDataFlavor == null)
        {
            m_dragAndDropPanelDataFlavor = new DataFlavor(MapItemView.class, "MapElementView");
        }

        return m_dragAndDropPanelDataFlavor;
    }

    /**
     * <p>One of three methods defined by the Transferable interface.</p>
     * <p>If multiple DataFlavor's are supported, can choose what Object to return.</p>
     * <p>In this case, we only support one: the actual JPanel.</p>
     * <p>Note we could easily support more than one. For example, if supports text and drops to a JTextField, could return the label's text or any arbitrary text.</p>
     * @param flavor
     * @return It returns an object.
     */
    public synchronized  Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
    {
        
        DataFlavor thisFlavor = null;
        try
        {
            thisFlavor = MapItemView.getDragAndDropPanelDataFlavor();
        } catch (Exception ex)
        {
            System.err.println("Problem lazy loading: " + ex.getMessage());
            ex.printStackTrace(System.err);
            return null;
        }

        // For now, assume wants this class... see loadDnD
        if (thisFlavor != null && flavor.equals(thisFlavor))
        {
            return this;
        }
        return null;
      
    }

    /**
     * <p>One of three methods defined by the Transferable interface.</p>
     * <p>Returns supported DataFlavor. Again, we're only supporting this actual Object within the JVM.</p>
     * <p>For more information, see the JavaDoc for DataFlavor.</p>
     * @return It returns a DataFlavor array.
     */
    @Override
    public synchronized  DataFlavor[] getTransferDataFlavors()
    {
        DataFlavor[] flavors =
        {
            null
        };
        try
        {
            flavors[0] = MapItemView.getDragAndDropPanelDataFlavor();
        } catch (Exception ex)
        {
            System.err.println("Problem lazy loading: " + ex.getMessage());
            ex.printStackTrace(System.err);
            return null;
        }
        return flavors;
    }

    /**
     * <p>One of three methods defined by the Transferable interface.</p>
     * <p>Determines whether this object supports the DataFlavor. In this case, only one is supported: for this object itself.</p>
     * @param flavor
     * @return True if DataFlavor is supported, otherwise false.
     */
    @Override
    public synchronized  boolean isDataFlavorSupported(DataFlavor flavor)
    {
        DataFlavor[] flavors =
        {
            null
        };
        try
        {
            flavors[0] = MapItemView.getDragAndDropPanelDataFlavor();
        } catch (Exception ex)
        {
            System.err.println("Problem lazy loading: " + ex.getMessage());
            ex.printStackTrace(System.err);
            return false;
        }
        for (DataFlavor f : flavors)
        {
            if (f.equals(flavor))
            {
                return true;
            }
        }
        return false;
    }
}
