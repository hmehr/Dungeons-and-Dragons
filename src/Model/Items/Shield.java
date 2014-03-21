
package Model.Items;

import Model.BodyParts.BodyPart;
import Model.OutputConsole;
import Model.ShieldType;
/**
 * Shield is a sub type of class Defense. One of the items a character can be equipped with during the game.
 * @author Hamid
 * @author Khushnuma
 * @version 1.2.0
 * For build 2 class modified to add three types of shields available and
 * variable added to show shield bonus for wearing each type of shield.
 */
public class Shield extends Defense
{
    private int _shieldBonus;

    /**
     * Constructor method for Shield.
     * @param pName Name of the Shield
     * @param pFileName Name of the image file name used for it
     */
    public Shield(String pName, String pFileName)
    {
        _fileName = pFileName;
        _name = pName;
        OutputConsole.GetOutputConsoleInstance().Write("A Shield was just created.");
    }

/**
 * Constructor for Shield that takes input as shield type.
 * @param pShieldType type of shield to be created.
 * @author khushnuma
 */
    public Shield(ShieldType pShieldType){
        
        if(pShieldType.equals(pShieldType.BUCKLER)){
            _name = "Buckler";
            _shieldBonus = 1;
        }
        if(pShieldType.equals(pShieldType.HEAVYSHIELD)){
            _name = "HeavyShield";
            _shieldBonus = 2;
        }
        if(pShieldType.equals(pShieldType.TOWERSHIELD)){
            _name = "TowerShield";
            _shieldBonus = 4;
        }
        OutputConsole.GetOutputConsoleInstance().Write("A Shield was just created.");
        
    }//end constructor

    /**
     * Default constructor
     */
    public Shield()
    {
        _name = "Shield";
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
     * Getter method to return the value of the shield bonus.
     * @return _shieldBonus is the shield bonus added to armor class for wearing shield.
     */
    public int getShieldBonus() {
        return _shieldBonus;
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

