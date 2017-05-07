package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 14:34:36 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.security.ACLHelper;
import permafrost.tundra.server.security.GroupHelper;
// --- <<IS-END-IMPORTS>> ---

public final class acl

{
	// ---( internal utility methods )---

	final static acl _instance = new acl();

	static acl _newInstance() { return new acl(); }

	static acl _cast(Object o) { return (acl)o; }

	// ---( server methods )---




	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $acl.name
		// [i] field:1:optional $groups.allowed
		// [i] field:1:optional $groups.denied
		// [i] field:0:optional $force? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$acl.name", String.class);
		    String[] allowed = IDataHelper.get(cursor, "$groups.allowed", String[].class);
		    String[] denied = IDataHelper.get(cursor, "$groups.denied", String[].class);
		    boolean force = IDataHelper.getOrDefault(cursor, "$force?", Boolean.class, false);
		
		    ACLHelper.create(name, GroupHelper.findOrCreate(allowed), GroupHelper.findOrCreate(denied), force);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

