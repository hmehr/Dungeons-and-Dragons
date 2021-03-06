/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;
import Model.Character;
import Model.CharacterBuilder;
import Model.CharacterBuilderMonster;
import Model.CharacterDirector;
import Model.Items.ItemFactory;
/**
 * This is a public class called map builder concrete which implements the interface of map builder base.
 * @author eric
 */
public class MapBuilderConcrete implements MapBuilderBase
{
    private int _level;
    private Map _map;
    
    /**
     * Constructor to set the map builder concrete.
     * @param pLevel set the level.
     * @param pMap set the map.
     */
    public MapBuilderConcrete(int pLevel,Map pMap)
    {
        _level = pLevel;
        _map = pMap;
    }
    
    /**
     * This is a public function called build.
     * invoke the function of build treasure and build monster.
     */
    public void build()
    {
        buildTreasure();
        buildMonster();
    }
    
    /**
     * To build treasure.
     */
    public void buildTreasure()
    {
        int cols = _map.getMapCols();
        int rows = _map.getMapRows();
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                MapCell cell = _map.getMapElement(row, col);
                if(cell instanceof MapCellTreasure)
                {
                    MapCellTreasure treasure = (MapCellTreasure)cell;
                    ChestBuilderConcrete tBuilder = new ChestBuilderConcrete(treasure,_level);
                    tBuilder.buildTreasure();
                    tBuilder.buildMagicEffect();
                }
            }
        }
    }
    
    /**
     * To build the monster.
     */
    public void buildMonster()
    {
        int index = 0;
        int cols = _map.getMapCols();
        int rows = _map.getMapRows();
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                MapCell cell = _map.getMapElement(row, col);
                if(cell instanceof MapCellMonster)
                {
                    /*
                    MapCellMonster monster = (MapCellMonster)cell;
                    monster.setLevel(_level);
                    monster.setDescription(monster.getDescription() + Integer.toString(index));
                    index += 1;*/
                    
                    CharacterBuilder characterBuilder = new CharacterBuilderMonster();
                    CharacterDirector cd = new CharacterDirector(characterBuilder, cell.getDescription()+ Integer.toString(index) , cell.getImageName(), _level);
                    cd.constructCharacter();
                    cd.getCharacter().SetRow(cell.GetRow());
                    cd.getCharacter().SetCol(cell.GetCol());
                    cd.getCharacter().setImage(cell.getImageName());
                    cd.getCharacter().setDescription(cell.getDescription()+ Integer.toString(index));
                    cd.getCharacter().wearItem(ItemFactory.getItemByName("longsword"));
                    _map.setMapElement(cd.getCharacter());
                    index += 1; 
                }
            }
        }
    }
    
    /**
     * To get the map and return the map.
     * @return map
     */
    public Map getMap()
    {
        return _map;
    }
    
}
