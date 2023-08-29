/*
 * Class Name : PropertyAgentLandingPage
 * Coder      : Chee Wei Jae
 * Purpose    : To let property agent choose to register or login to their account
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PropertyAgentLandingPage extends JFrame implements ActionListener {
    private JButton register, login, cancel;

    public PropertyAgentLandingPage() {
        super("PROPERTY AGENT LANDING PAGE");

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
            new PropertyAgentRegister();
        } else if (eve.getSource() == login) {
            this.dispose();
            new PropertyAgentLogin();
        } else if (eve.getSource() == cancel) {
            this.dispose();
            new Main();
        }
    }
}
