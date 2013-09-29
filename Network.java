import java.util.ArrayList;


public class Network {
	private static ArrayList<Ap> aps = null;
	
	public Network(){
		
	}
	
	public Network(String file){
		this.loadNet(file);
	}
	
	//Loads file and create an ArrayList of aps
	private void loadNet(String file){
		
	}
	
	//Searchs for ap.name based on mac code
	public String search(String mac){
		return null;
	}
	
	public Network getInstance(){
		return this;
	}
}
