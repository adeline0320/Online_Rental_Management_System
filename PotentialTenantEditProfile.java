/*
 * Class Name : PotentialTenantChangePassword
 * Coder      : Chee Wan Ying
 * Purpose    : Allow Potential Tenant to edit profile
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class PotentialTenantEditProfile extends JFrame implements ActionListener, KeyListener {
    public PotentialTenant t = PotentialTenantLogin.t;
    private JTextField username, fullName, contactNum;
    private JLabel bannerLbl, userLbl, fullLbl, genderLbl, contactNumLbl, noticeNumberOnly;
    private ButtonGroup bg;
    private JRadioButton male, female;
    private JButton changePasswordButton, saveButton, cancelButton;
    PotentialTenant tenant = new PotentialTenant();
    ArrayList<PotentialTenant> tenantList = tenant.readTenantList();
    int index = 0;

    public PotentialTenantEditProfile() {
        super("ACCOUNT DETAILS");
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("-- Edit Profile --");
        bannerLbl.setBounds(50, 10, 500, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // username label constructor
        userLbl = new JLabel("Username: ");
        userLbl.setBounds(20, 90, 150, 20);
        userLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(userLbl);

        // username textfield constructor
        username = new JTextField(t.getUsername());
        username.setBounds(270, 90, 250, 20);
        username.addKeyListener(this);
        panel.add(username);

        // full name label constructor
        fullLbl = new JLabel("Full Name: ");
        fullLbl.setBounds(20, 120, 150, 20);
        fullLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(fullLbl);
  
        // full name textfield constuctor
        fullName = new JTextField(t.getFullName());
        fullName.setBounds(270, 120, 250, 20);
        panel.add(fullName);

        // contact number label constructor
        contactNumLbl = new JLabel("Contact Number: ");
        contactNumLbl.setBounds(20, 150, 220, 20);
        contactNumLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(contactNumLbl);

        // contact number textfield constructor
        contactNum = new JTextField(t.getContactNum());
        contactNum.setBounds(270, 150, 250, 20);
        contactNum.addKeyListener(this);
        panel.add(contactNum);
		
        // Enter number only label constructor
        noticeNumberOnly = new JLabel("(*Please enter number only. WITHOUT DASH)");
        noticeNumberOnly.setBounds(20, 170, 450, 20);
        noticeNumberOnly.setFont(new Font("Monospaced", Font.PLAIN, 15));
        panel.add(noticeNumberOnly);

        // gender label constructor
        genderLbl = new JLabel("Gender: ");
        genderLbl.setBounds(20, 200, 150, 20);
        genderLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(genderLbl);

        // male and female radiobutton constructor
        bg = new ButtonGroup();
        male = new JRadioButton("Male");
        male.setFont(new Font("Monospaced", Font.PLAIN, 18));
        male.setBounds(270, 200, 100, 20);
        female = new JRadioButton("Female");
        female.setFont(new Font("Monospaced", Font.PLAIN, 18));
        female.setBounds(370, 200, 100, 20);
        bg.add(male);
        panel.add(male);
        bg.add(female);
        panel.add(female);
        gender();

        // change password button constructor
        changePasswordButton= new JButton("Change Password");
        changePasswordButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        changePasswordButton.setLocation(145, 240);
        changePasswordButton.setSize(240, 40);
        changePasswordButton.addActionListener(this);
        panel.add(changePasswordButton);
        
        // save button constructor
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        saveButton.setLocation(400, 300);
        saveButton.setSize(120, 40);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        // cancel button constructor
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelButton.setLocation(20, 300);
        cancelButton.setSize(120, 40);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setSize(550, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Coder : Chee Wan Ying
    // To return potential tenant's gender and selected the gender radio button constuctor
    private void gender() {
        if (t.getGender() == 'M') {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == changePasswordButton) {
            new PotentialTenantChangePassword();
        } else if (eve.getSource() == saveButton) {
            if (!PotentialTenantRegister.checkJTextFieldFilled(username, userLbl)) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty. Please enter a username.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(fullName, fullLbl)) {
                JOptionPane.showMessageDialog(null, "Full Name cannot be empty. Please enter your full name.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(contactNum, contactNumLbl)) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty. Please enter your contact number.");
            }
            if (PotentialTenantRegister.checkJTextFieldFilled(username, userLbl) && PotentialTenantRegister.checkJTextFieldFilled(fullName, fullLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(contactNum, contactNumLbl)) {
                if (PotentialTenantRegister.checkUsernameExist(username.getText()) || t.getUsername().equals(username.getText())) {
                    JOptionPane.showMessageDialog(null, "Your new profile is saved.");
                    for (int i = 0; i < tenantList.size(); i++) {
                        if (t.getUsername().equals(tenantList.get(i).getUsername())) {
                            index = i;
                        }
                    }
                    tenantList.get(index).setUsername(username.getText());
                    tenantList.get(index).setFullName(fullName.getText());
                    tenantList.get(index).setContactNumber(contactNum.getText());
                    tenantList.get(index).setGender(PotentialTenantRegister.getUserGender(male));
                    tenant.updateFile(tenantList);
                    userLbl.setForeground(Color.BLACK);
                } else {
                    JOptionPane.showMessageDialog(null, "Username exist. Please change another username.");
                    userLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == cancelButton) {
            this.dispose();
            new PotentialTenantHomePage();
        }
    }

    public void keyTyped(KeyEvent eve) {}

    // Coder : Chee Wan Ying
    // Only allow potential tenant key in numbers at contact number textfield constructor and
    // Only allow potential tenant key in alphabets at full name textfield constructor
    public void keyPressed(KeyEvent eve) {
        if (eve.getSource() == contactNum) {
            if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                contactNum.setEditable(true);
            } else {
                contactNum.setEditable(false);
            }
        }
        if (eve.getSource() == fullName) {
            if (eve.getKeyChar() >= 'A' && eve.getKeyChar() <= 'Z' || eve.getKeyChar() >= 'a' && eve.getKeyChar() <= 'z' || eve.getKeyChar() == '\b' || eve.getKeyChar() == ' ') {
                fullName.setEditable(true);
            } else {
                fullName.setEditable(false);
            }     
        }
    }

    // Coder : Chee Wan Ying
    // To make sure username are all in lower case.
    public void keyReleased(KeyEvent eve) {
        if (eve.getSource() == username) {
            username.setText(username.getText().toLowerCase());
        } 
    }
}