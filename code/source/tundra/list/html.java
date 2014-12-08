package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-08 12:50:29.431
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class html

{
	// ---( internal utility methods )---

	final static html _instance = new html();

	static html _newInstance() { return new html(); }

	static html _cast(Object o) { return (html)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$list");
		  if (list != null) IDataUtil.put(cursor, "$list", decode(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void encode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$list");
		  if (list != null) IDataUtil.put(cursor, "$list", encode(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// html decodes the given string
	public static String[] decode(String[] input) {
	  if (input == null) return null;
	
	  String[] output = new String[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = tundra.html.decode(input[i]);
	  }
	  return output;
	}
	
	// html encodes the given string
	public static String[] encode(String[] input) {
	  if (input == null) return null;
	
	  String[] output = new String[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = tundra.html.encode(input[i]);
	  }
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

