import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Network {
    private static HashMap<String, String> aps = new HashMap<String, String>();
	
    public Network(){
	
    }
	
    //Loads file and create an ArrayList of aps
    private static void loadNet(String file_path) throws IOException{
        try (DataInputStream file = new DataInputStream( new BufferedInputStream( 
                     new FileInputStream(
                         new File(file_path))))) {
            String[] line;
            
            while(file.available() > 0){
                line = file.readLine().split("\t");
                aps.put(line[1], line[0]);
                
            }
            System.out.println("APs list loaded");
        }
    }
 	//Searchs for ap.name based on mac code
    public static String search(String mac){
    	return aps.get(mac);
    }
    
    public static void main(String[] args) throws IOException{
        //Network n = new Network();
        Network.loadNet("/home/jonas/aps.tsv");
        System.out.println(Network.search("80:17:7d:46:ae:00"));
    }
}
