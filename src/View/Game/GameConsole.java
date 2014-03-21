/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Game;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * This is a public class called game console which inherits the JFrame.
 * @author eric
 */
public class GameConsole extends JFrame
{

    private JTable _table;
    DefaultTableModel _model = null;
    private static GameConsole _gameConsole = null;
    JScrollPane _scrollPane = null;

    /**
     * This is a protected constructor called game console.
     * 
     */
    protected GameConsole()
    {

        _model = new DefaultTableModel();
        _model.addColumn("Round");
        _model.addColumn("Actor");
        _model.addColumn("Message");
        _table = new JTable(_model);
        setSize(800, 300);
        // _table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        _scrollPane = new JScrollPane(_table);

        _table.setSize(1250,300);
        _table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int vColIndex = 0;
        TableColumn col = _table.getColumnModel().getColumn(vColIndex);
        int width = 50;
        col.setPreferredWidth(width);

        vColIndex = 1;
        col = _table.getColumnModel().getColumn(vColIndex);
        width = 100;
        col.setPreferredWidth(width);

        vColIndex = 2;
        col = _table.getColumnModel().getColumn(vColIndex);
        width = 1500;
        col.setPreferredWidth(width);
       

        this.setTitle("Game Console");
        _scrollPane.setAutoscrolls(true);
       // _scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Add the scroll pane to this panel.
        add(_scrollPane);

    }

    /**
     * This is a public function that get the game console.
     * If the game console is null, then create a new one.
     * @return game console.
     */
    public static GameConsole getGameConsole()
    {
        if (_gameConsole == null)
        {
            _gameConsole = new GameConsole();
        }
        return _gameConsole;
    }

    /**
     * This is a public function called append message.
     * To append message.
     * @param pRound round of the game.
     * @param pActor actor of the game.
     * @param pMsg message to append.
     */
    public void appendMessage(int pRound, String pActor, String pMsg)
    {

        _model.addRow(new Object[]
                {
                    pRound, pActor, pMsg
                });
        try
        {
            _table.scrollRectToVisible(_table.getCellRect(_table.getRowCount() - 1, 0, false));
        } catch (Exception e)
        {
        }
        //_model.insertRow(0,new Object[] {pRound, pActor, pMsg});
        //_model.fireTableRowsInserted(ERROR, ERROR);
        //JScrollBar vertical = _scrollPane.getVerticalScrollBar();
        //vertical.setValue( vertical.getMaximum() );
    }

    /**
     * This is a public function called clear.
     * To remove the row.
     */
    public void clear()
    {

        while (_model.getRowCount() > 0)
        {
            _model.removeRow(0);
        }
    }
}
