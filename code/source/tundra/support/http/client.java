package tundra.support.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-07-04 15:04:16 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.lang.ns.NSField;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSRecord;
import com.wm.lang.ns.NSService;
import com.wm.lang.ns.NSSignature;
import permafrost.tundra.data.IDataHelper;
// --- <<IS-END-IMPORTS>> ---

public final class client

{
	// ---( internal utility methods )---

	final static client _instance = new client();

	static client _newInstance() { return new client(); }

	static client _cast(Object o) { return (client)o; }

	// ---( server methods )---




	public static final void request (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(request)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required url
		// [i] field:0:required method {"get","post","head","put","delete","options","trace"}
		// [i] field:0:optional loadAs {"bytes","stream"}
		// [i] record:0:required data
		// [i] - record:0:optional args
		// [i] - field:2:optional table
		// [i] - field:0:optional string
		// [i] - object:0:optional bytes
		// [i] - object:0:optional mimeStream
		// [i] - object:0:optional stream
		// [i] - field:0:optional encoding
		// [i] record:0:optional auth
		// [i] - field:0:optional type
		// [i] - field:0:optional user
		// [i] - field:0:optional pass
		// [i] record:0:optional headers
		// [i] field:0:optional timeout
		// [i] field:0:optional connectTimeout
		// [i] field:0:optional maxKeepAliveConnections
		// [i] field:0:optional keepAliveTimeout
		// [i] field:0:optional newSession {"no","yes"}
		// [i] field:0:optional proxyAlias
		// [i] field:0:optional useJSSE {"no","yes"}
		// [o] field:0:required encodedURL
		// [o] record:0:optional header
		// [o] - record:0:required lines
		// [o] - field:0:required status
		// [o] - field:0:required statusMessage
		// [o] record:0:required body
		// [o] - object:0:optional bytes
		// [o] - object:0:optional stream
		IDataCursor cursor = pipeline.getCursor();
		boolean useJSSE = false;

		try {
		    String url = IDataHelper.get(cursor, "url", String.class);
		    boolean isJSSESpecified = IDataHelper.get(cursor, "useJSSE", String.class) != null;

		    useJSSE = !isJSSESpecified && isJSSESupported && url != null && url.toLowerCase().startsWith("https");

		    // try making the request using JSSE for TLS 1.1 and 1.2 support as the default
		    if (useJSSE) IDataHelper.put(cursor, "useJSSE", "yes");

		    pub.clientimpl.http(pipeline);
		} catch(ServiceException ex) {
		    String message = ex.getMessage();
		    boolean isUnauthorized = message != null && message.contains("ISC.0064.9314");

		    if (useJSSE && !isUnauthorized) {
		        // fallback to non-JSSE request
		        IDataHelper.put(cursor, "useJSSE", "no");
		        try {
		            pub.clientimpl.http(pipeline);
		        } catch(ServiceException fe) {
		            throw ex;
		        }
		    } else {
		        throw ex;
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	public static final boolean isJSSESupported = isJSSESupported();

	private static boolean isJSSESupported() {
	    boolean isJSSESupported = true;

	    NSService service = Namespace.getService(NSName.create("pub.client", "http"));
	    if (service != null) {
	        NSSignature signature = service.getSignature();
	        if (signature != null) {
	            NSRecord input = signature.getInput();
	            if (input != null) {
	                NSField field = input.getFieldByName("useJSSE");
	                isJSSESupported = field != null;
	            }
	        }
	    }

	    return isJSSESupported;
	}
	// --- <<IS-END-SHARED>> ---
}

