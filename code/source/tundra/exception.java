package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-30 16:01:31.660
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
		// [i] object:0:optional $exception
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String message = IDataUtil.getString(cursor, "$message");
		  Throwable exception = (Throwable)IDataUtil.get(cursor, "$exception");
		
		  if (exception != null) {
		    raise(exception);
		  } else {
		    raise(message);
		  } 
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
	  if (exception != null) {
	    if (exception instanceof ServiceException) {
	      throw (ServiceException)exception;
	    } else {
	    raise(message(exception));
	    }
	  }
	}
	
	// throws a new ServiceException with the given message
	public static void raise(String message) throws ServiceException {
	  throw new ServiceException(message == null ? "" : message);
	}
	
	// returns an exception as a string
	public static String message(Throwable exception) {
	  String message = "";
	
	  if (exception != null) {
	    if (exception instanceof ServiceException) {
	      message = exception.getMessage();
	    } else {
	      message = exception.getClass().getName() + ": " + exception.getMessage();
	    }
	  }
	
	  return message;
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
	
	public static IData[] stack(Throwable t) {
	  IData[] output = null;
	
	  if (t != null) {
	    StackTraceElement[] stack = t.getStackTrace();
	    output = new IData[stack.length];
	
	    for (int i = 0; i < stack.length; i++) {
	      output[i] = IDataFactory.create();
	      IDataCursor cursor = output[i].getCursor();
	      IDataUtil.put(cursor, "description", stack[i].toString());
	      IDataUtil.put(cursor, "file", stack[i].getFileName());
	      IDataUtil.put(cursor, "class", stack[i].getClassName());
	      IDataUtil.put(cursor, "method", stack[i].getMethodName());
	      IDataUtil.put(cursor, "line", "" + stack[i].getLineNumber());
	      IDataUtil.put(cursor, "native?", "" + stack[i].isNativeMethod());
	      cursor.destroy();
	    }
	  }
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

