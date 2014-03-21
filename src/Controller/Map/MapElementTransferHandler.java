/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Map;

import View.Map.MapItemView;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.DragSourceDragEvent;
/**
 * This is a class of MapElementTransferHandler inherit TransferHandler.
 * To create the transferable object.
 * And to see whether the components can be copied.
 * 
 * @author yukywang
 */
public class MapElementTransferHandler extends TransferHandler
{
    /**
     * Default constructor.
     */
    public MapElementTransferHandler()
    {
        super();
    }

    /**
     * <p>This creates the Transferable object. In our case, RandomDragAndDropPanel implements Transferable, so this requires only a type cast.</p>
     * @param c It takes a Java Component.
     * @return transferable object.
     */
    @Override()
    public Transferable createTransferable(JComponent c)
    {
        // TaskInstancePanel implements Transferable
        if (c instanceof MapItemView)
        {
            Transferable tip = (MapItemView) c;
            return tip;
        }
        // Not found
        return null;
    }

    /**
     * <p>This is queried to see whether the component can be copied, moved, both or neither. We are only concerned with copying.</p>
     * @param c It takes a Java Component.
     * @return <code>TransferHandler.MOVE</code>
     */
    @Override()
    public int getSourceActions(JComponent c)
    {
        return TransferHandler.MOVE;
    }
}
