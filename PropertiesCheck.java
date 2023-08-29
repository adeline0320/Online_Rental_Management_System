/* Class : PropertiesCheck
*Purpose : To check each input of facilities
*/
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PropertiesCheck {
    PropertiesCheck() {}

    //To check whether the swimming pool check box has been selected
    public String checkSwimmingPool(JCheckBox sp) {
        if (sp.isSelected()) {
            return "Swimming-pool";
        } else {
            return "";
        }
    }

     //To check whether the air con check box has been selected
    public  String checkAirCon(JCheckBox ac) {
        if (ac.isSelected()) {
            return "Air-con";
        } else {
            return "";
        }
    }
 //To check whether the wifi check box has been selected
    public String checkWifi(JCheckBox w) {
        if (w.isSelected()) {
            return "Wifi";
        } else {
            return "";
        }
    }

    //To check whether the water heater check box has been selected
    public String checkWaterHeater(JCheckBox wh) {
        if (wh.isSelected()) {
            return "Water heater";
        } else {
            return "";
        }
    }

    public Boolean checkAdditionalBox(JCheckBox add) {
        if (add.isSelected()) {
            return true;
        } else{
            return false;
        }
    }

     //To check whether the additional check box has been selected
    public String checkAdditional(JCheckBox add, String facilities) {
        if (add.isSelected()) {
            return "" + facilities;
        }
        return "";
    }

     //To uppercase the first character
    public String toTitleCase(JTextField addTF) {
        String facilities = addTF.getText();
        String[] arr = facilities.split(",");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length-1; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                .append(arr[i].substring(1)).append(", ");
        }   
        sb.append(Character.toUpperCase(arr[arr.length-1].charAt(0)))
                .append(arr[arr.length-1].substring(1));       
        return sb.toString().trim();
    }
    
     //To check return the status of each radio button
    public String getCurrentStatus(JRadioButton y) {
        if (y.isSelected()) {
            return "Active";
        } else {
            return "Inactive";
        }
    }
}
