package tundra.support.system;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2022-11-04 12:27:50 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.flow.variable.GlobalVariableHelper;
// --- <<IS-END-IMPORTS>> ---

public final class global

{
	// ---( internal utility methods )---

	final static global _instance = new global();

	static global _newInstance() { return new global(); }

	static global _cast(Object o) { return (global)o; }

	// ---( server methods )---




	public static final void export (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(export)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $system.globals.json
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$system.globals.json", GlobalVariableHelper.export());
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
		// [i] field:0:required $system.globals.json
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String importJSON = IDataHelper.get(cursor, "$system.globals.json", String.class);
		    GlobalVariableHelper.merge(importJSON);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

