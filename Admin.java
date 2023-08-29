/* Class Name : Admin
* Purpose : 1)To initialize Admin login and registration
            2)Add admin account,check login password
            3)Check available admin names and phone number in account
            4)Check Login id and password.
            5)Work as Model in MVC

*/
import java.io.File; 
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.*;

public class Admin 
{
    private String name, phone, id, password;
    private Boolean login = false;

    public Admin() {}
    
    //Coder:Adeline Low Hui Min
    //Initialise for admin login
    public Admin(String id, String password) {
        this.id = id;
        this.password = password;
    }

    //Coder : Adeline Low Hui Min
    //Initialise for admin add account
    public Admin(String name, String phone, String id, String password) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.password = password;
    }

    //Coder : Adeline Low Hui Min
    //To get phone
    public String getName() {
        return name;
    }

    //Coder : Adeline Low Hui Min
    public String getPhone() {
        return phone;
    }

    //Coder : Adeline Low Hui Min
    //To get ID
    public String getID() {
        return id;
    }

    //Coder : Adeline Low Hui Min
    //To getPassword
    public String getPassword() {
        return password;
    }

    //Coder : Adeline Low Hui Min
    //To get login status
    public Boolean getLogin() {
        return login;
    }

    //Coder : Adeline Low Hui Min
    //To set login status
    public void setLogin(Boolean login) {
        this.login = login;
    }

    //Coder : Adeline Low Hui Min
    //To add admin account
    public Boolean addAdminAccount(Admin a) {
        Boolean success = false;
        Boolean found = checkRegister(a);
        if(found == false) {
            try {
                saveFile(a);
                success = true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return success;
    }

    //Coder : Adeline Low Hui Min
    //To check password and id 
    public Boolean checkLogin(Admin a) {
        Boolean found = false;
        ArrayList<Admin> datas = readFile();
        for(int i = 0; i < datas.size(); i++) {
            if(datas.get(i).getID().equals(a.getID()) && datas.get(i).getPassword().equals(a.getPassword())) {

                found = true;
                setLogin(true);
            }
        }
        return found;
    }

    //Coder : Adeline Low Hui Min
    //TO check available names and phones in txt file
    public Boolean checkRegister(Admin a) {
        Boolean found = false;
        ArrayList<Admin> datas = readFile();
        for(int i = 0 ; i < datas.size(); i++){
            if(datas.get(i).getName().equals(a.getName())&& datas.get(i).getPhone().equals(a.getPhone())) { 
                found = true;
            }
        }
        return found;
    }

    //Coder : Adeline Low Hui Min
    @Override
    public String toString() {
        return getName() + "," + getPhone() + "," + getID() + "," + getPassword();
    }

    //Coder : Adeline Low Hui Min
    //To save data into txt file
    public void saveFile(Admin a) throws IOException {
        File file = new File("AdminList.txt");
        FileWriter fileWriter = new FileWriter(file, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append(a.toString() + "\n");
        bufferedWriter.close();
    }

    //Coder : Adeline Low Hui Min
    //To read datas from txt file
    public static ArrayList<Admin> readFile() {
        ArrayList<Admin> a = new ArrayList<Admin>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("AdminList.txt"));
            for(int i = 0 ; i < lines.size();i++){
                String[] items = lines.get(i).split(",");
                a.add(new Admin(items[0],items[1],items[2],items[3]));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return a;
    }
}