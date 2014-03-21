/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;


/**
 * This is a map cell wall class.
 * It inherit map cell.
 * To set the wall element in the map.
 * With the row and column, set the wall in the demanded cell.
 * 
 * @author yukywang
 */
public class MapCellWall extends MapCell
{

    /**
     * Default constructor. 
     * @param pRow The _row field.
     * @param pCol The _col field.
     * @param pDescription The _description field.
     * @param pImageFile The _imageName field.
     */
    public MapCellWall(int pRow, int pCol, String pDescription, String pImageFile)
    {
        _row = pRow;
        _col = pCol;
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * Another constructor method for this class.
     * @param pDescription The _description field.
     * @param pImageFile  The _imageName field.
     */
    public MapCellWall(String pDescription, String pImageFile)
    {
        _imageName = pImageFile;
        _description = pDescription;
    }
    
    /**
     * Indicates if an overlap is possible.
     * @return True
     */

    @Override
    public boolean canOverlap()
    {
        return false;
    }
}
