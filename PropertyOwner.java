/*
 * Class Name : PropertyOwner
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : 1. To initialize Property Agent login and registration
                2. Read and write PropertyAgentList.txt file
                3. Add new property agent to PendingTenant.txt file for waiting admin approve
*/

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PropertyOwner extends User {
    public PropertyOwner() {}

    // Coder : Jenniffer Teh Sue Ling
    // Initialize property owner login
    public PropertyOwner(String username, String password) {
        super(username, password);
    }
    
    // Coder : Jenniffer Teh Sue Ling
    // Initialize property owner registration
    public PropertyOwner(String f, String n, char g,String u, String p) {
        super(f,n,g,u,p);
    }

    // Coder : Jenniffer Teh Sue Ling
    // Read PropertOwner.txt file
    public ArrayList<PropertyOwner>readOwnerList() {
        ArrayList<ArrayList<String>> data = TxtFile.read("PropertyOwnerList"); 
        ArrayList<PropertyOwner> ownerList = new ArrayList<>();
        for (ArrayList<String> temp : data) {
            String name = temp.get(0);
            String num = temp.get(1);
            char gen = temp.get(2).charAt(0);
            String user = temp.get(3);
            String pass = temp.get(4);
            ownerList.add(new PropertyOwner(name, num, gen,user, pass)); 
        }
        return ownerList;
    }

    // Coder : Jenniffer Teh Sue Ling
    // Modify PropertyOwnerList.txt file
    public void updateFile(ArrayList<PropertyOwner> newList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < newList.size();i++){
            sb.append(newList.get(i) + "\n");
        }
        try {
            Files.write(Paths.get("PropertyOwnerList.txt"),sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}