
public class Interpreter {
    
    private static String[] tokens = {"GET", "POST", 
        "HEAD", "PUT", "DELETE", "TRACE", "CONNECT", "PATCH"};
    
    public static String understand(String http_request){
        String[] raw = http_request.split(" ");
        for(String text : raw){
            for(String token : tokens){
                if(text.equals(token)){
                    
                }
            }
        }
        return null;
    }
    
    public static String doGet(String request){
    	System.out.println(request);
        return "Response\n";
    }
}