/*
 * Class Name : TxtFile
 * Coder      : Chee Wan Ying
 * Purpose    : To read the txt file.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TxtFile {
    public static ArrayList<ArrayList<String>> read(String fileName) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        try {
            File f = new File(fileName + ".txt");
            Scanner data = new Scanner(f);
            while (data.hasNextLine()) {
              String[] tmp = data.nextLine().split(",");
              res.add(new ArrayList<String>(Arrays.asList(tmp))); // Returns a fixed-size list backed by the specified array
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
}
