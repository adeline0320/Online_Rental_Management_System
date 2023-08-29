/*Class : PropertyGetID
Purpose : To get ID from user input
*/

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class PropertyGetID extends JFrame implements ActionListener {
    public static int propID = 0;
    private JLabel idLbl;
    private JTextField id;
    private JButton backBtn, editBtn, delBtn;
    ArrayList<Property> propList = Property.readPropertiesList();
    
    //Coder : Chee Wei Jae
    public PropertyGetID() {
        super("Cyberjaya Online Rental Management System");
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        idLbl = new JLabel("Property ID: ");
        idLbl.setBounds(20, 20, 200, 20);
        idLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(idLbl);
  
        id = new JTextField();
        id.setBounds(220, 20, 250, 20);
        panel.add(id);

        editBtn = new JButton("EDIT");
        editBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        editBtn.setBounds(20, 60, 150, 50);
        editBtn.addActionListener(this);
        panel.add(editBtn);

        delBtn = new JButton("DELETE");
        delBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        delBtn.setBounds(340, 60, 150, 50);
        delBtn.addActionListener(this);
        panel.add(delBtn);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        backBtn.setBounds(20, 140, 150, 50);
        backBtn.addActionListener(this);
        panel.add(backBtn);

        setSize(550, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    //Coder : Chee Wei Jie
    //Purpose : To get property ID from user
    private int propertyID(String id) {
        int getIDindex = -1;
        ArrayList<Property> list = Property.readPropertiesList();
        for (int i = 0 ; i < list.size(); i++) {
            if (list.get(i).getPropertiesID().equals(id))
                getIDindex = i;
            }
        return getIDindex;
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == backBtn) {
            this.dispose();
            new PropertiesPage();
        }
        if (eve.getSource() == editBtn) {
            if (!PotentialTenantRegister.checkJTextFieldFilled(id, idLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter property's ID.");
            }
            if (PotentialTenantRegister.checkJTextFieldFilled(id, idLbl)) {
                propID = propertyID(id.getText());
                if (propID  >= 0) {
                    idLbl.setForeground(Color.BLACK);
                    this.dispose();
                    new PropertiesEdit();
                } else if (propID == -1) {
                    JOptionPane.showMessageDialog(null, "Invalid property ID");
                    idLbl.setForeground(Color.RED);
                }
            } 
        }
        if (eve.getSource() == delBtn) {
            if (!PotentialTenantRegister.checkJTextFieldFilled(id, idLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter property's ID.");
            }
            if (PotentialTenantRegister.checkJTextFieldFilled(id, idLbl)) {
                String deletepropID = id.getText();
                if (deletepropID != null) {
                    JOptionPane.showMessageDialog(null, "Successfully deleted.");
                    idLbl.setForeground(Color.BLACK);
                    try {
                        for(int i = 0; i < propList.size();i++) {
                            if(deletepropID.equals(propList.get(i).getPropertiesID())) {
                                propList.remove(i);
                            }
                        }
                        PropertiesControl.updateFile(propList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    deletepropID = null;
                    this.dispose();
                    new PropertiesPage();
                }
            } else if (propID == -1) {
                JOptionPane.showMessageDialog(null, "Invalid property ID");
                idLbl.setForeground(Color.RED);
            }
        }
    }
}