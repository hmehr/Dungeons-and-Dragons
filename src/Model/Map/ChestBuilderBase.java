/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Map;

/**
 * This is a public interface called treasure builder base.
 * @author eric
 */
public interface ChestBuilderBase
{
    /**
     * This is a function to build treasure.
     */
    public void buildTreasure();
    
    /**
     * This is a function to build magic effect of item in treasure chest.
     */
    public void buildMagicEffect();
    
}
