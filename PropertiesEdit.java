/*Class : PropertiesEdit
*Purpose : To show properties edit UI
*/
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class PropertiesEdit extends JFrame implements ActionListener, KeyListener, ItemListener {
    public int idIndex = PropertyGetID.propID;
    private JTextField propertiesName, propertiesAddress, propertiesCost, room, bathroom, parkingslot,
            additionalTF, rentalRate, buileup, propertiesImage;
    private JLabel bannerLbl, propertiesLbl, propertiesAddressLbl, propertiesIDLbl , propertiesID, propertiesCostLbl,
            proTypeLbl, propertiesFacilitiesLbl, roomLbl, bathroomLbl, parkingslotLbl, rentalRateLbl, buileupLbl,
            propertiesImageLbl, statusLbl;
    private JCheckBox swimmingPool, aircon, wifi, waterHeater, additional;
    private JButton saveButton, cancelButton;
    private Choice propertiesType;
    private ButtonGroup bg;
    private JRadioButton yes, no;
    private PropertiesCheck pc = new PropertiesCheck();
    private Property list = new Property();
    ArrayList<String> facilities = new ArrayList<>();
    Property prop = new Property();
    ArrayList<Property> propList = prop.readPropertiesList();

    public PropertiesEdit() {
        super("PROPERTY EDIT");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        // banner label constructor
        bannerLbl = new JLabel("EDIT PROPERTY");
        bannerLbl.setBounds(185, 20, 800, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        // properties name label constructor
        propertiesLbl = new JLabel("Properties Name: ");
        propertiesLbl.setBounds(20, 110, 400, 20);
        propertiesLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesLbl);

        // propertiesName textfield constructor
        propertiesName = new JTextField(propList.get(idIndex).getPropertiesName().replace("\t",","));
        propertiesName.setBounds(270, 110, 505, 20);
        panel.add(propertiesName);

        // propertiesAddress label constructor
        propertiesAddressLbl = new JLabel("Properties Address: ");
        propertiesAddressLbl.setBounds(20, 140, 400, 20);
        propertiesAddressLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesAddressLbl);

        // propertiesAddress textfield constructor
        propertiesAddress = new JTextField(propList.get(idIndex).getPropertiesAddress().replace("\t",","));
        propertiesAddress.setBounds(270, 140, 505, 20);
        panel.add(propertiesAddress);

        // propertiesID label constructor
        propertiesIDLbl = new JLabel("Properties ID: ");
        propertiesIDLbl.setBounds(20, 170, 400, 20);
        propertiesIDLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesIDLbl);

        // propertiesID label constructor
        propertiesID = new JLabel(propList.get(idIndex).getPropertiesID());
        propertiesID.setBounds(270, 170, 505, 20);
        panel.add(propertiesID);

        // propertiesCost label constructor
        propertiesCostLbl = new JLabel("Properties Cost: ");
        propertiesCostLbl.setBounds(20, 200, 400, 20);
        propertiesCostLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesCostLbl);

        // propertiesCost textfield constructor
        propertiesCost = new JTextField(propList.get(idIndex).getPropertiesCost());
        propertiesCost.setBounds(270, 200, 505, 20);
        propertiesCost.addKeyListener(this);
        panel.add(propertiesCost);

        // properties type label constructor
        proTypeLbl = new JLabel("Properties Type: ");
        proTypeLbl.setBounds(20, 230, 250, 20);
        proTypeLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(proTypeLbl);

        // Property type choice box
        String type = propList.get(idIndex).getPropertiesType();
        propertiesType = new Choice();
        propertiesType.setBounds(270, 230, 200, 20);
        propertiesType.add("Bungalow");
        propertiesType.add("Townhouse");
        propertiesType.add("Flat");
        propertiesType.add("Apartment");
        propertiesType.add("Condominium");
        propertiesType.add("Shop House");
        propertiesType.select(type);
        panel.add(propertiesType);

        // propertiesFacilities label constructor
        propertiesFacilitiesLbl = new JLabel("Properties Facilities: ");
        propertiesFacilitiesLbl.setBounds(20, 260, 300, 20);
        propertiesFacilitiesLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesFacilitiesLbl);

        // rooms label constructor
        roomLbl = new JLabel("Rooms: ");
        roomLbl.setBounds(270, 260, 100, 20);
        roomLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(roomLbl);

        // rooms textfield constructor
        room = new JTextField(propList.get(idIndex).getPropertiesRoom());
        room.setBounds(340, 260, 60, 20);
        room.addKeyListener(this);
        panel.add(room);

        // bathroom label constructor
        bathroomLbl = new JLabel("Bathroom: ");
        bathroomLbl.setBounds(410, 260, 180, 20);
        bathroomLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(bathroomLbl);

        // bathroom textfield constructor
        bathroom = new JTextField(propList.get(idIndex).getPropertiesBathroom());
        bathroom.setBounds(510, 260, 60, 20);
        bathroom.addKeyListener(this);
        panel.add(bathroom);

        // parkingslot label constructor
        parkingslotLbl = new JLabel("Parkingslot: ");
        parkingslotLbl.setBounds(580, 260, 180, 20);
        parkingslotLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(parkingslotLbl);

        // parkingslot textfield constructor
        parkingslot = new JTextField(propList.get(idIndex).getPropertiesParking());
        parkingslot.setBounds(715, 260, 60, 20);
        parkingslot.addKeyListener(this);
        panel.add(parkingslot);

        // swimming pool checkbox constructor
        swimmingPool = new JCheckBox("Swimming Pool", getSwimmingPool());
        swimmingPool.setBounds(270, 290, 180, 20);
        swimmingPool.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(swimmingPool);

        // air con checkbox constructor
        aircon = new JCheckBox("Air-Con", getAirCon());
        aircon.setBounds(450, 290, 150, 20);
        aircon.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(aircon);

        // wifi checkbox constructor
        wifi = new JCheckBox("WIFI", getWIFI());
        wifi.setBounds(600, 290, 150, 20);
        wifi.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(wifi);

        // water heater textfield constructor
        waterHeater = new JCheckBox("Water Heater", getWaterHeater());
        waterHeater.setBounds(270, 320, 180, 20);
        waterHeater.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(waterHeater);

        // additional checkbox constructor
        additional = new JCheckBox("Additional: ", checkAdditional());
        additional.setBounds(450, 320, 160, 20);
        additional.setFont(new Font("Monospaced", Font.PLAIN, 18));
        additional.addItemListener(this);
        panel.add(additional);

        // additional textfield constructor
        additionalTF = new JTextField();
        additionalTF.setBounds(610, 321, 150, 20);
        panel.add(additionalTF);
        checkAddionalTextFieldEnabled();
        
        // rental rate label constructor
        rentalRateLbl = new JLabel("Rental rate : RM");
        rentalRateLbl.setBounds(20, 318, 180, 100);
        rentalRateLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(rentalRateLbl);

        // rental rate textfield constructor
        rentalRate = new JTextField(propList.get(idIndex).getRentalRate());
        rentalRate.setBounds(200, 360, 70, 20);
        rentalRate.addKeyListener(this);
        panel.add(rentalRate);

        // buileup label constructor
        buileupLbl = new JLabel("Build up size(in square fit) : ");
        buileupLbl.setBounds(20, 348, 500, 100);
        buileupLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(buileupLbl);

        // buileup textfield constructor
        buileup = new JTextField(propList.get(idIndex).getBuiltUpSize());
        buileup.setBounds(365, 390, 90, 20);
        buileup.addKeyListener(this);
        panel.add(buileup);

        // image label constructor
        propertiesImageLbl = new JLabel("Properties Image: ");
        propertiesImageLbl.setBounds(20, 420, 300, 20);
        propertiesImageLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesImageLbl);

        // image textfield constructor
        propertiesImage = new JTextField(propList.get(idIndex).getIcon());
        propertiesImage.setBounds(270, 422, 505, 20);
        panel.add(propertiesImage);

        // status label constructor
        statusLbl = new JLabel("Status: ");
        statusLbl.setBounds(20, 450, 150, 20);
        statusLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(statusLbl);

        bg = new ButtonGroup();
        yes = new JRadioButton("Active");
        yes.setFont(new Font("Monospaced", Font.PLAIN, 18));
        yes.setBounds(270, 450, 100, 20);
        no = new JRadioButton("Inactive");
        no.setFont(new Font("Monospaced", Font.PLAIN, 18));
        no.setBounds(370, 450, 150, 20);
        bg.add(yes);
        panel.add(yes);
        bg.add(no);
        panel.add(no);
        status();

        // save button constructor
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        saveButton.setLocation(650, 490);
        saveButton.setSize(120, 40);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        // cancel button constructor
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelButton.setLocation(20, 490);
        cancelButton.setSize(120, 40);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setSize(810, 610);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Boolean getSwimmingPool() {
        String list = String.join("", propList.get(idIndex).getFacilities());
        if (list.contains("Swimming-pool")) {
            return true;
        } else{
            return false;
        }
    }

    private Boolean getAirCon() {
        String list = String.join("", propList.get(idIndex).getFacilities());
        if (list.contains("Air-con")) {
            return true;
        } else{
            return false;
        }
    }

    private Boolean getWIFI() {
        String list = String.join("", propList.get(idIndex).getFacilities());
        if (list.contains("Wifi")) {
            return true;
        } else{
            return false;
        }
    }

    private Boolean getWaterHeater() {
        String list = String.join("", propList.get(idIndex).getFacilities());
        if (list.contains("Water heater")) {
            return true;
        } else{
            return false;
        }
    }

    private Boolean checkAdditional() {
        String list = String.join(" ", propList.get(idIndex).getFacilities()).replace("\t",",");
        if (list.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void checkAddionalTextFieldEnabled() {
        String listArray = String.join("", propList.get(idIndex).getFacilities()).replace("\t",",");
        List<String> newList = new ArrayList<String>(Arrays.asList(listArray.split(", ")));
        ArrayList<String> finalList = list.removeEmptyString((ArrayList<String>) newList);
       
        String[] basicFacilites = {"Swimming-pool","Air-con","Wifi","Water heater"};
        for(int i = 0; i < finalList.size();i++){
            for(int j = 0;j < basicFacilites.length;j++)
            if(finalList.get(i).equals(basicFacilites[j])){
                finalList.remove(i);
            }
            
           
        }

        
        if (checkAdditional() == true) {
            additionalTF.setEnabled(true);
            additionalTF.setText(String.join(",",finalList));
        } else {
            additionalTF.setEnabled(false);
        }
        
    }

    private void status() {
        if (propList.get(idIndex).getStatus().equals("Active")) {
            yes.setSelected(true);
        } else {
            no.setSelected(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == saveButton) {
            if (!PotentialTenantRegister.checkJTextFieldFilled(propertiesName, propertiesLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the property name.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(propertiesAddress, propertiesAddressLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the address.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(propertiesCost, propertiesCostLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the cost of the property.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(room, roomLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the number of rooms of the property.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(bathroom, bathroomLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the number of bathrooms of the property.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(parkingslot, parkingslotLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the number of parkingslots of the property.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(rentalRate, rentalRateLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter property's rental rate.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(buileup, buileupLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter property's size.");
            }
            if (!PotentialTenantRegister.checkJTextFieldFilled(propertiesImage, propertiesImageLbl)) {
                JOptionPane.showMessageDialog(null, "Please enter the image's name of the property.");
            }
            if (PotentialTenantRegister.checkJTextFieldFilled(propertiesName, propertiesLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(propertiesAddress, propertiesAddressLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(propertiesCost, propertiesCostLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(room, roomLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(bathroom, bathroomLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(parkingslot, parkingslotLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(rentalRate, rentalRateLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(buileup, buileupLbl)
                && PotentialTenantRegister.checkJTextFieldFilled(propertiesImage, propertiesImageLbl)) {
                if (list.checkPropertiesnameExist(propertiesName.getText())
                        || propList.get(idIndex).getPropertiesName().equals(propertiesName.getText())) {
                    JOptionPane.showMessageDialog(null, "Your new profile is saved.");
                    propList.get(idIndex).setPropertiesName(propertiesName.getText().replace(",","\t"));
                    propList.get(idIndex).setPropertiesAddress(propertiesAddress.getText().replace(",","\t"));
                    propList.get(idIndex).setPropertiesCost(propertiesCost.getText());
                    propList.get(idIndex).setPropertiesType(propertiesType.getSelectedItem());
                    propList.get(idIndex).setPropertiesRoom(room.getText());
                    propList.get(idIndex).setPropertiesBathroom(bathroom.getText());
                    propList.get(idIndex).setPropertiesParking(parkingslot.getText());

                    String pool = pc.checkSwimmingPool(swimmingPool);
                    String airCon = pc.checkAirCon(aircon);

                    String WIFI = pc.checkWifi(wifi);
                    String heater = pc.checkWaterHeater(waterHeater);
                    String toUpperString = pc.toTitleCase(additionalTF);
                    String extra = pc.checkAdditional(additional, toUpperString);

                    facilities.add(pool);
                    facilities.add(airCon);
                    facilities.add(WIFI);
                    facilities.add(heater);
                    facilities.add(extra);

                    ArrayList<String> newList = new ArrayList<>();
                    newList = list.removeEmptyString(facilities);
                    propList.get(idIndex).setFacilities(newList);
                    
                   
                    propList.get(idIndex).setRentalRate(rentalRate.getText());
                    propList.get(idIndex).setBuiltUpSize(buileup.getText());
                    propList.get(idIndex).setIcon(propertiesImage.getText());
                    propList.get(idIndex).setStatus(pc.getCurrentStatus(yes));

                    try {
                        PropertiesControl.updateFile(propList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    idIndex = 0;
                    this.dispose();
                    new PropertiesPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Property Name exist. Please change another name.");
                    propertiesLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == cancelButton) {
            idIndex = 0;
            this.dispose();
            new PropertiesPage();
        }
    }

    public void keyTyped(KeyEvent eve) {}

    public void keyPressed(KeyEvent eve) {
        if (eve.getSource() == propertiesCost || eve.getSource() == room || eve.getSource() == bathroom
            || eve.getSource() == parkingslot || eve.getSource() == rentalRate || eve.getSource() == buileup) {
            if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                propertiesCost.setEditable(true);
                room.setEditable(true);
                bathroom.setEditable(true);
                parkingslot.setEditable(true);
                rentalRate.setEditable(true);
                buileup.setEditable(true);
            } else {
                propertiesCost.setEditable(false);
                room.setEditable(false);
                bathroom.setEditable(false);
                parkingslot.setEditable(false);
                rentalRate.setEditable(false);
                buileup.setEditable(false);
            }
        }
    }

    public void keyReleased(KeyEvent eve) {}

    @Override
    public void itemStateChanged(ItemEvent eve) {
        additionalTF.setEnabled(eve.getStateChange() == ItemEvent.SELECTED);
    }
}