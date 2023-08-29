/*Class : UserListGUI
* Purpose : 1) display user registration list
*/

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Dimension;
import java.util.ArrayList;

public class UserListGUI extends JFrame
{
    JPanel showList = new JPanel();
    JLabel title = new JLabel("User Registration List",SwingConstants.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton delete,home,save,pending;
    UserControl uc = new UserControl();
    JPanel listUser ;
    JPanel header = new JPanel(new BorderLayout());
    
    private String filename, pendingFilename;
    
    JScrollPane scrollPane;
    private ArrayList<? extends User> user, pendingUser;
    JFrame listGUI;

    public UserListGUI() {}

    //To initialize the userlist gui 
    public UserListGUI(ArrayList<? extends User> user, String filename) {
        this.user = user;
        this.filename = filename;
    }
    

     //To initialize the userlist gui
    public UserListGUI(ArrayList<? extends User> user,String pendingFilename,ArrayList<? extends User>pending,String filename) {
        this.user = user;
        this.filename = filename;
        this.pendingFilename = pendingFilename;
        this.pendingUser = pending;
    }
   
    //Coder : Adeline Low Hui Min
    //To display gui
    public void createGUI(String registrationTitle) {
        listGUI = new JFrame("User List");
        
        JLabel title = new JLabel(registrationTitle+" Registration List",SwingConstants.CENTER);
        title.setFont(new Font("Verdana",Font.BOLD,40));
        header.add(title,BorderLayout.CENTER);
       
        DefaultListModel<? extends User> model = uc.listModel(user);    
        
        JList userList = new JList(model);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setPreferredSize(new Dimension(1000,10000));
        userList.setFixedCellHeight(200);
       
        JPanel listUser = new JPanel(new BorderLayout());
        userList.setCellRenderer(new UsersCellRenderer());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(userList);

        listUser.add(scrollPane,BorderLayout.CENTER);

        pending = new JButton("PENDING REGISTRATION");
        pending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                listGUI.dispose();
                PendingUser pu = new PendingUser("Pending List");
                pu.pendingGUI(pendingUser,pendingFilename,filename);
            }
        });
       
        delete = new JButton("DELETE USER");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = userList.getSelectedIndex();
    
                int result = JOptionPane.showConfirmDialog(listGUI,"Do you want to delete this user?", "Delete user?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                
                if(result == JOptionPane.YES_OPTION) {
                    user.remove(index);
                    model.remove(index);
                    JOptionPane.showMessageDialog(null,
                    "The user has been deleted and user list has been updated.");

                    try {
                        updateTxt(user,filename);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                else if(result == JOptionPane.NO_OPTION){
                    dispose();
                }
            }
        });
       
        home = new JButton("HOME");
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                  new AdminHomeGUI();
            listGUI.dispose();
            }
        });
       
        buttonPanel.add(pending);
        buttonPanel.add(delete);
        buttonPanel.add(home);
        header.add(buttonPanel,BorderLayout.SOUTH);
        
        listGUI.add(listUser,BorderLayout.CENTER);
        listGUI.add(header,BorderLayout.NORTH);
        listGUI.setSize(5000, 1000);
        listGUI.setVisible(true);
        listGUI.setLocationRelativeTo(null);
        listGUI.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        listGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Coder :Adeline Low Hui Min
    //TO update user list in specific file
    private static void updateTxt(ArrayList<? extends User> list,String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size();i++){
            sb.append(list.get(i) + "\n");
        }
        Files.write(Paths.get(filename + ".txt"),sb.toString().getBytes());
    }
}