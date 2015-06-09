package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:35:57 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataHTMLParser;
import permafrost.tundra.html.HTMLHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
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



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    String encoding = IDataUtil.getString(cursor, "$encoding");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    IDataUtil.put(cursor, "$content", emit(list, mode, encoding));
		} catch (java.io.IOException ex) {
		    ExceptionHelper.raise(ex);
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
	    output[i] = HTMLHelper.decode(input[i]);
	  }
	  return output;
	}

	// html encodes the given string
	public static String[] encode(String[] input) {
	  if (input == null) return null;

	  String[] output = new String[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = HTMLHelper.encode(input[i]);
	  }
	  return output;
	}

	// converts an IData[] document list to HTML
	public static Object emit(IData[] input, String mode, String encoding) throws IOException {
	    IData document = IDataFactory.create();
	    IDataCursor cursor = document.getCursor();
	    IDataUtil.put(cursor,  "recordWithNoID", input);
	    cursor.destroy();

	    return ObjectHelper.convert(IDataHTMLParser.getInstance().emit(document, encoding), encoding, mode);
	}
	// --- <<IS-END-SHARED>> ---
}

