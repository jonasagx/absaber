import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

class Handler {
    private static ServerSocket s = null;
    private Socket client = null;
    
    public Handler(){
    	s = createServer(8080);
    }

    //It waits for a new socket connection and throw it for a thread
    public void listen(){
    	Processor p = new Processor();
    	p.start();
    	
    	System.out.println("Listining on port " + s.getLocalPort());
    	
    	while(true){
    		try{
    			this.client = s.accept();
    			p.append(this.client);
    		} catch (IOException e){
    			System.out.println(e.getMessage());
    			p.interrupt();
    			break;
    		}
    	}
    }

    private ServerSocket createServer(int port){
    	ServerSocket tmp = null;
        
    	try{
            tmp = new ServerSocket(port);
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        return tmp;
    }
}

class Processor extends Thread{
    private BufferedReader request = null;
	private DataOutputStream response = null;

	private Stack<Socket> clients = null;
	private boolean busy = false;
	
	public Processor(){
		this.clients = new Stack<Socket>();
	}
	
	@Override
	public void run(){
		System.out.println("Processor is running");
		while(true){
			if(this.clients.size() > 0){
				this.process();
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void process() {
		Socket client = this.clients.pop();
		
		try{
			request = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			
			response = new DataOutputStream(client.getOutputStream());
			
			StringBuilder r = new StringBuilder();
			String tmp;
						
			while( !(tmp = request.readLine()).equals("") ){				
				r.append(tmp);
				System.out.println(tmp);
			}
			
			response.writeBytes( Interpreter.understand(r) );
			client.close();
		} catch (IOException e){
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	public void append(Socket s){
		this.clients.add(s);
		this.busy = true;
	}
	
	public boolean state(){
		return this.busy;
	}	
}