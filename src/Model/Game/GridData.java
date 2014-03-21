/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Game;

/**
 * This is a public class called grid data.
 * @author eric
 */
public class GridData
{
    /**
     * Grid data x.
     */
    public int x;
    
    /**
     * Grid data y.
     */
    public int y;
    
    /**
     * To set the x and y value.
     * @param pX 
     * @param pY 
     */
    public GridData(int pX, int pY)
    {
        x = pX;
        y = pY;
    }
    
    /**
     * This is a public function that return a boolean value.
     * @param pOb 
     * @return a boolean value to indicate whether they are equals.
     */
    public boolean equals(GridData pOb)
    {
        return (x == pOb.x && y == pOb.y);
    }
}
