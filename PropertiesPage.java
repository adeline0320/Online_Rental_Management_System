/*Class name : PropertiesPage
*Purpose : To display properties details for admin,property agents and owners
*/

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PropertiesPage extends JFrame implements ActionListener {
	public PropertyAgent pa = PropertyAgentLogin.pa;
	public PropertyOwner po = PropertyOwnerLogin.po;
    Boolean adminLogin = AdminLogin.ad;

	JLabel bannerLbl, instruction, propertiesLbl;
	ButtonGroup bg;
	JButton AddButton;
	JButton DeleteButton,DeleteButtonID;
	JButton EditButton, backButton;
	JList propertiesList;
	JScrollPane scrollPane;

	PropertiesControl pc = new PropertiesControl();
	JFrame frame;
	JPanel showProp;
	
    ArrayList<Property> propList = pc.viewProperties();
	DefaultListModel<Property> model = pc.listModel(propList);

	public PropertiesPage() {
		frame = new JFrame("PROPERTIES");
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel header = new JPanel(new BorderLayout());

        JLabel title = new JLabel("PROPERTIES",SwingConstants.CENTER);
        title.setFont(new Font("Verdana",Font.BOLD,40));
        header.add(title,BorderLayout.CENTER);
		
		// Add button constructor
        AddButton = new JButton("ADD");
        AddButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        AddButton.addActionListener(this);
        panel.add(AddButton);
		
		// Delete button constructor
        DeleteButton = new JButton("DELETE");
        DeleteButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        DeleteButton.addActionListener(this);
        panel.add(DeleteButton);

		// Delete button using ID constructor
        DeleteButtonID = new JButton("DELETE USING ID");
        DeleteButtonID.setFont(new Font("Ink Free", Font.BOLD, 20));
        DeleteButtonID.addActionListener(this);
        panel.add(DeleteButtonID);
		
		// Edit button constructor
        EditButton = new JButton("EDIT");
        EditButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        EditButton.addActionListener(this);
        panel.add(EditButton);

		// Back button constructor
		backButton = new JButton("BACK");
        backButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        backButton.addActionListener(this);
        panel.add(backButton);  
        
        header.add(panel,BorderLayout.SOUTH);
         
        propertiesList = new JList(model);
        propertiesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        propertiesList.setLayoutOrientation(JList.VERTICAL);
        propertiesList.setPreferredSize(new Dimension(10,100000));
        propertiesList.setFixedCellHeight(350);

		showProp = new JPanel(new BorderLayout());
        showProp.setBorder(new EmptyBorder(10,10,10,10));
        
        propertiesList.setCellRenderer(new PropertiesRender());
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(propertiesList);
        showProp.add(scrollPane,BorderLayout.CENTER);
		frame.add(showProp,BorderLayout.CENTER);
		
		frame.add(header,BorderLayout.NORTH);
		frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent eve) {
		if (eve.getSource() == AddButton) {
            frame.dispose();
			new PropertiesDetails();
		} else if(eve.getSource() == DeleteButton) {
			int index = propertiesList.getSelectedIndex();
			int result = JOptionPane.showConfirmDialog(frame,"Do you want to delete this property?", "Delete user?",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                try {
                    propList.remove(index);
                    PropertiesControl.updateFile(propList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                model.remove(index);
                JOptionPane.showMessageDialog(null,
                "The properties has been deleted and list has been updated.");

            } else if(result == JOptionPane.NO_OPTION){
                this.dispose();
            }		
            
		} else if (eve.getSource() == EditButton || eve.getSource() == DeleteButtonID) {
			frame.dispose();
			new PropertyGetID();
		} else if (eve.getSource() == backButton) {
			frame.dispose();
			if (pa != null) {
				new PropertyAgentHomePage();
			} else if (po != null) {
				new PropertyOwnerHomePage();
			} else if(adminLogin == true){
                new AdminHomeGUI();
            }
		}
	}
}