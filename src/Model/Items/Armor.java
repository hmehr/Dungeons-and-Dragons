
package Model.Items;

import Model.ArmorType;
import Model.BodyParts.BodyPart;
import Model.OutputConsole;
/**
 * Armor is a sub type of class Defense. One of the items a character can be equipped with during the game.
 * @author Hamid
 */
public class Armor extends Defense
{

    private ArmorType pArmorType;
    private int _armorBonus;
    /**
     * Constructor method for Armor.
     * @param pName Name of the Armor
     * @param pFileName Name of the image file name used for it
     * @author Hamid
     */
    public Armor(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("An Armor was just created.");
    }

    /**
     * Constructor method for Armor.
     * 
     * @param pArmorType 
     * @author Fahim
     * @author khushnuma
     */
    public Armor(ArmorType pArmorType)
    {
        if(pArmorType.equals(pArmorType.BANDEDMAIL)){
            _name = "BandedMail";
            _armorBonus = 6;
        }
        if(pArmorType.equals(pArmorType.BREASTPLATE)){
            _name = "BreastPlate";
            _armorBonus =5 ;
        }
        if(pArmorType.equals(pArmorType.CHAINSHIRT)){
            _name = "ChainShirt";
            _armorBonus = 4;
        }
        if(pArmorType.equals(pArmorType.FULLPLATE)){
            _name = "FullPlate";
            _armorBonus = 8;
        }
        if(pArmorType.equals(pArmorType.HALFPLATE)){
            _name = "HalfPlate";
            _armorBonus = 7;
        }
        if(pArmorType.equals(pArmorType.PADDED)){
            _name = "Padded";
            _armorBonus = 1;
        }
        if(pArmorType.equals(pArmorType.STUDDEDLEATHER)){
            _name = "StuddedLeather";
            _armorBonus =3 ;
        }
        if(pArmorType.equals(pArmorType.LEATHER)){
            _name = "Leather";
            _armorBonus = 2;
        }
/*
        if(pArmorType.equals(pArmorType.HEAVY))
            _name = "ArmorHeavy";
        if(pArmorType.equals(pArmorType.MEDIUM))
            _name = "ArmorMedium";
        if(pArmorType.equals(pArmorType.LIGHT))
            _name = "ArmorLight";
    }
*/
    }//end method constructor
/**
 * Getter method for armor bonus. This is the value added to fighter
 * armor class when he is wearing a particular type of armor.
 * @return _armorBonus is the armor modifier.
 * @author khushnuma
 */
    public int getArmorBonus() {
        return _armorBonus;
    }

    /**
     * Get Method for pArmorType
     * 
     * @return pArmorType
     * @author Fahim
     */
    public ArmorType getArmorType()
    {
        return pArmorType;
    }
    
    /**
     * Set method for pArmorType
     * 
     * @param pArmorType To be set.
     * @author Fahim
     */
    public void setArmorType(ArmorType pArmorType)
    {
        this.pArmorType = pArmorType;
    }
    

     /**
     * set random magic setter with the corresponding item
     * @author Fahim
     */
    public void setRandomMagic()
    {
        _magicSetter = 1;
    }

    /**
     * Validates the body part with the corresponding item
     * 
     * @param pBodyPart The body part to be validated.
     * @return True if the body part is validated.
     * @author Fahim
     */
    @Override
    public boolean validateBodyPart(BodyPart pBodyPart)
    {
  
        return true;
    }
}

