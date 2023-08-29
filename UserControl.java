/*Class : UserControl
*Purpose : To control user activities such as 
    read pending files,accept user,save user pending registration and reject user registration

*/

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class UserControl
{

    ArrayList<User> userList = new ArrayList<>();
    UserControl(){

    }
    // Coder : Adeline Low Hui Min
    //To accept user registration and add into user files
    public void addUser(ArrayList<? extends User> a,String filename) throws IOException {

        File file = new File(filename + ".txt");
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(int i = 0; i < a.size();i++) {
            bufferedWriter.append(a.get(i) + "\n");
        }
        bufferedWriter.close();
    }

    // Coder : Adeline Low Hui Min
    //To add user registration into pending files
    public void pendingUserList(User user) throws IOException {
        String filename = "";
        if(user instanceof PotentialTenant){
            filename = "PendingTenant.txt";
        }
        else if(user instanceof PropertyAgent){
            filename = "PendingAgent.txt";
        }
        else if(user instanceof PropertyOwner){
            filename = "PendingOwner.txt";
        }
        File file = new File(filename);
        FileWriter fileWriter = new FileWriter(file,true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(user.toString()+ "\n");
        bufferedWriter.close();
    }
    
     // Coder : Adeline Low Hui Min
    //To update pending list after reject user registration
    public void removePending(ArrayList<? extends User > a, String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.size(); i++){
            sb.append(a.get(i) + "\n");
        }
        Files.write(Paths.get(filename + ".txt"), sb.toString().getBytes());
    }
    
    //Coder :Adeline Low Hui Min
    //To read pending list from tenant pending files
    public ArrayList<PotentialTenant> readTenantPendingFile() {
        ArrayList<PotentialTenant>tenantList = new ArrayList<>();
       
        try {
            List<String> lines = Files.readAllLines(Paths.get("PendingTenant.txt"));
            for(int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                char gender = items[2].charAt(0);
                tenantList.add(new PotentialTenant (items[0],items[1],gender,items[3],items[4]));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return tenantList;
    }

    //Coder :Adeline Low Hui Min
    //To read pending list from tenant owner files
    public ArrayList<PropertyOwner> readOwnerPendingFile() {
        ArrayList<PropertyOwner>ownerList = new ArrayList<>();
       
        try {
            List<String> lines = Files.readAllLines(Paths.get("PendingOwner.txt"));
            for(int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                char gender = items[2].charAt(0);
                ownerList.add(new PropertyOwner (items[0],items[1],gender,items[3],items[4]));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return ownerList;
    }

    //Coder :Adeline Low Hui Min
    //To read pending list from tenant agent files
    public ArrayList<PropertyAgent> readAgentPendingFile() {
        ArrayList<PropertyAgent>agentList = new ArrayList<>();
       
        try {
           List<String> lines = Files.readAllLines(Paths.get("PendingAgent.txt"));
           for(int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                char gender = items[2].charAt(0);
                agentList.add(new PropertyAgent(items[0],items[1],gender,items[3],items[4]));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return agentList;
    }
    
    //Coder :Adeline Low Hui Min
    //To return user list in defaultlistmodel to be used in jlist
     public DefaultListModel<? extends User> listModel (ArrayList<? extends User> tenantList) {
        DefaultListModel< User> model = new DefaultListModel<>();
        for(User val : tenantList){
            model.addElement(val);
        }
        return model;
    }
}