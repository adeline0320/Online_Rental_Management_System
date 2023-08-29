/*Class : ListType
Purpose : To display GUI for each button in Report GUI and update Report GUI list

*/

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ListType 
{
    public PotentialTenant t = PotentialTenantLogin.t;
    PropertiesControl pc = new PropertiesControl();
    ReportControl rc = new ReportControl();
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JTextField searchBar, minCost, maxCost;
    private JLabel bannerLbl, instruction, minCostLbl, maxCostLbl;
    private JButton searchBtn, cancelBtn;
    ArrayList<Property> list = Property.readPropertiesList();
    JFrame reportFrame;
    private String newSearchText;

    // Coder : Adeline Low Hui Min
    //To initialize basic User Interface
    public ListType(String title) {
        reportFrame = new JFrame(title);
        panel.setLayout(null);
        
        // banner label constructor
        bannerLbl = new JLabel("-- Search For --");
        bannerLbl.setBounds(50, 10, 450, 50);
        bannerLbl.setFont(new Font("Verdana", Font.BOLD, 50));
        panel.add(bannerLbl);

        instruction = new JLabel("Please type in " + title + " to search.");
        instruction.setBounds(20, 75, 400, 20);
        instruction.setFont(new Font("Verdana", Font.PLAIN, 17));
        panel.add(instruction);
                
        reportFrame.add(panel);
    }
    //Coder :Adeline Low Hui Min
    //To show UI for property type button
    public void propertyType() {
        // search bar textfield constructor
        searchBar = new JTextField();
        searchBar.setBounds(20, 120, 500, 20);
        panel.add(searchBar);
        
        // search button constructor
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("")) { 
                    JOptionPane.showMessageDialog(null, "Please enter the type that you wish to search.");
                } else {
                    reportFrame.dispose();
                    newSearchText = uppercaseString(searchBar.getText());
                    ArrayList<Property> propType = rc.getPropType(newSearchText);
                    if (t != null) {
                        if (tenantProperty(propType).size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Type is not found");
                        }
                        PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(propType));
                        p.createGUI();
                    } else {
                        if (propType.size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Type is not found");
                        }
                        new ReportGUI(propType);
                     
                    }
                }
            }
        });
        panel.add(searchBtn);

        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportFrame.dispose();
                if (t != null) {
                    PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(list));
                    p.createGUI();
                } else {
                    new ReportGUI(list);
                   
                }
            }
        });
        panel.add(cancelBtn);

        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   //Coder :Adeline Low Hui Min
    //To show UI for property status button
    public void propertyStatus() {
        // search bar textfield constructor
        searchBar = new JTextField();
        searchBar.setBounds(20, 120, 500, 20);
        panel.add(searchBar);
         
        // search button constructor
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("") || !searchBar.getText().equals("active") && !searchBar.getText().equals("Active") && !searchBar.getText().equals("inactive") && !searchBar.getText().equals("Inactive")) { 
                    JOptionPane.showMessageDialog(null, "Please enter 'active' / 'inactive' to search.");
                }
                if (searchBar.getText().equals("active") || searchBar.getText().equals("Active") || searchBar.getText().equals("inactive") || searchBar.getText().equals("Inactive")) {
                    reportFrame.dispose();
                    newSearchText = uppercaseString(searchBar.getText());
                    ArrayList<Property> propStatus = rc.getPropertiesStatus(newSearchText);

                    new ReportGUI(propStatus);
                   
                }
            }
        });
        panel.add(searchBtn);

        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportFrame.dispose();
                new ReportGUI(list);
               
            }
        });
        panel.add(cancelBtn);

        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Coder :Adeline Low Hui Min
    //To show UI for property project button
    public void propertyProject() {
        // search bar textfield constructor
        searchBar = new JTextField();
        searchBar.setBounds(20, 120, 500, 20);
        panel.add(searchBar);
         
        // search button constructor
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the project that you wish to search.");
                } else {
                    newSearchText = uppercaseString(searchBar.getText());
                    ArrayList<Property> propProject = rc.getPropProject(newSearchText);
                    reportFrame.dispose();
                    if (t != null) {
                        if (tenantProperty(propProject).size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Project not found");
                        }
                        PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(propProject));
                        p.createGUI();
                    } else {
                        if (propProject.size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Project not found");
                        }
                        new ReportGUI(propProject);
                     
                    }
                }
            }
        });
        panel.add(searchBtn);

        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reportFrame.dispose();
                if (t != null) {
                    PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(list));
                    p.createGUI();
                } else {
                    new ReportGUI(list);
                   
                }
            }
        });
        panel.add(cancelBtn);

        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Coder :Adeline Low Hui Min
    //To show UI for property facilities button
    public void propertyFac() {
        // search bar textfield constructor
        searchBar = new JTextField();
        searchBar.setBounds(20, 120, 500, 20);
        panel.add(searchBar);

        // search button constructor
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter any facilities that you wish to search.");
                } else {
                    newSearchText = uppercaseString(searchBar.getText());
                    ArrayList<Property> propFacilities = rc.getPropFacilities(newSearchText);
                    reportFrame.dispose();
                    if (t != null) {
                        if (tenantProperty(propFacilities).size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Facilities not found");
                        }
                        PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(propFacilities));
                        p.createGUI();
                    } else {
                        if (propFacilities.size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Facilities not found");
                        }
                        new ReportGUI(propFacilities);
                        
                    }
                }
            }
        });
        panel.add(searchBtn);

        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                reportFrame.dispose();
                if (t != null) {
                    PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(list));
                    p.createGUI();
                } else {
                    new ReportGUI(list);
                 
                }
            }
        });
        panel.add(cancelBtn);

        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //Coder :Adeline Low Hui Min
    //To show UI for property rate button
    public void propertyRate(){
        minCostLbl = new JLabel("Minimum Rental Rate : RM ");
        minCostLbl.setBounds(10, 120, 200, 20);
        minCostLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(minCostLbl);
    
        minCost = new JTextField();
        minCost.setBounds(150,120,100,20);
        minCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent eve) {}

            public void keyPressed(KeyEvent eve) {
                if (eve.getSource() == minCost) {
                    if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                        minCost.setEditable(true);
                    } else {
                        minCost.setEditable(false);
                    }
                }
            }
            public void keyReleased(KeyEvent eve) {}
        });
        panel.add(minCost);
    
        maxCostLbl = new JLabel("Maximum Rental Rate: RM ");
        maxCostLbl.setBounds(260, 120, 200, 20);
        maxCostLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(maxCostLbl);
    
        maxCost = new JTextField();
        maxCost.setBounds(400,120,100,20);
        maxCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent eve) {}

            public void keyPressed(KeyEvent eve) {
                if (eve.getSource() == maxCost) {
                    if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                        maxCost.setEditable(true);
                    } else {
                        maxCost.setEditable(false);
                    }
                }
            }
            public void keyReleased(KeyEvent eve) {}
        });
        panel.add(maxCost);

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (maxCost.getText().equals("") || minCost.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill the rates");
                }

                if (!maxCost.getText().equals("") && !minCost.getText().equals("")) {
                    reportFrame.dispose();
                    ReportControl rc = new ReportControl();
                    rc.getPropRate(minCost.getText(), maxCost.getText());
                    ArrayList<Property> propCost = rc.getList();
                    Property px = new Property();
                    px.setPropertiesList(propCost);

                    if (t != null) {
                        if (tenantProperty(propCost).size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Rental rate is not available");
                        }
                        PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(propCost));
                        p.createGUI();
                    } else {
                        if (propCost.size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Rental rate is not available");  
                        }
                        new ReportGUI(propCost);
                      
                    }
                }
            }
        });
        panel.add(searchBtn);
    
        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                reportFrame.dispose();
                if (t != null) {
                    PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(list));
                    p.createGUI();
                } else {
                    new ReportGUI(list);
                 
                }
            }
        });
        panel.add(cancelBtn);
    
        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //Coder :Adeline Low Hui Min
    //To show UI for property cost button
    public void propertyCost() {
        minCostLbl = new JLabel("Minimum Rental : RM ");
        minCostLbl.setBounds(10, 120, 200, 20);
        minCostLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(minCostLbl);
    
        minCost = new JTextField();
        minCost.setBounds(150,120,100,20);
        minCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent eve) {}

            public void keyPressed(KeyEvent eve) {
                if (eve.getSource() == minCost) {
                    if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                        minCost.setEditable(true);
                    } else {
                        minCost.setEditable(false);
                    }
                }
            }
            public void keyReleased(KeyEvent eve) {}
        });
        panel.add(minCost);
    
        maxCostLbl = new JLabel("Maximum Rental : RM ");
        maxCostLbl.setBounds(260, 120, 200, 20);
        maxCostLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(maxCostLbl);
    
        maxCost = new JTextField();
        maxCost.setBounds(400,120,100,20);
        maxCost.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent eve) {}

            public void keyPressed(KeyEvent eve) {
                if (eve.getSource() == maxCost) {
                    if (eve.getKeyChar() >= '0' && eve.getKeyChar() <= '9' || eve.getKeyChar() == '\b') {
                        maxCost.setEditable(true);
                    } else {
                        maxCost.setEditable(false);
                    }
                }
            }
            public void keyReleased(KeyEvent eve) {}
        });
        panel.add(maxCost);

        // search button constructor
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        searchBtn.setLocation(400, 200);
        searchBtn.setSize(120, 40);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maxCost.getText().equals("") || minCost.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill the rentals");
                }

                if (!maxCost.getText().equals("") && !minCost.getText().equals("")) {
                    reportFrame.dispose();
                    ReportControl rc = new ReportControl();
                    rc.getPropCost(minCost.getText(), maxCost.getText());
                    ArrayList<Property> propCost = rc.getList();
                    Property px = new Property();
                    px.setPropertiesList(propCost);

                    if (t != null) {
                        if (tenantProperty(propCost).size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Rental is not available");
                        }
                        PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(propCost));
                        p.createGUI();
                    } else {
                        if (propCost.size() == 0) {
                            JOptionPane.showMessageDialog(reportFrame, "Rental is not available");
                        }
                        new ReportGUI(propCost);
                        
                    }
                }
            }
        });
        panel.add(searchBtn);
    
        // cancel button constructor
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Ink Free", Font.BOLD, 20));
        cancelBtn.setLocation(20, 200);
        cancelBtn.setSize(120, 40);
        cancelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reportFrame.dispose();
                if (t != null) {
                    PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(tenantProperty(list));
                    p.createGUI();
                } else {
                    new ReportGUI(list);
                   
                }
            }
        });
        panel.add(cancelBtn);
    
        reportFrame.setSize(550, 300);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //Coder :Adeline Low Hui Min
    //To make the first user input into uppercase
    private String uppercaseString(String text) {
        String newText = "";
        String firstLetter = text.substring(0, 1);
        String remainingLetters = text.substring(1, text.length());

        firstLetter = firstLetter.toUpperCase();
        newText = firstLetter + remainingLetters;
        return newText;
    }

    // Coder : Chee Wan Ying
    // To get the properties in 'Active' status
    private ArrayList<Property> tenantProperty(ArrayList<Property> l) {
        ArrayList<Property> temp = new ArrayList<>();
        for (Property pl : l) {
            if (pl.getStatus().equals("Active")) {
                temp.add(pl);
            }
        }
        return temp;
    }
}