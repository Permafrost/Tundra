package tundra.support.exception;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-23 13:39:20 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import javax.activation.MimeTypeParseException;
import java.io.IOException;
import permafrost.tundra.content.Content;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.TransportException;
// --- <<IS-END-IMPORTS>> ---

public final class transport

{
	// ---( internal utility methods )---

	final static transport _instance = new transport();

	static transport _newInstance() { return new transport(); }

	static transport _cast(Object o) { return (transport)o; }

	// ---( server methods )---




	public static final void raise (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(raise)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $message
		// [i] object:0:optional $content
		// [i] field:0:optional $content.type
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String message = IDataUtil.getString(cursor, "$message");
		    Object content = IDataUtil.get(cursor, "$content");
		    String contentType = IDataUtil.getString(cursor, "$content.type");
		
		    throw new TransportException(message, Content.of(BytesHelper.normalize(content), contentType));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

