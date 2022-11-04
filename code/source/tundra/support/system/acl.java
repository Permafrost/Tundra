package tundra.support.system;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2022-11-04 13:04:16 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.security.ACLHelper;
// --- <<IS-END-IMPORTS>> ---

public final class acl

{
	// ---( internal utility methods )---

	final static acl _instance = new acl();

	static acl _newInstance() { return new acl(); }

	static acl _cast(Object o) { return (acl)o; }

	// ---( server methods )---




	public static final void export (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(export)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $system.acls.json
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$system.acls.json", ACLHelper.export());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void merge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(merge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $system.acls.json
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String importJSON = IDataHelper.get(cursor, "$system.acls.json", String.class);
		    ACLHelper.merge(importJSON);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

