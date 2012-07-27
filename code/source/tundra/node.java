package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-27 19:46:21.774
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
		// [i] field:0:optional $node
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String node = IDataUtil.getString(cursor, "$node");
		  IDataUtil.put(cursor, "$exists?", "" + exists(node));
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
		// [i] field:0:optional $type {&quot;service&quot;,&quot;record&quot;,&quot;interface&quot;,&quot;Flat File Schema&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:required $nodes
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String parent = IDataUtil.getString(cursor, "$interface");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  String type = IDataUtil.getString(cursor, "$type");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  IDataUtil.put(cursor, "$nodes", list(parent, pattern, type, recurse));
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
		  IDataUtil.put(cursor, "$type", type(node));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the type of the given node
	public static String type(String node) {
	  return node == null? null : type(com.wm.app.b2b.server.ns.Namespace.current().getNode(com.wm.lang.ns.NSName.create(node)));
	}
	
	// returns the type of the given node
	public static String type(com.wm.lang.ns.NSNode node) {
	  return node == null? null : node.getNodeTypeObj().toString();
	}
	
	// returns true if the given namespace node exists
	public static boolean exists(String name) {
	  return name != null && com.wm.app.b2b.server.ns.Namespace.current().nodeExists(com.wm.lang.ns.NSName.create(name));
	}
	
	// returns a list of all the leaf nodes defined under the given parent node on this server
	public static String[] list(String parent, String pattern, String type, boolean recurse) {
	  com.wm.lang.ns.NSNode node = null;
	
	  if (parent == null) {
	    node = com.wm.app.b2b.server.ns.Namespace.current().getRootNode();
	  } else {
	    node = com.wm.app.b2b.server.ns.Namespace.current().getNode(com.wm.lang.ns.NSName.create(parent));
	  }
	
	  java.util.SortedSet<String> listing = null;
	  if (node instanceof com.wm.lang.ns.NSInterface) {
	    listing = list((com.wm.lang.ns.NSInterface)node, pattern, type, recurse);
	  } else {
	    listing = new java.util.TreeSet<String>();
	  }
	
	  return listing.toArray(new String[0]);
	}
	
	// returns a list of all the child leaf nodes defined under the given parent node on this server
	public static java.util.SortedSet<String> list(com.wm.lang.ns.NSInterface parent, String pattern, String type, boolean recurse) {
	  java.util.SortedSet<String> children = new java.util.TreeSet<String>();
	
	  if (parent != null) {
	    com.wm.lang.ns.NSNode[] nodes = parent.getNodes();
	
	    for (int i = 0; i < nodes.length; i++) {
	      if (nodes[i] instanceof com.wm.lang.ns.NSInterface && recurse) {
	        java.util.SortedSet<String> grandchildren = list((com.wm.lang.ns.NSInterface)nodes[i], pattern, type, recurse);
	        children.addAll(grandchildren);
	      }
	
	      String name = nodes[i].getNSName().toString();
	      boolean include = (pattern == null || java.util.regex.Pattern.matches(pattern, name)) && 
	                        (type == null || type.equals(nodes[i].getNodeTypeObj().toString()));
	      
	      if (include) children.add(name);
	    }
	  }
	
	  return children;
	}
	// --- <<IS-END-SHARED>> ---
}

