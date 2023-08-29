/*
 * Class Name : PotentialTenantHomePage
 * Coder      : Chee Wan Ying
 * Purpose    : Potential tenant can choose to edit profile or view properties or logout
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PotentialTenantHomePage extends JFrame implements ActionListener {
    private JLabel bannerLbl;
    private JButton editProfile, viewProperties, logout;

    public PotentialTenantHomePage() {
        super("TENANT HOME PAGE");
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("HOME");
        bannerLbl.setBounds(55, 10, 500, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // edit profile button constructor
        editProfile = new JButton("Edit Profile");  
        editProfile.setFont(new Font("Ink Free", Font.BOLD, 20));
        editProfile.setLocation(20, 90);
        editProfile.setSize(250, 40);
        editProfile.addActionListener(this);
        panel.add(editProfile);

        // view properties button constructor
        viewProperties = new JButton("View Properties");
        viewProperties.setFont(new Font("Ink Free", Font.BOLD, 20));
        viewProperties.setLocation(20, 150);
        viewProperties.setSize(250, 40);
        viewProperties.addActionListener(this);
        panel.add(viewProperties);

        // logout button constructor
        logout = new JButton("Logout");
        logout.setFont(new Font("Ink Free", Font.BOLD, 20));
        logout.setLocation(20, 210);
        logout.setSize(250, 40);
        logout.addActionListener(this);
        panel.add(logout);

        setSize(300, 310);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == editProfile) {
            this.dispose();
            new PotentialTenantEditProfile();
        } 
        if (eve.getSource() == viewProperties) {
            this.dispose();
            ArrayList<Property> list = Property.readPropertiesList();
            ArrayList<Property> temp = new ArrayList<>();
            for (Property val : list) {
                String status = val.getStatus();
                if (status.equals("Active")) {
                    temp.add(val);
                }
            }
            PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(temp);
            p.createGUI();
        }
        if (eve.getSource() == logout) {
            this.dispose();
            JOptionPane.showMessageDialog(null, "Hope to see you again!");
            PotentialTenantLogin.t = null;
            new Main();
        }
    }
}