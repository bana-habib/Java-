package coursework2;

import java.io.File;

/**
 *
 * @author bh6779k
 */
public class Read {

    public String getFolderName(String FileName) {
        //used to read both text files 
        try {
            new File(FileName).mkdir(); // this is the code that makes the new file
            createTextFile(FileName + "Notes.txt");
            createTextFile(FileName + "Coursework.txt");
        } catch (Exception ex) {
            System.out.println("Problem! ");  // this is the error message 
        }

        return FileName;
    }

    private void createTextFile(String string) {
        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
