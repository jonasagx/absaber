import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Transeunte extends Thread {

	private Socket s = null;

	/*	static{
		Network network = new Network();
	}
	private static Network network = null;
*/
	
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
				request = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
				response = new DataOutputStream(this.s.getOutputStream());
				System.out.println(request.readLine());
				response.writeBytes("Message received\n");
			} catch (IOException e){
				System.err.println(e.getMessage() + "\n" + e.getStackTrace());
			}
		}
	}
}
	