/*Class name : PropertiesRender
Purpose : To display the properties details in JList

*/

import java.awt.*;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PropertiesRender extends JPanel implements ListCellRenderer<Property>
{
    JLabel iconImage = new JLabel();
    JLabel text = new JLabel();
    
    public PropertiesRender() {
        setLayout(new BorderLayout(5,5));
        text.setFont(new Font("Verdana", Font.PLAIN, 17));
        JPanel panelText = new JPanel(new GridLayout(0,1));
        panelText.add(text);
        setBackground(Color.WHITE);
        iconImage.setBackground(Color.WHITE);
        add(iconImage, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    //Coder : Adeline Low Hui Min
    //To display the properties details
    public Component getListCellRendererComponent(
        JList<? extends Property> list, Property prop, int index,
            boolean isSelected, boolean cellHasFocus) {

        String name = prop.getPropertiesName().replace("\t",",");
        String address = prop.getPropertiesAddress().replace("\t",",");
        String id = prop.getPropertiesID();
        String type = prop.getPropertiesType();
        
        String rental = prop.getPropertiesCost();
        String icon = prop.getIcon();
        String room = prop.getPropertiesRoom();
        String bathroom = prop.getPropertiesBathroom();
        String parking = prop.getPropertiesParking();
        
        ArrayList<String> fac = prop.getFacilities();
        String facilities = fromListToString(fac).replace("\t",",");
        String rate = prop.getRentalRate();
        String size = prop.getBuiltUpSize();
        String status = prop.getStatus();
        
        String labelText = "<html> Property name           : " + name + 
                            "<br/> Property address        : " + address +
                            "<br/> ID                      : " + id +
                            "<br/> Property Rental Price RM: " + rental + 
                            "<br/> Property Type           : " + type + 
                            "<br/> Number of rooms         : "+ room + " rooms" +
                            "<br/> Number of bathrooms     : " + bathroom + " bathrooms" +
                            "<br/> Number of parkings      : " + parking + " parking" +
                            "<br/> Facilities Available    : " +  facilities + 
                            "<br/> Rental rate           RM: " + rate + " per sq. ft(Built-up Size = " + size + " sq. ft)" + 
                            "<br/> Properties Status       : " + status;
                            
        
        text.setText(labelText);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Image/" + icon + ".jpg"));
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        iconImage.setIcon(imageIcon);
    
        if(isSelected){
            text.setBackground(Color.yellow);
            setForeground(Color.black);
        }
        else{
            text.setBackground(Color.white);
            setForeground(Color.black);
            setBorder(new LineBorder(Color.BLACK));
        }
        iconImage.setOpaque(true);
        text.setOpaque(true);
        return this;
    }

    //Coder : Adeline Low Hui Min
    //To convert arraylist to string
    private static String fromListToString(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String num : list) {
            sb.append(num);
        }
        return sb.toString();
    }
}