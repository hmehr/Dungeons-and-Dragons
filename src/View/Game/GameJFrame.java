package View.Game;

import Model.Game.Game;
import Model.Map.Map;
import Model.Character;
import java.awt.BorderLayout;
import java.io.*;

/**
 * GameJFrame class.
 * This class is the main frame view of game.
 * @author Xuexiang Hu
 */
public class GameJFrame extends javax.swing.JFrame
{

    Character ch;
    String loadPath;
    private Game _game = null;

    /** 
     * Creates new form GameJFrame
     * @param character is the character in game
     * @param pMap  is the map of game
     */
    public GameJFrame(Model.Character character, Map pMap)
    {

        initComponents();
        //this.setSize(600, 300);       
        if (character != null)
        {
            ch = character;
            ch.setImage("../resources/" + ch.getAvatar() + ".jpg");
        } else
        {
            ch = new Character();
            ch.setImage("../resources/bird.jpg");
        }

        String fileName = System.getProperty("user.dir");
        fileName += "/src/maps/map1.dd";
        Map map = null;

        if (pMap == null) //load the map class from disk
        {

            try
            {
                FileInputStream fis = null;
                ObjectInputStream in = null;
                fis = new FileInputStream(fileName);
                in = new ObjectInputStream(fis);
                map = (Map) in.readObject();
            } catch (IOException ex)
            {
            } catch (ClassNotFoundException ex)
            {
            }
        }
        else
        {
            map = pMap;
        }

        _game= new Game(ch, map);
        //Game newGame 
        GameView gameView = new GameView(_game , this);
        _game.addObserver(gameView);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(gameView, BorderLayout.CENTER);
        this.pack();
       
    }
/**
     * To get game.
     * @return _game
     * @author yukywang
     */
    public Game getGame()
    {
        return _game;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Dungeon and Dragons");
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
