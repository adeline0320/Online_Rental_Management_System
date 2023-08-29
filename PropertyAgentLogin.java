/*
 * Class Name : PropertyAgentLogin
 * Coder      : Chee Wei Jae
 * Purpose    : 1. To check username and password are same or not
 *              2. Get particular Property Agent if username and password are correct
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PropertyAgentLogin extends JFrame implements ActionListener, KeyListener {
    public static PropertyAgent pa = null;
    private JTextField username;
    private JPasswordField password;
    private JLabel bannerLbl, userLbl, passwordLbl;
    private JButton loginButton, backButton;
    PropertyAgent agent = new PropertyAgent();
    public PropertyAgentLogin() {
        super("PROPERTY AGENT LOGIN");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("WELCOME BACK!");
        bannerLbl.setBounds(20, 10, 500, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // username label constructor
        userLbl = new JLabel("Username: ");
        userLbl.setBounds(20, 100, 150, 20);
        userLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(userLbl);

        // username textfield constructor
        username = new JTextField();
        username.setBounds(220, 100, 250, 20);
        username.addKeyListener(this);
        panel.add(username);

        // password label constructor
        passwordLbl = new JLabel("Password: ");
        passwordLbl.setBounds(20, 140, 150, 20);
        passwordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(passwordLbl);

        // password passwordfield constuctor
        password = new JPasswordField();
        password.setBounds(220, 140, 250, 20);
        panel.add(password);

        // login button constructor
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        loginButton.setLocation(400, 200);
        loginButton.setSize(120, 40);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        // back button constructor
        backButton = new JButton("Back");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        backButton.setLocation(20, 200);
        backButton.setSize(120, 40);
        backButton.addActionListener(this);
        panel.add(backButton);
        
        setSize(550, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Coder : Chee Wei Jae
    // Return 1 if username and password are correct, return 0 if password incorrect and
    // return -1 if username not exist.
    private int checkUsernamePasswordSame(String username, String password) {
        int num = 0;
        for (PropertyAgent pa : agent.readAgentList()) {
            if (pa.getUsername().equals(username) && pa.getPassword().equals(password)) {
                num = 1;
                break;
            } else if (pa.getUsername().equals(username) && !pa.getPassword().equals(password)) {
                num = 0;
                break;
            } else if (!pa.getUsername().equals(username)) {
                num = -1;
            }
        }
        return num;
    }

    // Coder : Chee Wei Jae
    // Return Property Tgentenant if username and password are correct
    private PropertyAgent loginUser(String username, String password) {
        for (PropertyAgent pa : agent.readAgentList()) {
            if (pa.getUsername().equals(username) && pa.getPassword().equals(password)) {
                return pa;
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == loginButton) {
            if (!PotentialTenantRegister.checkJTextFieldFilled(username, userLbl)) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty. Please enter a username.");
            }
            if (!PotentialTenantRegister.checkPasswordFilled(password, passwordLbl)) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty. Please enter password.");
            }
            if (PotentialTenantRegister.checkJTextFieldFilled(username, userLbl) && PotentialTenantRegister.checkPasswordFilled(password, passwordLbl)) {
                String pass = String.valueOf(password.getPassword());
                if (checkUsernamePasswordSame(username.getText(), pass) == 1) {
                    JOptionPane.showMessageDialog(null, "Welcome Back!!! " + username.getText());
                    pa = loginUser(username.getText(), pass);
                    userLbl.setForeground(Color.BLACK);
                    passwordLbl.setForeground(Color.BLACK);
                    this.dispose();
                    new PropertyAgentHomePage();
                } else if (checkUsernamePasswordSame(username.getText(), pass) == 0) {
                    JOptionPane.showMessageDialog(null, "Password incorrect.");
                    password.setText("");
                    userLbl.setForeground(Color.BLACK);
                    passwordLbl.setForeground(Color.RED);
                } else {
                    JOptionPane.showMessageDialog(null, "Username does not exist.");
                    username.setText("");
                    password.setText("");
                    userLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == backButton) {
            this.dispose();
            new PropertyAgentLandingPage();
        }
    }

    public void keyTyped(KeyEvent eve) {}

    public void keyPressed(KeyEvent eve) {}

    // Coder : Chee Wei Jae
    // To make sure username are all in lower case.
    public void keyReleased(KeyEvent eve) {
        if (eve.getSource() == username) {
            username.setText(username.getText().toLowerCase());
        }
    }
}
