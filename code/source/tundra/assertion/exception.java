package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-08 20:51:15 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.StringHelper;
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
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    IData criteria = IDataUtil.getIData(cursor, "$exception");
		    String message = IDataUtil.getString(cursor, "$message");
		
		    raised(service, scope == null ? pipeline : scope, criteria, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// throws an assertion exception if the invocation of the give service does not throw an exception matching the given criteria
	public static void raised(String service, IData pipeline, IData criteria, String assertionMessage) {
	    String exceptionClassName = null;
	    String exceptionMessagePattern = null;
	    boolean exceptionMessagePatternLiteral = false;
	
	    if (criteria != null) {
	        IDataCursor cc = criteria.getCursor();
	        exceptionClassName = IDataUtil.getString(cc, "class");
	        IData messageCriteria = IDataUtil.getIData(cc, "message");
	        cc.destroy();
	
	        if (messageCriteria != null) {
	            IDataCursor mc = messageCriteria.getCursor();
	            exceptionMessagePattern = IDataUtil.getString(mc, "pattern");
	            exceptionMessagePatternLiteral = BooleanHelper.parse(IDataUtil.getString(mc, "literal?"));
	            mc.destroy();
	        }
	    }
	
	    raised(service, pipeline, exceptionClassName, exceptionMessagePattern, exceptionMessagePatternLiteral, assertionMessage);
	}
	
	// throws an assertion exception if the invocation of the give service does not throw an exception matching the given criteria
	public static void raised(String service, IData pipeline, String exceptionClassName, String exceptionMessagePattern, boolean exceptionMessagePatternLiteral, String assertionMessage) {
	    Throwable exception = null;
	
	    try {
	        tundra.service.invoke(service, pipeline);
	    } catch(Throwable ex) {
	        exception = ex;
	    } finally {
	        boolean assertionFailed = (exception == null) ||
	                (exceptionClassName != null && !instance(exception, exceptionClassName)) ||
	                (exceptionMessagePattern != null && !StringHelper.match(exception.getMessage(), exceptionMessagePattern, exceptionMessagePatternLiteral));
	
	        if (assertionFailed) throw new AssertionError(getMessage(exception, assertionMessage, exceptionClassName, exceptionMessagePattern, exceptionMessagePatternLiteral));
	    }
	}
	
	// returns true if the given object is an instance of the given className
	protected static boolean instance(Object object, String className) {
	    boolean result = false;
	
	    try {
	        result = tundra.object.instance(object, className);
	    } catch(ServiceException ex) { }
	
	    return result;
	}
	
	protected static String getMessage(Throwable exception, String assertionMessage, String exceptionClassName, String exceptionMessagePattern, boolean exceptionMessagePatternLiteral) {
	    String message = null;
	
	    if (exceptionClassName != null || exceptionMessagePattern != null) {
	        String criteriaMessage = null;
	        if (exceptionClassName != null) criteriaMessage = java.text.MessageFormat.format("that is an instance of class ''{0}''", exceptionClassName);
	        if (exceptionMessagePattern != null) {
	            if (criteriaMessage != null) {
	                if (exceptionMessagePatternLiteral) {
	                    criteriaMessage = java.text.MessageFormat.format("{0} with a message that literally matches ''{1}''", criteriaMessage, exceptionMessagePattern);
	                } else {
	                    criteriaMessage = java.text.MessageFormat.format("{0} with a message that matches the regular expression /{1}/", criteriaMessage, exceptionMessagePattern);
	                }
	            } else {
	                if (exceptionMessagePatternLiteral) {
	                    criteriaMessage = java.text.MessageFormat.format("with a message that literally matches ''{0}''", exceptionMessagePattern);
	                } else {
	                    criteriaMessage = java.text.MessageFormat.format("with a message that matches the regular expression /{0}/", exceptionMessagePattern);
	                }
	            }
	        }
	        message = java.text.MessageFormat.format("an exception {0} was expected but none was thrown", criteriaMessage);
	    } else {
	        message = "an exception was expected but none was thrown";
	    }
	
	    if (assertionMessage == null) {
	        assertionMessage = java.text.MessageFormat.format("Assertion failed: {0}", message);
	    } else {
	        assertionMessage = java.text.MessageFormat.format("Assertion failed: {0} ({1})", assertionMessage, message);
	    }
	
	    return assertionMessage;
	}
	// --- <<IS-END-SHARED>> ---
}

