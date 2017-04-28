package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-04-28 15:34:57.456
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
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
		// [i] record:1:optional $nodes
		// [i] - field:0:optional node
		// [i] - record:1:optional permissions
		// [i] -- field:0:required type {&quot;list&quot;,&quot;read&quot;,&quot;write&quot;,&quot;execute&quot;}
		// [i] -- field:0:optional acl
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] nodes = IDataHelper.get(cursor, "$nodes", IData[].class);
		    access(nodes);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Grants the specified permissions on the given nodes.
	 *
	 * @param nodes       The nodes to grant the permission on.
	 */
	public static void access(IData[] nodes) {
	    if (nodes != null) {
	        for (IData node : nodes) {
	            if (node != null) {
	                IDataCursor cursor = node.getCursor();
	                try {
	                    String name = IDataHelper.get(cursor, "node", String.class);
	                    IData[] permissions = IDataHelper.get(cursor, "permissions", IData[].class);

	                    NodeHelper.setPermissions(name, permissions);
	                } finally {
	                    cursor.destroy();
	                }
	            }
	        }
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

