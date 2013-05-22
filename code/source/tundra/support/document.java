package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-02-24 15:41:03 EST
// -----( ON-HOST: 172.16.189.144

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

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
	// wrapper class for fully qualified IData keys
	public static class Key {
	  public static final String SEPARATOR = "/";
	  public static final java.util.regex.Pattern INDEX_PATTERN = java.util.regex.Pattern.compile("\\[(-?\\d+?)\\]$");
	
	  protected boolean hasIndex = false;
	  protected int index = 0;
	  protected String key = null;
	
	  public Key(String key) {
	    java.util.regex.Matcher matcher = INDEX_PATTERN.matcher(key);
	    StringBuffer buffer = new StringBuffer();
	    while(matcher.find()) {
	      hasIndex = true;
	      index = Integer.parseInt(matcher.group(1));
	      matcher.appendReplacement(buffer, "");
	    }
	    matcher.appendTail(buffer);
	    this.key = buffer.toString();
	  }
	
	  public boolean hasIndex() {
	    return hasIndex;
	  }
	
	  public int getIndex() {
	    return index;
	  }
	
	  public String toString() {
	    return key;
	  }
	
	  public static java.util.Queue<Key> parse(String key) {
	    String[] parts = key.split(SEPARATOR);
	    java.util.Queue<Key> queue = new java.util.ArrayDeque<Key>(parts.length);
	
	    for (int i = 0; i < parts.length; i++) {
	      queue.add(new Key(parts[i]));
	    }
	    return queue;
	  }
	
	  public static boolean isFullyQualified(String key) {
	    return key != null && (key.contains(SEPARATOR) || INDEX_PATTERN.matcher(key).find());
	  }
	}
	
	// returns the value associated with the given key from the given IData document
	public static Object get(IData input, String key) {
	  Object value = null;
	  if (input != null && key != null) {
	    // try finding a value that matches the literal key
	    IDataCursor cursor = input.getCursor();
	    try {
	      value = IDataUtil.get(cursor, key);
	    } finally {
	      cursor.destroy();
	    }
	
	    // if value wasn't found using the literal key, the key could be fully qualified
	    if (value == null && Key.isFullyQualified(key)) value = get(input, Key.parse(key));
	  }
	  return value;
	}
	
	// gets a value from an IData document with a fully qualified key
	protected static Object get(IData input, java.util.Queue<Key> keys) {
	  Object value = null;
	
	  if (input != null && keys != null && keys.size() > 0) {
	    IDataCursor cursor = input.getCursor();
	    Key key = keys.remove();
	
	    if (keys.size() > 0) {
	      if (key.hasIndex()) {
	        IData[] array = IDataUtil.getIDataArray(cursor, key.toString());
	        value = get(tundra.list.object.get(array, key.getIndex()), keys);
	      } else {
	        value = get(IDataUtil.getIData(cursor, key.toString()), keys);
	      }
	    } else {
	      if (key.hasIndex()) {
	        Object[] array = IDataUtil.getObjectArray(cursor, key.toString());
	        value = tundra.list.object.get(array, key.getIndex());
	      } else {
	        value = IDataUtil.get(cursor, key.toString());
	      }
	    }
	
	    cursor.destroy();
	  }
	
	  return value;
	}
	
	// sets the value associated with the given key in the given IData document
	public static IData put(IData input, String key, Object value) {
	  return put(input, key == null ? null : Key.parse(key), value);
	}
	
	// sets the value associated with the given fully qualified key in the given IData document
	protected static IData put(IData input, java.util.Queue<Key> keys, Object value) {
	  if (keys != null && keys.size() > 0) {
	    if (input == null) input = IDataFactory.create();
	
	    IDataCursor cursor = input.getCursor();
	    Key key = keys.remove();
	
	    if (keys.size() > 0) {
	      if (key.hasIndex()) {
	        IData[] array = IDataUtil.getIDataArray(cursor, key.toString());
	        IData child = null;
	        try { child = tundra.list.object.get(array, key.getIndex()); } catch(ArrayIndexOutOfBoundsException ex) { }
	        value = tundra.list.object.put(array, put(child, keys, value), key.getIndex(), IData.class);
	      } else {
	        value = put(IDataUtil.getIData(cursor, key.toString()), keys, value);
	      }
	    } else if (key.hasIndex()) {
	      Class klass = Object.class;
	      if (value != null) {
	        if (value instanceof String) {
	          klass = String.class;
	        } else if (value instanceof IData) {
	          klass = IData.class;
	        }
	      }
	      value = tundra.list.object.put(IDataUtil.getObjectArray(cursor, key.toString()), value, key.getIndex(), klass);
	    }
	    IDataUtil.put(cursor, key.toString(), value);
	    cursor.destroy();
	  }
	
	  return input;
	}
	// --- <<IS-END-SHARED>> ---
}

