/*
 * Class Name : PotentialTenantRegister
 * Coder      : Chee Wan Ying
 * Purpose    : 1. To make sure all the textfield and passwordfield costructor is filled and all the radiobutton is selected.
 *              2. To check new potential tenant's username is exist or not.
 *              3. To check the password and confirm password are the same.
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class PotentialTenantRegister extends JFrame implements ActionListener, KeyListener {
    private JTextField username, fullName, contactNum;
    private JPasswordField password, confirmPassword;
    private JLabel bannerLbl, instruction, userLbl, fullLbl, passwordLbl, conPasswordLbl, genderLbl, contactNumLbl, noticeNumberOnly;
    private ButtonGroup bg;
    private JRadioButton male, female;
    private JButton registerButton, backButton;
    static PotentialTenant tenant = new PotentialTenant();
    public PotentialTenantRegister() {
        super("TENANT REGISTER");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("WELCOME!!");
        bannerLbl.setBounds(90, 10, 350, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // instruction label constructor
        instruction = new JLabel("Please fill in your information to register.");
        instruction.setBounds(20, 75, 400, 20);
        instruction.setFont(new Font("Verdana", Font.PLAIN, 17));
        panel.add(instruction);

        // username label constructor
        userLbl = new JLabel("Username: ");
        userLbl.setBounds(20, 120, 150, 20);
        userLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(userLbl);

        // username textfield constructor
        username = new JTextField();
        username.setBounds(220, 120, 250, 20);
        username.addKeyListener(this);
        panel.add(username);

        // password label constructor
        passwordLbl = new JLabel("Password: ");
        passwordLbl.setBounds(20, 150, 150, 20);
        passwordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(passwordLbl);

        // password passwordfield constuctor
        password = new JPasswordField();
        password.setBounds(220, 150, 250, 20);
        panel.add(password);

        // confirm password label constructor
        conPasswordLbl = new JLabel("Confirm Password: ");
        conPasswordLbl.setBounds(20, 180, 220, 20);
        conPasswordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(conPasswordLbl);

        // confirm password passwordfield constuctor
        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(220, 180, 250, 20);
        panel.add(confirmPassword);

        // full name label constructor
        fullLbl = new JLabel("Full Name: ");
        fullLbl.setBounds(20, 210, 150, 20);
        fullLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(fullLbl);
  
        // full name textfield constuctor
        fullName = new JTextField();
        fullName.setBounds(220, 210, 250, 20);
        fullName.addKeyListener(this);
        panel.add(fullName);

        // contact number label constructor
        contactNumLbl = new JLabel("Contact Number: ");
        contactNumLbl.setBounds(20, 240, 220, 20);
        contactNumLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(contactNumLbl);

        // contact number textfield constructor
        contactNum = new JTextField();
        contactNum.setBounds(220, 240, 250, 20);
        contactNum.addKeyListener(this);
        panel.add(contactNum);
		
        // Enter number only label constructor
        noticeNumberOnly = new JLabel("(*Please enter number only. WITHOUT DASH)");
        noticeNumberOnly.setBounds(20, 260, 450, 20);
        noticeNumberOnly.setFont(new Font("Monospaced", Font.PLAIN, 15));
        panel.add(noticeNumberOnly);

        // gender label constructor
        genderLbl = new JLabel("Gender: ");
        genderLbl.setBounds(20, 300, 150, 20);
        genderLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(genderLbl);

        // male and female radiobutton constructor
        bg = new ButtonGroup();
        male = new JRadioButton("Male");
        male.setFont(new Font("Monospaced", Font.PLAIN, 18));
        male.setBounds(220, 300, 100, 20);
        female = new JRadioButton("Female");
        female.setFont(new Font("Monospaced", Font.PLAIN, 18));
        female.setBounds(320, 300, 100, 20);
        bg.add(male);
        panel.add(male);
        bg.add(female);
        panel.add(female);
        
        // register button constructor
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        registerButton.setLocation(350, 350);
        registerButton.setSize(120, 40);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // back button constructor
        backButton = new JButton("Back");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        backButton.setLocation(20, 350);
        backButton.setSize(120, 40);
        backButton.addActionListener(this);
        panel.add(backButton);

        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Coder : Chee Wan Ying
    // To check textfield is filled or not.
    public static boolean checkJTextFieldFilled(JTextField str, JLabel lbl) {
        if (!str.getText().equals("")) {
            lbl.setForeground(Color.BLACK);
            return true;
        } else {
            lbl.setForeground(Color.RED);
            return false;
        }
    }

    // Coder : Chee Wan Ying
    // To check passwordfield is filled or not.
    public static boolean checkPasswordFilled(JPasswordField p, JLabel lbl) {
        char[] pass = p.getPassword();
        if (pass.length != 0) {
            lbl.setForeground(Color.BLACK);
            return true;
        } else {
            lbl.setForeground(Color.RED);
            return false;
        }
    }

    // Coder : Chee Wan Ying
    // To check both passwordfield are same or not.
    public static boolean checkConsistentPassword(JPasswordField p1, JPasswordField p2, JLabel lbl1, JLabel lbl2) {
        char[] pass = p1.getPassword();
        char[] confPass = p2.getPassword();
        if (Arrays.equals(pass, confPass)) {
            lbl1.setForeground(Color.BLACK);
            lbl2.setForeground(Color.BLACK);
            return true;
        } else {
            lbl1.setForeground(Color.RED);
            lbl2.setForeground(Color.RED);
            return false;
        }
    }

    // Coder : Chee Wan Ying
    // To check radiobutton is selected or not.
    public static boolean checkGenderSelected(JRadioButton m, JRadioButton f, JLabel l) {
        if (m.isSelected() || f.isSelected()) {
            l.setForeground(Color.BLACK);
            return true;
        } else {
            l.setForeground(Color.RED);
            return false;
        }
    }

    // Coder : Chee Wan Ying
    // Return char for the selected radiobutton.
    public static char getUserGender(JRadioButton m) {
        if (m.isSelected()) {
            return 'M';
        } else {
            return 'F';
        }
    }

    // Coder : Chee Wan Ying
    // To check username is exist or not.
    public static boolean checkUsernameExist(String u) {
        for (PotentialTenant t : tenant.readTenantList()){
            if (t.getUsername().equals(u)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == registerButton) {
            if (!checkJTextFieldFilled(username, userLbl)) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty. Please enter a username.");
            }
            if (!checkPasswordFilled(password, passwordLbl)) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty. Please enter password.");
            }
            if (!checkPasswordFilled(confirmPassword, conPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "Confirm Password cannot be empty. Please enter confirm password.");
            }
            if (!checkConsistentPassword(password, confirmPassword, passwordLbl, conPasswordLbl)) {
                JOptionPane.showMessageDialog(null, "The Password and Password Confirmation do not match.");
            }
            if (!checkJTextFieldFilled(fullName, fullLbl)) {
                JOptionPane.showMessageDialog(null, "Full Name cannot be empty. Please enter your full name.");
            }
            if (!checkJTextFieldFilled(contactNum, contactNumLbl)) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty. Please enter your contact number.");
            }
            if (!checkGenderSelected(male, female, genderLbl)) {
                JOptionPane.showMessageDialog(null, "Gender is not selected. Please select your gender.");
            }
            if (checkJTextFieldFilled(username, userLbl) && checkPasswordFilled(password, passwordLbl) 
                && checkPasswordFilled(confirmPassword, conPasswordLbl) 
                && checkConsistentPassword(password, confirmPassword, passwordLbl, conPasswordLbl) 
                && checkJTextFieldFilled(fullName, fullLbl) && checkJTextFieldFilled(contactNum, contactNumLbl) 
                && checkGenderSelected(male, female, genderLbl)) {
                if (checkUsernameExist(username.getText())) {
                    JOptionPane.showMessageDialog(null, "Congratulations. You have registered this system. The acceptance of your registration will take 1 working day.\nPlease login with your username and password after your registration has been accepted.");
                    String pass = String.valueOf(password.getPassword());   
                    PotentialTenant tenant = new PotentialTenant(fullName.getText(), contactNum.getText(), getUserGender(male),username.getText(), pass);
                    UserControl uc = new UserControl();
                    try {
                        uc.pendingUserList(tenant);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    username.setText("");
                    userLbl.setForeground(Color.BLACK);
                    fullName.setText("");
                    password.setText("");
                    confirmPassword.setText("");
                    contactNum.setText("");
                    bg.clearSelection();                    
                } else {
                    JOptionPane.showMessageDialog(null, "Register failed. Username exist. Please change another username.");
                    username.setText("");
                    userLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == backButton) {
            this.dispose();
            new PotentialTenantLogin();
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
            if (eve.getKeyChar() >= 'A' && eve.getKeyChar() <= 'Z' || eve.getKeyChar() >= 'a' && eve.getKeyChar() <= 'z' 
                || eve.getKeyChar() == '\b' || eve.getKeyChar() == ' ') {
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