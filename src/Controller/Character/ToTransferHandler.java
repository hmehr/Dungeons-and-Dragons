/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Character;

import Model.ArmorType;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.TransferHandler;
import Model.Character;
import Model.Items.*;
import Model.ShieldType;

/**
 * This class is written to handle the drop operation to a list box after dragging.
 * @author m_niza
 */
public class ToTransferHandler extends TransferHandler
{
    int action;
    boolean available;
    Character _character;
    ArmorType _armorType;
    ShieldType _shieldType;

    /**
     * Default constructor which takes an action type for copy or move operation.
     * @param action Defines action type, whether copy or move.
     */
    public ToTransferHandler(int action)
    {
        this.action = action;
        available = false;
    }

    /**
     * This method checks the import possibilities.
     * @param support
     * @return It returns a boolean.
     */
    @Override
    public boolean canImport(TransferHandler.TransferSupport support)
    {
        // for the demo, we'll only support drops (not clipboard paste)
        if (!support.isDrop())
        {
            return false;
        }

        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor))
        {
            return false;
        }

        boolean actionSupported = (action & support.getSourceDropActions()) == action;
        if (actionSupported)
        {
            support.setDropAction(action);
            return true;
        }

        return false;
    }

    /**
     * Performs the import operation after dragging from a Java Component.
     * @param support Support parameter transfer handler.
     * @return It returns boolean.
     */
    @Override
    public boolean importData(TransferHandler.TransferSupport support)
    {
        // if we can't handle the import, say so
        if (!canImport(support))
        {
            return false;
        }

        // fetch the drop location
        //JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();

        //int index = dl.getIndex();

        // fetch the data and bail if this fails
        String data;
        try
        {
            data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e)
        {
            return false;
        } catch (java.io.IOException e)
        {
            return false;
        }

        JList list = (JList) support.getComponent();
        DefaultListModel model = (DefaultListModel) list.getModel();
        
        //checks the item already exists in the destination or not
        for(int i=0; i<model.getSize(); i++)
        {
            if(model.getElementAt(i).toString().equalsIgnoreCase(data))
            {
                available = true;
                break;
            }                
            else
            {
                available = false;
            }                
        }        

        // If the item is not available then add it to the destination list
        if(available == false)
        {
            //model.insertElementAt(data, index);
            model.insertElementAt(data, model.getSize());
            createInventoryItem(data);
        }


//        Rectangle rect = list.getCellBounds(index, index);
//        list.scrollRectToVisible(rect);
//        list.setSelectedIndex(index);
//        list.requestFocusInWindow();

        return true;
    }

    public void setCharacter(Character pCharacter)
    {
        this._character = pCharacter;
    }

    private void createInventoryItem(String pItem)
    {
        if(pItem.equalsIgnoreCase("BandedMail"))
        {
            Armor armor_padded = new Armor(_armorType.BANDEDMAIL);
            _character.addBackpackItems(armor_padded, false);
        }
        else if(pItem.equalsIgnoreCase("BreastPlate"))
        {
            Armor armor_breastPlate = new Armor(_armorType.BREASTPLATE);
            _character.addBackpackItems(armor_breastPlate, false);
        }
        else if(pItem.equalsIgnoreCase("chainshirt"))
        {
            Armor armor_chainShirt = new Armor(_armorType.CHAINSHIRT);
            _character.addBackpackItems(armor_chainShirt, false);
        }
        else if(pItem.equalsIgnoreCase("fullplate"))
        {
            Armor armor_fullPlate = new Armor(_armorType.FULLPLATE);
            _character.addBackpackItems(armor_fullPlate, false);
        }
        else if(pItem.equalsIgnoreCase("halfplate"))
        {
            Armor armor_halfPlate = new Armor(_armorType.HALFPLATE);
            _character.addBackpackItems(armor_halfPlate, false);
        }
        else if(pItem.equalsIgnoreCase("padded"))
        {
            Armor armor_padded = new Armor(_armorType.PADDED);
            _character.addBackpackItems(armor_padded, false);
        }
        else if(pItem.equalsIgnoreCase("leather"))
        {
            Armor armor_leather = new Armor(_armorType.LEATHER);
            _character.addBackpackItems(armor_leather, false);
        }
        else if(pItem.equalsIgnoreCase("studdedleather"))
        {
            Armor armor_studdedLeather = new Armor(_armorType.STUDDEDLEATHER);
            _character.addBackpackItems(armor_studdedLeather, false);
        }
        else if(pItem.equalsIgnoreCase("buckler"))
        {
            Shield shield_buckler = new Shield(_shieldType.BUCKLER);
            _character.addBackpackItems(shield_buckler, false);
        }
        else if(pItem.equalsIgnoreCase("heavyshield"))
        {
            Shield shield_heavyShield = new Shield(_shieldType.HEAVYSHIELD);
            _character.addBackpackItems(shield_heavyShield, false);
        }
        else if(pItem.equalsIgnoreCase("towershield"))
        {
            Shield shield_towerShield = new Shield(_shieldType.TOWERSHIELD);
            _character.addBackpackItems(shield_towerShield, false);
        }
        else if(pItem.equalsIgnoreCase("HealingPotion"))
        {
            HealingPotion heal_potion = new HealingPotion();
            _character.addBackpackItems(heal_potion, false);
        }
        else if(pItem.equalsIgnoreCase("longsword"))
        {
            LongSword long_sword = new LongSword();
            _character.addBackpackItems(long_sword, false);
        }
        else if(pItem.equalsIgnoreCase("longbow"))
        {
            LongBow long_bow = new LongBow();
            _character.addBackpackItems(long_bow, false);
        }
        else if(pItem.equalsIgnoreCase("belt"))
        {
            Belt belt = new Belt();
            _character.addBackpackItems(belt, false);
        }
        else if(pItem.equalsIgnoreCase("helmet"))
        {
            Helmet helmet = new Helmet();
            _character.addBackpackItems(helmet, false);
        }
        else if(pItem.equalsIgnoreCase("ring"))
        {
            Ring ring = new Ring();
            _character.addBackpackItems(ring, false);
        }
        else if(pItem.equalsIgnoreCase("boot"))
        {
            Boot boot = new Boot();
            _character.addBackpackItems(boot, false);
        }
        else if(pItem.equalsIgnoreCase("gloves"))
        {
            Gloves gloves = new Gloves();
            _character.addBackpackItems(gloves, false);
        }
        else if(pItem.equalsIgnoreCase("bracer"))
        {
            Bracer bracer = new Bracer();
            _character.addBackpackItems(bracer, false);
        }
        
    }    

}


