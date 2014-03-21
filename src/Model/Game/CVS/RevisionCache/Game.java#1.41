package Model.Game;

import Model.Map.Map;
import Model.Character;
import Model.Map.MapBuilderConcrete;
import Model.Map.MapCell;
import Model.Map.MapCellMonster;
import Model.Map.MapCellTreasure;
import View.Game.GameConsole;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

/**
 * Game model class.
 * This class contents all the details and information of Game. It remains alive as
 * long as the game is active.
 * @author Xuexiang Hu
 * @version  2.0
 */
public class Game extends Observable
{

    private final int MAX_MOVE = 5;
    private Map _map = null;
    private Character _player = null;
    private boolean _gameOver = false;
    private ArrayList<Character> _characterList = new ArrayList<Character>();
    private int _activePlayer = 0;
    private int _currentRound = 0;
    private boolean _endTurn = false;
    private boolean _playerWin = true;
    private int _turn = 0;
    private ArrayList<GridData> _monsterPath = new ArrayList<GridData>();
    private int[][] _distanceMatrix = null;
    

    /**
     * The constructor initializes all the properties of the game.
     * @param pPlayer it is the active and single player in the game. 
     * @param pMap  it is the map we use during the game.
     */
    public Game(Character pPlayer, Map pMap)
    {
        _player = pPlayer;
        _map = pMap;
        _player.setDescription(_player.getName());
        MapBuilderConcrete builder = new MapBuilderConcrete(_player.getLevel(), _map);
        builder.build();
        _player.SetRow(pMap.getStartPoint().GetRow());
        _player.SetCol(pMap.getStartPoint().GetCol());
        _characterList.add(_player);
        _distanceMatrix = new int[pMap.getMapRows()][pMap.getMapCols()];
        for (int row = 0; row < pMap.getMapRows(); row++)
        {
            for (int col = 0; col < pMap.getMapCols(); col++)
            {
                MapCell cell = pMap.getMapElement(row, col);
                if (cell instanceof MapCellMonster)
                {
                    _characterList.add((Character) cell);
                }
            }
        }
    }

    /**
     * To get the monster path.
     * @return the monster path.
     */
    public ArrayList<GridData> getMonsterPath()
    {
        return _monsterPath;
    }

    /**
     * This is a private function to calculate the monster path.
     * @param pMonster the monster in the cell.
     */
    private void calculateMonsterPath(Character pMonster)
    {

        for (int row = 0; row < _map.getMapRows(); row++)
        {
            for (int col = 0; col < _map.getMapCols(); col++)
            {
                if (_map.getMapElement(row, col) == null)
                {
                    _distanceMatrix[row][col] = -1;
                } else
                {
                    _distanceMatrix[row][col] = -2;
                }

            }
        }
        _distanceMatrix[pMonster.GetRow()][pMonster.GetCol()] = 0;
        buildPathMatrix(pMonster.GetRow(), pMonster.GetCol(), _map.getMapRows(), _map.getMapCols());
        //printMatrix();
        traceBackPathMatrix(_player.GetRow(), _player.GetCol(), _map.getMapRows(), _map.getMapCols());
        //printPath();
    }

    /*
    void printMatrix()
    {
    for (int row = 0; row < _map.getMapRows(); row++)
    {
    for (int col = 0; col < _map.getMapCols(); col++)
    {
    String str = Integer.toString(_distanceMatrix[row][col]);
    if (str.length() == 1)
    {
    str += " ";
    }
    System.out.print(str);
    System.out.print(" | ");
    }
    System.out.println();
    }
    }
    
    void printPath()
    {
    for(int i = 0; i < _monsterPath.size(); i ++)
    {
    System.out.print(_monsterPath.get(i).x);
    System.out.print("*");
    System.out.print(_monsterPath.get(i).y);
    System.out.println();
    }
    }
     */
    
    /**
     * This is a private function to build the path matrix.
     * @param pRow the row of the cell.
     * @param pCol the column of the cell.
     * @param pMaxRow the maximum row of the cell.
     * @param pMaxCol  the maximum column of the cell.
     */
    private void buildPathMatrix(int pRow, int pCol, int pMaxRow, int pMaxCol)
    {
        int newInt = pRow + 1;
        if (newInt < pMaxRow)
        {
            if (_distanceMatrix[newInt][pCol] == -1 || _distanceMatrix[newInt][pCol] > _distanceMatrix[pRow][pCol] + 1)
            {
                _distanceMatrix[newInt][pCol] = _distanceMatrix[pRow][pCol] + 1;
                buildPathMatrix(newInt, pCol, pMaxRow, pMaxCol);
            }
        }

        newInt = pRow - 1;
        if (newInt >= 0)
        {
            if (_distanceMatrix[newInt][pCol] == -1 || _distanceMatrix[newInt][pCol] > _distanceMatrix[pRow][pCol] + 1)
            {
                _distanceMatrix[newInt][pCol] = _distanceMatrix[pRow][pCol] + 1;
                buildPathMatrix(newInt, pCol, pMaxRow, pMaxCol);
            }
        }

        newInt = pCol + 1;
        if (newInt < pMaxCol)
        {
            if (_distanceMatrix[pRow][newInt] == -1 || _distanceMatrix[pRow][newInt] > _distanceMatrix[pRow][pCol] + 1)
            {
                _distanceMatrix[pRow][newInt] = _distanceMatrix[pRow][pCol] + 1;
                buildPathMatrix(pRow, newInt, pMaxRow, pMaxCol);
            }
        }

        newInt = pCol - 1;
        if (newInt >= 0)
        {
            if (_distanceMatrix[pRow][newInt] == -1 || _distanceMatrix[pRow][newInt] > _distanceMatrix[pRow][pCol] + 1)
            {
                _distanceMatrix[pRow][newInt] = _distanceMatrix[pRow][pCol] + 1;
                buildPathMatrix(pRow, newInt, pMaxRow, pMaxCol);
            }
        }
    }

    /**
     * To trace the back path matrix.
     * @param pRow the row of the cell.
     * @param pCol the column of the cell.
     * @param pMaxRow the maximum row of the cell.
     * @param pMaxCol  the maximum column of the cell.
     */
    private void traceBackPathMatrix(int pRow, int pCol, int pMaxRow, int pMaxCol)
    {
        _monsterPath.clear();
        //if (_distanceMatrix[pRow][pCol] > 0)
        {

            int distance = 0;
            int row = pRow;
            int col = pCol;
            while (_distanceMatrix[pRow][pCol] != 0)
            {
                distance = 0;
                boolean found = false;
                int newInt = pRow + 1;
                if (newInt < pMaxRow)
                {
                    if (_distanceMatrix[newInt][pCol] >= 0)
                    {
                        distance = _distanceMatrix[newInt][pCol];
                        row = newInt;
                        col = pCol;
                        found = true;
                    }
                }

                newInt = pRow - 1;
                if (newInt >= 0)
                {
                    if (_distanceMatrix[newInt][pCol] >= 0)
                    {
                        if (!found || _distanceMatrix[newInt][pCol] < distance)
                        {
                            distance = _distanceMatrix[newInt][pCol];
                            row = newInt;
                            col = pCol;
                            found = true;
                        }
                    }
                }

                newInt = pCol + 1;
                if (newInt < pMaxCol)
                {
                    if (_distanceMatrix[pRow][newInt] >= 0)
                    {
                        if (!found || _distanceMatrix[pRow][newInt] < distance)
                        {
                            distance = _distanceMatrix[pRow][newInt];
                            row = pRow;
                            col = newInt;
                            found = true;
                        }
                    }
                }

                newInt = pCol - 1;
                if (newInt >= 0)
                {
                    if (_distanceMatrix[pRow][newInt] >= 0)
                    {
                        if (!found || _distanceMatrix[pRow][newInt] < distance)
                        {
                            distance = _distanceMatrix[pRow][newInt];
                            row = pRow;
                            col = newInt;
                            found = true;
                        }
                    }
                }
                if (found)
                {
                    pRow = row;
                    pCol = col;
                    _monsterPath.add(new GridData(pRow, pCol));
                } else
                {
                    break;
                }
            }

            if(_monsterPath.size() > 1)
            {
                _monsterPath.remove(_monsterPath.size()-1);
            }
        }

    }

    /**
     * This public function to get the return which is boolean value.
     *
     * @return has attack
     * @author yukywang
     */
    public boolean canAttack()
    {
        return (_player.getAttackLeftPerRound() > 0);
    }

    /**
     * This is a private function that check if the enemy is in the adjacent cell.
     * To check whether the player and monsters are in the adjacent cell or at the same cell or not.
     * @param pCellA the current cell of the monster or player.
     * @param pCellB the current cell of the monster or player.
     * @return a boolean type value. 
     * @author yukywang
     */
    private boolean ifAdjacent(MapCell pCellA, MapCell pCellB)
    {
        if (pCellA.GetCol() == pCellB.GetCol())
        {
            if (Math.abs(pCellA.GetRow() - pCellB.GetRow()) == 1)
            {
                return true;
            }
        } else if (pCellA.GetRow() == pCellB.GetRow())
        {
            if (Math.abs(pCellA.GetCol() - pCellB.GetCol()) == 1)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This is a public function that get the character's name frome the character list.
     * 
     * @param pIndex an index of the character list.
     * @return the name of the character.
     * @author yukywang
     */
    public String GetName(int pIndex)
    {
        return _characterList.get(pIndex).getDescription();
    }

    /**
     * This is a public function that get the attack list.
     * First check whether the character is in the attack range or not,
     * If the character is in the attack range, then add this character to the enemy list.
     * Or, check the character to see if they are adjacent or not. 
     * If they are adjacent, then add this character to the enemy list.
     * @return enemy list 
     * @author yukywang
     */
    public ArrayList<Integer> getAttackList()
    {
        ArrayList<Integer> enemyList = new ArrayList<Integer>();
        if (_player.isIsRange())
        {
            for (int i = 0; i < _characterList.size(); i++)
            {
                if (_characterList.get(i) != _player)
                {
                    enemyList.add(new Integer(i));
                }
            }
        } else
        {
            for (int i = 0; i < _characterList.size(); i++)
            {
                if (_characterList.get(i) != _player)
                {
                    if (ifAdjacent(_player, _characterList.get(i)))
                    {
                        enemyList.add(new Integer(i));
                    }
                }
            }
        }
        return enemyList;
    }

    /**
     * This is the public function to attack enemy.
     * set the has attack value to true.
     * @param pIndex Index of the monster position.
     * @author yukywang
     */
    public void attackEnemy(int pIndex)
    {
        Character monster = _characterList.get(pIndex);
        attack(_player, monster);
        monster.setIsMad(true);
        _player.minusAttackPerRound();
        if (monster.getHitPoints() == 0)
        {
            String str = monster.getDescription() + " was killed";
            GameConsole.getGameConsole().appendMessage(_turn, "System", str);
            _map.removeElement(monster.GetRow(), monster.GetCol());
            _characterList.remove(pIndex);
            _activePlayer = _characterList.indexOf(_player);
        }
        UpdateView();
    }

    /**
     * This is a private function that print the output message about the information when the attack happened.
     * If the total of the dice and the attack bonus is more than destinate character's armor,
     * then print the message about the information when the attack happened.
     * If it is not, then print no damage message.
     * @param src the source character who attack the destinate character.
     * @param dest the destinate character who is attacked.
     * @author yukywang
     */
    private void attack(Character src, Character dest)
    {
        Dice dice = new Dice(1, 20);
        int d = dice.roll();
        int ab = src.getAttackBonus();
        int total = d + ab;
        StringBuffer msg = new StringBuffer("");
        msg.append("Attacks ");
        msg.append(dest.getDescription());
        msg.append(". Attacker'total attack bonus");
        msg.append(total);
        msg.append("=dice");
        msg.append(d);
        msg.append("+");
        msg.append("attack bonus");
        msg.append(ab);
        msg.append(". Defender armor class=");
        msg.append(dest.getArmorClass());
        if (total >= dest.getArmorClass())
        {
            msg.append(". Conduct a damage \n");
            Dice d8 = new Dice(1, 8);
            int dd = d8.roll();
            int totalDamage = dd + src.getDamageBonus();
            msg.append(". Total damage");
            msg.append(totalDamage);
            msg.append("=dice");
            msg.append(dd);
            msg.append("+");
            msg.append("damage bonus");
            msg.append(src.getDamageBonus());

            msg.append(";");
            msg.append(dest.getDescription());
            msg.append(" Before attack hitpoints");
            msg.append(dest.getHitPoints());

            dest.minusHitPoints(totalDamage);
            msg.append(";");
            msg.append(dest.getDescription());
            msg.append(" remaining hitpoints");
            msg.append(dest.getHitPoints());
        } else
        {
            msg.append(". No damage.");
        }
        GameConsole.getGameConsole().appendMessage(_turn, src.getDescription(), msg.toString());

        System.out.println(msg);

    }

    /**
     * This method will save the character when the game is over and character wins.
     */
    public void saveCharacter()
    {
        _player.addLevel();
        _player.saveCharacter();
        GameConsole.getGameConsole().appendMessage(_turn, _player.getDescription(), "level up and won the game");
    }

    /**
     * To get the active cell in the map cell.
     * @return active player.
     * @author yukywang
     */
    public Character getActiveCell()
    {
        return _characterList.get(_activePlayer);
    }

    /**
     * This is a public function that get the return boolean value of the player turn.
     * @return the boolean value 
     * @author yukywang
     */
    public boolean playerTurn()
    {
        return (getActiveCell() == _player);
    }

    /**
     * This is the public function that start the game.
     */
    public void startGame()
    {
        initialTurn();
    }

    /**
     * This is a public function attack and move.
     * get the active cell and move the player.
     * @return the boolean type value.
     * @author yukywang
     */
    public boolean attackAndMove()
    {

        if (ifAdjacent(getActiveCell(), _player))
        {
            while (getActiveCell().getAttackLeftPerRound() > 0)
            {
                //_player.minusHitPoints(1/*getActiveCell().getAttackBonus()*/);
                attack(getActiveCell(), _player);
                getActiveCell().minusAttackPerRound();
            }
            getActiveCell().endOfTurn();
            if (_player.getHitPoints() == 0)
            {
                _playerWin = false;
                _gameOver = true;
                //_characterList.remove(_player);
            }
            return true;
        } else
        {
            int direct = getNextMove();
            if (direct == -1)
            {
                Dice dice = new Dice(0, 3);
                direct = dice.roll();
            }
            if (movePlayer(getActiveCell(), direct))
            {
                if (ifAdjacent(getActiveCell(), _player))
                {
                    while (getActiveCell().getAttackLeftPerRound() > 0)
                    {
                        //_player.minusHitPoints(/*getActiveCell().getAttackBonus()*/1);
                        attack(getActiveCell(), _player);
                        getActiveCell().minusAttackPerRound();
                    }
                    getActiveCell().endOfTurn();
                    if (_player.getHitPoints() == 0)
                    {
                        _playerWin = false;
                        _gameOver = true;
                        // _characterList.remove(_player);
                    }
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * To get the next move.
     * @return integer type value of the ret.
     */
    private int getNextMove()
    {
        int ret = -1;
        if (_monsterPath.size() > 0)
        {
            GridData gd = _monsterPath.remove(_monsterPath.size() - 1);
            if (getActiveCell().GetRow() == gd.x)
            {
                int dis = getActiveCell().GetCol() - gd.y;
                if (dis == 1) //move left
                {
                    ret = 2;
                } else if (dis == -1) //move right
                {
                    ret = 3;
                }
            } else if (getActiveCell().GetCol() == gd.y)
            {
                int dis = getActiveCell().GetRow() - gd.x;
                if (dis == 1) //move up
                {
                    ret = 0;
                } else if (dis == -1) //move down
                {
                    ret = 1;
                }
            }
        }
        return ret;
    }

    /**
     * This is a public function that return a value to indicate whether the player win the game.
     * @return boolean type value true indicates player win the game.
     * @author yukywang
     */
    public boolean isPlayerWin()
    {
        return _playerWin;
    }

    /**
     * This is a public function that get the return value to show whether the character can move.
     * @return boolean type value true means that the character can move. 
     * @author yukywang
     */
    public boolean ifCanMove()
    {
        return (_currentRound != MAX_MOVE);
    }

    /**
     * This is a custom comparator class which implements the comparator.
     * To compare the two characters. And get a integer type return value.
     */
    class CustomComparator implements Comparator<Character>
    {

        /**
         * This is a override public function.
         * To compare two characters and get a integer type return value.
         * @param c1 one character.
         * @param c2 another character.
         * @return integer type value.
         */
        @Override
        public int compare(Character c1, Character c2)
        {
            return c2.initialRoll - c1.initialRoll;
        }
    }

    /**
     * This is a private function that initialize the turn of the characters.
     * If there are more than one character in the character list, 
     * then the two characters need to rolling a dice to determine the play order.
     * @author yukywang
     */
    private void initialTurn()
    {
        _turn += 1;
        if (_characterList.size() > 1)
        {
            // GameConsole.getGameConsole().clear();
            GameConsole.getGameConsole().appendMessage(_turn, "System", "Rolling to determine play order");
            Dice dice = new Dice(1, 20);
            for (int i = 0; i < _characterList.size(); i++)
            {
                int roll = dice.roll();
                int dex = _characterList.get(i).calculateModifier(_characterList.get(i).getDexterity());
                _characterList.get(i).initialRoll = roll + dex;
                StringBuffer msg = new StringBuffer();
                msg.append("Roll a dice of ");
                msg.append(roll);
                msg.append("Plus dexterity modifier ");
                msg.append(dex);
                msg.append("Total number is");
                msg.append(_characterList.get(i).initialRoll);
                GameConsole.getGameConsole().appendMessage(_turn, _characterList.get(i).getDescription(), msg.toString());
            }
            Collections.sort(_characterList, new CustomComparator());
            StringBuffer msg = new StringBuffer();
            msg.append("Play order is: ");
            for (int i = 0; i < _characterList.size(); i++)
            {
                msg.append("->");
                msg.append(_characterList.get(i).getDescription());
            }
            GameConsole.getGameConsole().appendMessage(_turn, "System", msg.toString());
        }
    }

    /**
     * This is a public function called play round.
     * To set active player 
     * And if the current player is human, active control. 
     * Then update the view.
     */
    public void playRound()
    {
        //set active player
        if (!playerTurn())
        {
            if (_currentRound == 0 && this.getActiveCell().isMad())
            {
                this.calculateMonsterPath(this.getActiveCell());
                UpdateView();
            }
            if (attackAndMove())
            {
                _currentRound = MAX_MOVE;
            } else
            {
                _currentRound += 1;
            }
        }
        //if current player is human, active control
        if (_currentRound == MAX_MOVE)
        {

            if (playerTurn() && _endTurn)
            {
                if (_activePlayer == _characterList.size() - 1)
                {
                    initialTurn();
                }
                _activePlayer = (_activePlayer + 1) % _characterList.size();
                _player.endOfTurn();
                _endTurn = false;
                _currentRound = 0;
            } else if (!playerTurn())
            {
                if (_activePlayer == _characterList.size() - 1)
                {
                    initialTurn();
                }
                _currentRound = 0;
                _activePlayer = (_activePlayer + 1) % _characterList.size();
            }
            this._monsterPath.clear();
        }
        UpdateView();
    }

    /**
     * This method checks if the game is over or not.
     * @return Returns true if the game is over, otherwise false.
     */
    public boolean isGameOver()
    {
        return _gameOver;
    }

    /**
     * This method returns the map using by the game.
     * @return Returns value of map.
     */
    public Map getMap()
    {
        return _map;
    }

    /**
     * This method returns the current character of the game player
     * @return Returns value of Character object
     */
    public Character getPlayer()
    {
        return _player;
    }

    /**
     * To move the human player.
     * 
     * @param pDirection the direction of the human player.
     * @author yukywang
     */
    public void moveHumanPlayer(int pDirection)
    {
        if (playerTurn() && _currentRound < MAX_MOVE)
        {
            if (movePlayer(_player, pDirection))
            {
                _currentRound += 1;
                playRound();
            }
        }
    }

    /**
     * This is the function that end the human player turn.
     * 
     */
    public void endHumanPlayerTurn()
    {
        _currentRound = MAX_MOVE;
        _endTurn = true;
        playRound();
    }

    /**
     * To get the treasure in the map cell treasure.
     * 
     * @return the map cell treasure ret or null.
     * @author yukywang
     */
    public MapCellTreasure getTreasure()
    {
        MapCell ret = _map.getMapElement(_player.GetRow(), _player.GetCol());
        if (ret instanceof MapCellTreasure)
        {
            return (MapCellTreasure) ret;
        }
        return null;
    }

    /**
     * This is a public function that pick treasure.
     * Then update the view.
     * 
     */
    public void pickTreasure(int pIndex)
    {
        MapCellTreasure treasure = (MapCellTreasure) _map.getMapElement(_player.GetRow(), _player.GetCol());
        if (treasure != null)
        {
            _player.addBackpackItems(treasure.getItemList().get(pIndex), true);
            GameConsole.getGameConsole().appendMessage(_turn, _player.getDescription(), " picks " + treasure.getItemList().get(pIndex).getName());
        }
        _map.removeElement(_player.GetRow(), _player.GetCol());
        UpdateView();
    }

    /**
     * This is a private function called player in cell.
     * This function return a boolean type value to indicate whether the player is in the cell or not.
     * @param row the row of the player cell.
     * @param col the colum of the player cell.
     * @return boolean type value.
     */
    private boolean playerInCell(int row, int col)
    {
        return (_player.GetRow() == row && _player.GetCol() == col);
    }

    /**
     * This method moves the current player to four possible new locations which could be up
     * down, left or right. This method also cause the game view to refresh
     * @param pDirection is an integer 0: direction up 1: direction down 2: direction left
     * 3: direction right
     */
    private boolean movePlayer(MapCell player, int pDirection)
    {
        boolean ret = false;
        int row = player.GetRow();
        int col = player.GetCol();
        if (pDirection == 0) //move up
        {
            if (row > 0)
            {
                MapCell cell = _map.getMapElement(row - 1, col);
                if (playerInCell(row - 1, col))
                {
                    cell = _player;
                }
                if ((cell == null) || (cell.canOverlap() && player.canOverlap()))
                {
                    player.SetRow(row - 1);
                    if (player != _player)
                    {
                        _map.moveElement(row, col, row - 1, col);
                    }
                    //UpdateView();
                    ret = true;
                }
            }
        } else if (pDirection == 1) //move down
        {
            if (row < _map.getMapRows() - 1)
            {
                MapCell cell = _map.getMapElement(row + 1, col);
                if (playerInCell(row + 1, col))
                {
                    cell = _player;
                }
                if (cell == null || (cell.canOverlap() && player.canOverlap()))
                {
                    player.SetRow(row + 1);
                    if (player != _player)
                    {
                        _map.moveElement(row, col, row + 1, col);
                    }
                    //UpdateView();
                    ret = true;
                }
            }
        } else if (pDirection == 2) //move left
        {
            if (col > 0)
            {
                MapCell cell = _map.getMapElement(row, col - 1);
                if (playerInCell(row, col - 1))
                {
                    cell = _player;
                }
                if (cell == null || (cell.canOverlap() && player.canOverlap()))
                {
                    player.SetCol(col - 1);
                    if (player != _player)
                    {
                        _map.moveElement(row, col, row, col - 1);
                    }
                    //UpdateView();
                    ret = true;
                }
            }
        } else if (pDirection == 3) //move right
        {
            if (col < _map.getMapCols() - 1)
            {
                MapCell cell = _map.getMapElement(row, col + 1);
                if (playerInCell(row, col + 1))
                {
                    cell = _player;
                }
                if (cell == null || (cell.canOverlap() && player.canOverlap()))
                {
                    player.SetCol(col + 1);
                    if (player != _player)
                    {
                        _map.moveElement(row, col, row, col + 1);
                    }
                    //UpdateView();
                    ret = true;
                }
            }
        }
        return ret;
    }

    /**
     * This private method update the all the views which binded to this game model.
     */
    private void UpdateView()
    {
        if (!_gameOver)
        {
            if (_player.GetRow() == _map.getEndPoint().GetRow() && _player.GetCol() == _map.getEndPoint().GetCol())
            {
                if (_characterList.size() == 1)
                {
                    _gameOver = true;
                }
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
}
