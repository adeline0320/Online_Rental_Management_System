/*Class name : AdminLogin
 *Purpose : To show Login UI and direct to specific page for each buttons
 *Coder : Adeline Low Hui Min
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AdminLogin extends JFrame 
{
    public static Boolean ad = false;
    private JPanel submitBtn,loginPanel;
    private JLabel label,adminId,adminPass;
    private JTextField id;
    private JPasswordField password;
    private JButton submit,cancel;
    //AdminLogin constructor
    AdminLogin() {
        super("Admin Page");
        JPanel labelPosition = new JPanel();
        label = new JLabel("Welcome to Admin Page",SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 15));
        
        loginPanel = new JPanel(new GridLayout(2,2,10,10));
       
        adminId = new JLabel("Admin ID",SwingConstants.CENTER);
        id = new JTextField(10);
        adminPass = new JLabel("Admin Password",SwingConstants.CENTER);
        password = new JPasswordField();
       
        loginPanel.add(adminId);
        loginPanel.add(id);
        loginPanel.add(adminPass);
        loginPanel.add(password);
        
        submitBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        cancel = new JButton("CANCEL");
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new Main();
            }
        });
        
        submit = new JButton("LOGIN");
        submit.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent e){
                String getId = id.getText();
                String getPassword = String.valueOf(password.getPassword());
                System.out.println(getPassword);
                Admin admin = new Admin(getId,getPassword);
                Boolean adminCheck = admin.checkLogin(admin); 
                ad = admin.getLogin();
                if(adminCheck == true){
                    JOptionPane.showMessageDialog(null,"Login Success. Welcome back.");
                    new AdminHomeGUI();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Login failed. Please try again.");
                    id.setText("");
                    password.setText("");
                }
            }
        });
       
        labelPosition.add(label);
        
        submitBtn.add(cancel);
        submitBtn.add(submit);
        
        add(labelPosition,BorderLayout.NORTH);
        add(loginPanel,BorderLayout.CENTER); 
        add(submitBtn,BorderLayout.SOUTH);
       
        setSize(500,200);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}