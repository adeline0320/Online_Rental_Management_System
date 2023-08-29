
/*Class name : User class
 *Coder : Adeline Low Hui Min
 *Purpose : Superclass for all users
 * 
 */
public class User 
{
    private String fullName;
    private String contactNum;
    private char gender;
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String fullName, String contactNum, char gender, String username, String password) {
        this.fullName = fullName;
        this.contactNum = contactNum;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNumber(String contactNum) {
        this.contactNum = contactNum;
    }

    public char getGender() {
        return gender;
    } 

    public void setGender(char gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString(){
        return getFullName() + "," + getContactNum() + "," + getGender() + "," + 
                getUsername() + "," + getPassword();
    }
}