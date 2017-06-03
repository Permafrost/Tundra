package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-03 18:04:00 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.text.MessageFormat;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.server.ServiceHelper;
// --- <<IS-END-IMPORTS>> ---

public final class exception

{
	// ---( internal utility methods )---

	final static exception _instance = new exception();

	static exception _newInstance() { return new exception(); }

	static exception _cast(Object o) { return (exception)o; }

	// ---( server methods )---




	public static final void raised (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(raised)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] record:0:optional $exception
		// [i] - field:0:optional class
		// [i] - record:0:optional message
		// [i] -- field:0:optional pattern
		// [i] -- field:0:optional literal? {"false","true"}
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    IData criteria = IDataHelper.get(cursor, "$exception", IData.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    raised(service, scope == null ? pipeline : scope, criteria, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// throws an assertion exception if the invocation of the give service does not throw an exception matching the given criteria
	public static void raised(String service, IData pipeline, IData criteria, String assertionMessage) {
	    Class exceptionClass = null;
	    String exceptionMessagePattern = null;
	    boolean exceptionMessagePatternLiteral = false;
	
	    if (criteria != null) {
	        IDataCursor cc = criteria.getCursor();
	        exceptionClass = IDataHelper.get(cc, "class", Class.class);
	        IData messageCriteria = IDataHelper.get(cc, "message", IData.class);
	        cc.destroy();
	
	        if (messageCriteria != null) {
	            IDataCursor mc = messageCriteria.getCursor();
	            exceptionMessagePattern = IDataHelper.get(mc, "pattern", String.class);
	            exceptionMessagePatternLiteral = IDataHelper.getOrDefault(mc, "literal?", Boolean.class, false);
	            mc.destroy();
	        }
	    }
	
	    raised(service, pipeline, exceptionClass, exceptionMessagePattern, exceptionMessagePatternLiteral, assertionMessage);
	}
	
	// throws an assertion exception if the invocation of the give service does not throw an exception matching the given criteria
	public static void raised(String service, IData pipeline, Class exceptionClass, String exceptionMessagePattern, boolean exceptionMessagePatternLiteral, String assertionMessage) {
	    Throwable exception = null;
	
	    try {
	        ServiceHelper.invoke(service, pipeline);
	    } catch(Throwable ex) {
	        exception = ex;
	    } finally {
	        boolean assertionFailed = (exception == null) ||
	                (exceptionClass != null && !tundra.object.instance(exception, exceptionClass)) ||
	                (exceptionMessagePattern != null && !StringHelper.match(exception.getMessage(), exceptionMessagePattern, exceptionMessagePatternLiteral));
	
	        if (assertionFailed) throw new AssertionError(getMessage(exception, assertionMessage, exceptionClass, exceptionMessagePattern, exceptionMessagePatternLiteral));
	    }
	}
	
	private static String getMessage(Throwable exception, String assertionMessage, Class exceptionClass, String exceptionMessagePattern, boolean exceptionMessagePatternLiteral) {
	    String message = null;
	
	    if (exceptionClass != null || exceptionMessagePattern != null) {
	        String criteriaMessage = null;
	        if (exceptionClass != null) criteriaMessage = MessageFormat.format("that is an instance of class ''{0}''", exceptionClass.getName());
	        if (exceptionMessagePattern != null) {
	            if (criteriaMessage != null) {
	                if (exceptionMessagePatternLiteral) {
	                    criteriaMessage = MessageFormat.format("{0} with a message that literally matches ''{1}''", criteriaMessage, exceptionMessagePattern);
	                } else {
	                    criteriaMessage = MessageFormat.format("{0} with a message that matches the regular expression /{1}/", criteriaMessage, exceptionMessagePattern);
	                }
	            } else {
	                if (exceptionMessagePatternLiteral) {
	                    criteriaMessage = MessageFormat.format("with a message that literally matches ''{0}''", exceptionMessagePattern);
	                } else {
	                    criteriaMessage = MessageFormat.format("with a message that matches the regular expression /{0}/", exceptionMessagePattern);
	                }
	            }
	        }
	        message = MessageFormat.format("an exception {0} was expected but none was thrown", criteriaMessage);
	    } else {
	        message = "an exception was expected but none was thrown";
	    }
	
	    if (assertionMessage == null) {
	        assertionMessage = MessageFormat.format("Assertion failed: {0}", message);
	    } else {
	        assertionMessage = MessageFormat.format("Assertion failed: {0} ({1})", assertionMessage, message);
	    }
	
	    return assertionMessage;
	}
	// --- <<IS-END-SHARED>> ---
}

