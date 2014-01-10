package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-01-10 12:52:16.055
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class gzip

{
	// ---( internal utility methods )---

	final static gzip _instance = new gzip();

	static gzip _newInstance() { return new gzip(); }

	static gzip _cast(Object o) { return (gzip)o; }

	// ---( server methods )---




	public static final void compress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content.gzip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (content != null) IDataUtil.put(cursor, "$content.gzip", compress(content, encoding, mode));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void decompress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decompress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content.gzip
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object gzip = IDataUtil.get(cursor, "$content.gzip");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (gzip != null) IDataUtil.put(cursor, "$content", decompress(gzip, encoding, mode));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// GZIP compresses a string, byte array, or input stream
	public static Object compress(Object content, String encoding, String mode) throws java.io.IOException {
	  if (content == null) return null;
	
	  byte[] buffer = new byte[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	  java.io.InputStream in = null;
	  java.util.zip.GZIPOutputStream out = null;
	
	  try {
	    java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	    
	    in = tundra.stream.normalize(content, encoding);
	    out = new java.util.zip.GZIPOutputStream(baos);
	
	    int count;
	    while ((count = in.read(buffer)) > 0) {
	      out.write(buffer, 0, count);
	    }
	
	    buffer = baos.toByteArray();
	  } finally {
	    if (in != null) in.close();
	    if (out != null) out.close();
	  }
	
	  return convert(buffer, mode);
	}
	
	// GZIP decompresses a string, byte array, or input stream
	public static Object decompress(Object gzip, String encoding, String mode) throws java.io.IOException {
	  if (gzip == null) return null;
	
	  byte[] buffer = new byte[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	  java.util.zip.GZIPInputStream in = null;
	  java.io.ByteArrayOutputStream out = null;
	
	  try {
	    if (gzip instanceof String) gzip = tundra.base64.decode((String)gzip);
	    in = new java.util.zip.GZIPInputStream(tundra.stream.normalize(gzip));
	    out = new java.io.ByteArrayOutputStream();
	  
	    int count;
	    while((count = in.read(buffer)) > 0) {
	      out.write(buffer, 0, count);
	    }
	
	    buffer = out.toByteArray();
	  } finally {
	    if (in != null) in.close();
	    if (out != null) out.close();
	  }
	
	  return tundra.object.convert(buffer, encoding, mode);
	}
	
	// converts a byte array to either an input stream, byte array, or string
	protected static Object convert(byte[] bytes, String mode) {
	  Object result = null;
	  if (mode == null || mode.equals("stream")) {
	    result = new java.io.ByteArrayInputStream(bytes);
	  } else if (mode.equals("bytes")) {
	    result = bytes;
	  } else if (mode.equals("string")) {
	    result = tundra.base64.encode(bytes);
	  }
	
	  return result;
	}
	// --- <<IS-END-SHARED>> ---
}

