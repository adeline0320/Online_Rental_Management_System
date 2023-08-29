/*CLass : PendingUser
* Purpose: To show the UI of pending registration of each users
*/
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

// Coder :Adeline Low Hui Min
public class PendingUser
{
    private JFrame frame;
    private JLabel label = new JLabel(); ;
  
    private JPanel btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel showPending = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new BorderLayout());
    private JButton reject,accept,back;
    private UserControl uc = new UserControl();
    private JScrollPane scrollPane;
   
    //Initialize UI title
    public PendingUser(String title){
        frame = new JFrame(title); 
    }

    //set UI header
    public void setHeader(String labelText){
        label.setText(labelText);
        label.setFont(new Font("Verdana",Font.BOLD,40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(label,BorderLayout.CENTER);

    }

    //To show pending registration UI
    public void pendingGUI(ArrayList<? extends User> pendingUser,String pendingFilename,String filename){
        if(pendingFilename.equals("PendingTenant")){
            setHeader("Potential Tenant Pending Registration List");
        }
        else if(pendingFilename.equals("PendingOwner")){
            setHeader("Property Owner Pending Registration List");
        }
        else if(pendingFilename.equals("PendingAgent")){
            setHeader("Property Agent Pending Registation List");
        }

        DefaultListModel<? extends User> modelPending = uc.listModel(pendingUser);
        JList<User> pending = new JList(modelPending);
        pending.setCellRenderer(new UsersCellRenderer());
        pending.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pending.setLayoutOrientation(JList.VERTICAL);
        pending.setPreferredSize(new Dimension(1000,1000));
        pending.setFixedCellHeight(200);
 
       
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(pending);

        showPending.add(scrollPane,BorderLayout.CENTER);       

        reject = new JButton("REJECT");
        reject.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int[] indexUser = pending.getSelectedIndices();
                int result = JOptionPane.showConfirmDialog(frame,"Do you want to delete this user?", "Delete user?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION){
                    for(int i = (indexUser.length-1); i >=0; i--){
                        pendingUser.remove(indexUser[i]);
                        modelPending.remove(indexUser[i]);
                        JOptionPane.showMessageDialog(null,
                        "The user has been deleted and user list has been updated.");
                    }
        
                    try {
                        uc.removePending(pendingUser,pendingFilename);
                    } catch(Exception ex) {
                        System.out.println(ex);
                    }
                 }
                 
            }
        });

        accept = new JButton("ACCEPT");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int[] indexUser = pending.getSelectedIndices();
                ArrayList<User> pendingList = new ArrayList<>();
                for(int i = (indexUser.length-1); i >=0; i--){
                    pendingList.add(modelPending.get(indexUser[i]));
                    pendingUser.remove(indexUser[i]);
                    modelPending.remove(indexUser[i]);
                }
    
                try {
                    uc.addUser(pendingList,filename);
                    uc.removePending(pendingUser,pendingFilename);
                    pendingList.clear();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
            }
        });

        back = new JButton("HOME");
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new AdminHomeGUI();
                frame.dispose();
            }
        });
        
        btn.add(reject);
        btn.add(accept);
        btn.add(back);
        header.add(btn,BorderLayout.SOUTH);
        
        frame.add(header,BorderLayout.NORTH);
        frame.add(showPending,BorderLayout.CENTER);
        
        frame.setSize(1000,900);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}