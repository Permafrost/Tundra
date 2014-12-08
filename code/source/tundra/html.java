package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-08 14:57:50.895
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



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  IDataUtil.put(cursor, "$content", emit(document, mode, encoding));
		} catch (java.io.IOException ex) {
		  tundra.exception.raise(ex);
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
	protected static final String NULL_ENTITY = "&#x2400;";
	
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
	
	// converts an IData document to HTML
	public static Object emit(IData input, String mode, String encoding) throws java.io.IOException {
	  Object content = emit(input, true);
	
	  if (mode == null || mode.equals("stream")) {
	    content = tundra.stream.normalize(content, encoding);
	  } else if (mode.equals("bytes")) {
	    content = tundra.bytes.normalize(content, encoding);
	  }
	
	  return content;
	}
	
	// converts an IData document to HTML
	public static String emit(IData input, boolean recurse) {
	  if (input == null) return NULL_ENTITY;
	
	  input = tundra.document.normalize(input, recurse);
	  int size = tundra.document.size(input);
	  StringBuilder buffer = new StringBuilder();
	
	  if (size == 0) {
	    buffer.append("&empty;");
	  } else {
	    IDataCursor cursor = input.getCursor();
	    String[] keys = tundra.document.keyset(input);
	
	    // table
	    buffer.append("<table>");
	
	    // thead
	    buffer.append("<thead>");
	    buffer.append("<tr>");
	    buffer.append("<th>Key</th>");
	    buffer.append("<th>Value</th>");
	    buffer.append("</tr>");
	    buffer.append("</thead>");
	
	    // tbody
	    buffer.append("<tbody>");
	    for (String key : keys) {
	      Object value = IDataUtil.get(cursor, key);
	      buffer.append("<tr>");
	      buffer.append("<th>");
	      buffer.append(encode(key));
	      buffer.append("</th>");
	      buffer.append("<td>");
	      if (value == null) {
	        buffer.append(NULL_ENTITY);
	      } else if (recurse) {
	        if (value instanceof IData) {
	          buffer.append(emit((IData)value, recurse));
	        } else if (value instanceof IData[]) {
	          buffer.append(emit((IData[])value, recurse));
	        } else if (value instanceof Object[][]) {
	          buffer.append(emit((Object[][])value));
	        } else if (value instanceof Object[]) {
	          buffer.append(emit((Object[])value));
	        } else {
	          buffer.append(encode(value.toString()));       
	        }
	      } else {
	        buffer.append(encode(value.toString()));
	      }
	      buffer.append("</td>");
	      buffer.append("</tr>");
	    }
	    buffer.append("</tbody>");
	    buffer.append("</table>");
	
	    cursor.destroy();
	  }
	
	  return buffer.toString();
	}
	
	// converts an IData[] document list to HTML
	public static String emit(IData[] input, boolean recurse) {
	  if (input == null) return NULL_ENTITY;
	
	  String[] keys = tundra.list.document.keys(input);
	  StringBuilder buffer = new StringBuilder();
	
	  if (input.length == 0) {
	    buffer.append("&empty;");
	  } else {
	    // table
	    buffer.append("<table>");
	
	    // thead
	    buffer.append("<thead>");
	    buffer.append("<tr>");
	    for (String key : keys) {
	      buffer.append("<th>");
	      buffer.append(encode(key));
	      buffer.append("</th>");
	    }
	    buffer.append("</tr>");
	    buffer.append("</thead>");
	
	    // tbody
	    buffer.append("<tbody>");
	    for (IData document : input) {
	      IDataCursor cursor = document.getCursor();
	      buffer.append("<tr>");
	      for (String key : keys) {
	        buffer.append("<td>");
	        Object value = IDataUtil.get(cursor, key);
	        if (value == null) {
	          buffer.append(NULL_ENTITY);
	        } else if (recurse) {
	          if (value instanceof IData) {
	            buffer.append(emit((IData)value, recurse));
	          } else if (value instanceof IData[]) {
	            buffer.append(emit((IData[])value, recurse));
	          } else if (value instanceof Object[][]) {
	            buffer.append(emit((Object[][])value));
	          } else if (value instanceof Object[]) {
	            buffer.append(emit((Object[])value));
	          } else {
	            buffer.append(encode(value.toString()));
	          }
	        } else {
	          buffer.append(encode(value.toString()));
	        }
	        buffer.append("</td>");
	      }
	      buffer.append("</tr>");
	      cursor.destroy();
	    }
	    buffer.append("</tbody>");
	    buffer.append("</table>");
	  }
	  return buffer.toString();
	}
	
	// converts an Object[][] object table to HTML
	protected static String emit(Object[][] input) {
	  StringBuilder buffer = new StringBuilder();
	
	  // table
	  buffer.append("<table>");
	
	  // tbody
	  buffer.append("<tbody>");
	  for (int i = 0; i < input.length; i++) {
	    buffer.append("<tr>");
	    for (int j = 0; j < input[i].length; j++) {
	      buffer.append("<td>");
	      if (input[i][j] == null) {
	        buffer.append(NULL_ENTITY); 
	      } else {
	        buffer.append(encode(input[i][j].toString()));  
	      }
	      buffer.append("</td>");
	    }
	    buffer.append("</tr>");
	  }
	  buffer.append("</tbody>");
	  buffer.append("</table>");
	
	  return buffer.toString();
	}
	
	protected static String emit(Object[] input) {
	  StringBuilder buffer = new StringBuilder();
	
	  // table
	  buffer.append("<table>");
	
	  // tbody
	  buffer.append("<tbody>");
	  for (int i = 0; i < input.length; i++) {
	    buffer.append("<tr>");
	    buffer.append("<td>");
	    if (input[i] == null) {
	      buffer.append(NULL_ENTITY); 
	    } else {
	      buffer.append(encode(input[i].toString()));  
	    }
	    buffer.append("</td>");
	    buffer.append("</tr>");
	  }
	  buffer.append("</tbody>");
	  buffer.append("</table>");
	
	  return buffer.toString();
	}
	// --- <<IS-END-SHARED>> ---
}

