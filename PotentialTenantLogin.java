/*
 * Class Name : PotentialTenantLogin
 * Coder      : Chee Wan Ying
 * Purpose    : 1. To check username and password are same or not
 *              2. Get particular Potential Tenant if username and password are correct
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PotentialTenantLogin extends JFrame implements ActionListener, KeyListener {
    public static PotentialTenant t = null;
    private JTextField username;
    private JPasswordField password;
    private JLabel bannerLbl, userLbl, passwordLbl, instruction;
    private JButton loginButton, registerButton, cancelButton;
    PotentialTenant tenant = new PotentialTenant();
    public PotentialTenantLogin() {
        super("POTENTIAL TENANT LOGIN");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("TENANT LOGIN");
        bannerLbl.setBounds(55, 10, 500, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // instruction label constructor
        instruction = new JLabel("Please fill in username & password to login.");
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
        passwordLbl.setBounds(20, 160, 150, 20);
        passwordLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(passwordLbl);

        // password passwordfield constuctor
        password = new JPasswordField();
        password.setBounds(220, 160, 250, 20);
        panel.add(password);

        // register button constructor
        registerButton = new JButton("No account? CLICK HERE TO CREATE ONE!");
        registerButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        registerButton.setBounds(20, 200, 370, 30);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        // cancel button constructor
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelButton.setLocation(20, 250);
        cancelButton.setSize(120, 40);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        // login button constructor
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        loginButton.setLocation(400, 250);
        loginButton.setSize(120, 40);
        loginButton.addActionListener(this);
        panel.add(loginButton);
        
        setSize(550, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Coder : Chee Wan Ying
    // Return 1 if username and password are correct, return 0 if password incorrect and
    // return -1 if username not exist.
    private int checkUsernamePasswordSame(String username, String password) {
        int num = 0;
        for (PotentialTenant t : tenant.readTenantList()){
            if (t.getUsername().equals(username) && t.getPassword().equals(password)) {
                num = 1;
                break;
            } else if (t.getUsername().equals(username) && !t.getPassword().equals(password)) {
                num = 0;
                break;
            } else if (!t.getUsername().equals(username)) {
                num = -1;
            }
        }
        return num;
    }

    // Coder : Chee Wan Ying
    // Return Potential Tenant if username and password are correct
    private PotentialTenant loginUser(String username, String password) {
        for (PotentialTenant pt : tenant.readTenantList()) {
            if (pt.getUsername().equals(username) && pt.getPassword().equals(password)) {
                return pt;
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == registerButton) {
            this.dispose();
            new PotentialTenantRegister();
        } else if (eve.getSource() == cancelButton) {
            this.dispose();
            new Main();
            
        } else if (eve.getSource() == loginButton) {
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
                    t = loginUser(username.getText(), pass);
                    userLbl.setForeground(Color.BLACK);
                    passwordLbl.setForeground(Color.BLACK);
                    this.dispose();
                    new PotentialTenantHomePage();
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
        }
    }

    public void keyTyped(KeyEvent eve) {}

    public void keyPressed(KeyEvent eve) {}

    // Coder : Chee Wan Ying
    // To make sure username are all in lower case.
    public void keyReleased(KeyEvent eve) {
        if (eve.getSource() == username) {
            username.setText(username.getText().toLowerCase());
        }
    }
}
