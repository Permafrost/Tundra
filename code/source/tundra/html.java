package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-08 12:51:13.743
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", decode(string));
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", encode(string));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// html decodes the given string
	public static String decode(String input) {
	  if (input == null) return null;
	  return org.springframework.web.util.HtmlUtils.htmlUnescape(input);
	}
	
	// html encodes the given string
	public static String encode(String input) {
	  if (input == null) return null;
	  return org.springframework.web.util.HtmlUtils.htmlEscape(input);
	}
	// --- <<IS-END-SHARED>> ---
}

