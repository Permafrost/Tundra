package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-27 15:01:58.189
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class node

{
	// ---( internal utility methods )---

	final static node _instance = new node();

	static node _newInstance() { return new node(); }

	static node _cast(Object o) { return (node)o; }

	// ---( server methods )---




	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $name
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String name = IDataUtil.getString(cursor, "$name");
		  IDataUtil.put(cursor, "$exists?", "" + exists(name));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $name
		// [o] record:0:required $node
		// [o] - field:0:required package
		// [o] - field:0:required name
		// [o] - field:0:required type
		// [o] - field:0:optional comment
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String name = IDataUtil.getString(cursor, "$name");
		  IDataUtil.put(cursor, "$node", get(name));
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:required $nodes
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String parent = IDataUtil.getString(cursor, "$interface");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  IDataUtil.put(cursor, "$nodes", list(parent, recurse));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// formats the given namespace node's properties as an IData document
	public static IData get(String name) {
	  IData output = null;
	  com.wm.lang.ns.NSNode node = com.wm.app.b2b.server.ns.Namespace.current().getNode(name);
	
	  if (node != null) {
	    output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();
	
	    try {
	      IDataUtil.put(cursor, "package", node.getPackage().getName());
	      IDataUtil.put(cursor, "name", node.getNSName().toString());
	      IDataUtil.put(cursor, "type", node.getNodeTypeObj().toString());
	      IDataUtil.put(cursor, "comment", node.getComment());
	    } finally {
	      cursor.destroy();
	    }
	  }
	
	  return output;
	}
	
	// returns true if the given namespace node exists
	public static boolean exists(String name) {
	  return name != null && com.wm.app.b2b.server.ns.Namespace.current().nodeExists(com.wm.lang.ns.NSName.create(name));
	}
	
	// returns a list of all the leaf nodes defined on this server
	public static String[] list(boolean recurse) {
	  return list((String)null, recurse);
	}
	
	// returns a list of all the leaf nodes defined under the given parent node on this server
	public static String[] list(String parent, boolean recurse) {
	  com.wm.lang.ns.NSNode node = null;
	
	  if (parent == null) {
	    node = com.wm.app.b2b.server.ns.Namespace.current().getRootNode();
	  } else {
	    node = com.wm.app.b2b.server.ns.Namespace.current().getNode(com.wm.lang.ns.NSName.create(parent));
	  }
	
	  java.util.SortedSet<String> listing = null;
	  if (node instanceof com.wm.lang.ns.NSInterface) {
	    listing = list((com.wm.lang.ns.NSInterface)node, recurse);
	  } else {
	    listing = new java.util.TreeSet<String>();
	    listing.add(node.getNSName().toString());
	  }
	
	  return listing.toArray(new String[0]);
	}
	
	// returns a list of all the child leaf nodes defined under the given parent node on this server
	public static java.util.SortedSet<String> list(com.wm.lang.ns.NSInterface parent, boolean recurse) {
	  java.util.SortedSet<String> children = new java.util.TreeSet<String>();
	
	  if (parent != null) {
	    com.wm.lang.ns.NSNode[] nodes = parent.getNodes();
	
	    for (int i = 0; i < nodes.length; i++) {
	      if (nodes[i] instanceof com.wm.lang.ns.NSInterface) {
	        if (recurse) {
	          java.util.SortedSet<String> grandchildren = list((com.wm.lang.ns.NSInterface)nodes[i], recurse);
	          children.addAll(grandchildren);
	        }
	      } else {
	        // only add leaves to the listing
	        children.add(nodes[i].getNSName().toString());
	      }
	    }
	  }
	
	  return children;
	}
	// --- <<IS-END-SHARED>> ---
}

