/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Items.LongBow;
import Model.Items.LongSword;
import Model.Items.Armor;
import Model.Items.Shield;
import Model.Items.Helmet;
import Model.Items.Item;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test Class to test the functions of the character according to d20 rules.
 * @author eric
 * Class added to and modified by khushnuma for build 2 and build 3. Added methods for all changed rules.
 * @author khushnuma
 * @vresion 1.2.0
 */
public class CharacterTest
{
    
    public CharacterTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getName method, of class Character.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        Character instance = new Character();
        String expResult = "eric";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvatar method, of class Character.
     */
    @Test
    public void testGetAvatar()
    {
        System.out.println("getAvatar");
        Character instance = new Character();
        String expResult = "avata";
        instance.setAvatar(expResult);
        String result = instance.getAvatar();
        assertEquals(expResult, result);
    }


    /**
     * Test of addBackpackItems method, of class Character.
     */
    @Test
    public void testAddBackpackItems()
    {
        System.out.println("addBackpackItems");
        Item pItem = new Helmet();
        Character instance = new Character();
        instance.addBackpackItems(pItem, true);
        int expect = 1;
        int actual = instance.getBackPack().size();
        assertEquals(expect, actual);
    }

    /**
     * Test of getBodyPartsName method, of class Character.
     */
//    @Test
//    public void testGetBodyPartsName()
//    {
//        System.out.println("getBodyPartsName");
//        Character instance = new Character();
//        instance.createBodyParts();
//        int expect = 7;
//        int actual = instance.getBodyPartsName().getSize();
//        assertEquals(actual, expect);
//    }

    /**
     * Test of canOverlap method, of class Character.
     */
    @Test
    public void testCanOverlap()
    {
        System.out.println("canOverlap");
        Character instance = new Character();
        boolean expResult = true;
        boolean result = instance.canOverlap();
        assertEquals(expResult, result);
    }
/**
 * Test calculateModifier method of the character class that calculates each abilities modifier value.
 * @khushnuma
 * @version 1.0.0
 */
    @Test
    public void testCalculateModifier(){
        System.out.println("Calculate Modifier");
        Character instance = new Character();
        int abInteligence, abStrength, abCharisma;
        int modifierIntelligence, modifierStrength, modifierCharisma;
        abInteligence = 0; abStrength = 10; abCharisma = 18;

        modifierStrength =  instance.calculateModifier(abStrength);
        modifierIntelligence =  instance.calculateModifier(abInteligence);
        modifierCharisma =  instance.calculateModifier(abCharisma);
        
        assertEquals(0, modifierStrength);
        assertEquals(-5, modifierIntelligence);
        assertEquals(4, modifierCharisma);
    }
    /**
     * method to test CalculateAttackBonus() function.
     * @author khushnuma
     * @version 1.0.0
     */
    @Test
    public void testCalculateAttackBonus(){
        System.out.println("Calculate AttackBonus - Range and Melee");
        Character instance = new Character();
        int abDexterity, abStrength;
        int modifierDexterity, modifierStrength;
        int rangeBonus, meleeBonus, baseBonus;

        abDexterity = 13; abStrength = 16;
        instance.setDexterity(abDexterity);
        instance.setStrength(abStrength);
        modifierStrength =  instance.calculateModifier(abStrength);
        modifierDexterity =  instance.calculateModifier(abDexterity);

        int level = instance.getLevel();
        System.out.println("Character level : "+ level + "\n");
        //instance.calculateBaseAttackBonus();
        baseBonus = instance.getBaseAttackBonus();
        rangeBonus = instance.getRangeAttackBonus();
        meleeBonus = instance.getMeleeAttackBonus();

        assertEquals(1, baseBonus);
        assertEquals(2, rangeBonus);
        assertEquals(4, meleeBonus);
    }
/**
 * Method to test calculate attack per round method.
 * @version 1.0.0
 * @author khushnuma
 */
    @Test
    public void testCalculateAttackPerRound(){
        System.out.println("Calculate Attack Per Round - Level Dependant");
        Character instance = new Character();
        assertEquals(1, instance.getAttackPerRound());
        instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();
        assertEquals(2, instance.getAttackPerRound());
        instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();
        assertEquals(3, instance.getAttackPerRound());
        instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();
        assertEquals(4, instance.getAttackPerRound());
    }
/**
 * Method to test get base attack bonus values of the class for all levels.
 * @author khushnuma
 * @version 1.0.0
 */
    @Test
    public void testBaseAttackBonus(){
        System.out.println("Calculate Base Attack Bonus - Level Dependant");
        Character instance = new Character();
        assertEquals(1, instance.getBaseAttackBonus());
        
        instance.addLevel(); assertEquals(2, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(3, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(4, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(5, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(6, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(7, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(8, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(9, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(10, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(11, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(12, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(13, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(14, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(15, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(16, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(17, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(18, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(19, instance.getBaseAttackBonus());
        instance.addLevel(); assertEquals(20, instance.getBaseAttackBonus());

        
    }//end method test base attack bonus
/**
 * method to test the calculate armor class method to see if it being correctly computed.
 * @author khushnuma
 * @version 1.0.0
 */
    @Test
    public void testArmorClass(){
        System.out.println("Calculate Armor Class- Modifier Dependant");
        Character instance = new Character();
        instance.createBodyParts();     

        instance.setDexterity(10);//gives us dexterity modifier of 0
        assertEquals(10, instance.getArmorClass());
        Shield shield_buckler = new Shield(ShieldType.BUCKLER);
        instance._bodyArray.get(2).wearItem(shield_buckler);
        instance.setShieldModifier(shield_buckler.getShieldBonus());
        assertEquals(11, instance.getArmorClass());

        Armor armor_fullPlate = new Armor(ArmorType.FULLPLATE);
        instance._bodyArray.get(1).wearItem(armor_fullPlate);
        instance.setArmorModifier(armor_fullPlate.getArmorBonus());
        assertEquals(19, instance.getArmorClass());
        //instance._bodyArray.get(2).takeOffItem(shield_buckler);
    }
    /**
     * method to test the set shield modifier method
     * @version 1.0.0
     * @author khushnuma
     */
    @Test
    public void testShieldModifier(){
        System.out.println("Shield Modifiers - Modifier Dependant on shied type worn");
        Character instance = new Character();
        instance.createBodyParts();
        assertEquals("No shield",0, instance.getShieldModifier());

        Shield shield_buckler = new Shield(ShieldType.BUCKLER);
        Shield shield_heavyShield = new Shield(ShieldType.HEAVYSHIELD);
        Shield shield_towerShield = new Shield(ShieldType.TOWERSHIELD);

        instance._bodyArray.get(2).wearItem(shield_buckler);
        instance.setShieldModifier(shield_buckler.getShieldBonus());
        assertEquals(1, instance.getShieldModifier());
        instance._bodyArray.get(2).takeOffItem(shield_buckler);
        instance.setShieldModifier(0);

        instance._bodyArray.get(2).wearItem(shield_heavyShield);
        instance.setShieldModifier(shield_heavyShield.getShieldBonus());
        assertEquals("heavy Shield",2, instance.getShieldModifier());
        instance._bodyArray.get(2).takeOffItem(shield_heavyShield);
        instance.setShieldModifier(0);

        instance._bodyArray.get(2).wearItem(shield_towerShield);
        instance.setShieldModifier(shield_towerShield.getShieldBonus());
        assertEquals("Tower shield",4, instance.getShieldModifier());
        instance._bodyArray.get(2).takeOffItem(shield_towerShield);
        instance.setShieldModifier(0);   

    }
    /**
     * method to test the armor modifier method.
     * @author khushnuma
     * @version 1.0.0
     */
    @Test
    public void testArmorModifier(){
        System.out.println("Armor Modifiers - Modifier Dependant on armor type worn");
        Character instance = new Character();
        instance.createBodyParts();
        assertEquals("No armor",0, instance.getShieldModifier());

        Armor armor_leather = new Armor(ArmorType.LEATHER);
        Armor armor_halfPlate = new Armor(ArmorType.HALFPLATE);
        Armor armor_chainShirt = new Armor(ArmorType.CHAINSHIRT);

        instance._bodyArray.get(1).wearItem(armor_leather);
        instance.setArmorModifier(armor_leather.getArmorBonus());
        assertEquals(2, instance.getArmorModifier());
        instance._bodyArray.get(1).takeOffItem(armor_leather);
        instance.setArmorModifier(0);

        instance._bodyArray.get(1).wearItem(armor_halfPlate);
        instance.setArmorModifier(armor_halfPlate.getArmorBonus());
        assertEquals(7, instance.getArmorModifier());
        instance._bodyArray.get(1).takeOffItem(armor_halfPlate);
        instance.setArmorModifier(0);

        instance._bodyArray.get(1).wearItem(armor_chainShirt);
        instance.setArmorModifier(armor_chainShirt.getArmorBonus());
        assertEquals(4, instance.getArmorModifier());
        instance._bodyArray.get(1).takeOffItem(armor_chainShirt);
        instance.setArmorModifier(0);

    }
    /**
     * method to test the add level method in class
     * @version
     * @author khushnuma
     */
    @Test
    public void testAddLevel(){
        System.out.println("Add Level  - changes values");
        Character instance = new Character();
        int abDexterity, abStrength, abConstitution;
        int hitPoint1, hitPoint2;
        //int modifierDexterity, modifierStrength, modifierConstitution;
        abDexterity = 16; abStrength = 18;abConstitution = 16;//modifier 3, 4
        instance.setDexterity(abDexterity);
        instance.setStrength(abStrength);
        instance.setConstitution(abConstitution);
        hitPoint1 = instance.getHitPoints();
        assertEquals(1, instance.getLevel());

        instance.addLevel();
        assertEquals(2, instance.getLevel());
        assertEquals(2, instance.getBaseAttackBonus());
        assertEquals(5, instance.getRangeAttackBonus());
        assertEquals(6, instance.getMeleeAttackBonus());
        assertEquals(1, instance.getAttackPerRound());
        //assertTrue(instance.getHitPoints() > hitPoint1);
        hitPoint2 = instance.getHitPoints();
        instance.addLevel();instance.addLevel();instance.addLevel();instance.addLevel();

        assertEquals(6, instance.getLevel());
        assertEquals(6, instance.getBaseAttackBonus());
        assertEquals(9, instance.getRangeAttackBonus());
        assertEquals(10, instance.getMeleeAttackBonus());
        assertEquals(2, instance.getAttackPerRound());
        //assertTrue(instance.getHitPoints() > hitPoint2);
    }

    @Test
    public void testMinusLevel(){
        System.out.println("Minus Level  - changes values depending on level ");
        Character instance = new Character();
        int abDexterity, abStrength, abConstitution;
        abDexterity = 16; abStrength = 18;abConstitution = 16;//modifier 3, 4
        instance.setDexterity(abDexterity);
        instance.setStrength(abStrength);
        instance.setConstitution(abConstitution);
        assertEquals(1, instance.getLevel());

        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        assertEquals(6, instance.getLevel());
        int hitPoint6, hitPoint5;
        hitPoint6 = instance.getHitPoints(); //assertEquals();
        instance.minusLevel();
        assertEquals(5, instance.getLevel());
        hitPoint5 = instance.getHitPoints();
        //assertTrue(hitPoint5 <hitPoint6 );
        assertEquals(5, instance.getBaseAttackBonus());
        assertEquals(8, instance.getRangeAttackBonus());
        assertEquals(9, instance.getMeleeAttackBonus());
        assertEquals(1, instance.getAttackPerRound());
    }
    /**
     * method to test the reduced base attack bonus that occurs after every attack.
     * @author khushnuma
     * @version 1.0.0
     */
    @Test
    public void testReduceAttackPerRound(){
        System.out.println("Reduce Base Attack Bonus  - changes values depending on level and attacks left");
        Character instance = new Character();
        int abDexterity, abStrength, abConstitution;
        abDexterity = 16; abStrength = 18;abConstitution = 16;//modifier 3, 4
        instance.setDexterity(abDexterity);
        instance.setStrength(abStrength);
        instance.setConstitution(abConstitution);
        assertEquals(1, instance.getLevel());
        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        instance.addLevel();
        assertEquals(6, instance.getLevel());
        assertEquals(6, instance.getBaseAttackBonus());
        assertEquals(2, instance.getAttackLeftPerRound());
        assertEquals(2, instance.getAttackPerRound());

        instance.minusAttackPerRound();

        assertEquals(6, instance.getLevel());
        assertEquals(1, instance.getBaseAttackBonus());
        assertEquals(1, instance.getAttackLeftPerRound());
        assertEquals(2, instance.getAttackPerRound());

        instance.minusAttackPerRound();

        assertEquals(6, instance.getLevel());
        assertEquals(0, instance.getBaseAttackBonus());
        assertEquals(0, instance.getAttackLeftPerRound());
        assertEquals(2, instance.getAttackPerRound()); 
    }
    /**
     * Method to test the get damage bonus function of the character class.
     * It should be 0 in case of holding bow and
     * value of strength modifier in case of holding sword.
     */
    @Test
    public void testGetDamageBonus(){
        System.out.println("Calculate damage bonus - Dependant on sword or shield");
        Character instance = new Character();
        instance.createBodyParts();        
        instance.setStrength(14);//gives modifier value 2
        instance.setDexterity(10);//gives us dexterity modifier of 0
        assertEquals(10, instance.getArmorClass());
        Shield shield_buckler = new Shield(ShieldType.BUCKLER);
        instance._bodyArray.get(2).wearItem(shield_buckler);
        instance.setShieldModifier(shield_buckler.getShieldBonus());
        assertEquals(11, instance.getArmorClass());

        Armor armor_fullPlate = new Armor(ArmorType.FULLPLATE);
        instance._bodyArray.get(1).wearItem(armor_fullPlate);
        instance.setArmorModifier(armor_fullPlate.getArmorBonus());
        assertEquals(19, instance.getArmorClass());
        //instance._bodyArray.get(2).takeOffItem(shield_buckler);

        LongSword long_sword = new LongSword();
        instance._bodyArray.get(3).wearItem(long_sword);
        instance.setIsMelee(true);
        int actual_value = instance.getDamageBonus();
        assertEquals(instance.calculateModifier(instance.getStrength()), actual_value);
        instance._bodyArray.get(3).takeOffItem(long_sword);
        instance.setIsMelee(false);

        LongBow long_bow = new LongBow();
        instance._bodyArray.get(3).wearItem(long_bow);
        instance.setIsRange(true);
        int actualValue = instance.getDamageBonus();
        assertEquals(0, actualValue);
        instance._bodyArray.get(3).takeOffItem(long_bow);
        instance.setIsRange(false);
    }
/**
 * method to test wear item function of the player class.
 * This method modifies the player values and flags as per bonus and magic values of the worn item.
 * @author khush build 3
 */
@Test
public void testWearItem(){
        System.out.println("Check wear item function ");
        Character instance = new Character();
        instance.createBodyParts();        
        instance.setStrength(14);//gives modifier value 2
        instance.setDexterity(10);//gives us dexterity modifier of 0
        assertEquals(10, instance.getArmorClass());

        Shield shield_buckler = new Shield(ShieldType.BUCKLER);
        shield_buckler.setMagicValue(1);
        shield_buckler.setMagicSetter(1);
        Item pItem = (Item)shield_buckler;
        assertEquals(1, shield_buckler.getShieldBonus());//check shield modifier set
        assertEquals(1, shield_buckler.getMagicValue());//check shield magic value

        instance.wearItem(shield_buckler);//wear a magic shield
        assertEquals(1, instance.getShieldModifier());
        assertEquals(12, instance.getArmorClass());//armor class modified properly
        assertTrue(true == instance.isIsShield());//flag set
}//end method wear item test

/**
 * method to test remove item function of the player class.
 * This method modifies the player values and flags as per bonus and magic values of the worn item.
 * @author khush build 3
 */
@Test
public void testRemoveItem(){
        System.out.println("Check wear item function ");
        Character instance = new Character();
        instance.createBodyParts();
        instance.setStrength(14);//gives modifier value 2
        instance.setDexterity(10);//gives us dexterity modifier of 0
        assertEquals(10, instance.getArmorClass());

        Shield shield_buckler = new Shield(ShieldType.BUCKLER);
        shield_buckler.setMagicValue(1);
        shield_buckler.setMagicSetter(1);
        Item pItem = (Item)shield_buckler;
        assertEquals(1, shield_buckler.getShieldBonus());//check shield modifier set
        assertEquals(1, shield_buckler.getMagicValue());//check shield magic value

        instance.wearItem(shield_buckler);//wear a magic shield
        assertEquals(1, instance.getShieldModifier());
        assertEquals(12, instance.getArmorClass());//armor class modified properly
        assertTrue(true == instance.isIsShield());//flag set

        instance.removeItem(shield_buckler);//remove item
        assertEquals(0, instance.getShieldModifier());
        assertEquals(10, instance.getArmorClass());//armor class modified properly
        assertTrue(false == instance.isIsShield());//flag unset
}//end method remove item test
    
}
