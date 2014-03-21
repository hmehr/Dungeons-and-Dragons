package Model.Map;

import java.io.Serializable;
import java.util.Observable;

/**
 * This is a class of map implements the observable.
 * This is a class of map implementation.
 * To do the change of the map.
 * Add the element to the map, move the element, remove the element from the map.
 * 
 * @author yukywang
 */
public class Map extends Observable implements Serializable
{

    private MapCell[][] m_Matrix = null;
    private int m_Rows = 2;
    private int m_Cols = 2;
    private MapCell _startPoint = null;
    private MapCell _endPoint = null;
    private String _mapFile;

    /**
     * Constructor to initialize the map.
     * 
     * @param pRows the row in the map
     * @param pCols the columns in the map
     * 
     * @author yukywang
     */
    public Map(int pRows, int pCols)
    {
        m_Rows = pRows;
        m_Cols = pCols;
        m_Matrix = new MapCell[pRows][pCols];
        for (int row = 0; row < pRows; row++)
        {
            for (int col = 0; col < pCols; col++)
            {
                m_Matrix[row][col] = null;
            }
        }
    }

    /**
     * To set the map file.
     * @param _mapFile map file.
     */
    public void setMapFile(String _mapFile)
    {
        this._mapFile = _mapFile;
    }

    /**
     * To get the map file.
     * @return the string type value of the map file.
     */
    public String getMapFile()
    {
        return _mapFile;
    }

    
    
    /**
     * It will return a MapCell object.
     * @return <code>_startPoint</code>
     */
    public MapCell getStartPoint()
    {
        return _startPoint;
    }

    /**
     * It will return a MapCell object.
     * @return <code>_endPoint</code>
     */
    public MapCell getEndPoint()
    {
        return _endPoint;
    }

    /**
     * It will return the number rows.
     * @return <code>m_rows</code>
     */
    public int getMapRows()
    {
        return m_Rows;
    }

    /**
     * It will return number of columns.
     * @return <code>m_Cols</code>
     */
    public int getMapCols()
    {
        return m_Cols;
    }

    /**
     * To get the element in the map.
     * 
     * @param pRow the rows in the map
     * @param pCol the columns in the map
     * @return the mapCell ret
     * 
     * @author yukywang
     */
    public MapCell getMapElement(int pRow, int pCol)
    {
        MapCell ret = null;
        if ((pRow >= 0 && pRow < m_Rows) || (pCol >= 0 && pCol < m_Cols))
        {
            ret = m_Matrix[pRow][pCol];
        }
        return ret;
    }

    /**
     * To add the element in the map.
     * get the row and column where the element want to be added.
     * To see whether the row and column exist and whether the element is start
     * or end. 
     * Add the element to the map.
     * set the change and notify the observers.
     * 
     * @param pMapElement element in the map.
     * @return mapCell ret.
     * 
     * @author yukywang
     */
    public boolean addMapElement(MapCell pMapElement)
    {
        boolean ret = false;
        int row = pMapElement.GetRow();
        int col = pMapElement.GetCol();
        if ((row >= 0 && row < m_Rows) || (col >= 0 && col < m_Cols))
        {
            if (m_Matrix[row][col] == null)
            {
                if (pMapElement instanceof MapCellStart)
                {
                    if (_startPoint == null)
                    {
                        _startPoint = pMapElement;
                    } else
                    {
                        return false;
                    }
                }
                if (pMapElement instanceof MapCellEnd)
                {
                    if (_endPoint == null)
                    {
                        _endPoint = pMapElement;
                    } else
                    {
                        return false;
                    }
                }
                m_Matrix[row][col] = pMapElement;
                ret = true;
                this.setChanged();
                this.notifyObservers(new MapMessage(pMapElement));
            }
        }
        return ret;
    }

    /**
     * To remove the element from the map.
     * If the row and column exist and there is an element in that cell.
     * Remove the element from that cell.
     * set the change and notify the observers.
     * @param pRow the rows in the map
     * @param pCol the columns in the map
     * @return mapCell ret
     * 
     * @author yukywang
     */
    public boolean removeMapElement(int pRow, int pCol)
    {
        boolean ret = false;
        if ((pRow >= 0 && pRow < m_Rows) || (pCol >= 0 && pCol < m_Cols))
        {
            if (m_Matrix[pRow][pCol] != null)
            {
                if (!(m_Matrix[pRow][pCol] instanceof MapCellEnd || m_Matrix[pRow][pCol] instanceof MapCellStart))
                {
                    m_Matrix[pRow][pCol] = null;
                    ret = true;
                    this.setChanged();
                    this.notifyObservers(new MapMessage(pRow, pCol));
                }
            }
        }
        return ret;
    }
    
    /**
     * This is a function that set map elements.
     * @param pEle 
     */
    public void setMapElement(MapCell pEle)
    {
        m_Matrix[pEle.GetRow()][pEle.GetCol()] = pEle;
    }
    
    /**
     * This is a function to remove elements.
     * @param pRow
     * @param pCol 
     */
    public void removeElement(int pRow, int pCol)
    {
        m_Matrix[pRow][pCol] = null;
    }

    /**
     * To move the element.
     * To see whether the destinate row and column in the map and the old cell is not none
     * and destinate cell is none. Then move the element to the destinate row and column.
     * set the change and notify the observers.
     * 
     * @param pSrcRow the old row in the map
     * @param pSrcCol the old column in the map
     * @param pDestRow the destinate row in the map
     * @param pDestCol the destinate column in the map
     * @return mapCell ret
     * 
     * @author yukywang
     */
    public boolean moveMapElement(int pSrcRow, int pSrcCol, int pDestRow, int pDestCol)
    {
        boolean ret = false;
        if ((pDestRow >= 0 && pDestRow < m_Rows) || (pDestCol >= 0 && pDestCol < m_Cols))
        {
            if (m_Matrix[pSrcRow][pSrcCol] != null && m_Matrix[pDestRow][pDestCol] == null)
            {
                moveElement(pSrcRow, pSrcCol, pDestRow, pDestCol);
                ret = true;
                this.setChanged();
                this.notifyObservers(new MapMessage(pSrcRow, pSrcCol, pDestRow, pDestCol));
            }
        }
        return ret;
    }

    /**
     * To move Element.
     * @param pSrcRow the old row in the map
     * @param pSrcCol the old column in the map
     * @param pDestRow the destinate row in the map
     * @param pDestCol the destinate column in the map
    
     */
    public void moveElement(int pSrcRow, int pSrcCol, int pDestRow, int pDestCol)
    {
        m_Matrix[pSrcRow][pSrcCol].SetCol(pDestCol);
        m_Matrix[pSrcRow][pSrcCol].SetRow(pDestRow);
        m_Matrix[pDestRow][pDestCol] = m_Matrix[pSrcRow][pSrcCol];
        m_Matrix[pSrcRow][pSrcCol] = null;
    }

    /**
     * To validate the map.
     * To see whether there have a start element and an end element in the map.
     * 
     * @param pMsg
     * @return mapCell ret with true or false
     * 
     * @author yukywang
     */
    public boolean validateMap(StringBuffer pMsg)
    {
        boolean ret = true;
        if (_startPoint == null)
        {
            ret = false;
            pMsg.append("Miss Start Point");
        } else if (_endPoint == null)
        {
            ret = false;
            pMsg.append("Miss End Point");
        }
        return ret;
    }
}
