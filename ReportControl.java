/*Class ReportControl
*Purpose : To work as Control in MVC design pattern
*/

import java.util.ArrayList;
import javax.swing.*;

public class ReportControl 
{
    ArrayList<Property> selectedProp = new ArrayList<Property>();
    private ArrayList<Property> propList =Property.readPropertiesList();

    //Coder :Adeline Low Hui Min
    //To return all property list in propertiesList.txt
    public ArrayList<Property>  viewProperties() {
        return propList;
    }

    //Coder : Adeline Low Hui Min
    //To return properties that have the type that the user search
    public ArrayList<Property> getPropType(String type){
       ArrayList<Property> t = new ArrayList<Property>();
        for(int i = 0 ; i < propList.size();i++){
            if(propList.get(i).getPropertiesType().equals(type)){
                t.add(propList.get(i));
            }
        }
        return t;
    }

    //Coder : Chee Wan Ying
    //To return properties that have the states that the user search
    public ArrayList<Property> getPropertiesStatus(String status){
        ArrayList<Property> t = new ArrayList<Property>();
        for(int i = 0 ; i < propList.size();i++){
            if(propList.get(i).getStatus().equals(status)){
                t.add(propList.get(i));
            }
        }
        return t;
    }
    
    //Coder : Chee Wei Jae
    //To return properties that have the project that the user search
    public ArrayList<Property> getPropProject(String project) {
        ArrayList<Property> t = new ArrayList<Property>();
        for (Property l : propList) {
            if (l.getPropertiesName().contains(project) || l.getPropertiesAddress().contains(project)) {
                t.add(l);
            }
        }   
        return t;  
    }
   
    //Coder : Chee Wei Jae
    //To return properties that have the facility that the user search
    public ArrayList<Property> getPropFacilities(String facility) {
        ArrayList<Property> t = new ArrayList<Property>();
        for (Property l : propList) {
            String list = "";
            list = String.join(",", l.getFacilities());
            if (list.contains(facility)) {
                t.add(l);
            }
        }   
        return t;  
    }
    
    //Coder : Jenniffer Teh Sue Ling
    //To return properties that have the cost that the user search
    public void getPropCost(String min,String max){
        for(int i = 0 ; i < propList.size();i++){
            double cost = Double.parseDouble(propList.get(i).getPropertiesCost());
            double minCost =  Double.parseDouble(min);
            double maxCost =  Double.parseDouble(max);
            if(cost >= minCost && cost<= maxCost){ 
                selectedProp.add(propList.get(i));
            } 
        }
    }

    //Coder : Jenniffer Teh Sue Ling
    //To return properties that have the property rate that the user search
    public void getPropRate(String min,String max){
        double minCost =  Double.parseDouble(min);
        double maxCost =  Double.parseDouble(max);
        for(int i = 0 ; i < propList.size();i++){
            double cost = Double.parseDouble(propList.get(i).getRentalRate());
            if(cost >= minCost && cost<= maxCost){ 
                selectedProp.add(propList.get(i));
            } 
        }
    }

    //To return property list
    public ArrayList<Property> getList(){
        return selectedProp;
    }

    

    //Coder : Adeline Low Hui Min
    //To return arraylist into default list model for jlist
    public DefaultListModel<Property> listModel (ArrayList<Property> propList){
        DefaultListModel<Property> model = new DefaultListModel<Property>();
        for(Property val : propList){
            model.addElement(val);
        }
        return model;
    }
}
