/*Class : PropertiesControl
*Purpose : To act as Control in MVC design pattern
*/

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

public class PropertiesControl 
{

    //To return all datas from propertiesList.txt
    public ArrayList<Property>  viewProperties() {
        ArrayList<Property> list = Property.readPropertiesList();
        return list;
    }
    
    //To update files
    public static void updateFile(ArrayList<Property> propList) throws IOException{
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < propList.size();i++){
            sb.append(propList.get(i) + "\n");
        }
        Files.write(Paths.get("PropertiesList.txt"),sb.toString().getBytes());
    }

    
    //To return ArrayList in the DefaulitListModel to use in JList
    public DefaultListModel<Property> listModel (ArrayList<Property> propList){
        DefaultListModel<Property> model = new DefaultListModel<Property>();
        for(Property val : propList){
            model.addElement(val);
        }
        return model;
    }
}