package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-07-14T05:25:35.560
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSType;
import java.util.SortedSet;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.server.NodeHelper;
// --- <<IS-END-IMPORTS>> ---

public final class node

{
	// ---( internal utility methods )---

	final static node _instance = new node();

	static node _newInstance() { return new node(); }

	static node _cast(Object o) { return (node)o; }

	// ---( server methods )---




	public static final void access (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(access)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $node
		// [i] record:1:optional $permissions
		// [i] - field:0:required type {"list","read","write","execute"}
		// [i] - field:0:optional acl
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String node = IDataHelper.get(cursor, "$node", String.class);
		    IData[] permissions = IDataHelper.get(cursor, "$permissions", IData[].class);

		    NodeHelper.setPermissions(node, permissions);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $node
		// [o] field:0:required $exists? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String node = IDataHelper.get(cursor, "$node", String.class);
		    IDataHelper.put(cursor, "$exists?", NodeHelper.exists(node), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $interface
		// [i] field:0:optional $pattern
		// [i] field:0:optional $type {"service","record","interface","Flat File Schema"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] field:1:required $nodes
		// [o] field:0:required $nodes.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String parent = IDataHelper.get(cursor, "$interface", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String type = IDataHelper.get(cursor, "$type", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    SortedSet<String> set = NodeHelper.list(parent, pattern, type, recurse);

		    IDataHelper.put(cursor, "$nodes", set.toArray(new String[0]));
		    IDataHelper.put(cursor, "$nodes.length", set.size(), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void type (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(type)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $node
		// [o] field:0:optional $type {"service","record","interface","Flat File Schema"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String node = IDataHelper.get(cursor, "$node", String.class);
		    NSType type = NodeHelper.getNodeType(node);
		    if (type != null) IDataHelper.put(cursor, "$type", type.toString());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

