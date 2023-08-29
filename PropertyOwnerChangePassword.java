/*
 * Class Name : PropertyOwnerChangePassword
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : Allow property owner to change their password
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PropertyOwnerChangePassword extends JFrame implements ActionListener {
    public PropertyOwner po = PropertyOwnerLogin.po;
    private JPasswordField oldPassword, newPassword, confirmPassword;
    private JLabel oldPasswordLbl, newPasswordLbl, conPasswordLbl;
    private JButton saveButton, backButton;
    PropertyOwner owner = new PropertyOwner();
    ArrayList<PropertyOwner> ownerList = owner.readOwnerList();
    int index = 0;

    public PropertyOwnerChangePassword() {
        super("CHANGE PASSWORD");
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // current password label constructor
        oldPasswordLbl = new JLabel("Current Password: ");
        oldPasswordLbl.setBounds(20, 20, 250, 20);
        oldPasswordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(oldPasswordLbl);

        // current password passwordfield constuctor
        oldPassword = new JPasswordField();
        oldPassword.setBounds(270, 20, 250, 20);
        panel.add(oldPassword);

        // new password label constructor
        newPasswordLbl = new JLabel("New Password: ");
        newPasswordLbl.setBounds(20, 50, 250, 20);
        newPasswordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(newPasswordLbl);

        // new password passwordfield constuctor
        newPassword = new JPasswordField();
        newPassword.setBounds(270, 50, 250, 20);
        panel.add(newPassword);

        // confirm password label constructor
        conPasswordLbl = new JLabel("Confirm New Password: ");
        conPasswordLbl.setBounds(20, 80, 270, 20);
        conPasswordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(conPasswordLbl);

        // confirm password passwordfield constuctor
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(270, 80, 250, 20);
        panel.add(confirmPassword);

        // save button constructor
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        saveButton.setLocation(400, 140);
        saveButton.setSize(120, 40);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        // back button constructor
        backButton = new JButton("Back");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        backButton.setLocation(20, 140);
        backButton.setSize(120, 40);
        backButton.addActionListener(this);
        panel.add(backButton);

        setSize(550, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == saveButton) {
            if (!PotentialTenantRegister.checkPasswordFilled(oldPassword, oldPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter your current password.");
            }
            if (!PotentialTenantRegister.checkPasswordFilled(newPassword, newPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter your new password.");
            }
            if (!PotentialTenantRegister.checkPasswordFilled(confirmPassword, conPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter confirm password.");
            }
            if (!PotentialTenantRegister.checkConsistentPassword(newPassword, confirmPassword, newPasswordLbl, conPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "New Password and Confirm New Password do not match.");
            }
            if (PotentialTenantRegister.checkPasswordFilled(oldPassword, oldPasswordLbl) 
                && PotentialTenantRegister.checkPasswordFilled(newPassword, newPasswordLbl) 
                && PotentialTenantRegister.checkPasswordFilled(confirmPassword, conPasswordLbl) 
                && PotentialTenantRegister.checkConsistentPassword(newPassword, confirmPassword, newPasswordLbl, conPasswordLbl)) {
                for (int i = 0; i < ownerList.size(); i++) {
                    if (po.getPassword().equals(ownerList.get(i).getPassword())) {
                        index = i;
                    }
                }
                String oldPass = String.valueOf(oldPassword.getPassword());
                if (po.getPassword().equals(oldPass)) {
                    JOptionPane.showMessageDialog(null, "Password changed successfully.");
                    String newPass = String.valueOf(newPassword.getPassword());
                    po.setPassword(newPass);
                    ownerList.get(index).setPassword(newPass);
                    owner.updateFile(ownerList);
                    oldPasswordLbl.setForeground(Color.BLACK);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect password.");
                    oldPasswordLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == backButton) {
            this.dispose();
        }
    }
}