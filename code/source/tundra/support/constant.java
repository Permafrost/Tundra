package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-11-06 19:38:04 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class constant

{
	// ---( internal utility methods )---

	final static constant _instance = new constant();

	static constant _newInstance() { return new constant(); }

	static constant _cast(Object o) { return (constant)o; }

	// ---( server methods )---




	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $encoding.default
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$encoding.default", DEFAULT_CHARACTER_ENCODING);
		  IDataUtil.put(cursor, "$buffer.length.default", DEFAULT_BUFFER_SIZE);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final String DEFAULT_CHARACTER_ENCODING = java.nio.charset.Charset.forName("UTF-8").name();
	public static final int    DEFAULT_BUFFER_SIZE        = 8192;
	// --- <<IS-END-SHARED>> ---
}

