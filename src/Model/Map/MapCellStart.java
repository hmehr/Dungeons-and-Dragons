/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;



/**
 * This is a map cell start class.
 * It inherit map cell.
 * Set the map cell start with the row and column.
 * 
 * @author yukywang
 */
public class MapCellStart extends MapCell
{

    
    /**
     * Constructor method. 
     * 
     * @param pRow The _row field.
     * @param pCol The _col field.
     * @param pDescription The _imageName field.
     * @param pImageFile  The _description field.
     */
    public MapCellStart(int pRow, int pCol, String pDescription, String pImageFile)
    {
        _row = pRow;
        _col = pCol;
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * Another constructor method.
     * @param pDescription The _imageName field.
     * @param pImageFile The _description field.
     */
    public MapCellStart(String pDescription, String pImageFile)
    {
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * Returns true of they overlap.
     * @return True or false.
     */
    @Override
    public boolean canOverlap()
    {
        return true;
    }
}
