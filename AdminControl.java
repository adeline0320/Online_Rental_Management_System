/*Class name : AdminControl
* Purpose : Work as Control in MVC to take datas from different classes
*/
import java.util.*;

public class AdminControl
{
    private PotentialTenant pt = new PotentialTenant();
    private PropertyAgent agents = new PropertyAgent();
    private PropertyOwner owners = new PropertyOwner();
    private PropertiesControl prop = new PropertiesControl();
    private UserControl uc = new UserControl();

    //Coder :Adeline Low Hui Min
    //To show tenant registration and pending registration list
    public void getTenant() {
        ArrayList <PotentialTenant> tenant = pt.readTenantList();
        ArrayList<PotentialTenant> pending = uc.readTenantPendingFile();
        UserListGUI userGui = new UserListGUI(tenant, "PendingTenant", pending, "PotentialTenantList");
        userGui.createGUI("Potential Tenant");
        userGui.dispose();
    }

    //Coder :Adeline Low Hui Min
    //To show property owner registration and pending registration list
    public void getPropOwner() {
        ArrayList<PropertyOwner> owner = owners.readOwnerList();
        ArrayList<PropertyOwner> pending = uc.readOwnerPendingFile();
        UserListGUI userGui = new UserListGUI(owner, "PendingOwner", pending, "PropertyOwnerList");
        userGui.createGUI("Property Owner");
        userGui.dispose();
    }

    //Coder :Adeline Low Hui Min
    //To show property agent registration and pending registration list
    public void getPropAgent() {
        ArrayList<PropertyAgent> agent = agents.readAgentList();
        ArrayList<PropertyAgent> pending = uc.readAgentPendingFile();
        UserListGUI usergui = new UserListGUI(agent, "PendingAgent", pending, "PropertyAgentList");
        usergui.createGUI("Property Agent");
        usergui.dispose();
    }

    //Coder :Adeline Low Hui Min
    //To show form for adding admin account
    public void getAdmin() {
        new AddAdminAccountGUI();
    }

    //Coder :Adeline Low Hui Min
    //To show properties page
    public void getProperties() {
        PropertiesPage list = new PropertiesPage();
        list.dispose();
    }

    //Coder :Adeline Low Hui Min
    //To show report
    public void getReport() {
        ArrayList<Property>propList = prop.viewProperties();
       new ReportGUI(propList);
       
    }
}
