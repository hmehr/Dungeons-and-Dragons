/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.BodyParts.*;
import Model.Items.*;
import Model.Items.Item;
import Model.Map.MapCell;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataListener;
import Model.Dice;
import Model.OutputConsole;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;


/**
 * Character model class.
 * This class contents all the details of character/player. It remains alive as
 * long as the player is on the game.
 * @author m_niza
 * For build 2: we added variables for armor class, shield modifier, armor modifier, base attack bonus,
 * movement and movement left.
 * Created methods for all ability points, and modifier calculations.
 *Added many methods for build 3
 * @author khushnuma
 * @version 1.3.0
 */
public class Character extends MapCell implements Serializable
{

    int _level;
    int _hitPoints;
    //Ability scores
    int _strength;
    int _dexterity;
    int _constitution;
    int _wisdom;
    int _intelligence;
    int _charisma;
    //
    int _modifier;//not needed but havent removed as i dont want to disrupt anything else
    int _damageBonus;//not needed but havent removed as i dont want to disrupt anything else
    boolean _isRange;
    boolean _isMelee;
    int _attackBonus;//changed by khushnuma
    int _baseAttackBonus;//added by khush
    int _rangeAttackBonus;//added by khush
    int _meleeAttackBonus;//added by khush
    int _armorClass;//added by khush
    int _armorModifier;//added by khush
    int _shieldModifier;//added by khush
    int _attackPerRound;//added by khush
    int _attackLeftPerRound; //added by khush
    //movement variables
    int _movement;//added by khush
    int _movementLeft; //added by khush
    int _maxHitPoints;//added by khush
    int _healingPotion;
    String _name;
    String _avatar;
    String _type;
    ArmorType _armorType;
    ShieldType _shieldType;//added by khush
    CharacterType _characterType;
    DefaultListModel _modelInventory;
    DefaultListModel _modelBackpack;
    //DefaultComboBoxModel _dfc;
    /**
     * It's a list of body parts where all the body part of player is assigned in
     * the index.
     */
    public ArrayList<BodyPart> _bodyArray;
    ArrayList<Item> _backpackItem;
    ArrayList<Item> _inventroyItem;
    /**
     * The value of the roll will be assigned here.
     */
    public int initialRoll = 0;
    
    /**
     * This is a protected is mad value. It is a boolean type value.
     */
    protected boolean _isMad = false;
    
    /**
     * booleans for wearing helmet belt boot, gloves, bracer and ring
     * @author khush
     */
    boolean _isHelmet;
    boolean _isRing;
    boolean _isBoot;
    boolean _isBelt;
    boolean _isBracer;
    boolean _isGloves;
    boolean _isArmor;
    boolean _isShield;
    // magic modifiers for build 3 by khush
    int _helmetMagic, _armorMagic,_shieldMagic, _bracerMagic, _ringMagic, _bootsMagic, _bowMagic, _swordMagic, _beltMagic;
    int _ringStrength, _bracerStrength, _bootDexterity, _ringWisdom, _helmetWisdom, _ringCharisma, _helmetIntelligence;
    int _strengthModifier, _dexterityModifier, _wisdomModifier, _charismaModifier, _intelligenceModifier;
    /**
     * The constructor initializes all the properties of the character with a value
     * which may be changed later in runtime during the play.
     */
    public Character()
    {
//        if(pType.equals(pType.BULLY))
//        {
//            _type = "Bully Player";
//        }
//        if(pType.equals(pType.NIMBLE))
//        {
//            _type = "Nimble Player";
//        }
//        if(pType.equals(pType.TANK))
//        {
//            _type = "Tank Player";
//        }
        _name = "Default Character";
        _level = 1;
        _strength = 0;//changed by khush
        _dexterity = 0;//changed by khush
        _constitution = 0;//changed by khush
        _wisdom = 0;//changed by khush
        _intelligence = 0;//changed by khush
        _charisma = 0;//changed by khush
        _modifier = 0;//changed by khush
        _damageBonus = 0;
        //_attackBonus = 1; //changed by khush

        _baseAttackBonus = 1;
        _armorClass = 10;//added by khush
        _armorModifier = 0;//added by khush
        _shieldModifier = 0;//added by khush
        _attackPerRound = 1;//added by khush
        _attackLeftPerRound = 1;//added by khush
        //movement variables
        _movement = 6;//added by khush
        _movementLeft = 6; //added by khush

        _healingPotion = 20; //it will increase hitpoints by 20
        _hitPoints = Dice.d10Dice() + getConstitutionModifier();      //not 100
        _maxHitPoints = _hitPoints;
        //added by khush
        calculateAttackBonus();

        _bodyArray = new ArrayList<BodyPart>();
        //_dfc = new DefaultComboBoxModel();
        _inventroyItem = new ArrayList<Item>();
        _backpackItem = new ArrayList<Item>();
        _modelInventory = new DefaultListModel();
        _modelBackpack = new DefaultListModel();
        _isRange = false;
        _isMelee = false;
//all added by khush from here
         _isHelmet=false;
         _isRing=false;
         _isBoot=false;
         _isBelt=false;
         _isGloves = false;
         _isBracer = false;
         _isArmor = false;
         _isShield = false;

         _helmetMagic =0;
         _armorMagic=0;
         _shieldMagic = 0;
         _bracerMagic =0;
         _ringMagic = 0;
         _bootsMagic = 0;
         _bowMagic = 0;
         _swordMagic =0;
         _beltMagic = 0;

         _ringStrength=0;
         _bracerStrength =0;
         _bootDexterity = 0;
         _ringWisdom =0;
         _helmetWisdom =0;
         _ringCharisma =0 ;
         _helmetIntelligence=0;
         _strengthModifier = getStrengthModifier();
         _dexterityModifier = getDexterityModifier();
         _wisdomModifier = getWisdomModifier();
         _charismaModifier = getCharismaModifier();
         _intelligenceModifier = getIntelligenceModifier();
    }
/**
 * setter method for belt worn
 * @param _isBelt is boolean
 */
    public void setIsBelt(boolean _isBelt) {
        this._isBelt = _isBelt;
    }
/**
 * setter method for boot worn
 * @param _isBoot boolean
 */
    public void setIsBoot(boolean _isBoot) {
        this._isBoot = _isBoot;
    }
/**
 * setter method for helmet worn
 * @param _isHelmet is boolean
 */
    public void setIsHelmet(boolean _isHelmet) {
        this._isHelmet = _isHelmet;
    }
/**
 * setter method for ring worn
 * @param _isRing is boolean
 */
    public void setIsRing(boolean _isRing) {
        this._isRing = _isRing;
    }
/**
 * getter method for belt worn
 * @return boolean
 */
    public boolean isIsBelt() {
        return _isBelt;
    }
/**
 * getter method for boot worn
 * @return boolean
 */
    public boolean isIsBoot() {
        return _isBoot;
    }
/**
 * getter method for helmet worn
 * @return boolean
 */
    public boolean isIsHelmet() {
        return _isHelmet;
    }
/**
 * getter method for ring worn
 * @return boolean
 */
    public boolean isIsRing() {
        return _isRing;
    }
    

    /**
     * method to get the characters max hit points at the level.
     * @return _maxHitPoints
     */
    public int getMaxHitPoints()
    {
        return _maxHitPoints;
    }

    /**
     * Name of the Player in the game.
     * @return It returns the name of the character
     */
    public String getName()
    {
        return _name;
    }

    /**
     * To get the player image, which is set as Avatar.
     * @return It returns the image file location path of the player Avatar.
     */
    public String getAvatar()
    {
        return _avatar;
    }

    /**
     * It's a property of a player ability.
     * @return _charisma returns the charisma score of the player (format intger).
     */
    public int getCharisma()
    {
        return _charisma;
    }

    /**
     * It's a property of a player ability.
     * @return _constitution returns the constitution score of the player (format intger).
     */
    public int getConstitution()
    {
        return _constitution;
    }

    /**
     * It's a property of a player ability.
     * @return _dexterity returns the dexterity score of the player (format intger).
     */
    public int getDexterity()
    {
        return _dexterity;
    }

    /**
     * It's a property of a player ability.
     * @return _intelligence returns the intelligence score of the player (format intger).
     */
    public int getIntelligence()
    {
        return _intelligence;
    }

    /**
     * It's a property of a player ability.
     * @return _strength returns the strength score of the player (format intger).
     */
    public int getStrength()
    {
        return _strength;

    }

    /**
     * It's a property of a player ability.
     * @return _wisdom returns the wisdom score of the player (format intger).
     */
    public int getWisdom()
    {
        return _wisdom;
    }

    /**
     * This getter method is for part of the damage bonus inflicted on opponent during attack.
     * If using sword you send strength modifier or 0 for a bow.
     * The second part is calculated by controller which is a d10 dice roll.
     * Plus magic influence of items: Long sword and long bow
     * @return _damageBonus returns the damageBonus score of the player (format integer).
     * @version 1.0.0     
     */
    public int getDamageBonus()
    {
        if (_isRange)
        {//bow
            _damageBonus = 0 + _bowMagic;
            OutputConsole.GetOutputConsoleInstance().Write("Range Damage Bonus" +_damageBonus +" = 0 + Bowmagic"+_bowMagic);
        } else if (_isMelee)
        {//sword
            _damageBonus = getStrengthModifier() + _swordMagic;
            OutputConsole.GetOutputConsoleInstance().Write("Melee Damage Bonus" +_damageBonus +" = strengthmod"+getStrengthModifier()+ " + Bowmagic"+_bowMagic);
        } else
        {
            _damageBonus = 0;
            OutputConsole.GetOutputConsoleInstance().Write("no range no melee Damage Bonus = 0");
        }
        return _damageBonus;
    }

    /**
     * Basically it indicates the ability of a player to absorb the hit from
     * a monster. It is in fact the life of a player.
     * @return _attackBonus returns the attackBonus score of the player (format integer).
     */
    public int getHitPoints()
    {
        return _hitPoints;
    }

    /**
     * It's the level of a player achieved based on performance.
     * @return _attackBonus returns the attackBonus score of the player (format intger).
     */
    public int getLevel()
    {
        return _level;
    }

    /**
     * This is a public function that get the character type.
     * @return character type.
     */
    public CharacterType getCharacterType()
    {
        return _characterType;
    }

    /**
     * Healing Potion is used to increase the Hit Point, i.e., the life of the
     * player.
     * @return It returns the vale of a potion, by which the hit point will increase.
     */
    public int getHealingPotion()
    {
        return _healingPotion;
    }


    /**
     * It's a property of a player ability.
     * @param pConstitution sets the constitution of the player (format intger).
     */
    /**
     * This method sets the image of the avatar.
     * @param pAvatar Path of the image location of the avatar.
     */
    public void setAvatar(String pAvatar)
    {
        this._avatar = pAvatar;
    }


    /**
     * It's a property of a player ability.
     * @param pCharisma sets the charisma of the player (format intger).
     */
    public void setCharisma(int pCharisma)
    {
        this._charisma = pCharisma;
        OutputConsole.GetOutputConsoleInstance().Write("Charisma: " + _charisma);
    }

    /**
     * This is a public function that set constitution.
     * @param pConstitution 
     */
    public void setConstitution(int pConstitution)
    {
        int oldModifier = calculateModifier(_constitution);
        this._constitution = pConstitution;
        OutputConsole.GetOutputConsoleInstance().Write("Constitution: " + _constitution);
//
    }

    /**
     * method to calculate hit points at every level change.
     */
    private void calculateHitPoints()
    {
        int n = _level;
        int tempHitPoint = 0;
        for (int i = 1; i <= n; i++)
        {
            tempHitPoint = tempHitPoint + (Dice.d10Dice() + getConstitutionModifier());
        }
        _hitPoints = tempHitPoint;
        if(_hitPoints ==0){
            _hitPoints = 5;// to ensure you get some value
        }
        _maxHitPoints = _hitPoints;
        OutputConsole.GetOutputConsoleInstance().Write("Hit Points: " + _hitPoints);
    }

    /**
     * Setter method for players dexterity.
     * It's a property of a player ability that affects both the armor class and the range attack bonus.
     * @param pDexterity sets the constitution of the player (format integer).
     * modified by khushnuma for build 2
     */
    public void setDexterity(int pDexterity)
    {
        this._dexterity = pDexterity;
        OutputConsole.GetOutputConsoleInstance().Write("Dexterity: " + _dexterity);
        calculateArmorClass();//added by khush
        calculateAttackBonus();//added by khushnuma
    }

    /**
     * It's a property of a player ability.
     * @param pIntelligence sets the intelligence of the player (format integer).
     */
    public void setIntelligence(int pIntelligence)
    {
        this._intelligence = pIntelligence;
        OutputConsole.GetOutputConsoleInstance().Write("Intelligence: " + _intelligence);
    }

    /**
     * It's the name of the player, which is set at the beginning of the game.
     * @param pName sets the name of the player.
     */
    public void setName(String pName)
    {
        this._name = pName;
    }

    /**
     * This is a public function that set character type.
     * To set type.
     * @param pType the type of the character.
     */
    public void setType(CharacterType pType)
    {
        _characterType = pType;
    }

    /**
     * Setter method for players strength
     * It's a property of a player ability.it affects the players attack bonus for melee weapons.
     * @param pStrength sets the strength of the player (format integer).     
     */
    public void setStrength(int pStrength)
    {
        this._strength = pStrength;
        OutputConsole.GetOutputConsoleInstance().Write("Strength: " + _strength);
        calculateAttackBonus();//addded by khushnuma
    }

    /**
     * It's a property of a player ability.
     * @param pWisdom sets the wisdom of the player (format integer).
     */
    public void setWisdom(int pWisdom)
    {
        this._wisdom = pWisdom;
        OutputConsole.GetOutputConsoleInstance().Write("Wisdom: " + _wisdom);
    }

    /**
     * Basically it indicates the ability of a player to absorb the hit from
     * a monster. It is in fact the life of a player.
     * @param pHitPoints sets the hit point score of the player at the begining of the game (format integer).
     */
    public void setHitPoints(int pHitPoints)
    {
        this._hitPoints = pHitPoints;
        OutputConsole.GetOutputConsoleInstance().Write("HitPoints: " + _hitPoints);
    }

    /**
     * It is the backpack of items kept real time in the game.
     * @return It returns the backpack list of items used in the game.
     */
    public ArrayList<Item> getBackPack()
    {
        return _backpackItem;
    }

    /**
     * This method creates the body parts of the player. Each body parts can be associated with
     * an Item during the play. Each body part is kept in a particular index in the list.
     * <p>
     * head --> 0
     * upper_body --> 1
     * left_hand --> 2
     * right_hand --> 3
     * finger --> 4
     * waist --> 5
     * feet --> 6
     */
    public void createBodyParts()
    {
        UpperBody upper_body = new UpperBody();
        RightHand right_hand = new RightHand();
        LeftHand left_hand = new LeftHand();
        Head head = new Head();
        Feet feet = new Feet();
        Finger finger = new Finger();
        Waist waist = new Waist();
        Palm palm = new Palm();
        ForeArm forearm = new ForeArm();

        _bodyArray.add(head); //0
        _bodyArray.add(upper_body); //1
        _bodyArray.add(left_hand); //2
        _bodyArray.add(right_hand); //3
        _bodyArray.add(finger); //4
        _bodyArray.add(waist); //5
        _bodyArray.add(feet); //6
        _bodyArray.add(palm);//7
        _bodyArray.add(forearm);//8

//        for (int i = 0; i < _bodyArray.size(); i++)
//        {
//            _dfc.addElement(_bodyArray.get(i).getName());
//        }
    }

    /**
     * createInventoryItems() method creates the Item by default at the beginging
     * of the game and keeps those into inventory list. It also initializes the default
     * list model to populate the JList in the view.
     * @author khushnuma
     * @version 1.2.0
     * changed for build 2 to include different armor and shields etc
     */
    public void createInventoryItems()
    {
        //commented out by khush
//        Armor armor_heavy = new Armor(_armorType.HEAVY);
//        Armor armor_medium = new Armor(_armorType.MEDIUM);
//        Armor armor_light = new Armor(_armorType.LIGHT);

        //added by khush
        Armor armor_padded = new Armor(_armorType.PADDED);
        Armor armor_bandedMail = new Armor(_armorType.BANDEDMAIL);
        Armor armor_breastPlate = new Armor(_armorType.BREASTPLATE);
        Armor armor_chainShirt = new Armor(_armorType.CHAINSHIRT);
        Armor armor_fullPlate = new Armor(_armorType.FULLPLATE);
        Armor armor_halfPlate = new Armor(_armorType.HALFPLATE);
        Armor armor_leather = new Armor(_armorType.LEATHER);
        Armor armor_studdedLeather = new Armor(_armorType.STUDDEDLEATHER);

//commented out by khush
//        Shield shield = new Shield();

        //added by khush
        Shield shield_buckler = new Shield(_shieldType.BUCKLER);
        Shield shield_heavyShield = new Shield(_shieldType.HEAVYSHIELD);
        Shield shield_towerShield = new Shield(_shieldType.TOWERSHIELD);


        HealingPotion heal_potion = new HealingPotion();

        LongSword long_sword = new LongSword();
        LongBow long_bow = new LongBow();

        Belt belt = new Belt();
        Helmet helmet = new Helmet();
        Ring ring = new Ring();
        Boot boot = new Boot();
        Gloves gloves = new Gloves();
        Bracer bracer = new Bracer();


        long_sword.setIsMelee(true);
        long_bow.setIsLongRange(true);

        //commented out by khush
//        _inventroyItem.add(armor_heavy);
//        _inventroyItem.add(armor_medium);
//        _inventroyItem.add(armor_light);
//        _inventroyItem.add(shield);

        //added by khush
        _inventroyItem.add(shield_buckler);
        _inventroyItem.add(shield_towerShield);
        _inventroyItem.add(shield_heavyShield);
        _inventroyItem.add(armor_padded);
        _inventroyItem.add(armor_leather);
        _inventroyItem.add(armor_studdedLeather);
        _inventroyItem.add(armor_fullPlate);
        _inventroyItem.add(armor_halfPlate);
        _inventroyItem.add(armor_chainShirt);
        _inventroyItem.add(armor_breastPlate);
        _inventroyItem.add(armor_bandedMail);

        _inventroyItem.add(heal_potion);
        _inventroyItem.add(long_sword);
        _inventroyItem.add(long_bow);
        _inventroyItem.add(belt);
        _inventroyItem.add(helmet);
        _inventroyItem.add(ring);
        _inventroyItem.add(boot);
        _inventroyItem.add(gloves);
        _inventroyItem.add(bracer);

        //populating the DefaultListModel.
        for (int i = 0; i < _inventroyItem.size(); i++)
        {
            _modelInventory.addElement(_inventroyItem.get(i).getName());
        }
    }

    /**
     * This method adds an item to the backpack and into the DefaultModelList, which
     * will update the backpack item list and DefaultListModel (for JList).
     * @param pItem It is the reference for the item.
     */
    public void addBackpackItems(Item pItem, boolean pInModel)
    {
        _backpackItem.add(pItem);
        
        if(pInModel == true)
        {
            _modelBackpack.addElement(pItem.getName());
        }
    }

    /**
     * To remove the back pack items.
     * @param pIndex the item in the item.
     * @param pInModel the boolean type value of the in model.
     */
    public void removeBackpackItems(int pIndex, boolean pInModel)
    {
//        _backpackItem.remove(pItem);
//
//        if(pInModel == true)
//        {
//            _modelBackpack.removeElement(pItem.getName());
//        }

        _backpackItem.remove(pIndex);

        if(pInModel == true)
        {
            _modelBackpack.removeElementAt(pIndex);
        }
    }

    /**
     * It will return DefaultComboBoxModel to populate JCombobox.
     * @return It returns the list for body parts.
     */
//    public DefaultComboBoxModel getBodyPartsName()
//    {
//        return _dfc;
//    }

    /**
     * It will return the inventory items of the character.
     * @return It returns the list for inventory items.
     */
    public ArrayList<Item> getInventroyItem()
    {
        return _inventroyItem;
    }

    /**
     * It will return DefaultLisModel to populate JList.
     * @return It will return a DefaultListModel for jList.
     */
    public DefaultListModel getInventoryList()
    {
        return _modelInventory;
    }

    /**
     * It will return DefaultLisModel to populate JList.
     * @return It will return a DefaultListModel for jList.
     */
    public DefaultListModel getBackpackList()
    {
        return _modelBackpack;
    }

    /**
     * It will check the overlap while player moves/travels throughout the map.
     * @return It returns boolean.
     */
    @Override
    public boolean canOverlap()
    {
        return true;
    }

    /**
     * Getter method to get value of armor class
     * @return it returns <code>_armorClass</code>
     */
    public int getArmorClass()
    {
        this._armorClass = 10 + _armorModifier + _shieldModifier + getDexterityModifier()+ _helmetMagic + _armorMagic + _shieldMagic + _bracerMagic + _ringMagic + _bootsMagic;
        return _armorClass;
    }

    /**
     * This is a method that calculate the armor class.
     * It is based on base bonus modifiers of shield and armor and dexterity modifier
     * Influence of magic items: armor, shield, bracer, ring, boots
     */
    public void calculateArmorClass() {
        int dexterityModifier = getDexterityModifier();
        this._armorClass = 10 + _armorModifier + _shieldModifier + dexterityModifier + _helmetMagic + _armorMagic + _shieldMagic + _bracerMagic + _ringMagic + _bootsMagic;
        OutputConsole.GetOutputConsoleInstance().Write("AC = 10 + am + sm + dexMod + armorMagic + shieldMagic + bracerMagic + ringMagic + bootsMagic: 10+ " + _armorModifier + " + " + _shieldModifier + " + " + dexterityModifier + " + "+ _helmetMagic +" + "+ _armorMagic + " + " + _shieldMagic + " + " + _bracerMagic + " + " + _ringMagic + " + " + _bootsMagic);
        OutputConsole.GetOutputConsoleInstance().Write("AC: " + _armorClass);
    }

    /**
     * It will return the value of armor modifiers.
     * @return it returns the <cide>_armorModifier</code>.
     */
    public int getArmorModifier()
    {
        return _armorModifier;
    }

    /**
     * It will get the value of armor modifiers and calculate armor class.
     * @param _armorModifier The value of <code>_armorModifer</code>.
     */
    public void setArmorModifier(int _armorModifier)
    {
        this._armorModifier = _armorModifier;
        OutputConsole.GetOutputConsoleInstance().Write("ArmorModifier: " + _armorModifier);
        //calculateArmorClass();
    }

    /**
     * getter method for shield modifier currently held by player
     * @return _shieldModifier is shield modifier value
     * @author khushnuma
     */
    public int getShieldModifier()
    {
        return _shieldModifier;
    }

    /**
     * Setter method for shield modifier. Set to the value in shield depending on which shield was worn.
     * @param _shieldModifier is value of shield modifier.
     */
    public void setShieldModifier(int _shieldModifier)
    {
        this._shieldModifier = _shieldModifier;
        OutputConsole.GetOutputConsoleInstance().Write("ShieldModifier: " + _shieldModifier);
        //calculateArmorClass();
    }

    /**
     * Getter method to show the total number of movements available to player per turn
     * @return _movement is the total number of movements available to player per turn
     */
    public int getMovement()
    {
        return _movement;
    }

    /**
     * Getter method to show the number of player movements left in a given turn.
     * @return _movementLeft is movement left in turn
     * @author khushnuma
     */
    public int getMovementLeft()
    {
        return _movementLeft;
    }

    /**
     * Setter method to reduce the number of movements available or left to player at each turn.
     * It should be reset to 6 at beginning of each turn.
     * @param _movementLeft is movement left in turn
     * @author khushnuma
     */
    public void setMovementLeft(int _movementLeft)
    {
        this._movementLeft = _movementLeft;
        OutputConsole.GetOutputConsoleInstance().Write("Movement Left: " + _movementLeft);
    }

    
    /**
     * This is a public function to set the back pack list.
     * @param pBackpackList the back pack list.
     */
    public void setBackpackList(DefaultListModel pBackpackList)
    {
        this._modelBackpack = _modelBackpack;
    }



    /**
     * Getter method for seeing a players base attack bonus for a given level.
     * @return <code>_baseAttackBonus</code>
     */
    public int getBaseAttackBonus()
    {
        return _baseAttackBonus;
    }


    /**
     * It's a bonus for a player performance used when attacking a monster with meele weapon.
     * @return _rangeAttackBonus returns the range attack Bonus score of the player (format integer).
     * @author khushnuma
     * @version 1.0.0
     */
    public int getMeleeAttackBonus()
    {
        _meleeAttackBonus = _baseAttackBonus + getStrengthModifier()+ _swordMagic;
        return _meleeAttackBonus;
    }

    /**
     * It's a bonus for a player performance used when attacking a monster with range weapon.
     * @return _rangeAttackBonus returns the range attack Bonus score of the player (format integer).
     * @author khushnuma
     * @version 1.0.0
     */
    public int getRangeAttackBonus()
    {
        _rangeAttackBonus = _baseAttackBonus + getDexterityModifier() + _bowMagic;
        return _rangeAttackBonus;
    }

    /**
     * method to set the level of a player.
     * @param plevel is the level of the player. It assumes that level is being incremented by one each time.
     * @author khushnuma modified it for build 2
     */
    public void setLevel(int plevel)
    {
        //this._level = plevel;

        if (plevel > this._level)
        {

            int temp = plevel - this._level;

            for (int i = 1; i <= temp; i++)
            {

                addLevel();

            }

        } else if (plevel < this._level)
        {

            int temp = this._level - plevel;

            for (int i = 1; i <= temp; i++)
            {

                minusLevel();

            }
        } else if (plevel == this._level)
        {

            calculateHitPoints();
            calculateAttackPerRound();
            _baseAttackBonus = _level;
            calculateAttackBonus();
        }

    }

    /**
     * method to add to the level of a player by 1.
     * It is the level of the player. It assumes that level is being incremented by one each time.
     * @author khushnuma added it for build 2
     */
    public void addLevel()
    {
        if (_level < 20)
        {
            this._level = this._level + 1;
            OutputConsole.GetOutputConsoleInstance().Write("Level: " + _level);
            this._baseAttackBonus = this._level;
            OutputConsole.GetOutputConsoleInstance().Write("Base Attack Bonus: " + _baseAttackBonus);
            calculateAttackBonus();//add hit point
            calculateHitPoints();
            calculateAttackPerRound();
        }
    }

    /**
     * method to minus to the level of a player by 1.
     * It is the level of the player. It assumes that level is being decremented by one each time.
     * @author khushnuma added it for build 2
     * @version 1.0.0
     */
    public void minusLevel()
    {
        if (_level > 1)
        {
            this._level = this._level - 1;
            OutputConsole.GetOutputConsoleInstance().Write("Level: " + _level);
            this._baseAttackBonus = this._level;
            OutputConsole.GetOutputConsoleInstance().Write("Base Attack Bonus: " + _baseAttackBonus);
            calculateAttackBonus();
            calculateHitPoints();
            calculateAttackPerRound();
        }
    }

    /**
     * called by game class to minus a players hit points during game play.
     * @version 1.0.0
     */
    public void minusHitPoints(int pminusValue)
    {
        _hitPoints = _hitPoints - pminusValue;
        if (_hitPoints < 0)
        {
            _hitPoints = 0;//hit points should not be negative
        }
        OutputConsole.GetOutputConsoleInstance().Write("Hit Points: " + _hitPoints);
    }
//    /**
//     * Method to reduce a players hit points when they go down a level from the player editor.
//     * @author khushnuma added it for build 2
//     * @version 1.0.0
//     */
//    private void reduceHitPoint(){
//        int diceValue = Dice.d10Dice();
//        int constitutionModifier = calculateModifier(_constitution);
//        int changeValue = diceValue + constitutionModifier;
//        OutputConsole.GetOutputConsoleInstance().Write("HitPoint before reduction: "+_hitPoints );
//        OutputConsole.GetOutputConsoleInstance().Write("HitPoint Reduction By( 1d10 + constitMod): "+diceValue + constitutionModifier );
//        this._hitPoints = this._hitPoints - changeValue;
//        OutputConsole.GetOutputConsoleInstance().Write("HitPoint after reduction: "+_hitPoints );
//    }

    /**
     * Method to calculate the ability modifier for a given ability point according to the rules.
     * @author khushnuma
     * @param pAbilityScore is the ability score of the given ability
     * @return abilityModifer is the ability modifier for the ability score pAbilityScore.
     * @version 1.0.0
     */
    public int calculateModifier(int pAbilityScore)
    {
        int abilityModifer;
        if (pAbilityScore == 0 || pAbilityScore == 1)
        {
            abilityModifer = -5;
        } else if (pAbilityScore == 2 || pAbilityScore == 3)
        {
            abilityModifer = -4;
        } else if (pAbilityScore == 4 || pAbilityScore == 5)
        {
            abilityModifer = -3;
        } else if (pAbilityScore == 6 || pAbilityScore == 7)
        {
            abilityModifer = -2;
        } else if (pAbilityScore == 8 || pAbilityScore == 9)
        {
            abilityModifer = -1;
        } else if (pAbilityScore == 10 || pAbilityScore == 11)
        {
            abilityModifer = 0;
        } else
        {
            int temp = pAbilityScore - 11;
            int q = temp / 2;
            int r = temp % 2;
            abilityModifer = q + r;
        }
        return abilityModifer;
    }//end method calculate ability modifier

    /**
     * getter method for attacks per round available to player
     * @return _attackPerRound is attacks per round.
     * @author khushnuma
     * @version 1.0.0
     */
    public int getAttackPerRound()
    {
        return _attackPerRound;
    }

    /**
     * getter method to show number of attacks left in that round / turn of a player.
     * max attack per round is level dependant and stored in variable _attackPerRound.
     * @return  _attackLeftPerRound number of attacks left in that round / turn of a player.
     * @author khushnuma
     * @version 1.0.0
     */
    public int getAttackLeftPerRound()
    {
        return _attackLeftPerRound;
    }

    /**
     * Method to decrement the attack left per round/ turn of a player.
     * @author khushnuma
     * @version 1.0.0
     */
    public void minusAttackPerRound()
    {
        _attackLeftPerRound = _attackLeftPerRound - 1;
        reduceBaseAttackBonus();
    }

    /**
     * Method to decrement the movement left or available to a player by 1 during the players turn / move.
     * @author khushnuma
     * @version 1.0.0
     */
    public void minusMovementLeft()
    {
        _movementLeft = _movementLeft - 1;
        OutputConsole.GetOutputConsoleInstance().Write("Movement Left in turn: " + _movementLeft);
    }

    /**
     * method executed at the end of each players turn that will reset the players values for
     * available movements, base attack bonus and attacks per round in time for his next turn.
     * @author khushnuma
     * @version 1.0.0
     */
    public void endOfTurn()
    {
        _baseAttackBonus = _level;
        calculateAttackBonus();
        _attackLeftPerRound = _attackPerRound;
        _movementLeft = _movement;
    }

    /**
     * Method to recalculate the base attack bonus of a player during his turn. This is required as he can have
     * more than one attack in one turn depending on his level. But his base attack bonus reduces with each
     * consecutive attack.
     * @author khushnuma
     * @version 1.0.0
     */
    public void reduceBaseAttackBonus()
    {
        //do this per level
        switch (_level)
        {
            case 1:
                if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 2:
                if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 3:
                if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 4:
                if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 5:
                if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 6:
                if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 1;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 7:
                if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 2;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 8:
                if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 3;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 9:
                if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 4;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 10:
                if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 5;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;

            case 11:
                if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 6;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 1;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 12:
                if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 7;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 2;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 13:
                if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 8;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 3;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 14:
                if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 9;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 4;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 15:
                if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 10;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 5;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 16:
                if (_attackLeftPerRound == 3)
                {
                    _baseAttackBonus = 11;
                } else if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 6;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 1;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 17:
                if (_attackLeftPerRound == 3)
                {
                    _baseAttackBonus = 12;
                } else if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 7;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 2;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 18:
                if (_attackLeftPerRound == 3)
                {
                    _baseAttackBonus = 13;
                } else if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 8;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 3;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 19:
                if (_attackLeftPerRound == 3)
                {
                    _baseAttackBonus = 14;
                } else if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 9;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 4;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
            case 20:
                if (_attackLeftPerRound == 3)
                {
                    _baseAttackBonus = 15;
                } else if (_attackLeftPerRound == 2)
                {
                    _baseAttackBonus = 10;
                } else if (_attackLeftPerRound == 1)
                {
                    _baseAttackBonus = 5;
                } else if (_attackLeftPerRound == 0)
                {
                    _baseAttackBonus = 0;
                }
                break;
        }//end switch
        OutputConsole.GetOutputConsoleInstance().Write("Reduced base Attack Bonus: " + _baseAttackBonus);
        calculateAttackBonus();
    }

    /**
     * method too calculate the number of attacks per round available to the player.
     * it changes on a per level basis.
     * @author khushnuma
     * @version 1.0.0
     */
    public void calculateAttackPerRound()
    {
        //do this per level
        switch (_level)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                _attackPerRound = 1;
                _attackLeftPerRound = 1;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                _attackPerRound = 2;
                _attackLeftPerRound = 2;
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                _attackPerRound = 3;
                _attackLeftPerRound = 3;
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                _attackPerRound = 4;
                _attackLeftPerRound = 4;
                break;
        }//end switch
        OutputConsole.GetOutputConsoleInstance().Write("Attack per Round: " + _attackPerRound);
        OutputConsole.GetOutputConsoleInstance().Write("Attack Left per Round: " + _attackLeftPerRound);
    }

    /**
     * Method to calculate the range and melee attack bonus for a player.
     * These values are used during attacks by the player. They are both dependant on
     * the base attack bonus which is itself dependant on the player level.
     * Range bonus is dependant on dexterity modifier also
     * Influence of magic items: sword, bow on melee and range respectively.
     * Incase of no weapon, we use base attack bonus itself.
     * @author khushnuma
     * @version 1.0.0
     */
    private void calculateAttackBonus()
    {
        _rangeAttackBonus = _baseAttackBonus + getDexterityModifier() + _bowMagic;
        OutputConsole.GetOutputConsoleInstance().Write("Range AB = Base AB + dexMod + bowMagic: " + _baseAttackBonus + " + " + getDexterityModifier()+ " + " + _bowMagic);
        OutputConsole.GetOutputConsoleInstance().Write("Range AB: " + _rangeAttackBonus);
        //_meleeAttackBonus = _baseAttackBonus + calculateModifier(_strength)+ _swordMagic;
        _meleeAttackBonus = _baseAttackBonus + getStrengthModifier()+ _swordMagic;
        OutputConsole.GetOutputConsoleInstance().Write("Melee AB = Base AB + strengthMod + swordMagic: " + _baseAttackBonus + " + " + getStrengthModifier()+ " + "+ _swordMagic);
        OutputConsole.GetOutputConsoleInstance().Write("Melee AB: " + _meleeAttackBonus);
        if (_isRange)
        {
            _attackBonus = _rangeAttackBonus;
            OutputConsole.GetOutputConsoleInstance().Write("%%%%Total Attack Bonus: " + _attackBonus);
        } else if (_isMelee)
        {
            _attackBonus = _meleeAttackBonus;
            OutputConsole.GetOutputConsoleInstance().Write("%%%%Total Attack Bonus: " + _attackBonus);
        } else//neither melee or range or no sword or no bow
        {
            _attackBonus = _baseAttackBonus;
            OutputConsole.GetOutputConsoleInstance().Write("%%%%Total Attack Bonus no weapon: " + _attackBonus);
        }
    }
///**
// * this method calculates the hit points made available to a player depending on
// * his constitution modifier, a random dice value from d10 dice and his earlier hit points.
// * it changes when player level changes by one. It is internally called by the addLevel method.
// * @author khushnuma
// * @version 1.0.0
// */
//    private void calculateHitPoint() {
//        int diceValue = Dice.d10Dice();
//        _hitPoints = _hitPoints + (diceValue+ calculateModifier(_constitution));
//        OutputConsole.GetOutputConsoleInstance().Write("Addition Factor to HitPoints = 1d10 + constitMod: " +diceValue+" + "+calculateModifier(_constitution));
//        OutputConsole.GetOutputConsoleInstance().Write("HitPoints: "+_hitPoints );
//    }

    /**
     * method to get if melee weapon is worn.
     * @return _isMelee boolean if true means sword is worn ad false means sword is not worn
     */
    public boolean isIsMelee()
    {
        return _isMelee;
    }

    /**
     * Method to get if range weapon is worn
     * @return _isRange boolean if true means sword is worn ad false means bow is not worn
     */
    public boolean isIsRange()
    {
        return _isRange;
    }

    /**
     * method to set if melee weapon is worn
     * true  = long sword worn, false = long sword not worn
     * @param pIsMelee boolean if true means sword is worn ad false means sword is not worn
     */
    public void setIsMelee(boolean pIsMelee)
    {
        this._isMelee = pIsMelee;
        OutputConsole.GetOutputConsoleInstance().Write("Long Sword Worn: " + _isMelee);
        //calculateAttackBonus();
    }

    /**
     * Setter method to set if range weapon is worn
     * true  = longbow worn, false = long bow not worn
     * @param pIsRange boolean if true means bow is worn ad false means sword is not worn
     */
    public void setIsRange(boolean pIsRange)
    {
        this._isRange = pIsRange;
        OutputConsole.GetOutputConsoleInstance().Write("Long Bow Worn: " + _isRange);
        //calculateAttackBonus();
    }

    /**
     * getter method to check the attack bonus of a player.
     * @return _attackBonus for the player at the present time.
     */
    public int getAttackBonus()
    {
         if (_isRange)
        {
            _attackBonus = getRangeAttackBonus();

        } else if (_isMelee)
        {
            _attackBonus = getMeleeAttackBonus();

        } else//neither melee or range or no sword or no bow
        {
            _attackBonus = getBaseAttackBonus();
        }
        return _attackBonus;
    }

    /**
     * Saves the character to disk.
     *
     */
    public void saveCharacter()
    {
        String _savePath;
        _savePath = System.getProperty("user.dir");
        _savePath += "\\src\\View\\resources\\characters\\";

        try
        {
            Boolean oosFlag = false;
            FileOutputStream fout = new FileOutputStream(_savePath + this.getName() + ".ch");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            File file = new File(_savePath + this.getName() + ".ch");
            oos.writeObject(this);
            OutputConsole.GetOutputConsoleInstance().Write("Character was  saved to file.");
            
            /*
            if (!file.exists())
            {
                oos.writeObject(this);
                OutputConsole.GetOutputConsoleInstance().Write("Character was  saved to file.");
            } else
            {
                oos.close();
                oosFlag = true;

                Boolean result = file.delete();

                if (result)
                {
                    FileOutputStream fout2 = new FileOutputStream(_savePath + this.getName() + ".ch");
                    ObjectOutputStream oos2 = new ObjectOutputStream(fout2);
                    oos2.writeObject(this);
                    oos2.close();
                }
                

            }
            if( oosFlag == false )
                oos.close();
            */
            oos.close();
        } catch (Exception e)
        {
            OutputConsole.GetOutputConsoleInstance().WriteException("Error Occured:CS " + e.getMessage());
        }

    }

//    public void endGameInitialize()
//    {
//        for(int i = 0; i<7; i++)
//            _bodyArray.get(i).takeOffItem(null);
//        this.setIsRange(false);// in case of longbow
//        this.setIsMelee(false) ;//in case of sword.
//        this.setShieldModifier(0); //in case of removing any shield.
//        this.setArmorModifier(0); //in case of removing any armor.
//        this._armorMagic = 0;
//        this._helmetMagic = 0;
//        this._shieldMagic =0;
//        this._bracerMagic =0;
//        this._ringMagic = 0;
//        this._bootsMagic = 0;
//        this._bowMagic = 0;
//        this._swordMagic = 0;
//        this._beltMagic = 0;
//        this.setIsArmor(false);
//        this.setIsShield(false);
//        this.setIsBelt(false);
//        this.setIsBoot(false);
//        this.setIsHelmet(false);
//        this.setIsRing(false);
//        this.setIsBracer(false);
//        this.setIsGloves(false);
//    }

    /**
     * To set the mad.
     * @param pIsMad boolean type value of the mad.
     */
    public void setIsMad(boolean pIsMad)
    {
        _isMad = pIsMad;
    }   
    
    /**
     * To return a boolean type value of is mad.
     * This is a public function called is mad.
     * @return a boolean type value.
     */
    public boolean isMad()
    {
        return _isMad;
    }

    /**
     * To return a boolean type value of bracer.
     * Check to see whether worn the bracer.
     * @return a boolean type value.
     */
    public boolean isIsBracer() {
        return _isBracer;
    }
    /**
     * To return a boolean type value of gloves.
     * Check to see whether worn the gloves.
     * @return a boolean type value.
     */
    public boolean isIsGloves() {
        return _isGloves;
    }

    /**
     * This is a public function to set the bracer.
     * true  = bracer worn, false = bracer not worn
     * @param pIsBracer the boolean type value of the bracer.
     */
    public void setIsBracer(boolean pIsBracer) {
        this._isBracer = pIsBracer;
        OutputConsole.GetOutputConsoleInstance().Write("Bracer worn: " + _isBracer);
    }

    /**
     * To set the gloves.
     * true  = gloves worn, false = gloves not worn
     * @param pIsGloves the boolean type value of the gloves.
     */
    public void setIsGloves(boolean pIsGloves) {
        this._isGloves = pIsGloves;
        OutputConsole.GetOutputConsoleInstance().Write("Gloves worn: " + _isGloves);
    }

    /**
     * To return a boolean type value of armor.
     * This is a public function called is is armor.
     * 
     * @return a boolean type value.
     */
    public boolean isIsArmor() {
        return _isArmor;
    }

    /**
     * This is a public function to set the armor.
     * true  = armor worn, false = armor not worn
     * @param _isArmor the boolean type value of the armor.
     */
    public void setIsArmor(boolean _isArmor) {
        this._isArmor = _isArmor;
        OutputConsole.GetOutputConsoleInstance().Write("Armor worn: " + _isArmor);
    }

    /**
     * This is a public function to set the shield flag.
     * true  = shield worn, false = shield not worn
     * @param _isShield the boolean type value of the shield. 
     */
    public void setIsShield(boolean _isShield) {
        this._isShield = _isShield;
        OutputConsole.GetOutputConsoleInstance().Write("Shield worn: " + _isShield);
    }

     /**
     * To return a boolean type value of shield.
     * Check to see whether worn the shield.
     * @return a boolean type value.
     */
    public boolean isIsShield() {
        return _isShield;
    }
    
    /**
     * Method to wear the item for the player.
     * This sets all flags and sets all magic and normal modifiers to appropriate values and then calls
     * the appropriate functions to recalculate other values as necessary.
     * @author khush
     * @param pItem the item which want to wear.
     */
    public void wearItem(Item pItem){
        String itemName = pItem.getName().toLowerCase();
        if(itemName.equalsIgnoreCase("BandedMail")|| itemName.equalsIgnoreCase("BreastPlate")|| itemName.equalsIgnoreCase("ChainShirt")|| itemName.equalsIgnoreCase("FullPlate")|| itemName.equalsIgnoreCase("HalfPlate")|| itemName.equalsIgnoreCase("Padded")|| itemName.equalsIgnoreCase("StuddedLeather")|| itemName.equalsIgnoreCase("Leather")){//begin wear armor
            setIsArmor(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears armor: " + itemName);
            Armor armor_temp = (Armor)pItem;
            this.setArmorModifier(armor_temp.getArmorBonus());
            //setArmorModifier(armor_temp.getArmorBonus());
            this._armorMagic = armor_temp.getMagicValue();
            OutputConsole.GetOutputConsoleInstance().Write("Armor magic: " + _armorMagic);
            calculateArmorClass();
        }//end wear armor
        else if (itemName.equalsIgnoreCase("belt")){//begin wear belt
            OutputConsole.GetOutputConsoleInstance().Write("character wears belt: " + itemName);
            setIsBelt(true);
            Belt belt_temp = (Belt)pItem;
            this._beltMagic = pItem.getMagicValue();
            OutputConsole.GetOutputConsoleInstance().Write("Belt magic: " + _beltMagic);
            //do something for strength modifier here and impact attack bonus and damage bonus
            calculateAttackBonus();// also changes damage bonus but since it is calculated at every get we dont dont anything
            calculateArmorClass();
        }//end belt wear
        else if(itemName.equalsIgnoreCase("boot")){//begin boot wear
            setIsBoot(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears boot: " + itemName);
            Boot boot_temp = (Boot)pItem;
            if(pItem.getMagicSetter()== 1){//armor class
                this._bootsMagic = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Boots magic to armor class: " + _bootsMagic);
                calculateAttackBonus();//impact also on damage bonus
                calculateArmorClass();
            }
            else if (pItem.getMagicSetter()==2){//dexterity modifer
                this._bootDexterity = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Boots magic to dexterity modifer: " + _bootDexterity);
                // no need to call dexterity modifier function it is calculated on teh fly every time.
                calculateAttackBonus();//impact also on damage bonus
                calculateArmorClass();
            }
        }//end booot wear
        else if(itemName.equalsIgnoreCase("helmet")){// begin helmet
            setIsHelmet(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears helmet: " + itemName);
            Helmet helmet_temp = (Helmet)pItem;
            if(pItem.getMagicSetter() == 1){//armor class modified
                this._helmetMagic = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to armor class: " + _helmetMagic);
                calculateAttackBonus();//impact also on damage bonus
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter() == 6){//intelligence modifier
                this._helmetIntelligence = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to intelligence modifier: " + _helmetIntelligence);
                // no need to calculate intelligence modifier it is always calulated on the fly.
            }
            else if(pItem.getMagicSetter() == 4){// wisdom modifier
                this._helmetWisdom = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to wisdom modifier: " + _helmetWisdom);
                // no need to calculate wisdom modifier it is always calulated on the fly.
            }
        }//end helmet
        else if(itemName.equalsIgnoreCase("longbow")){//begin range long bow
            setIsRange(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears longbow: " + itemName);
            LongBow longbow_temp = (LongBow)pItem;
            this._bowMagic = pItem.getMagicValue();
            OutputConsole.GetOutputConsoleInstance().Write("LongBow magic: " + _bowMagic);
            calculateAttackBonus();//attack and damage bonus
            calculateArmorClass();
        }//end longbow
        else if (itemName.equalsIgnoreCase("longsword")){//begin melee longsword
            setIsMelee(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears longsword: " + itemName);
            LongSword longsword_temp = (LongSword)pItem;
            this._swordMagic = pItem.getMagicValue();
            OutputConsole.GetOutputConsoleInstance().Write("LongSword magic: " + _swordMagic);
            calculateAttackBonus();//attack and damamge bonus
            calculateArmorClass();
        }//end longsword
        else if(itemName.equalsIgnoreCase("ring")){//begin ring
            setIsRing(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears ring: " + itemName);
            Ring ring_temp = (Ring)pItem;
            if(pItem.getMagicSetter()== 1){//armor class modified
                this._ringMagic = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to armor class: " + _ringMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter()==3){//strength modifier
                this._ringStrength = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to strength modifier: " + _ringStrength);
                //no need to calculate strenght modifier it is calculated on fly every time it is called
                calculateArmorClass();
                calculateAttackBonus();
            }
            else if(pItem.getMagicSetter()==4){//wisdom modifier
                this._ringWisdom = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to wisdom modifier: " + _ringWisdom);
                //no need to calculate wisdom modifier it is calculated on fly every time it is called
            }
            else if (pItem.getMagicSetter()==5){//charisma modifier
                this._ringCharisma = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to charisma modifier: " + _ringCharisma);
                //no need to calculate charisma modifier it is calculated on fly every time it is called
            }
        }//end ring
        else if(itemName.equalsIgnoreCase("HeavyShield")|| itemName.equalsIgnoreCase("Buckler")|| itemName.equalsIgnoreCase("TowerShield")){// begin shield
            setIsShield(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears shield: " + itemName);
            Shield shield_temp  = (Shield)pItem;
            this.setShieldModifier(shield_temp.getShieldBonus());
            this._shieldMagic = pItem.getMagicValue();
            OutputConsole.GetOutputConsoleInstance().Write("Shield magic to armor class: " + _shieldMagic);
            calculateArmorClass();
        }//end shield
        else if(itemName.equalsIgnoreCase("gloves")){//begin gloves
            setIsGloves(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears gloves: " + itemName);
            Gloves gloves_temp = (Gloves)pItem;
            // no bonus no magic no effect on anything
        }//end gloves
        else if(itemName.equalsIgnoreCase("bracer")){//begin bracer
            setIsBracer(true);
            OutputConsole.GetOutputConsoleInstance().Write("character wears bracer: " + itemName);
            Bracer bracer_temp = (Bracer)pItem;
            if(pItem.getMagicSetter()== 1){//armor class modified
                this._bracerMagic = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Bracer magic to armor class: " + _bracerMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter()== 3){//strenght modifier changed
                this._bracerStrength = pItem.getMagicValue();
                OutputConsole.GetOutputConsoleInstance().Write("Bracer magic to strength modifier: " + _bracerStrength);
                calculateArmorClass();
                calculateAttackBonus();
                // no need to calculate strenght modifier it is calculated on the fly every time.
            }
        }//end bracer
    }//end wear item method
    /**
     * Method to remove the item from player.
     * This un sets all flags and sets all magic and normal modifiers to zero and then calls
     * the appropriate functions to recalculate values as necessary.
     * @author khush
     * @param pItem the item which want to remove.
     */
    public void removeItem(Item pItem){
        String itemName = pItem.getName().toLowerCase();
        if(itemName.equalsIgnoreCase("BandedMail")|| itemName.equalsIgnoreCase("BreastPlate")|| itemName.equalsIgnoreCase("ChainShirt")|| itemName.equalsIgnoreCase("FullPlate")|| itemName.equalsIgnoreCase("HalfPlate")|| itemName.equalsIgnoreCase("Padded")|| itemName.equalsIgnoreCase("StuddedLeather")|| itemName.equalsIgnoreCase("Leather")){//begin armor takeoff
            setIsArmor(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes armor: " + itemName);
            Armor armor_temp = (Armor)pItem;
            this.setArmorModifier(0);            
            this._armorMagic = 0;
            OutputConsole.GetOutputConsoleInstance().Write("Armor magic to armor class: " + _armorMagic);
            calculateArmorClass();
        }//end armor take off
        else if (itemName.equalsIgnoreCase("belt")){//begin belt takeoff
            setIsBelt(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes belt: " + itemName);
            Belt belt_temp = (Belt)pItem;
            this._beltMagic = 0;
            OutputConsole.GetOutputConsoleInstance().Write("Belt magic to armor class: " + _beltMagic);
            //do something for strength modifier impact attack bonus and damage bonus
            calculateAttackBonus();//dont do anything for damage since it is calculated at every get.
            calculateArmorClass();
        }//end belt take off
        else if(itemName.equalsIgnoreCase("boot")){//begin boot take off
            setIsBoot(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes boot: " + itemName);
            Boot boot_temp = (Boot)pItem;
            if(pItem.getMagicSetter()== 1){//armor class modified
                this._bootsMagic = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Boots magic to armor class: " + _bootsMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter()== 2){//dexterity modifier
                this._bootDexterity = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Boots magic to dexterity modifier: " + _bootDexterity);
                // no need to calculate dexterity modifier it is dont on teh fly wiht every get
                calculateArmorClass();
                calculateAttackBonus();
            }
        }//end boot take off
        else if(itemName.equalsIgnoreCase("helmet")){//begin helmet take off
            setIsHelmet(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes helmet: " + itemName);
            Helmet helmet_temp = (Helmet)pItem;
            if(pItem.getMagicSetter()==1){//armor class modified
                this._helmetMagic = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to armor class: " + _helmetMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter()== 4){//wisdom modifier
                this._helmetWisdom = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to widom modifier: " + _helmetWisdom);
                //no need to calculate wisdom modifier it is done on teh fly every time.
            }
            else if(pItem.getMagicSetter()== 6){//intelligence modifier
                this._helmetIntelligence = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Helmet magic to intelligence modifier: " + _helmetIntelligence);
                //no need to calculate intellegence modifier it is done on the fly every time.
            }
        }//end helmet take off
        else if(itemName.equalsIgnoreCase("longbow")){//range long bow take off
            setIsRange(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes longbow: " + itemName);
            LongBow longbow_temp = (LongBow)pItem;
            this._bowMagic = 0;
            OutputConsole.GetOutputConsoleInstance().Write("LongBow magic to attack bonus and damage bonus: " + _bowMagic);
            calculateAttackBonus();//attack and damage bonus
            calculateArmorClass();
        }//end range long bow take off
        else if (itemName.equalsIgnoreCase("longsword")){//begin melee long sword take off
            setIsMelee(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes longsword: " + itemName);
            LongSword longsword_temp = (LongSword)pItem;
            this._swordMagic = 0;
            OutputConsole.GetOutputConsoleInstance().Write("LongSword magic to attack bonus and damage bonus: " + _swordMagic);
            calculateAttackBonus();
            calculateArmorClass();
        }//end melee long sword take off
        else if(itemName.equalsIgnoreCase("ring")){//begin ring take off
            setIsRing(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes ring: " + itemName);
            Ring ring_temp = (Ring)pItem;
            if(pItem.getMagicSetter() == 1){//armor class modified
                this._ringMagic = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to armor class: " + _ringMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter() == 3){//strength modifier
                this._ringStrength =0;
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to strength modifier: " + _ringStrength);
                //no need to calculate strenght modifier it is calculated on teh fly every time
                calculateArmorClass();
                calculateAttackBonus();
            }
            else if(pItem.getMagicSetter() == 4){//wisdom modifier
                this._ringWisdom =0;
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to wisdom modifier: " + _ringWisdom);
                //no need to calculate wisdom modifier it is calculated on teh fly every time.
            }
            else if(pItem.getMagicSetter() == 5){//charisma modifier
                this._ringCharisma = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Ring magic to charisma modifier: " + _ringCharisma);
                //no need to calculate charisma modifier it is calculated on teh fly every time.
            }            
        }//end ring take off
        else if(itemName.equalsIgnoreCase("HeavyShield")|| itemName.equalsIgnoreCase("Buckler")|| itemName.equalsIgnoreCase("TowerShield")){//begin shield take off
            setIsShield(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes shield: " + itemName);
            Shield shield_temp  = (Shield)pItem;
            this.setShieldModifier(0);
            this._shieldMagic = 0;
            OutputConsole.GetOutputConsoleInstance().Write("Shield magic to armor class: " + _shieldMagic);
            calculateArmorClass();
        }//end shield take off
        else if(itemName.equalsIgnoreCase("gloves")){//begin gloves take off
            setIsGloves(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes gloves: " + itemName);
            Gloves gloves_temp = (Gloves)pItem;
            // no bonus no magic no effect on anything
        }//end gloves take off
        else if(itemName.equalsIgnoreCase("bracer")){//begin bracer take off
            setIsBracer(false);
            OutputConsole.GetOutputConsoleInstance().Write("character removes bracer: " + itemName);
            Bracer bracer_temp = (Bracer)pItem;
            if(pItem.getMagicSetter()==1){//armor class modified
                this._bracerMagic = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Bracer magic to armor class: " + _bracerMagic);
                calculateArmorClass();
            }
            else if(pItem.getMagicSetter()== 3){//strength modifier
                this._bracerStrength = 0;
                OutputConsole.GetOutputConsoleInstance().Write("Bracer magic to strength modifier: " + _bracerStrength);
                calculateArmorClass();
                calculateAttackBonus();
                //no need to caulculate the strenght modifier it is calculated ont the fly every time.
            }
        }//end bracer take off
    }//end remove item method
    
    /**
     * To get the strength modifier and return a integer value of the strength modifier.
     * This is a public function called get strength modifier.
     * Influence of magic items: belt, ring, bracer
     * @return integer value of strength modifier.
     * @author khush
     */
    public int getStrengthModifier(){
        this._strengthModifier = calculateModifier(_strength) + _beltMagic +_ringStrength +_bracerStrength;
        OutputConsole.GetOutputConsoleInstance().Write("TotStrengthMod "+_strengthModifier+" = strengthmod "+ calculateModifier(_strength)+"+ beltmagic " + _beltMagic+ "+ ringmagic "+ _ringStrength+"+ bracermagic "+_bracerStrength );
        return this._strengthModifier;
    }
/**
 * getter method for dexterity modifier,
 * this calculates total value based on actual ability score + all magic influences if any every time.
 * Influence of magic items: boot.
 * @return integer value of dexterity modifier
 * @author khush
 */
    public int getDexterityModifier(){
        this._dexterityModifier = calculateModifier(_dexterity)+ _bootDexterity;
        OutputConsole.GetOutputConsoleInstance().Write("TotDexterityMod "+_dexterityModifier+" = dexteritymod "+calculateModifier(_dexterity)+" + bootmagic "+ _bootDexterity);
        return this._dexterityModifier;
    }
/**
 * getter method for wisdom modifier,
 * this calculates total value based on actual ability score + all magic influences if any every time.
 * Influence of magic items: ring, helmet.
 * @return integer value of wisdom modifier
 * @author khush
 */
    public int getWisdomModifier(){
        this._wisdomModifier = calculateModifier(_wisdom) + _ringWisdom +_helmetWisdom;
        return this._wisdomModifier;
    }
/**
 * getter method for charisma modifier,
 * this calculates total value based on actual ability score + all magic influences if any every time.
 * Influence of magic items: ring.
 * @return integer value of charisma modifier
 * @author khush
 */
    public int getCharismaModifier(){
        this._charismaModifier = calculateModifier(_charisma) + _ringCharisma;
        return this._charismaModifier;
    }
/**
 * getter method for intelligence modifier,
 * this calculates total value based on actual ability score + all magic influences if any every time.
 * Influence of magic items: helmet.
 * @return integer value of intelligence modifier
 * @author khush
 */
    public int getIntelligenceModifier(){
        this._intelligenceModifier = calculateModifier(_intelligence) + _helmetIntelligence;
        return this._intelligenceModifier;
    }
/**
 * getter method for constitution modifier,
 * this calculates total value based on actual ability score + all magic influences if any every time.
 * Influence of magic items: none.
 * @return integer value of constitution modifier
 * @author khush
 */
    public int getConstitutionModifier(){
        return calculateModifier(_constitution);
    }
}//end class

