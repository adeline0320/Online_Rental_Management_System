/*
 * Class Name : PropertyOwnerHomePage
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : Property owner can choose to edit profile or view properties or logout
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PropertyOwnerHomePage extends JFrame implements ActionListener {
    private JButton editProfile, PropertyPage, Reports, logout;

    public PropertyOwnerHomePage() {
        super("PROPERTY OWNER HOME PAGE");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // edit profile button constructor
        editProfile = new JButton("Edit Profile");
        panel.add(editProfile);
        editProfile.setBounds(100, 50, 200, 50);
        editProfile.addActionListener(this);

        // Properties button constructor
        PropertyPage = new JButton("Properties");
        panel.add(PropertyPage);
        PropertyPage.setBounds(100, 100, 200, 50);
        PropertyPage.addActionListener(this);

        // Reports button constructor
        Reports = new JButton("Reports");
        panel.add(Reports);
        Reports.setBounds(100, 150, 200, 50);
        Reports.addActionListener(this);

        // logout button constructor
        logout = new JButton("Logout");
        panel.add(logout);
        logout.setBounds(100, 200, 200, 50);
        logout.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == editProfile) {
            this.dispose();
            new PropertyOwnerEditProfile();
        }
        if (eve.getSource() == PropertyPage) {
            this.dispose();
            new PropertiesPage();
        }
        if (eve.getSource() == Reports) {
            this.dispose();
            ArrayList<Property>propList = Property.readPropertiesList();
            ReportGUI report = new ReportGUI(propList);
            //report.createGui();
        }
        if (eve.getSource() == logout) {
            this.dispose();
            JOptionPane.showMessageDialog(null, "Hope to see you again!");
            new Main();
            PropertyOwnerLogin.po = null;
        }
    }
}