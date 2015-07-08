package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-08 21:26:23 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.NameHelper;
// --- <<IS-END-IMPORTS>> ---

public final class dns

{
	// ---( internal utility methods )---

	final static dns _instance = new dns();

	static dns _newInstance() { return new dns(); }

	static dns _cast(Object o) { return (dns)o; }

	// ---( server methods )---




	public static final void localhost (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(localhost)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $domain
		// [o] field:0:required $host
		// [o] field:0:required $ip
		IDataUtil.merge(NameHelper.localhost(), pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void resolve (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resolve)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $name
		// [o] field:0:required $domain
		// [o] field:0:required $host
		// [o] field:0:required $ip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataUtil.merge(NameHelper.resolve(IDataUtil.getString(cursor, "$name")), pipeline);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

