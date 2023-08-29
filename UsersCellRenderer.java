/*Class : UsersCellRenderer
Purpose : To display the users information in JList

*/

import java.awt.*;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UsersCellRenderer extends JPanel implements ListCellRenderer<User> {
    String name, phone, gender, username, password, icon;

    JLabel text = new JLabel();
    JLabel genderIcon = new JLabel();

    public UsersCellRenderer() {
        setLayout(new BorderLayout(5,5));
        text.setFont(new Font("Verdana", Font.PLAIN, 20));
        JPanel panelText = new JPanel(new GridLayout(0,1));
        panelText.add(text);
        setBackground(Color.WHITE);
        add(panelText,BorderLayout.CENTER);
        add(genderIcon,BorderLayout.WEST);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?extends User> list,User user, int index,
            boolean isSelected, boolean cellHasFocus) {
        
        if(user instanceof PotentialTenant) {
            PotentialTenant tenant = (PotentialTenant) user;
            name = tenant.getFullName();
            phone = tenant.getContactNum();
            gender = Character.toString(tenant.getGender());
            username = tenant.getUsername();
        }
        else if(user instanceof PropertyAgent) {
            PropertyAgent agent = (PropertyAgent) user;
            name = agent .getFullName();
            phone = agent .getContactNum();
            gender = Character.toString(agent .getGender());
            username = agent .getUsername();
        }
        else if(user instanceof PropertyOwner) {
            PropertyOwner owner = (PropertyOwner) user;
            name = owner.getFullName();
            phone = owner.getContactNum();
            gender = Character.toString(owner.getGender());
            username = owner.getUsername();
        }

        if(gender.equals("F")){
            icon = "female";
        }
        else {
            icon = "male";
        }

        String labelText = "<html>Name         : " + name + 
                            "<br/>Phone number : " + phone +
                            "<br/>Gender       : " + gender +
                            "<br/>Username     : " + username;
        text.setText(labelText);
    
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(
        "/Image/" + icon + ".jpg"));
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        genderIcon.setIcon(imageIcon);
        
        if(isSelected) {
            text.setBackground(Color.lightGray);
            setForeground(Color.black);
        }
        else {
            text.setBackground(Color.white);
            setForeground(Color.black);
            setBorder(new LineBorder(Color.BLACK));
        }
        text.setOpaque(true);
        return this;
    }
}
