/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.Random;

/**
 *This class holds all the dice values generated and used in this game. Build 2
 * @author khushnuma
 * @version 1.0.0
 */
public final class Dice {
   
/**
 * method to generate a random value between 1 and 10.
 * @return randomInt is a random number between 1 and 10.
 */
    public static int d10Dice(){
        Random randomGenerator = new Random();
        int randomInt = 0;
        while (randomInt <1){
           randomInt = randomGenerator.nextInt(11);
        }
        OutputConsole.GetOutputConsoleInstance().Write("D10 Dice: "+ randomInt);
        return randomInt;
    }
/**
 * method to generate a random value between 1 and 8.
 * @return randomInt is a random number between 1 and 8.
 */
    public static int d8Dice(){
        Random randomGenerator = new Random();
        int randomInt = 0;
        while (randomInt <1){
           randomInt = randomGenerator.nextInt(9);
        }
        //OutputConsole.GetOutputConsoleInstance().Write("D8 Dice: "+ randomInt);
        return randomInt;
    }
/**
 * method to generate a random value between 1 and 6.
 * @return randomInt is a random number between 1 and 6.
 */
    public static int d6Dice(){
        Random randomGenerator = new Random();
        int randomInt = 0;
        while (randomInt <1){
           randomInt = randomGenerator.nextInt(7);
        }
        return randomInt;
    }
/**
 * method to generate a random value between 1 and 20.
 * @return randomInt is a random number between 1 and 20.
 */
    public static int d20Dice(){
        Random randomGenerator = new Random();
        int randomInt = 0;
        while (randomInt <1){
           randomInt = randomGenerator.nextInt(21);
        }
        //OutputConsole.GetOutputConsoleInstance().Write("D20 Dice: "+ randomInt);
        return randomInt;
    }

}//end class
