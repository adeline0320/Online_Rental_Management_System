/* CLass: Main 
*  Purpose : To show main page of the program and run the program.
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main 
{
    private JFrame frame;
    private JPanel header,showUsers;
    private JLabel label;
    private JButton admin,pt,po,pa;
    PotentialTenant tenant = new PotentialTenant();
    PropertyAgent agent = new PropertyAgent();
    PropertyOwner owner = new PropertyOwner();
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        frame = new JFrame("Main Page");
        header = new JPanel(new BorderLayout());
        label = new JLabel("Welcome to Cyberjaya Online Rental Management System", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        header.add(label);
        
        showUsers = new JPanel(new GridLayout(2,2,10,10));
        
        admin = new JButton("Admin");
        admin.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new AdminLogin();
               frame.dispose();
           }
        });
        
        pt = new JButton("Potential Tenant");
        pt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tenant.readTenantList();
                new PotentialTenantLogin();
                frame.dispose();
            }
        });
        
        po = new JButton("Property Owner");
        po.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                   owner.readOwnerList();
                    new PropertyOwnerLandingPage();
                    frame.dispose();
                }
            });
        
        pa = new JButton("Property Agent");
        pa.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
               agent.readAgentList();
                new PropertyAgentLandingPage();
                frame.dispose();
            }
        });

        showUsers.add(admin);
        showUsers.add(pt);
        showUsers.add(po);
        showUsers.add(pa);
        
        frame.add(header,BorderLayout.NORTH);
        frame.add(showUsers,BorderLayout.CENTER);
        
        frame.setSize(700,700);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}