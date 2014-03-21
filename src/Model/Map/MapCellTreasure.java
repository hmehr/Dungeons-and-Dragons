/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import Model.Items.*;
import Model.OutputConsole;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a map cell treasure class.
 * It inherit map cell.
 * To set the treasure in the map.
 * According to the row and column, put the treasure in the demanded cell.
 * 
 * @author yukywang
 */
public class MapCellTreasure extends MapCell
{
    private ArrayList<Item>_itemList = new ArrayList<Item>();
    /**
     * Constructor method for the class.
     * @param pRow The _row field.
     * @param pCol The _col field.
     * @param pDescription The _imageName field.
     * @param pImageFile  The _description field.
     */
    public MapCellTreasure(int pRow, int pCol, String pDescription, String pImageFile)
    {
        _row = pRow;
        _col = pCol;
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * Another constructor method for the class.
     * @param pDescription The _description field.
     * @param pImageFile The _imageName field.
     */
    public MapCellTreasure(String pDescription, String pImageFile)
    {
        _imageName = pImageFile;
        _description = pDescription;
    }

    /**
     * This is a public function to get the item list.
     * @return the item list.
     */
    public ArrayList<Item> getItemList()
    {
        return _itemList;
    }

    
    
    
    
    /**
     * Indicates it there's an overlap.
     * @return True or false.
     */
    @Override
    public boolean canOverlap()
    {
        return true;
    }

    /*
    public void addToBackpack(Model.Character pCharacter)
    {
    ArrayList backpack = pCharacter.getBackPack();
    if ( ! backpack.contains(_treasureContent))
    {
    if ( _treasureContent != null)
    {
    pCharacter.addBackpackItems(_treasureContent);
    OutputConsole.GetOutputConsoleInstance().Write(_treasureContent.getName() + " was added to backback.");
    }
    }
    else
    OutputConsole.GetOutputConsoleInstance().Write("The content of the treasure is already in the backpack");
    
    }
     */
}
