package tundra.support.exception;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-06-30T05:35:12.592
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import javax.activation.MimeType;
import java.io.IOException;
import permafrost.tundra.content.Content;
import permafrost.tundra.data.IDataHelper;
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
		    String message = IDataHelper.get(cursor, "$message", String.class);
		    Object content = IDataHelper.get(cursor, "$content");
		    MimeType contentType = IDataHelper.get(cursor, "$content.type", MimeType.class);

		    throw new TransportException(message, new Content(BytesHelper.normalize(content), contentType));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

