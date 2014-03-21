package View.Game;

import Model.Game.Game;
import Model.Map.MapCell;
import Model.Map.MapCellMonster;
import Model.Character;
import Model.Map.Map;
import Model.OutputConsole;
import View.Character.CharacterGame;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Game view class.
 * This class render the entire game on the round based.
 * @author Xuexiang Hu
 */
public class GameView extends JPanel implements Observer, KeyListener, MouseListener
{

    private Game _game = null;
    private JFrame _frame = null;
    CharacterGame _cGameView;
    Timer _timer;

    /**
     * The constructor initializes all the properties of the game.
     * @param pGame is the parameter of game model
     * @param pFrame is the parent class of game view class 
     */
    public GameView(Game pGame, JFrame pFrame)
    {
        GameConsole.getGameConsole().clear();
        _game = pGame;
        _game.startGame();
        addKeyListener(this);
        _frame = pFrame;
        addMouseListener(this);
        _timer = new Timer();
        _timer.schedule(new RemindTask(), 800);
        GameConsole.getGameConsole().setVisible(true);
    }

    /**
     * This class RemindTask is inherited TimerTask.
     * @author yukywang
     */
    class RemindTask extends TimerTask
    {

        public RemindTask()
        {
        }

        /**
         * To reset the game play round and timer schedule.
         * @author yukwang
         */
        public void run()
        {
            if (!_game.playerTurn())
            {
                try
                {
                    _game.playRound();
                    _timer.schedule(new RemindTask(), 800);
                } catch (Exception e)
                {
                }
            }
        }
    }

    /**
     * This method cause the entire game view to re-render by observer pattern
     * @param pOb is the Observable cause this class to re-render
     * @param pArg is the parameter argument passed from Observable
     */
    public void update(Observable pOb, Object pArg)
    {
        this.repaint();

        if (_game.isGameOver())
        {


            _timer.cancel();

            if (_game.isPlayerWin())
            {
                if (_game.getPlayer().getLevel() == 20)
                {
                    JOptionPane.showMessageDialog(this, "Game Over And You Win", "Finish", JOptionPane.DEFAULT_OPTION);
                } else
                {
                    JOptionPane.showMessageDialog(this, "You Win And Adance To Next Game", "Advance", JOptionPane.DEFAULT_OPTION);
                    //_game.getPlayer().endGameInitialize();
                    _game.saveCharacter();
                    try
                    {
                        File file = new File(_game.getMap().getMapFile());
                        FileInputStream fin = new FileInputStream(file.getAbsoluteFile());
                        ObjectInputStream ois = new ObjectInputStream(fin);
                        Map loadedMap = (Map) ois.readObject();
                        loadedMap.setMapFile(_game.getMap().getMapFile());
                        GameJFrame newGameJFrame = new GameJFrame(_game.getPlayer(), loadedMap);
                        newGameJFrame.setSize(60 * loadedMap.getMapCols(), 60 * loadedMap.getMapRows());
                        newGameJFrame.setVisible(true);
                        newGameJFrame.setLocationRelativeTo(this);
                    } catch (Exception e)
                    {
                        OutputConsole.GetOutputConsoleInstance().WriteException("Error Occured: " + e.getMessage());
                    }
                }
                _frame.setVisible(false);

                //GameConsole.getGameConsole().setVisible(false);
            } else
            {
                JOptionPane.showMessageDialog(this, "Game Over And You Lost", "Finish", JOptionPane.DEFAULT_OPTION);
                _frame.setVisible(false);
                //GameConsole.getGameConsole().setVisible(false);
            }

        }
    }

    /**
     * This method draw the entire game view which includes map and a character on map
     * @param pGraphics is the graphics object we use to draw on
     */
    @Override
    public void paintComponent(Graphics pGraphics)
    {
        // fill with the color you want
        int wide = getWidth();
        int tall = getHeight();
        int cols = _game.getMap().getMapCols();
        int rows = _game.getMap().getMapRows();
        pGraphics.setColor(Color.white);
        pGraphics.fillRect(0, 0, wide, tall);
        // go into Graphics2D for all the fine art, more options
        // optional, here I just get variable Stroke sizes

        Graphics2D g2 = (Graphics2D) pGraphics;
        int w = wide / cols;
        int h = tall / rows;
        pGraphics.setColor(Color.YELLOW);

        if (_game.getActiveCell().isMad())
        {
            for (int i = 0; i < _game.getMonsterPath().size(); i++)
            {
                pGraphics.fillRect(_game.getMonsterPath().get(i).y * w, _game.getMonsterPath().get(i).x * h, w, h);
            }

        }




        g2.setColor(Color.cyan);
        g2.setStroke(new BasicStroke(1));
        // the verticles
        for (int i = 1; i < cols; i++)
        {
            g2.drawLine(i * w, 0, i * w, tall);
        }
        // the horizontals
        for (int i = 1; i < rows; i++)
        {
            g2.drawLine(0, i * h, wide, i * h);
        }
        boolean overlap = false;
        //draw the game grid
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                MapCell cell = _game.getMap().getMapElement(row, col);
                if (cell != null)
                {
                    if (cell.GetRow() == _game.getPlayer().GetRow() && cell.GetCol() == _game.getPlayer().GetCol())
                    {
                        drawMapCell(g2, cell, col * w + 2, row * h + 2, (w - 4) / 2, (h - 4) / 2);
                        drawMapCell(g2, _game.getPlayer(), col * w + 2 + (w - 4) / 2, row * h + 2 + (h - 4) / 2, (w - 4) / 2, (h - 4) / 2);
                        overlap = true;

                    } else
                    {
                        drawMapCell(g2, cell, col * w + 2, row * h + 2, w - 4, h - 4);
                    }
                }
            }
        }
        if (!overlap)
        {
            //determine if we draw the overlap cells in the same matrix spot.
            drawMapCell(g2, _game.getPlayer(), _game.getPlayer().GetCol() * w + 2, _game.getPlayer().GetRow() * h + 2, w - 4, h - 4);
        }
    }

    /**
     * This is the method to draw the map cell.
     * 
     * @param pGraphics the map graphics.
     * @param pCell the map cell.
     * @param x the row of the map
     * @param y the colum of the map
     * @param w the wide of the map
     * @param h the high of the map
     * @author yukywang
     */
    private void drawMapCell(Graphics2D pGraphics, MapCell pCell, int x, int y, int w, int h)
    {
        if (pCell != _game.getActiveCell() && (pCell instanceof MapCellMonster || pCell instanceof Character))
        {
            try
            {
                BufferedImage img = pCell.getImage();
                if (img == null)
                {
                    java.net.URL imageURL = GameView.class.getResource(pCell.getImageName());
                    img = ImageIO.read(imageURL);
                    pCell.setImage(img);
                }
                BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                pGraphics.drawImage(op.filter(img, null), x + 5, y + 10, w - 10, h - 10, this);
                pGraphics.setColor(Color.black);
                pGraphics.drawRect(x, y, w, 8);
                pGraphics.setColor(Color.red);
                Character ch = (Character) pCell;
                int hpBar = (int) ((w * 1.0 * ch.getHitPoints()) / (ch.getMaxHitPoints() * 1.0));
                pGraphics.fillRect(x, y, hpBar, 8);
            } catch (IOException e)
            {
            }
        } else
        {
            try
            {
                BufferedImage img = pCell.getImage();
                if (img == null)
                {
                    java.net.URL imageURL = GameView.class.getResource(pCell.getImageName());
                    img = ImageIO.read(imageURL);
                    pCell.setImage(img);
                }
                if (pCell instanceof MapCellMonster || pCell instanceof Character)
                {
                    Character ch = (Character) pCell;
                    pGraphics.drawImage(img, x + 5, y + 10, w - 10, h - 10, this);
                    pGraphics.setColor(Color.black);
                    pGraphics.drawRect(x, y, w, 8);
                    pGraphics.setColor(Color.red);

                    int hpBar = (int) ((w * 1.0 * ch.getHitPoints()) / (ch.getMaxHitPoints() * 1.0));
                    pGraphics.fillRect(x, y, hpBar, 8);
                } else
                {
                    pGraphics.drawImage(img, x, y, w, h, this);
                }
            } catch (IOException e)
            {
            }
        }
    }

    /**
     * This method will be invoked when keyboard was pressed
     * @param pEvt object is keyboard press event
     */
    public void keyPressed(KeyEvent pEvt)
    {
        if (_game.playerTurn())
        {
            int keyCode = pEvt.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT)
            {
                _game.moveHumanPlayer(2);
            } else if (keyCode == KeyEvent.VK_RIGHT)
            {
                _game.moveHumanPlayer(3);
            } else if (keyCode == KeyEvent.VK_UP)
            {
                _game.moveHumanPlayer(0);
            } else if (keyCode == KeyEvent.VK_DOWN)
            {
                _game.moveHumanPlayer(1);
            }
            if (!_game.playerTurn())
            {
                _timer.schedule(new RemindTask(), 800);
            }
        }
    }

    /**
     * This method will be invoked when keyboard was released
     * @param evt object is keyboard press event
     */
    public void keyReleased(KeyEvent evt)
    {
    }

    /**
     * This method will be invoked when keyboard was typed
     * @param evt object is keyboard press event
     */
    public void keyTyped(KeyEvent evt)
    {
    }

    /**
     * This method will be invoked when keyboard was typed
     */
    @Override
    public boolean isFocusTraversable()
    {
        return true;
    }

    /**
     * This is a private function that get the character.
     * To get the character.
     * @param pX use this parameter to calculate the colum.
     * @param pY use this parameter to calculate the row.
     * @return the cell of the character.
     */
    private Character getCharacter(int pX, int pY)
    {
        Character ret = null;
        int wide = getWidth();
        int tall = getHeight();
        int cols = _game.getMap().getMapCols();
        int rows = _game.getMap().getMapRows();
        int w = wide / cols;
        int h = tall / rows;
        int col = pX / w;
        int row = pY / h;
        MapCell cell = _game.getMap().getMapElement(row, col);
        if (cell instanceof MapCellMonster)
        {
            ret = (Character) cell;
        }
        return ret;
    }

    /**
     * This method will be invoked when mouse was clicked
     * @param pEvent is the mouse event
     */
    public void mouseClicked(MouseEvent pEvent)
    {
        if (_game.playerTurn())
        {
            int wide = getWidth();
            int tall = getHeight();
            int cols = _game.getMap().getMapCols();
            int rows = _game.getMap().getMapRows();
            int w = wide / cols;
            int h = tall / rows;
            int boundWidth = _game.getPlayer().GetCol() * w;
            int boundHeight = _game.getPlayer().GetRow() * h;
            if (pEvent.getClickCount() == 2 && !pEvent.isConsumed())
            {
                pEvent.consume();
                if (pEvent.getPoint().x > boundWidth && pEvent.getPoint().x < boundWidth + w && pEvent.getPoint().y > boundHeight && pEvent.getPoint().y < boundHeight + h)
                {
                    //handle double click.
                    if (_cGameView == null)
                    {
                        _cGameView = new CharacterGame(_game.getPlayer());

                    }
                    _cGameView.refreshAllLabels();
                    _cGameView.setVisible(true);//refresh inventory screen
                } else
                {
                    Character ch = getCharacter(pEvent.getPoint().x, pEvent.getPoint().y);
                    if (ch != null)
                    {
                        CharacterGame cgv = new CharacterGame(ch);
                        cgv.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * This is a public function called mouse pressed.
     * when the turn is player turn and if all the condition are satisfied,
     * the player can choose move, attack, pick and end buttons.  
     * @param pEvent 
     */
    public void mousePressed(MouseEvent pEvent)
    {
        if (pEvent.getButton() == MouseEvent.BUTTON3)
        {
            if (_game.playerTurn())
            {
                final JPopupMenu menu = new JPopupMenu();

                if (_game.ifCanMove())
                {
                    JMenuItem moveMenu = new JMenuItem("Move");
                    menu.add(moveMenu);
                }

                if (_game.canAttack() && _game.getAttackList().size() > 0)
                {
                    JMenu attackMenu = new JMenu("Attack");
                    final ArrayList<Integer> list = _game.getAttackList();
                    for (int i = 0; i < list.size(); i++)
                    {
                        final int index = i;
                        JMenuItem itemAttack = new JMenuItem(_game.GetName(list.get(index)));
                        attackMenu.add(itemAttack);
                        itemAttack.addMouseListener(new MouseAdapter()
                        {

                            public void mousePressed(MouseEvent evt)
                            {
                                if (_game.playerTurn())
                                    _game.attackEnemy(list.get(index));
                            }
                        });
                    }
                    menu.add(attackMenu);
                }

                if (_game.getTreasure() != null)
                {
                    JMenu itemPick = new JMenu("Pick");
                    menu.add(itemPick);
                    for (int i = 0; i < _game.getTreasure().getItemList().size(); i++)
                    {
                        final int index = i;
                        JMenuItem itemTreasure = new JMenuItem(_game.getTreasure().getItemList().get(index).getName());
                        itemPick.add(itemTreasure);
                        itemTreasure.addMouseListener(new MouseAdapter()
                        {
                            public void mousePressed(MouseEvent evt)
                            {
                                if (_game.playerTurn())
                                {
                                    _game.pickTreasure(index);
                                }
                            }
                        });

                    }


                }



                JMenuItem itemEnd = new JMenuItem("End");
                menu.add(itemEnd);
                itemEnd.addMouseListener(new MouseAdapter()
                {

                    public void mousePressed(MouseEvent evt)
                    {
                        if (_game.playerTurn())
                        {
                            _game.endHumanPlayerTurn();
                            _timer.schedule(new RemindTask(), 800);
                        }
                    }
                });

                menu.show(pEvent.getComponent(), pEvent.getX(), pEvent.getY());
            }
        }
    }

    /**
     * This is a public function called mouse released.
     * To released the mouse.
     * @param e 
     */
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * This is a public function called mouse entered.
     * To entered the mouse.
     * @param e 
     */
    public void mouseEntered(MouseEvent e)
    {
    }

    /**
     * This is a public function called mouse exited.
     * To exited the mouse.
     * @param e 
     */
    public void mouseExited(MouseEvent e)
    {
    }
}
