/*Class name : AddAdminAccountGUI
Purpose : To let admin add accounts

*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddAdminAccountGUI extends JFrame implements KeyListener
{
    Boolean login = AdminLogin.ad;
    
    JPanel header = new JPanel();
    JLabel headerLabel = new JLabel("Add Admin Account");
    JPanel adminInsert = new JPanel(new GridLayout(4,1));
    JPanel submitPanel = new JPanel();
    JLabel adminId,password,name,phoneNumber;
    JTextField tID,tPassword,tname,tphone;
    JButton submit,cancel;
    public AddAdminAccountGUI() {
        super("Create Admin Account");
        header.add(headerLabel,BorderLayout.CENTER);
        
        name = new JLabel("Admin Name");
        tname = new JTextField(20);
        
        phoneNumber = new JLabel("Phone Number");
        tphone = new JTextField(20);
        tphone.addKeyListener(this);

        adminId = new JLabel("Admin ID");
        tID = new JTextField(10);

        password = new JLabel("Password");
        tPassword = new JTextField(10);
        
        adminInsert.add(name);
        adminInsert.add(tname);
        adminInsert.add(phoneNumber);
        adminInsert.add(tphone);
        adminInsert.add(adminId);
        adminInsert.add(tID);
        adminInsert.add(password);
        adminInsert.add(tPassword);
        
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String id = tID.getText();
                String pw = tPassword.getText();
                String name = tname.getText();
                String phone = tphone.getText();

                Admin admin = new Admin(name,phone,id,pw);
                Boolean status = admin.addAdminAccount(admin);
                
                if(status == true) {
                    JOptionPane.showMessageDialog(null,"Account has been added.");
                    System.out.println(admin.getLogin());
                    if(login == true) {
                        tID.setText("");
                        tPassword.setText("");
                        tname.setText("");
                        tphone.setText("");
                    }
                    else {
                        dispose();
                        new AdminLogin();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Available name and phone. Please try again.");
                    tID.setText("");
                    tPassword.setText("");
                    tname.setText("");
                    tphone.setText("");
                }
            }
        });
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                dispose();
                if (login == true) {
                    new AdminHomeGUI();
                } else {
                    new AdminLogin();
                }
           }
        });
        
        submitPanel.add(submit);
        submitPanel.add(cancel);
        
        add(header,BorderLayout.NORTH);
        add(adminInsert,BorderLayout.CENTER);
        add(submitPanel,BorderLayout.SOUTH);
       
        setSize(400,200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    //Coder : Adeline Low Hui Min
    //Purpose : To check whether user enters number of characters
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() == tphone){
            if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == '\b'){
                tphone.setEditable(true);
            }
            else
                tphone.setEditable(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
