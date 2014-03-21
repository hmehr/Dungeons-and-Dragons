/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectMap.java
 *
 * Created on Oct 16, 2011, 8:18:10 PM
 */
package View;

import Model.Map.Map;
import Model.OutputConsole;
import View.Game.GameJFrame;
import View.Map.MapView;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *This class is responsible to provide the user with a frame from which s/he can select a map for the game.
 * 
 * @author Hamid
 */
public class SelectMapForm extends javax.swing.JFrame
{

    String _savePath;
    Model.Character _character;
    ArrayList<MapView> _mapViews;
    ArrayList<Map> _maps;
    Map _map;

    /** Creates new form SelectMap */
    public SelectMapForm(Model.Character pCharacter)
    {
        initComponents();
        _savePath = System.getProperty("user.dir");
        _savePath += "/src/Maps/";
        _character = pCharacter;
        fetchMapsFromDirectory();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        _mapView = new MapView();
        jLabel1 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(SelectMapForm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel2.foreground")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 160, -1));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 570, -1, -1));

        _mapView.setName("_mapView"); // NOI18N
        getContentPane().add(_mapView, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 300, 250));

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Creates a new GameJFrame object and set the visibility of the current frame to false.
     * 
     * @param evt Mouse Click Event
     * @author Hamid
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        GameJFrame newGameJFrame = new GameJFrame(_character, _map);
        newGameJFrame.setSize(60*_map.getMapCols(), 60*_map.getMapRows());
        newGameJFrame.setVisible(true);
        newGameJFrame.setLocationRelativeTo(this);
        this.setVisible(false);
       // newGameJFrame.getGame().playRound();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Populate the jPanel with the respective map which user selects from the ComboBox.
     * 
     * 
     * @param evt ComboBox Event parameter.
     * @author Hamid
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox1ActionPerformed
    {//GEN-HEADEREND:event_jComboBox1ActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        _map = (Map) _maps.get(cb.getSelectedIndex()); 
        ((MapView) _mapView).setMapModel(_map);
        _mapView.setSize(420, 340);
    }//GEN-LAST:event_jComboBox1ActionPerformed


    /**
     * Fetch map objects from the saved file on the disk.
     * 
     * @author Hamid
     */
    public void fetchMapsFromDirectory()
    {

        _maps = new ArrayList<Map>();
        File directory = new File(_savePath);
        String filename[] = directory.list();
        for (int i = 0; i < filename.length; i++)
        {
            try
            {
                if (filename[i].indexOf(".dd") > 0)
                {
                    File file = new File(_savePath + filename[i]);
                    FileInputStream fin = new FileInputStream(file.getAbsoluteFile());
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    Map loadedMap = (Map) ois.readObject();
                    loadedMap.setMapFile(_savePath + filename[i]);
                    _maps.add(loadedMap);                    
                    jComboBox1.addItem(filename[i]);
                }
            } catch (Exception e)
            {
                OutputConsole.GetOutputConsoleInstance().WriteException("Error Occured: " + e.getMessage());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel _mapView;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
