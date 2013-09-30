import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transeunte extends Thread {

    private Socket s = null;
    private Network n = null;
    public Transeunte(Socket s){
    	this.s = s;
	this.start();
    }
	
    @Override
    public void run(){
    	BufferedReader request = null;
    	DataOutputStream response = null;
		while(true){			
		    try{
		    	request = new BufferedReader(
	                        new InputStreamReader(this.s.getInputStream()));
			
		    	response = new DataOutputStream(this.s.getOutputStream());
		    	response.writeBytes(Interpreter.doGet(request.readLine()));
		    } catch (IOException e){
		    	System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		    }
	            break;
		}
        try {
            this.s.close();
        } catch (IOException ex) {
            Logger.getLogger(Transeunte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
	
