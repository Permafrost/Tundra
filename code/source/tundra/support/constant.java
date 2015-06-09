package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-11-07 09:06:25.812
// -----( ON-HOST: -

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
		// [o] record:0:required $tundra.constants
		// [o] - field:0:required encoding.default
		// [o] - object:0:required buffer.length.default
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IData constants = IDataFactory.create();
		  IDataCursor cc = constants.getCursor();
		  IDataUtil.put(cc, "encoding.default", DEFAULT_CHARACTER_ENCODING);
		  IDataUtil.put(cc, "buffer.length.default", DEFAULT_BUFFER_SIZE);
		  cc.destroy();

		  IDataUtil.put(cursor, "$tundra.constants", constants);
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

