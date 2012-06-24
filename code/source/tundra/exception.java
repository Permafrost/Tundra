package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-24 12:57:19 EST
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class exception

{
	// ---( internal utility methods )---

	final static exception _instance = new exception();

	static exception _newInstance() { return new exception(); }

	static exception _cast(Object o) { return (exception)o; }

	// ---( server methods )---




	public static final void raise (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(raise)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String message = IDataUtil.getString(cursor, "$message");
		  raise(message);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// throws a new ServiceException with the class and message from the given Throwable, which
	// is useful because java services are hard-wired to only throw ServiceExceptions
	public static void raise(Throwable[] exceptions) throws ServiceException {
	  if (exceptions != null) raise(message(exceptions));
	}
	
	// throws a new ServiceException with the class and message from the given Throwable, which
	// is useful because java services are hard-wired to only throw ServiceExceptions
	public static void raise(Throwable exception) throws ServiceException {
	  if (exception != null) raise(message(exception));
	}
	
	// throws a new ServiceException with the given message
	public static void raise(String message) throws ServiceException {
	  throw new ServiceException(message == null ? "" : message);
	}
	
	// returns an exception as a string
	public static String message(Throwable exception) {
	  return exception.getClass().getName() + ": " + exception.getMessage();
	}
	
	// returns a list of exceptions as a string
	public static String message(Throwable[] exceptions) {
	  StringBuilder msg = new StringBuilder();
	  if (exceptions != null) {
	    for (int i = 0; i < exceptions.length; i++) {
	      if (exceptions[i] != null) {
	        msg.append("[").append(i).append("]: ").append(message(exceptions[i]));
	        if (i < exceptions.length - 1) msg.append("\n");
	      }
	    }
	  }
	  return msg.toString();
	}
	// --- <<IS-END-SHARED>> ---
}

