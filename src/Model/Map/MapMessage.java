package Model.Map;

import Model.Map.MapCell;


/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eric
 */
/**
 * This is the map message class.
 * To add and delete the map message.
 * To update the map message.Change the old x and y to the new x and y.
 * 
 * @author yukywang
 */
public class MapMessage
{
    /**
     * Enumeration for add, delete or update actions.
     */
    public enum MapMessageType
    {
        /**
         * This is a add constant.
         */
        ADD,
        
        /**
         * This is a delete constant.
         */
        DELETE,
        
        /**
         * This is a update constant.
         */
        UPDATE
    }
    
    /**
     * Inner class MapMoveMessage.
     */
    public class MapMoveMessage
    {
        public int oldX;
        public int oldY;
        public int newX;
        public int newY;
    }
    
    /**
     * MapDelete inner class.
     */
    public class MapDeleteMessage
    {
        public int deleteX;
        public int deleteY;
    }
    /**
     * This is the message type of the map message type.
     */
    public MapMessageType messageType ;
    /**
     * This is a message body of the object.
     */
    public Object messageBody;
            
    /**
     * To do the add action.
     * @param pMapModel the command line arguments
     * @author yukywang
     */
    /**
     * The constructor for the class.
     * @param pMapModel the message body field to be set.
     */
    public MapMessage(MapCell pMapModel)
    {
        messageType = MapMessageType.ADD;
        messageBody = (Object)pMapModel;
    }
    /**
     * To do the delete action.
     * @param pX the arguments 
     * @param pY the arguments
     * @author yukywang
     */
    public MapMessage(int pX, int pY)
    {
        messageType = MapMessageType.DELETE;
        MapDeleteMessage deleteMessage = new MapDeleteMessage();
        deleteMessage.deleteX = pX;
        deleteMessage.deleteY = pY;
        messageBody = (Object)deleteMessage;
    }
    /**
     * To do the update.
     * Change the old map message to the new map message.
     * @param pOldX the old x
     * @param pOldY the old y
     * @param pNewX the new x
     * @param pNewY the new y
     * 
     * @author yukywang
     */
    public MapMessage(int pOldX, int pOldY, int pNewX, int pNewY)
    {
        messageType = MapMessageType.UPDATE;
        MapMoveMessage updateMessage = new MapMoveMessage();
        updateMessage.oldX = pOldX;
        updateMessage.oldY = pOldY;
        updateMessage.newX = pNewX;
        updateMessage.newY = pNewY;
        messageBody = (Object)updateMessage;
    }
    
    
}
