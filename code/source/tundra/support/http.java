package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-01-28 13:05:47.125
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class http

{
	// ---( internal utility methods )---

	final static http _instance = new http();

	static http _newInstance() { return new http(); }

	static http _cast(Object o) { return (http)o; }

	// ---( server methods )---




	public static final void status (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(status)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $code
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  int code = Integer.parseInt(IDataUtil.getString(cursor, "$code"));
		  IDataUtil.put(cursor, "$message", status(code));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	protected static java.util.Map<Integer, String> statusMap = createStatusMap();
	
	// creates a map of the standard HTTP status codes
	// <http://www.iana.org/assignments/http-status-codes/http-status-codes.txt>
	protected static java.util.Map<Integer, String> createStatusMap() {
	  java.util.Map<Integer, String> map = new java.util.TreeMap<Integer, String>();
	
	  map.put(100, "Continue");
	  map.put(101, "Switching Protocols");
	  map.put(102, "Processing");
	  map.put(200, "OK");
	  map.put(201, "Created");
	  map.put(202, "Accepted");
	  map.put(203, "Non-Authoritative Information");
	  map.put(204, "No Content");
	  map.put(205, "Reset Content");
	  map.put(206, "Partial Content");
	  map.put(207, "Multi-Status");
	  map.put(208, "Already Reported");
	  map.put(226, "IM Used");
	  map.put(300, "Multiple Choices");
	  map.put(301, "Moved Permanently");
	  map.put(302, "Found");
	  map.put(303, "See Other");
	  map.put(304, "Not Modified");
	  map.put(305, "Use Proxy");
	  map.put(306, "Reserved");
	  map.put(307, "Temporary Redirect");
	  map.put(308, "Permanent Redirect");
	  map.put(400, "Bad Request");
	  map.put(401, "Unauthorized");                          
	  map.put(402, "Payment Required");                      
	  map.put(403, "Forbidden");                             
	  map.put(404, "Not Found");                             
	  map.put(405, "Method Not Allowed");                    
	  map.put(406, "Not Acceptable");                        
	  map.put(407, "Proxy Authentication Required");         
	  map.put(408, "Request Timeout");                       
	  map.put(409, "Conflict");                              
	  map.put(410, "Gone");                                  
	  map.put(411, "Length Required");                       
	  map.put(412, "Precondition Failed");                   
	  map.put(413, "Request Entity Too Large");              
	  map.put(414, "Request-URI Too Long");
	  map.put(415, "Unsupported Media Type");                
	  map.put(416, "Requested Range Not Satisfiable");       
	  map.put(417, "Expectation Failed");                    
	  map.put(422, "Unprocessable Entity");                  
	  map.put(423, "Locked");                                
	  map.put(424, "Failed Dependency");                     
	  map.put(425, "Unassigned");
	  map.put(426, "Upgrade Required");                      
	  map.put(427, "Unassigned");
	  map.put(428, "Precondition Required");                 
	  map.put(429, "Too Many Requests");                     
	  map.put(430, "Unassigned");
	  map.put(431, "Request Header Fields Too Large");       
	  map.put(500, "Internal Server Error");                 
	  map.put(501, "Not Implemented");                       
	  map.put(502, "Bad Gateway");                           
	  map.put(503, "Service Unavailable");                   
	  map.put(504, "Gateway Timeout");                       
	  map.put(505, "HTTP Version Not Supported");            
	  map.put(506, "Variant Also Negotiates");
	  map.put(507, "Insufficient Storage");                  
	  map.put(508, "Loop Detected");                         
	  map.put(509, "Unassigned");    
	  map.put(510, "Not Extended");                          
	  map.put(511, "Network Authentication Required");      
	
	  return map;
	}
	
	// returns the standard message for the given HTTP status code
	// <http://www.iana.org/assignments/http-status-codes/http-status-codes.txt>
	public static String status(int code) {
	  String message = statusMap.get(code);
	  if (message == null) message = "Unassigned";
	  
	  return message;
	}
	// --- <<IS-END-SHARED>> ---
}

