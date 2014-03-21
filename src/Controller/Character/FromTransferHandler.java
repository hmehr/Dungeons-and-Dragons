/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Character;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 * This class is written to handle the drag operation from a list box.
 * @author m_niza
 */
public class FromTransferHandler extends TransferHandler
{

    private JList dragFrom;
    private DefaultListModel from;

    /**
     * The default constructor take the reference for a JList control and the default
     * list model for that component.
     * @param dragFrom passes the reference for a JList component.
     * @param from passes the reference for a DefaultListModel.
     */
    public FromTransferHandler(JList dragFrom, DefaultListModel from)
    {
        this.dragFrom = dragFrom;
        this.from = from;
    }

    /**
     * To get the source actions and return a integer type value.
     * @param comp takes the reference of a java component.
     * @return integer type properties for move or copy for a drag.
     */
    public int getSourceActions(JComponent comp)
    {
        return COPY_OR_MOVE;
    }
    private int index = 0;

    /**
     * Makes the component transferable.
     * @param comp takes the reference of a java component.
     * @return int type properties for move or copy for a drag.
     */
    public Transferable createTransferable(JComponent comp)
    {
        index = dragFrom.getSelectedIndex();
        if (index < 0 || index >= from.getSize())
        {
            return null;
        }

        return new StringSelection((String) dragFrom.getSelectedValue());
    }

    /**
     * It takes action like remove objects after transfer.
     * @param comp takes the reference of a java component.
     * @param trans Transferable object.
     * @param action It takes value for copy or move.
     */
    public void exportDone(JComponent comp, Transferable trans, int action)
    {
        if (action != MOVE)
        {
            return;
        }

        from.removeElementAt(index);
    }
}
