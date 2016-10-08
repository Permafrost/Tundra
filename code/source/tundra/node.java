package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-08 12:13:16 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSType;
import java.util.SortedSet;
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
		    String node = IDataUtil.getString(cursor, "$node");
		    IData[] permissions = IDataUtil.getIDataArray(cursor, "$permissions");
		
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
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String node = IDataUtil.getString(cursor, "$node");
		    IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(NodeHelper.exists(node)));
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
		    String parent = IDataUtil.getString(cursor, "$interface");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String type = IDataUtil.getString(cursor, "$type");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
		    SortedSet<String> set = NodeHelper.list(parent, pattern, type, recurse);
		
		    IDataUtil.put(cursor, "$nodes", set.toArray(new String[set.size()]));
		    IDataUtil.put(cursor, "$nodes.length", IntegerHelper.emit(set.size()));
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
		// [o] field:0:optional $type
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String node = IDataUtil.getString(cursor, "$node");
		    NSType type = NodeHelper.getNodeType(node);
		    if (type != null) IDataUtil.put(cursor, "$type", type.toString());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

