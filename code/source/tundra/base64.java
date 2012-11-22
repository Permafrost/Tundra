package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-11-22 15:13:55.617
// -----( ON-HOST: TNFDEVWAP103.test.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class base64

{
	// ---( internal utility methods )---

	final static base64 _instance = new base64();

	static base64 _newInstance() { return new base64(); }

	static base64 _cast(Object o) { return (base64)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $base64
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object input = IDataUtil.get(cursor, "$base64");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  IDataUtil.put(cursor, "$content", decode(input, encoding, mode));
		} catch(java.io.IOException ex) {
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
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $base64
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  IDataUtil.put(cursor, "$base64", encode(content, encoding, mode));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// encodes a string, byte array or stream as a base-64 stream, byte array or string
	public static Object encode(Object input, String encoding, String mode) throws java.io.IOException {
	  return tundra.object.convert(encode(input, encoding), encoding, mode);
	}
	
	// encodes a string, byte array or input stream as a base-64 string
	public static String encode(Object input, String encoding) throws java.io.IOException {
	  return encode(tundra.bytes.normalize(input, encoding));  
	}
	
	// encodes a byte array as a base-64 string
	public static String encode(byte[] input) {
	  if (input == null) return null;
	  return javax.xml.bind.DatatypeConverter.printBase64Binary(input);
	}
	
	// decodes a base-64 string to a stream, byte array, or string
	public static Object decode(Object input, String encoding, String mode) throws java.io.IOException {
	  return tundra.object.convert(decode(input, encoding), encoding, mode);
	}
	
	// decodes a base-64 string to a byte array
	public static byte[] decode(Object input, String encoding) throws java.io.IOException {
	  return decode(tundra.string.normalize(input, encoding));  
	}
	
	// decodes a base-64 string to a byte array
	public static byte[] decode(String input) {
	  if (input == null) return null;
	  return javax.xml.bind.DatatypeConverter.parseBase64Binary(input);
	}
	// --- <<IS-END-SHARED>> ---
}

