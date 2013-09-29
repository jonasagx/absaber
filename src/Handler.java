import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Handler {
    private static ServerSocket s = null;
    private Socket client = null;
    private ArrayList<Socket> clients = new ArrayList<Socket>();
    
    public Handler(){
    	s = createServer(8080);
    }

    //It waits for a new socket connection and trow it for a thread
    public void listen(){
    	System.out.println("Listining on port " + s.getLocalPort());
    	while(true){
    		try{
    			this.client = s.accept();
    			System.out.println("Connections: " + clients.size());
    			clients.add(this.client);
    			new Transeunte(this.client);
    		} catch (IOException e){
    			System.out.println(e.getMessage());
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
