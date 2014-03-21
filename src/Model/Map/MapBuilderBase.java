/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

/**
 * This is a public interface called map builder base.
 * @author eric
 */
public interface MapBuilderBase
{
    /**
     * This is a public function called build.
     */
    public void build();
    
    /**
     * This is a public function that build the treasure.
     */
    public void buildTreasure();
    
    /**
     * This is a public function that build the monster.
     */
    public void buildMonster();
    
    /**
     * To get the map.
     * @return It returns <code>Map</code> object.
     */
    public Map getMap();
}
