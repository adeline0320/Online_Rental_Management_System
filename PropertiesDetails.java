/*Class name : PropertiesDetails
*Purpose : To show add properties UI
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class PropertiesDetails extends JFrame implements ActionListener, KeyListener, ItemListener {
    private JTextField propertiesName, propertiesAddress, propertiesCost, room, bathroom, parkingslot,
                        additionalTF, propertiesImage;
    JLabel bannerLbl, instruction, propertiesLbl, propertiesAddressLbl, propertiesIDLbl, propertiesID, propertiesCostLbl,
            propertiesTypeLbl, propertiesFacilitiesLbl, roomLbl, bathroomLbl, parkingslotLbl, propertiesImageLbl,
            statusLbl, swimmingPoolLbl, airconLbl, wifiLbl, waterHeaterLbl,imageNotes;
    JLabel rentalRateLbl,buileupLbl;
    JTextField rentalRate,buileup;

    private JCheckBox swimmingPool, aircon, wifi, waterHeater, additional;
    Property list = new Property();
    JButton submitButton, backButton;
    Choice propertiesType;
    ArrayList<String> facilities = new ArrayList<>();
    PropertiesCheck pc = new PropertiesCheck();
  
    public PropertiesDetails() {
        super("ADD PROPERTIES");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        add(panel);

        bannerLbl = new JLabel("ADD PROPERTIES");
        bannerLbl.setBounds(20, 30, 800, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        instruction = new JLabel("Please fill in properties details");
        instruction.setBounds(20, 75, 400, 20);
        instruction.setFont(new Font("Verdana", Font.PLAIN, 17));
        panel.add(instruction);

        // propertiesName label constructor
        propertiesLbl = new JLabel("Properties Name: ");
        propertiesLbl.setBounds(20, 120, 400, 20);
        propertiesLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesLbl);

        // propertiesName textfield constructor
        propertiesName = new JTextField();
        propertiesName.setBounds(240, 120, 500, 20);
        panel.add(propertiesName);

        // propertiesAddress label constructor
        propertiesAddressLbl = new JLabel("Properties Address: ");
        propertiesAddressLbl.setBounds(20, 150, 400, 20);
        propertiesAddressLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesAddressLbl);

        // propertiesAddress textfield constructor
        propertiesAddress = new JTextField();
        propertiesAddress.setBounds(240, 150, 500, 20);
        panel.add(propertiesAddress);

        // propertiesID label constructor
        propertiesIDLbl = new JLabel("Properties ID: ");
        propertiesIDLbl.setBounds(20, 180, 400, 20);
        propertiesIDLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesIDLbl);

        // propertiesID textfield constructor
        propertiesID = new JLabel(getLatestID());
        propertiesID.setBounds(240, 180, 500, 20);
        propertiesID.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesID);

        // propertiesCost label constructor
        propertiesCostLbl = new JLabel("Properties Cost: ");
        propertiesCostLbl.setBounds(20, 210, 400, 20);
        propertiesCostLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesCostLbl);

        // propertiesCost textfield constructor
        propertiesCost = new JTextField();
        propertiesCost.setBounds(240, 210, 500, 20);
        propertiesCost.addKeyListener(this);
        panel.add(propertiesCost);

        // propertiesType label constructor
        propertiesTypeLbl = new JLabel("Properties Type: ");
        propertiesTypeLbl.setBounds(20, 240, 200, 20);
        propertiesTypeLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesTypeLbl);

        // Property type choice box//CHECK
        propertiesType = new Choice();
        propertiesType.setBounds(240, 240, 200, 20);
        propertiesType.add("Bungalow");
        propertiesType.add("Townhouse");
        propertiesType.add("Flat");
        propertiesType.add("Apartment");
        propertiesType.add("Condominium");
        propertiesType.add("Shop House");
        panel.add(propertiesType);

        propertiesFacilitiesLbl = new JLabel("Properties Facilities: ");
        propertiesFacilitiesLbl.setBounds(20, 270, 300, 20);
        propertiesFacilitiesLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesFacilitiesLbl);

        // rooms label constructor
        roomLbl = new JLabel("Rooms: ");
        roomLbl.setBounds(260, 270, 100, 20);
        roomLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(roomLbl);

        // rooms textfield constructor
        room = new JTextField();
        room.setBounds(330, 270, 60, 20);
        room.addKeyListener(this);
        panel.add(room);

        // bathroom label constructor
        bathroomLbl = new JLabel("Bathroom: ");
        bathroomLbl.setBounds(400, 270, 180, 20);
        bathroomLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(bathroomLbl);

        // bathroom textfield constructor
        bathroom = new JTextField();
        bathroom.setBounds(500, 270, 60, 20);
        bathroom.addKeyListener(this);
        panel.add(bathroom);

        // parkingslot label constructor
        parkingslotLbl = new JLabel("Parkingslot: ");
        parkingslotLbl.setBounds(570, 270, 180, 20);
        parkingslotLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(parkingslotLbl);

        // parkingslot textfield constructor
        parkingslot = new JTextField();
        parkingslot.setBounds(700, 270, 60, 20);
        parkingslot.addKeyListener(this);
        panel.add(parkingslot);

        // swimming pool checkbox constructor
        swimmingPool = new JCheckBox("Swimming Pool");
        swimmingPool.setBounds(260, 300, 190, 20);
        swimmingPool.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(swimmingPool);

        // air con checkbox constructor
        aircon = new JCheckBox("Air-Con");
        aircon.setBounds(450, 300, 150, 20);
        aircon.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(aircon);

        // wifi checkbox constructor
        wifi = new JCheckBox("WIFI");
        wifi.setBounds(600, 300, 150, 20);
        wifi.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(wifi);

        // water heater checkbox constructor
        waterHeater = new JCheckBox("Water Heater");
        waterHeater.setBounds(260, 330, 190, 20);
        waterHeater.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(waterHeater);

        // additional checkbox constructor
        additional = new JCheckBox("Additional: ");
        additional.setBounds(450, 330, 160, 20);
        additional.setFont(new Font("Monospaced", Font.PLAIN, 18));
        additional.addItemListener(this);
        panel.add(additional);

        // additional textfield constructor
        additionalTF = new JTextField();
        additionalTF.setBounds(610, 331, 150, 20);
        panel.add(additionalTF);
        additionalTF.setEnabled(false); 
          
        rentalRateLbl = new JLabel("Rental rate : RM");
        rentalRateLbl.setBounds(20, 320, 180, 100);
        rentalRateLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(rentalRateLbl);

        rentalRate = new JTextField();
        rentalRate.setBounds(210, 362, 70, 20);
        rentalRate.addKeyListener(this);
        panel.add(rentalRate);

        buileupLbl = new JLabel("Built up Size(in sq. ft) : ");
        buileupLbl.setBounds(20, 350, 500, 100);
        buileupLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(buileupLbl);

        buileup = new JTextField();
        buileup.setBounds(315, 392, 90, 20);
        buileup.addKeyListener(this);
        panel.add(buileup);

        // image label constructor
        propertiesImageLbl = new JLabel("Properties Image: ");
        propertiesImageLbl.setBounds(20, 420, 300, 20);
        propertiesImageLbl.setFont(new Font("Monospaced", Font.PLAIN, 18));
        panel.add(propertiesImageLbl);

        // image textfield constructor
        propertiesImage = new JTextField();
        propertiesImage.setBounds(220, 422, 100, 20);
        panel.add(propertiesImage);

        //notes
        imageNotes = new JLabel("Please save the image in the same file as this system and set the image file as .jpg.\nType \"b1\" for default image");
        imageNotes.setBounds(20,440,1000,20);
        imageNotes.setFont(new Font("Monopaced",Font.PLAIN,15));
        panel.add(imageNotes);

        // submit button constructor
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        submitButton.setLocation(450, 500);
        submitButton.setSize(submitButton.getPreferredSize());
        submitButton.addActionListener(this);
        panel.add(submitButton);

        // back button constructor
        backButton = new JButton("Back");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        backButton.setLocation(250, 500);
        backButton.setSize(backButton.getPreferredSize());
        backButton.addActionListener(this);
        panel.add(backButton);

        setSize(800, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void AddProperties() {
        try {
            FileWriter f = new FileWriter(new File("PropertiesList.txt"), true);
            BufferedWriter bw = new BufferedWriter(f);
            
            String type = propertiesType.getSelectedItem();
            String address = propertiesAddress.getText().replace(",", "\t");
            String name = propertiesName.getText();
            String id = propertiesID.getText();
            String price = propertiesCost.getText();
            String roomAmount = room.getText();
            String bathroomAmount = bathroom.getText();
            String parking = parkingslot.getText();
            String pool = pc.checkSwimmingPool(swimmingPool).trim();
            String airCon = pc.checkAirCon(aircon).trim();
            String WIFI = pc.checkWifi(wifi).trim();
            String heater = pc.checkWaterHeater(waterHeater).trim();

            facilities.add(pool);
            facilities.add(airCon);
            facilities.add(WIFI);
            facilities.add(heater);

            if (additionalTF.getText().length() == 0) {
                facilities.add("");
            } else {

                String addFacilities = pc.toTitleCase(additionalTF);
                facilities.add(addFacilities);
                
            }
        
            String rate = rentalRate.getText();
            String size = buileup.getText();
            String image = propertiesImage.getText();
            
            ArrayList<String> newList = new ArrayList<>();
            newList = list.removeEmptyString(facilities);
        
            Property list =  new Property(name,address,id,price,type,
            roomAmount,bathroomAmount,parking,newList,rate,size,image);
            bw.append(list.toString() + "\n");
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLatestID() {
        Property prop = new Property();
        ArrayList<Property> propList = prop.readPropertiesList();
        String newID = "0";
        int size = propList.size();
        if (size == 0) {
            newID = "1";
        } else{
            String propID = propList.get(propList.size()-1).getPropertiesID();
            newID = String.valueOf(Integer.parseInt(propID) + 1);
        }
        return newID;
    }
       
    @Override
    public void actionPerformed(ActionEvent eve) {
        if (eve.getSource() == submitButton) {
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
                if (list.checkPropertiesnameExist(propertiesName.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Congratulations. Your property details has been successfully created.");

                    AddProperties();
                    propertiesName.setText("");
                    propertiesAddress.setText("");
                    propertiesID.setText("");
                    propertiesCost.setText("");
                    rentalRate.setText("");
                    buileup.setText("");

                    room.setText("");
                    bathroom.setText("");
                    parkingslot.setText("");
                    swimmingPool.setSelected(false);
                    aircon.setSelected(false);
                    wifi.setSelected(false);
                    waterHeater.setSelected(false);
                    additional.setSelected(false);
                    propertiesImage.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Submit failed. Properties exist. Please change another username.");
                    propertiesName.setText("");
                    propertiesLbl.setForeground(Color.RED);
                }
            }
        } else if (eve.getSource() == backButton) {
            this.dispose();
            new PropertiesPage();
        }
    }

    public void keyTyped(KeyEvent eve) {}

    public void keyPressed(KeyEvent eve) {
        if (eve.getSource() == propertiesCost || eve.getSource() == room || eve.getSource() == bathroom
            || eve.getSource() == parkingslot || eve.getSource() == rentalRate || eve.getSource() == buileup) {
            if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b' || eve.getKeyChar() == '.') { 
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