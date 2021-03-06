/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

import Model.Map.*;
import java.util.Hashtable;
import java.util.Set;

/**
 * This is a static map cell list class.
 * It contains the map cell of monster list, wall list, treasure list, start list and end list.
 * 
 * @author yukywang
 */
public class StaticMapCellList
{

    private static final String ITEM_MONSTER = "Monseter";
    private static final String ITEM_WALL = "Wall";
    private static final String ITEM_TREASURE = "Treasure";
    private static final String ITEM_START_POINT = "Start Point";
    private static final String ITEM_END_POINT = "End Point";
    private static MapCell[] MONSTER_LIST =
    {
        new MapCellMonster("Bird", "../resources/bird.jpg"),
        new MapCellMonster("Dragon", "../resources/dragon.jpg"),
        new MapCellMonster("Gigan", "../resources/gigan.jpg"),
        new MapCellMonster("Gigant", "../resources/gigant.jpg"),
        new MapCellMonster("Rodan", "../resources/rodan.jpg"),
        new MapCellMonster("Spider", "../resources/spider.jpg"),
        new MapCellMonster("Yog", "../resources/yog.jpg")
    };
    private static MapCell[] WALL_LIST =
    {
        new MapCellWall("Wall", "../resources/wall-3d.jpg"),
        new MapCellWall("Wall", "../resources/wall-black.jpg"),
        new MapCellWall("Wall", "../resources/wall-red.jpg"),
    };
    private static MapCell[] TREASURE_LIST =
    {
        new MapCellTreasure("Treasure", "../resources/treasure-1.jpg"),
        new MapCellTreasure("Treasure", "../resources/treasure-2.jpg"),
        new MapCellTreasure("Treasure", "../resources/treasure-3.jpg"),
    };
    private static MapCell[] START_LIST =
    {
        new MapCellStart("Start", "../resources/start.jpg"),
    };
    private static MapCell[] END_LIST =
    {
        new MapCellEnd("End", "../resources/end.jpg"),
    };
    
    private static StaticMapCellList m_Instance = null;
    private Hashtable m_Items = new Hashtable();

    /**
     * Default constructor. Exists only to defeat instantiation.
     */
    protected StaticMapCellList()
    {
        // Exists only to defeat instantiation.
    }
    /**
     * To get the instance.
     * If the instance is none, then we can create a new instance with the map cell list.
     * And do the initial map item list.
     * @return the instance
     * 
     * @author yukywang
     */
    public static StaticMapCellList getInstance()
    {
        if (m_Instance == null)
        {
            m_Instance = new StaticMapCellList();
            m_Instance.InitialMapItemList();
        }
        return m_Instance;
    }
    
    /**
     * Returns a set as keys to the m_Items array.
     * @return An array.
     */
    public Set getKeys()
    {
        return m_Items.keySet();
    }
    
    /**
     * Gets item collection.
     * 
     * @param pName Based on this parameter, an array of map cells are returned.
     * @return An array of Map Cells
     */
    
    public MapCell[] getItemCollection(String pName)
    {
        return (MapCell[])m_Items.get(pName);
    }    
    
    /**
     * To do the initial map item list.
     * initialize the map item in the list.
     * 
     * @author yukywang
     */
    private void InitialMapItemList()
    {
        m_Items.put(ITEM_MONSTER, MONSTER_LIST);
        m_Items.put(ITEM_WALL, WALL_LIST);
        m_Items.put(ITEM_TREASURE, TREASURE_LIST);
        m_Items.put(ITEM_START_POINT, START_LIST);
        m_Items.put(ITEM_END_POINT, END_LIST);
    }
}
