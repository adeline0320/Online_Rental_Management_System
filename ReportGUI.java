/*Class : ReportGUI
*Purpose : To show UI for report 
*/ 

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.EmptyBorder;

public class ReportGUI extends JFrame implements ItemListener {   
    JPanel selectionBtn = new JPanel();
    JPanel resultPanel = new JPanel();
    JPanel bottomHeader = new JPanel(new BorderLayout());
    JPanel listOfProp = new JPanel();
    JPanel header = new JPanel(new BorderLayout());
    JButton allProp,propType,propStat,propPro,propFac,propRentalRate,propRentalPrice,home;
    Choice sort;
    ReportControl rc = new ReportControl();
    JLabel title,sortLabel,resultLabel,resultNum;
    JFrame guiFrame;
    private ArrayList<Property> list;   
    JScrollPane scrollPane;
    public PropertyAgent pa = PropertyAgentLogin.pa;
	public PropertyOwner po = PropertyOwnerLogin.po;
    Boolean ad = AdminLogin.ad;
    //Coder : Adeline Low Hui Min
    //initialize for the properties list
    public ReportGUI(ArrayList<Property> propList){
        list = propList;
        createGui();
    }
    
    //Coder: Adeline Low Hui Min
    //To show Report UI
    private void createGui(){
        guiFrame = new JFrame("Report");
        title = new JLabel ("Report",SwingConstants.CENTER);
        title.setFont(new Font("Verdana",Font.BOLD,40));
        header.add(title,BorderLayout.CENTER);
        DefaultListModel<Property>model =rc.listModel(list);
        
        JList propertiesList = new JList(model);
        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        propertiesList.setLayoutOrientation(JList.VERTICAL);
        propertiesList.setPreferredSize(new Dimension(10,100000));
        propertiesList.setFixedCellHeight(400);
        
        //show in panel
        JPanel showProp = new JPanel(new BorderLayout());
        showProp.setBorder(new EmptyBorder(10,10,10,10));
        propertiesList.setCellRenderer(new PropertiesRender());
        
        //add into scrollpane for scrollable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(propertiesList);
       
        showProp.add(scrollPane,BorderLayout.CENTER);

        resultLabel = new JLabel("No. of Results :");
        resultLabel.setFont(new Font("Verdana",Font.PLAIN,14));
        resultNum = new JLabel(String.valueOf(list.size()));
        resultPanel.add(resultLabel);
        resultPanel.add(resultNum);
        
        allProp = new JButton("All Property");
        allProp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                guiFrame.dispose();
                new ReportGUI(Property.readPropertiesList()).createGui();
            }
        });

        propType = new JButton("Property Types");
        propType.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Property Types");
                rg.propertyType();
                guiFrame.dispose();
            }
        });

        propStat = new JButton("Property Status");
        propStat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Status");
                rg.propertyStatus();
                guiFrame.dispose();
            }
        });

        propPro = new JButton("Property Projects");
        propPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListType rg = new ListType("Property Projects");
                rg.propertyProject();
                guiFrame.dispose();
            }
        });

        propFac = new JButton("Property Facilities");
        propFac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListType rg = new ListType("Property Facilities");
                rg.propertyFac();
                guiFrame.dispose();
            }
        });

        propRentalRate = new JButton("Property Rental Rate");
        propRentalRate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Rental Rate");
                rg.propertyRate();
                guiFrame.dispose();
            }
        });

        propRentalPrice = new JButton("Property Rental Price");
        propRentalPrice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Rental");
                rg.propertyCost();
                guiFrame.dispose();
            }
        });
        sortLabel  = new JLabel("Sort Rental Price by: ");

        sort = new Choice();
        sort.add("-----");
        sort.add("Lowest Price");
        sort.add("Highest Price");

        sort.addItemListener(this);

        home = new JButton("HOME");
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                guiFrame.dispose();
                if (pa != null) {
                    new PropertyAgentHomePage();
                } else if (po != null) {
                    new PropertyOwnerHomePage();
                } else if (ad != null) {
                    new AdminHomeGUI();
                }
            }
        });

       
        selectionBtn.add(allProp);
        selectionBtn.add(propType);
        selectionBtn.add(propStat);
        selectionBtn.add(propPro);
        selectionBtn.add(propFac);
        selectionBtn.add(propRentalRate);
        selectionBtn.add(propRentalPrice);
        selectionBtn.add(sortLabel);
        selectionBtn.add(sort);
        selectionBtn.add(home);

        bottomHeader.add(resultPanel,BorderLayout.WEST);
        bottomHeader.add(selectionBtn,BorderLayout.EAST);

        header.add(bottomHeader,BorderLayout.SOUTH);
        
        guiFrame.add(header,BorderLayout.NORTH);
        
        guiFrame.add(showProp,BorderLayout.CENTER);
        
        guiFrame.setSize(5000,1000);
        guiFrame.setVisible(true);
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Coder :Adeline Low Hui Min
    //To show the report list after user selects for sorting
    @Override
    public void itemStateChanged(ItemEvent e) {
       String select = sort.getSelectedItem();

       if (select.equals("Lowest Price")){
            guiFrame.dispose();
            Collections.sort(list,new Property.PriceComparator());
            new ReportGUI(list);
            
       }
       else if (select.equals("Highest Price")){
            guiFrame.dispose();
            Collections.sort(list,Collections.reverseOrder(new Property.PriceComparator()));
            new ReportGUI(list);
            
       }
    }
} 