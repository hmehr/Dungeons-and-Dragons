/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Items;

import Model.ArmorType;
import Model.Game.Dice;
import Model.ShieldType;

/**
 * This is a public class called item factory.
 * @author eric
 */
public class ItemFactory
{

    /**
     * This is a public function to get the item by name.
     * @param pName the name of the item.
     * @return the item.
     */
    public static Item getItemByName(String pName)
    {
        Item item = null;
        if (pName.equals("longbow"))
        {
            item = new LongBow();
        } else if (pName.equals("longsword"))
        {
            item = new LongSword();
        } else if (pName.equals("healingpotion"))
        {
            item = new HealingPotion();
        } else if (pName.equals("helmet"))
        {
            item = new Helmet();
        } else if (pName.equals("boot"))
        {
            item = new Boot();
        }
        else if (pName.equals("bracer"))
        {
            item = new Bracer();
        }
        else if (pName.equals("ring"))
        {
            item = new Ring();
        }
        else if (pName.equals("belt"))
        {
            item = new Belt();
        } else if (pName.equals("shield"))
        {
            Dice d = new Dice(1, 3);
            int value = d.roll();
            if (value == 1)
            {
                item = new Shield(ShieldType.BUCKLER);
            } else if (value == 2)
            {
                item = new Shield(ShieldType.HEAVYSHIELD);
            } else
            {
                item = new Shield(ShieldType.TOWERSHIELD);
            }
        }else if(pName.equals("armor"))
        {
            Dice d = new Dice(1, 8);
            int value = d.roll();
            if (value == 1)
            {
                item = new Armor(ArmorType.BANDEDMAIL);
            } else if (value == 2)
            {
                item = new Armor(ArmorType.BREASTPLATE);
            }else if (value == 3)
            {
                item = new Armor(ArmorType.CHAINSHIRT);
            } else if (value == 4)
            {
                item = new Armor(ArmorType.FULLPLATE);
            } else if (value == 5)
            {
                item = new Armor(ArmorType.HALFPLATE);
            } else if (value == 6)
            {
                item = new Armor(ArmorType.LEATHER);
            } else if (value == 7)
            {
                item = new Armor(ArmorType.PADDED);
            }
            else
            {
                item = new Armor(ArmorType.STUDDEDLEATHER);
            }
        }

        return item;
    }
}
