/*
 * Class Name : Property
 * Coder      : Jenniffer Teh Sue Ling
 * Purpose    : To initialize property and get and set property details
*/

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Property  {
    private ArrayList<Property > list = new ArrayList<>();
    private String propertiesName;
    private String propertiesAddress;
    private String propertiesID;
    private String propertiesCost;
    private String propertiesType;
    private String propertiesRoom;
    private String propertiesBathroom;
    private String propertiesParking;
    private String rentalRate;
    private String propertiesBuiltupSize;
    private String propertiesImage;
    private String status;
    private ArrayList<String> facilities;

    public Property() {}

    //To initialize property
    public Property(String n, String a, String i, String c, String t, String r, String b, String p,ArrayList<String>facilities,String rate,String size,String icon) {
        propertiesName = n;
        propertiesAddress = a;
        propertiesID = i;
        propertiesCost = c;
        propertiesType = t;
        propertiesRoom = r;
        propertiesBathroom = b;
        propertiesParking = p;
        this.facilities = facilities;
        rentalRate = rate;
        propertiesBuiltupSize = size;
        propertiesImage = icon;
        status = "Active";
        list.add(this);
    }
    //to read files
    public Property(String n, String a, String i, String c, String t, String r, String b, String p,ArrayList<String>facilities,String rate,String size,String icon,String s) {
        propertiesName = n;
        propertiesAddress = a;
        propertiesID = i;
        propertiesCost = c;
        propertiesType = t;
        propertiesRoom = r;
        propertiesBathroom = b;
        propertiesParking = p;
        this.facilities = facilities;
        rentalRate = rate;
        propertiesBuiltupSize = size;
        propertiesImage = icon;
        status = s;
        list.add(this);
    }
    
    public ArrayList<Property> getPropertiesList(){
        return list;
    }

    public void setPropertiesList(ArrayList<Property> list){
        this.list = list;
    }

    public ArrayList<String> getFacilities(){
        return facilities;
    }

    public void setFacilities(ArrayList<String>facilities){
        this.facilities = facilities;
    }
    
    public String getPropertiesName() {
        return propertiesName;
    }
    
    public void setPropertiesName(String n) {
        propertiesName = n;
    }
    
    public String getPropertiesAddress() {
        return propertiesAddress;
    }
    
    public void setPropertiesAddress(String a) {
        propertiesAddress = a;
    }
    
    public String getPropertiesID() {
        return propertiesID;
    }
    
    public void setPropertiesID(String i) {
        propertiesID = i;
    }
    
    public String getPropertiesCost() {
        return propertiesCost;
    }
    
    public void setPropertiesCost(String c) {
        propertiesCost = c;
    }

    public String getPropertiesType() {
        return propertiesType;
    }
    
    public void setPropertiesType(String t) {
        propertiesType = t;
    }

    public String getPropertiesRoom() {
        return propertiesRoom;
    }
    
    public void setPropertiesRoom(String r) {
        propertiesRoom = r;
    }
    
    public String getPropertiesBathroom() {
        return propertiesBathroom;
    }
    
    public void setPropertiesBathroom(String b) {
        propertiesBathroom = b;
    }

    public String getPropertiesParking() {
        return propertiesParking;
    }
    
    public void setPropertiesParking(String p) {
        propertiesParking = p;
    }
    
    public String getRentalRate(){
        return rentalRate;
    }

    public void setRentalRate(String rate){
        this.rentalRate = rate;
    }

    public String getBuiltUpSize(){
        return propertiesBuiltupSize;
    }
    public void setBuiltUpSize(String size){
        this.propertiesBuiltupSize = size;
    }
    
    public String getIcon(){
        return propertiesImage;
    }
     
    public void setIcon(String name){
        this.propertiesImage = name;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String s) {
        status = s;
    }
    
    @Override
    public String toString() {
        return getPropertiesName().replace(",","\t") + "," + getPropertiesAddress() + "," + getPropertiesID() + "," 
            + getPropertiesCost() + "," + getPropertiesType() + "," + getPropertiesRoom() + "," 
            + getPropertiesBathroom() + "," + getPropertiesParking() + "," 
            + getFacilities().toString().replace("[","").replace("]","").replace(",","\t") + "," 
            + getRentalRate() + "," + getBuiltUpSize() + "," + getIcon() + "," + getStatus();
    }
    
    // read PropertiesList file
    public static ArrayList<Property> readPropertiesList() {
        ArrayList<Property> list = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(Paths.get("PropertiesList.txt"));
            for(int i = 0 ; i < lines.size();i++){
                String[] items = lines.get(i).split(",");
                List<String> facilities = new ArrayList<String>(Arrays.asList(items[8].split(",")));
                list.add(new Property(items[0],items[1],items[2],items[3],items[4],items[5],
                items[6],items[7],(ArrayList<String>) facilities,items[9],items[10],items[11],
                items[12]));
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    static class PriceComparator implements Comparator<Property>{
        @Override
        public int compare(Property p1 ,Property p2){
            double prop1 = Double.parseDouble(p1.getPropertiesCost());
            double prop2 = Double.parseDouble(p2.getPropertiesCost()); 
           return Double.compare(prop1,prop2);
        }
    }

    public ArrayList<String> removeEmptyString(ArrayList<String>facilities){
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < facilities.size();i++){
            if(!facilities.get(i).trim().isEmpty()){
                list.add(facilities.get(i));
            }
        }
        return list;
    }

    public boolean checkPropertiesnameExist(String n) {
        for (Property t : getPropertiesList()) {
            if (t.getPropertiesName().equals(n)) {
                return false;
            }
        }
        return true;
    }
} 