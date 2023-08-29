/*
 * Class Name : PropertyOwnerLogin
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : 1. To check username and password are same or not
 *              2. Get particular Property Owner if username and password are correct
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PropertyOwnerLogin extends JFrame implements ActionListener, KeyListener {
    public static PropertyOwner po = null;
    private JTextField username;
    private JPasswordField password;
    private JLabel bannerLbl, userLbl, passwordLbl;
    private JButton loginButton, backButton;
    PropertyOwner owner = new PropertyOwner();
    public PropertyOwnerLogin() {
        super("PROPERTY OWNER LOGIN");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

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

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        loginButton.setLocation(400, 200);
        loginButton.setSize(120, 40);
        loginButton.addActionListener(this);
        panel.add(loginButton);

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

    private int checkUsernamePasswordSame(String username, String password) {
        int num = 0;
        for (PropertyOwner po : owner.readOwnerList()) {
            if (po.getUsername().equals(username) && po.getPassword().equals(password)) {
                num = 1;
                break;
            } else if (po.getUsername().equals(username) && !po.getPassword().equals(password)) {
                num = 0;
                break;
            } else if (!po.getUsername().equals(username)) {
                num = -1;
            }
        }
        return num;
    }

    private PropertyOwner loginUser(String username, String password) {
        for (PropertyOwner po : owner.readOwnerList()){
            if (po.getUsername().equals(username) && po.getPassword().equals(password)) {
                return po;
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
                    po = loginUser(username.getText(), pass);
                    userLbl.setForeground(Color.BLACK);
                    passwordLbl.setForeground(Color.BLACK);
                    this.dispose();
                    new PropertyOwnerHomePage();
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
            new PropertyOwnerLandingPage();
        }
    }

    public void keyTyped(KeyEvent eve) {}

    public void keyPressed(KeyEvent eve) {}

    public void keyReleased(KeyEvent eve) {
        if (eve.getSource() == username) {
            username.setText(username.getText().toLowerCase());
        }
    }
}
