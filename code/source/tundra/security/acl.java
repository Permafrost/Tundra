package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-29 19:33:59 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
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
		// [i] field:0:optional $acl.name
		// [i] field:1:optional $groups.allowed
		// [i] field:1:optional $groups.denied
		// [i] field:0:optional $force? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataUtil.getString(cursor, "$acl.name");
		    String[] allowed = IDataUtil.getStringArray(cursor, "$groups.allowed");
		    String[] denied = IDataUtil.getStringArray(cursor, "$groups.denied");
		    boolean force = BooleanHelper.parse(IDataUtil.getString(cursor, "$force?"));
		
		    ACLHelper.create(name, GroupHelper.findOrCreate(allowed), GroupHelper.findOrCreate(denied), force);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

