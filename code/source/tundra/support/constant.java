package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-06 16:42:03 EST
// -----( ON-HOST: 172.16.70.129

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




	public static final void noop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(noop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final String DEFAULT_CHARACTER_ENCODING = java.nio.charset.Charset.defaultCharset().name();
	public static final int    DEFAULT_BUFFER_SIZE        = 4096;
	// --- <<IS-END-SHARED>> ---
}

