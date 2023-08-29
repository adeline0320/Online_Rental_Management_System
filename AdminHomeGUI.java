/*Class Name : AdminHomeGUI
Purpose: To display Admin Home and work as View in MVC design pattern
Coder : Adeline Low Hui Min

*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AdminHomeGUI extends JFrame implements ActionListener
{
    String[] buttonName = new String[]{"Add Admin Account","Properties","Potential Tenant","Property Owner","Property Agent","Reports","Log Out"};
    AdminControl facade = new AdminControl();
    JFrame frame;
    Boolean login = AdminLogin.ad;

    public AdminHomeGUI() {
        createGUI();
    }

    public void createGUI() {
        frame = new JFrame("Admin Home Page");
        JLabel hpLabel = new JLabel("Admin Home Page",SwingConstants.CENTER);
        hpLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        JPanel homePanel = new JPanel(new GridLayout(4,2,10,10));
        
        homePanel.setBorder(BorderFactory.createLineBorder(Color.black));
       
        for(int i = 0 ; i < buttonName.length; i++) {
            JButton btn = new JButton(buttonName[i]);
            btn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
            homePanel.add(btn);
            btn.addActionListener(this);
        }
        
        frame.add(hpLabel,BorderLayout.NORTH);
        frame.add(homePanel,BorderLayout.CENTER);
        frame.setSize(700,700);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ev) {
        String event = ev.getActionCommand();
        if(event.equals("Add Admin Account")) {
            frame.dispose();
            facade.getAdmin();
        }
        else if(event.equals("Properties")) {
            frame.dispose();
            facade.getProperties();
        }
        else if(event.equals("Potential Tenant")) {
           frame.dispose();
           facade.getTenant();
        }
        else if(event.equals("Property Owner")) {
            frame.dispose();
            facade.getPropOwner();
        }
        else if(event.equals("Property Agent")) {
            facade.getPropAgent();
            frame.dispose();
        }
        else if(event.equals("Reports")) {
            frame.dispose();
            facade.getReport();
        }
        else if(event.equals("Log Out")) {
            int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to Logout?", "Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION) {
                login = false;
                frame.dispose();
                new Main();
            }
        }
    }
}
