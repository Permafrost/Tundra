package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-11-19 10:29:17.529
// -----( ON-HOST: TNFDEVWAP103.test.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		// [i] field:0:required $name
		// [o] field:0:required $domain
		// [o] field:0:required $host
		// [o] field:0:required $ip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  java.net.InetAddress address = java.net.InetAddress.getLocalHost();
		  IDataUtil.put(cursor, "$domain", address.getCanonicalHostName().toLowerCase());
		  IDataUtil.put(cursor, "$host", address.getHostName().toLowerCase());
		  IDataUtil.put(cursor, "$ip", address.getHostAddress().toLowerCase());
		} catch (java.net.UnknownHostException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void resolve (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resolve)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $domain
		// [o] field:0:required $host
		// [o] field:0:required $ip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String name = IDataUtil.getString(cursor, "$name");
		  java.net.InetAddress address = java.net.InetAddress.getByName(name);
		  IDataUtil.put(cursor, "$domain", address.getCanonicalHostName().toLowerCase());
		  IDataUtil.put(cursor, "$host", address.getHostName().toLowerCase());
		  IDataUtil.put(cursor, "$ip", address.getHostAddress().toLowerCase());
		} catch (java.net.UnknownHostException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

