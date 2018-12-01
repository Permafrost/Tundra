package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-07-25 17:31:09 EST
// -----( ON-HOST: 192.168.20.5

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.security.SecureRandom;
import java.util.Random;
import permafrost.tundra.data.IDataHelper;
// --- <<IS-END-IMPORTS>> ---

public final class security

{
	// ---( internal utility methods )---

	final static security _instance = new security();

	static security _newInstance() { return new security(); }

	static security _cast(Object o) { return (security)o; }

	// ---( server methods )---




	public static final void random (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(random)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] object:0:required $generator.random
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$generator.random", Holder.RANDOM_GENERATOR);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static class Holder {
	    private static final SecureRandom RANDOM_GENERATOR = new SecureRandom();
	}
	
	public static Random getRandom() {
	    return Holder.RANDOM_GENERATOR;
	}
	// --- <<IS-END-SHARED>> ---
}

