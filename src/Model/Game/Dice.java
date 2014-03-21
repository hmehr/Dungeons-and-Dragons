/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Game;

import java.util.Random;

/**
 * This is the public class called Dice.
 * And it has two functions called Dice and roll.
 * @author eric
 */
public class Dice
{

    private int _min;
    private int _max;
    private Random _rand = null;

    /**
     * This is a public constructor that set the minium and maximum number of the dice.
     * @param pMin the minium number of the dice.
     * @param pMax the maximum number of the dice.
     */
    public Dice(int pMin, int pMax)
    {
        _min = pMin;
        _max = pMax;
        _rand = new Random();
    }

    /**
     * This is a public function called roll.
     * To calculate the number of the roll.
     * @return integer type value.
     */
    public int roll()
    {
        int n = _max - _min + 1;
        int i = _rand.nextInt(n);
        return _min + i;
    }
}
