import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.EmptyBorder;

public class PotentialTenantPropertyGUI extends JFrame implements ItemListener {    
    JPanel selectionBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel listOfProp = new JPanel();
    JPanel header = new JPanel(new BorderLayout());
    JButton allProp, propType, propPro, propFac, propRentalRate, propRentalPrice, home;
    Choice sort;
    ReportControl rc = new ReportControl();
    JLabel title,sortLabel;
    JFrame guiFrame;
    Property p;
    private ArrayList<Property> list = Property.readPropertiesList();   
    JScrollPane scrollPane;
    
    public PotentialTenantPropertyGUI(ArrayList<Property> propList) {
        list = propList;
    }
    
    public void createGUI() {
        guiFrame = new JFrame("PROPERTIES");
        title = new JLabel ("PROPERTIES", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 40));
        header.add(title,BorderLayout.CENTER);
        DefaultListModel<Property>model = rc.listModel(list);
        
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
      
        allProp = new JButton("All Property");
        allProp.setFont(new Font("Ink Free", Font.BOLD, 18));
        allProp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                guiFrame.dispose();
                ArrayList<Property> list = Property.readPropertiesList();
                ArrayList<Property> temp = new ArrayList<>();
                for (Property val : list) {
                    String status = val.getStatus();
                    if (status.equals("Active")) {
                        temp.add(val);
                    }
                }
                PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(temp);
                p.createGUI();
                }
        });

        propType = new JButton("Property Types");
        propType.setFont(new Font("Ink Free", Font.BOLD, 18));
        propType.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Property Types");
                rg.propertyType();
                guiFrame.dispose();
            }
        });

        propPro = new JButton("Property Projects");
        propPro.setFont(new Font("Ink Free", Font.BOLD, 18));
        propPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListType rg = new ListType("Property Projects");
                rg.propertyProject();
                guiFrame.dispose();
            }
        });

        propFac = new JButton("Property Facilities");
        propFac.setFont(new Font("Ink Free", Font.BOLD, 18));
        propFac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListType rg = new ListType("Property Facilities");
                rg.propertyFac();
                guiFrame.dispose();
            }
        });

        propRentalRate = new JButton("Property Rental Rate");
        propRentalRate.setFont(new Font("Ink Free", Font.BOLD, 18));
        propRentalRate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ListType rg = new ListType("Rental Rate");
                rg.propertyRate();
                guiFrame.dispose();
            }
        });

        propRentalPrice = new JButton("Property Rental Price");
        propRentalPrice.setFont(new Font("Ink Free", Font.BOLD, 18));
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

        home = new JButton("Home");
        home.setFont(new Font("Ink Free", Font.BOLD, 18));
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiFrame.dispose();
                new PotentialTenantHomePage();
            }
        });

        selectionBtn.add(allProp);
        selectionBtn.add(propType);
        selectionBtn.add(propPro);
        selectionBtn.add(propFac);
        selectionBtn.add(propRentalRate);
        selectionBtn.add(propRentalPrice);
        selectionBtn.add(sortLabel);
        selectionBtn.add(sort);
        selectionBtn.add(home);
        header.add(selectionBtn,BorderLayout.SOUTH);
        
        guiFrame.add(header,BorderLayout.NORTH);
        
        guiFrame.add(showProp,BorderLayout.CENTER);
        
        guiFrame.setSize(5000,1000);
        guiFrame.setVisible(true);
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String select = sort.getSelectedItem();

        if (select.equals("Lowest Price")) {
            guiFrame.dispose();
            ArrayList<Property> temp = new ArrayList<>();
            for (Property val : list) {
                String status = val.getStatus();
                if (status.equals("Active")) {
                    temp.add(val);
                }
            }
            Collections.sort(temp, new Property.PriceComparator());
            PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(temp);
            p.createGUI();
        } else if (select.equals("Highest Price")) {
            guiFrame.dispose();
            ArrayList<Property> temp = new ArrayList<>();
            for (Property val : list) {
                String status = val.getStatus();
                if (status.equals("Active")) {
                    temp.add(val);
                }
            }
            Collections.sort(temp, Collections.reverseOrder(new Property.PriceComparator()));
            PotentialTenantPropertyGUI p = new PotentialTenantPropertyGUI(temp);
            p.createGUI();
        }
    }
}