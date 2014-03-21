/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;
import Model.Character;

/**
 * This is a map cell monster class.
 * It inherit map cell.
 * To set the monster in the map.
 * According to the row and column, put the monster in the demanded cell.
 * 
 * @author yukywang
 */
public class MapCellMonster extends Character
{
    /**
     * default constructor.
     */
     public MapCellMonster()
     {
         super();
     }
    
    /**
     * Cell with monster, create combat situation.
     * @param pRow row position
     * @param pCol column postion
     * @param pDescription Description
     * @param pImageFile Imaage file with location path.
     */
    public MapCellMonster(int pRow, int pCol, String pDescription, String pImageFile)
    {
        _row = pRow;
        _col = pCol;
        _imageName = pImageFile;
        _description = pDescription;
        _isMad = false;
    }

    /**
     * Cell with monster, create combat situation.
     * @param pDescription Description
     * @param pImageFile Imaage file with location path.
     */
    public MapCellMonster(String pDescription, String pImageFile)
    {
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * Checks whether the cell has overlap object or not.
     * @return It returns boolean.
     */
    @Override
    public boolean canOverlap()
    {
        return false;
    }
    
    
}
