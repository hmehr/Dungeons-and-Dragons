/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Map;

import Model.Map.StaticMapCellList;
import Controls.CustomListView.ListItemModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 * This is the combo box action listener implementation.
 * set the m_list.
 * When invoke the actionPerformed, instantiated a itemListModel. 
 * Then do the setModel.
 * 
 * @author yukywang
 */
public class ComboBoxActionListener implements ActionListener
{
    private JList m_List = null;
    /**
     * It initializes by taking the references of a JList
     * @param pList Reference of a JList.
     */
    public ComboBoxActionListener(JList pList)
    {
        m_List = pList;
    }
    
    /**
     * Handler for the combo box.
     * @param e Action event.
     */
    public void actionPerformed(ActionEvent e)
    {
        JComboBox comboBox = (JComboBox) e.getSource();
        String className = (String) comboBox.getSelectedItem();
        ListItemModel itemListModel = new ListItemModel(StaticMapCellList.getInstance().getItemCollection(className));
        m_List.setModel(itemListModel);
    }
}
