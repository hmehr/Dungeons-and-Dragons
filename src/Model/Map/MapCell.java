package Model.Map;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * MapCell class a an abstract class for all graphics cell to be drawn in map. A MapCell
 * encapsulates all the graphic information which needed by map to describe a cell in
 * map. This information includes:
 * 
 * <ul>
 * <li>Map cell row: since map is a n*m matrix, row indicates horizontal location of cell in map</li>
 * <li>Map cell column: since map is a n*m matrix, column indicates vertical location of cell in map </li>
 * <li>Map cell image: describe which image file will be used to draw on map for this cell </li>
 * <li>Map cell description: a text string describes this particular map cell </li>
 * </ul>
 * 
 * MapCell class implements Transferable interface which can be set as data flavor when create drag and drop 
 * event. <br/>
 * MapCell class implements Serializable interface which allow MapCell object to be saved and loaded from disk<br/>
 * @author Xuexiang, Hu
 * @version  1.0
 */
public abstract class MapCell implements Transferable, Serializable
{
    /**
     * This is the protected integer row of the map cell.
     */
    protected int _row;
    /**
     * This is the protected integer colum of the map cell.
     */
    protected int _col;
    /**
     * This is the protected string image name.
     */
    protected String _imageName = null;
    /**
     * This is the protected string description.
     */
    protected String _description = null;
    private static DataFlavor _dragAndDropDataFlavor = null;
    private transient BufferedImage _image = null;
    /**
     * It returns the number of rows.
     * @return <code>_row</code>
     */
    public int GetRow()
    {
        return _row;
    }

    /**
     * It returns the number of columns.
     * @return <code>_col</code>
     */
    public int GetCol()
    {
        return _col;
    }
    /**
     * To set the Images.
     * @param pImage the Image of the buffered image.
     * @author yukywang
     */
    public void setImage(BufferedImage pImage)
    {
        _image = pImage;
    }
    
    /**
     * To get the image.
     * @return the image of the buffered image.
     * @author yukywang
     */
    public BufferedImage getImage()
    {
        return _image;
    }

    /**
     * To set the description.
     * @param pDescription 
     */
    public void setDescription(String pDescription)
    {
        _description = pDescription;
    }

    /**
     * It returns the description.
     * @return It returns <code>_description</code>.
     */
    public String getDescription()
    {
        return _description;
    }

    /**
     * It returns the imageName.
     * @return It returns <code>_imageName</code>.
     */
    public String getImageName()
    {
        return _imageName;
    }

    /**
     * It sets the imageName - path of the file.
     * @param pName takes parameter <code>_imageName</code>.
     */
    public void setImage(String pName)
    {
        _imageName = pName;
    }

    /**
     * It sets the number of the rows.
     * @param pRow This is a integer, number of rows.
     */
    public void SetRow(int pRow)
    {
        _row = pRow;
    }

    /**
     * It sets the number of the columns.
     * @param pCol This is a integer, number of columns.
     */
    public void SetCol(int pCol)
    {
        _col = pCol;
    }

    /**
     * It returns the description as string.
     * @return <code>return _description;</code>
     */
    @Override
    public String toString()
    {
        return _description;
    }
    
   
    /**
     * This method is an abstract method, which determines if this map cell can be overlapped with other map cell
     * 
     *@return <code>true</code> if this map cell can be overlapped with other map cell
     *         <code>false</code> if this map cell can not be overlapped with other map cell
     */
     
    public abstract boolean canOverlap(); 
     
    /**
     * <p>Returns (creating, if necessary) the DataFlavor representing RandomDragAndDropPanel</p>
     * @return _dragAndDropDataFlavor
     * @throws Exception  if something unusual happen.
     * 
     * @author yukywang
     */
    public static DataFlavor getDragAndDropPanelDataFlavor() throws Exception
    {
        // Lazy load/create the flavor
        if (_dragAndDropDataFlavor == null)
        {
            _dragAndDropDataFlavor = new DataFlavor(MapCell.class, "MapItemModel");
        }
        return _dragAndDropDataFlavor;
    }

    /**
     * <p>One of three methods defined by the Transferable interface.</p>
     * <p>If multiple DataFlavor's are supported, can choose what Object to return.</p>
     * <p>In this case, we only support one: the actual JPanel.</p>
     * <p>Note we could easily support more than one. For example, if supports text and drops to a JTextField, could return the label's text or any arbitrary text.</p>
     * @param flavor
     * @return java Object
     * @throws UnsupportedFlavorException and IOException
     */
    @Override
    public synchronized  Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
    {
        
        DataFlavor thisFlavor = null;
        try
        {
            thisFlavor = MapCell.getDragAndDropPanelDataFlavor();
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
     * @return It returns <code>DataFlavor[]</code> array.
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
            flavors[0] = MapCell.getDragAndDropPanelDataFlavor();
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
            flavors[0] = MapCell.getDragAndDropPanelDataFlavor();
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
