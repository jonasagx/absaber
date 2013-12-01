
public class Interpreter {
    
    private static String[] tokens = {"GET", "POST", 
        "HEAD", "PUT", "DELETE", "TRACE", "CONNECT", "PATCH"};
    
    /*
     * Structure of an HTTP request:
     * 
     * COMMAND /ADDRESS HTTP-VERSION
     * 
     * Example:
     * GET / HTTP/1.1
     */
    
    @SuppressWarnings("null")
	public static String understand(StringBuilder http_request){
        if(http_request != null || http_request.length() > 0){        	
        	String[] raw = http_request.toString().split(" ");
        	
        	switch(raw[0]){
        		case "GET": return doGet(http_request);
        		
        		default: return "HTTP/1.1 200 OK" +
								"Date: Mon, 23 May 2005 22:38:34 GMT" +
								"Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)" +
								"Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT" +
								"Etag: '3f80f-1b6-3e1cb03b'" +
								"Content-Type: text/html; charset=UTF-8" +
								"Content-Length: 131" +
								"Connection: close" +
								"\n\n" +
								"<html><head>" + 
								"<title>An Example Page</title>" +
								"</head><body>" +
								"Hello World, this is a very simple HTML document." +
								"</body></html>";
        	}
        }
        return "";
    }
    
    public static String doGet(StringBuilder request){
    	System.out.println("Done");
    	String response = "HTTP/1.1 200 Ok" +
				"Date: Mon, 23 May 2005 22:38:34 GMT" +
				"Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)" +
				"Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT" +
				"Etag: '3f80f-1b6-3e1cb03b'" +
				"Content-Type: text/html; charset=UTF-8" +
				"Content-Length: " + request.toString().length() + 
				"Connection: close" +
				"\n\n" +
				request.toString();
    	return response;
    }
}