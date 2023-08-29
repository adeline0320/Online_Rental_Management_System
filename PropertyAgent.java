/*
 * Class Name : PropertyAgent
 * Coder      : Chee Wei Jae
 * Purpose    : 1. To initialize Property Agent login and registration
                2. Read and write PropertyAgentList.txt file
                3. Add new property agent to PendingTenant.txt file for waiting admin approve
*/

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class PropertyAgent extends User {
    public PropertyAgent() {}

    // Coder : Chee Wei Jae
    // Initialize property agent login
    public PropertyAgent(String username, String password) {
        super(username, password);
    }
    
    // Coder : Chee Wei Jae
    // Initialize property agent registration
    public PropertyAgent(String f, String c, char g, String u, String p) {
        super(f,c,g,u,p);
    }

    // Coder : Chee Wei Jae
    // Read PropertyAgent.txt file
    public ArrayList<PropertyAgent> readAgentList() {
        ArrayList<ArrayList<String>> data = TxtFile.read("PropertyAgentList"); 
        ArrayList<PropertyAgent> agent = new ArrayList<>();
        for (ArrayList<String> temp : data) {
            String name = temp.get(0);
            String num = temp.get(1);
            char gen = temp.get(2).charAt(0);
            String user = temp.get(3);
            String pass = temp.get(4);
            agent.add(new PropertyAgent(name,num,gen,user,pass)); 
        }
        return agent;
    }

    // Coder : Chee Wei Jae
    // Modify PropertyAgentList.txt file
    public void updateFile(ArrayList<PropertyAgent> newList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < newList.size();i++){
            sb.append(newList.get(i) + "\n");
        }
        try {
            Files.write(Paths.get("PropertyAgentList.txt"),sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Coder : Chee Wei Jae
    // Add new property agent to PendingAgent.txt file for waiting admin approve
    public void pendingAgentList(PropertyAgent agent) throws IOException {
        File file = new File("PendingAgent.txt");
        FileWriter fileWriter = new FileWriter(file,true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(agent.toString() + "\n");
        bufferedWriter.close();
    }
}