package tundra.support.table;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-05-25 16:06:22 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void _ (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(_)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns a new table with all null elements removed
	public static <T> T[][] compact(T[][] table) {
	  if (table == null) return table;
	
	  java.util.List<T[]> list = new java.util.ArrayList<T[]>(table.length);
	  
	  for (int i = 0; i < table.length; i++) {
	    T[] row = tundra.list.object.compact(table[i]);
	    if (row != null) list.add(row);
	  }
	
	  return list.toArray(java.util.Arrays.copyOf(table, 0));
	}
	
	// returns a new table with all empty or null elements removed
	public static <T> T[][] squeeze(T[][] table) {
	  if (table == null || table.length == 0) return null;
	
	  java.util.List<T[]> list = new java.util.ArrayList<T[]>(table.length);
	  
	  for (int i = 0; i < table.length; i++) {
	    T[] row = tundra.list.object.squeeze(table[i]);
	    if (row != null) list.add(row);
	  }
	
	  table = list.toArray(java.util.Arrays.copyOf(table, 0));
	  if (table.length == 0) table = null;
	
	  return table;
	}
	// --- <<IS-END-SHARED>> ---
}

