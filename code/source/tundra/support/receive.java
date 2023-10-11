package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2023-06-15 05:01:38 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.server.ServiceHelper;
// --- <<IS-END-IMPORTS>> ---

public final class receive

{
	// ---( internal utility methods )---

	final static receive _instance = new receive();

	static receive _newInstance() { return new receive(); }

	static receive _cast(Object o) { return (receive)o; }

	// ---( server methods )---




	public static final void respond (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(respond)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $response.content
		// [i] field:0:optional $response.content.type
		// [i] field:0:optional $response.content.encoding
		// [i] field:0:optional $response.content.id
		// [i] object:0:optional $exception
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.getOrDefault(cursor, "$response.content", Object.class, "");
		    String contentType = IDataHelper.get(cursor, "$response.content.type", String.class);
		    Charset charset = IDataHelper.get(cursor, "$response.content.encoding", Charset.class);
		    String responseID = IDataHelper.get(cursor, "$response.content.id", String.class);
		    Throwable exception = IDataHelper.get(cursor, "$exception", Throwable.class);
		
		    int responseCode;
		    InputStream responseBody;
		
		    List<NSService> stack = ServiceHelper.getCallStack();
		    if (stack.size() > 0) stack.remove(stack.size() - 1); // remove call to this service
		    boolean initiator = stack.size() <= 1;
		
		    if (initiator) {
		        if (exception == null) {
		            responseCode = 202;
		            responseBody = InputStreamHelper.normalize(content, charset);
		        } else {
		            Throwable initialException = ExceptionHelper.getInitialCause(exception);
		            String initialExceptionClass = initialException.getClass().getName();
		            if ("permafrost.tundra.content.MalformedException".equals(initialExceptionClass)) {
		                responseCode = 400;
		            } else if ("permafrost.tundra.lang.AuthenticationException".equals(initialExceptionClass)) {
		                responseCode = 401;
		            } else if ("permafrost.tundra.lang.SecurityException".equals(initialExceptionClass)) {
		                responseCode = 403;
		            } else if ("permafrost.tundra.content.UnsupportedException".equals(initialExceptionClass)) {
		                responseCode = 406;
		            } else if ("permafrost.tundra.content.DuplicateException".equals(initialExceptionClass)) {
		                responseCode = 409;
		            } else if ("permafrost.tundra.content.ValidationException".equals(initialExceptionClass)) {
		                responseCode = 422;
		            } else {
		                responseCode = 500;
		            }
		            responseBody = InputStreamHelper.normalize(ExceptionHelper.getMessage(exception, false, false), charset);
		        }
		
		        IData headers = null;
		        if (responseID != null) {
		            headers = IDataFactory.create();
		            IDataCursor headersCursor = headers.getCursor();
		            headersCursor.insertAfter("X-Response-ID", responseID);
		            headersCursor.destroy();
		        }
		
		        ServiceHelper.respond(responseCode, null, headers, responseBody, contentType, charset);
		    } else if (exception != null) {
		        ExceptionHelper.raise(exception);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

