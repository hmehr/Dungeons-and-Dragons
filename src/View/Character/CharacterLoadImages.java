/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Character;

import java.io.File;
import javax.swing.JComboBox;

/**
 * This is a public class to load the character images.
 * @author m_niza
 */
public class CharacterLoadImages
{
    File _dir;
    String _dirPath;
    JComboBox _imageListCombo;

    /**
     * This is a public constructor called character load images.
     * To set the direct path, direct and image list combo.
     * @param pDirPath the path of the direct
     * @param pImageListCombo 
     */
    public CharacterLoadImages(String pDirPath, JComboBox pImageListCombo)
    {
        this._dirPath = pDirPath;
        this._dir = new File(_dirPath);
        this._imageListCombo = pImageListCombo;
    }

    void getFileNameList()
    {
        String[] children = _dir.list();

        if (children == null)
        {
            // Either dir does not exist or is not a directory
        }
        else
        {
            for (int i=0; i<children.length; i++)
            {
                // Get filename of file or directory
                //String filename = children[i];
                //CharacterJFrame
                //_imageListCombo.addItem(children[i]);
                _imageListCombo.addItem(children[i]);
            }
        }

        
    }


}
