package tundra.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-04 09:25:18.980
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class response

{
	// ---( internal utility methods )---

	final static response _instance = new response();

	static response _newInstance() { return new response(); }

	static response _cast(Object o) { return (response)o; }

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
	protected static java.util.Map<Integer, String> statusMap = new java.util.TreeMap<Integer, String>();

	// creates a map of the standard HTTP status codes
	// <http://www.iana.org/assignments/http-status-codes/http-status-codes.txt>
	static {
	  statusMap.put(100, "Continue");
	  statusMap.put(101, "Switching Protocols");
	  statusMap.put(102, "Processing");
	  statusMap.put(200, "OK");
	  statusMap.put(201, "Created");
	  statusMap.put(202, "Accepted");
	  statusMap.put(203, "Non-Authoritative Information");
	  statusMap.put(204, "No Content");
	  statusMap.put(205, "Reset Content");
	  statusMap.put(206, "Partial Content");
	  statusMap.put(207, "Multi-Status");
	  statusMap.put(208, "Already Reported");
	  statusMap.put(226, "IM Used");
	  statusMap.put(300, "Multiple Choices");
	  statusMap.put(301, "Moved Permanently");
	  statusMap.put(302, "Found");
	  statusMap.put(303, "See Other");
	  statusMap.put(304, "Not Modified");
	  statusMap.put(305, "Use Proxy");
	  statusMap.put(306, "Reserved");
	  statusMap.put(307, "Temporary Redirect");
	  statusMap.put(308, "Permanent Redirect");
	  statusMap.put(400, "Bad Request");
	  statusMap.put(401, "Unauthorized");
	  statusMap.put(402, "Payment Required");
	  statusMap.put(403, "Forbidden");
	  statusMap.put(404, "Not Found");
	  statusMap.put(405, "Method Not Allowed");
	  statusMap.put(406, "Not Acceptable");
	  statusMap.put(407, "Proxy Authentication Required");
	  statusMap.put(408, "Request Timeout");
	  statusMap.put(409, "Conflict");
	  statusMap.put(410, "Gone");
	  statusMap.put(411, "Length Required");
	  statusMap.put(412, "Precondition Failed");
	  statusMap.put(413, "Request Entity Too Large");
	  statusMap.put(414, "Request-URI Too Long");
	  statusMap.put(415, "Unsupported Media Type");
	  statusMap.put(416, "Requested Range Not Satisfiable");
	  statusMap.put(417, "Expectation Failed");
	  statusMap.put(422, "Unprocessable Entity");
	  statusMap.put(423, "Locked");
	  statusMap.put(424, "Failed Dependency");
	  statusMap.put(425, "Unassigned");
	  statusMap.put(426, "Upgrade Required");
	  statusMap.put(427, "Unassigned");
	  statusMap.put(428, "Precondition Required");
	  statusMap.put(429, "Too Many Requests");
	  statusMap.put(430, "Unassigned");
	  statusMap.put(431, "Request Header Fields Too Large");
	  statusMap.put(500, "Internal Server Error");
	  statusMap.put(501, "Not Implemented");
	  statusMap.put(502, "Bad Gateway");
	  statusMap.put(503, "Service Unavailable");
	  statusMap.put(504, "Gateway Timeout");
	  statusMap.put(505, "HTTP Version Not Supported");
	  statusMap.put(506, "Variant Also Negotiates");
	  statusMap.put(507, "Insufficient Storage");
	  statusMap.put(508, "Loop Detected");
	  statusMap.put(509, "Unassigned");
	  statusMap.put(510, "Not Extended");
	  statusMap.put(511, "Network Authentication Required");
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

