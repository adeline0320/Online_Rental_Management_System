/*
 * Class Name : PotentialTenant
 * Coder      : Chee Wan Ying
 * Purpose    : 1. To initialize Potential Tenant login and registration
                2. Read and write PotentialTenantList.txt file
                3. Add new potential tenant to PendingTenant.txt file for waiting admin approve
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PotentialTenant extends User {
    public PotentialTenant() {}

    // Coder : Chee Wan Ying
    // Initialize potential tenant login
    public PotentialTenant(String username, String password) {
        super(username, password);
    }

    // Coder : Chee Wan Ying
    // Initialize potential tenant registration
    public PotentialTenant(String f, String n, char g, String u, String p) {
        super(f,n,g,u,p);
    }

    // Coder : Chee Wan Ying
    // Read PotentialTenantList.txt file
    public ArrayList<PotentialTenant> readTenantList() {
        ArrayList<ArrayList<String>> data = TxtFile.read("PotentialTenantList"); 
        ArrayList<PotentialTenant> list = new ArrayList<>();
        for (ArrayList<String> temp : data) {
            String name = temp.get(0);
            String num = temp.get(1);
            char gen = temp.get(2).charAt(0);
            String user = temp.get(3);
            String pass = temp.get(4);
            list.add(new PotentialTenant(name, num, gen,user, pass)); 
        }
        return list;
    }

    // Coder : Chee Wan Ying
    // Modify PotentialTenantList.txt file
    public void updateFile(ArrayList<PotentialTenant> newList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < newList.size();i++){
            sb.append(newList.get(i) + "\n");
        }
        try {
            Files.write(Paths.get("PotentialTenantList.txt"),sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Coder : Chee Wan Ying
    // Add new potential tenant to PendingTenant.txt file for waiting admin approve
    public void pendingTenantList(PotentialTenant pending) throws IOException{
        File file = new File("PendingTenant.txt");
        FileWriter fileWriter = new FileWriter(file,true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(pending.toString()+ "\n");
        bufferedWriter.close();
    }
}