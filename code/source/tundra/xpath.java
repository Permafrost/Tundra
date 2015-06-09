package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-04 20:21:24 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.xml.XPathHelper;
// --- <<IS-END-IMPORTS>> ---

public final class xpath

{
	// ---( internal utility methods )---

	final static xpath _instance = new xpath();

	static xpath _newInstance() { return new xpath(); }

	static xpath _cast(Object o) { return (xpath)o; }

	// ---( server methods )---




	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:required $expression
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String encoding = IDataUtil.getString(cursor, "$encoding");
		    String expression = IDataUtil.getString(cursor, "$expression");
		    IData namespace = IDataUtil.getIData(cursor, "$namespace");
		    boolean result = false;

		    if (content != null) result = XPathHelper.exists(StreamHelper.normalize(content, encoding), expression, namespace);

		    IDataUtil.put(cursor, "$exists?", "" + result);
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

