/*
 * Class Name : PropertyOwnerLandingPage
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : To let property owner choose to register or login to their account
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PropertyOwnerLandingPage extends JFrame implements ActionListener {
    private JButton register, login, cancel;

    public PropertyOwnerLandingPage() {
        super("PROPERTY OWNER LANDING PAGE");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // register button constructor
        register = new JButton("Register");
        panel.add(register);
        register.setBounds(50, 50, 200, 50);
        register.addActionListener(this);

        // login button constructor
        login = new JButton("Login");
        panel.add(login);
        login.setBounds(50, 100, 200, 50);
        login.addActionListener(this);

        // back button constructor
        cancel = new JButton("Back");
        panel.add(cancel);
        cancel.setBounds(50, 150, 200, 50);
        cancel.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == register) {
            this.dispose();
            new PropertyOwnerRegister();
        } else if (eve.getSource() == login) {
            this.dispose();
            new PropertyOwnerLogin();
        } else if (eve.getSource() == cancel) {
            this.dispose();
            new Main();
        }
    }
}
