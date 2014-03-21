/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Character;

/**
 * This is a ability tester class.
 * @author khushnuma
 */
public class AbilityTester {
    
    /**
     * default constructor
     */
    public AbilityTester(){}
    
    /**
     * test class
     * @param args 
     */
     public static void main(String[] args)
    {
        AbilityScoreGeneration  abs = new AbilityScoreGeneration();
        int[] result = abs.createAbilityList();

    }

}
