package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-01-10 14:58:36.563
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class zip

{
	// ---( internal utility methods )---

	final static zip _instance = new zip();

	static zip _newInstance() { return new zip(); }

	static zip _cast(Object o) { return (zip)o; }

	// ---( server methods )---




	public static final void compress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $contents
		// [i] - field:0:required name
		// [i] - object:0:required content
		// [i] - field:0:optional encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $contents.zip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] contents = IDataUtil.getIDataArray(cursor, "$contents");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (contents != null && contents.length > 0) IDataUtil.put(cursor, "$contents.zip", compress(contents, mode));
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
		// [i] object:0:optional $contents.zip
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] record:1:optional $contents
		// [o] - field:0:required name
		// [o] - object:0:required content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object zip = IDataUtil.get(cursor, "$contents.zip");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (zip != null) IDataUtil.put(cursor, "$contents", decompress(zip, encoding, mode));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// compresses the given contents into a zip archive
	public static Object compress(IData[] contents, String mode) throws java.io.IOException {
	  if (contents == null) return null;
	
	  byte[] buffer = new byte[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	  java.io.ByteArrayOutputStream baos = null;
	  java.util.zip.ZipOutputStream out = null;
	
	  try {
	    baos = new java.io.ByteArrayOutputStream();
	    out = new java.util.zip.ZipOutputStream(baos);
	
	    for (int i = 0; i < contents.length; i++) {
	      if (contents[i] != null) {
	        IDataCursor cursor = contents[i].getCursor();
	        String name = IDataUtil.getString(cursor, "name");
	        Object content = IDataUtil.get(cursor, "content");
	        String encoding = IDataUtil.getString(cursor, "encoding");
	        cursor.destroy();
	
	        if (content != null) {
	          java.io.InputStream in = null;
	          try {
	            if (name == null) name = "Untitled " + (i + 1);
	            in = tundra.stream.normalize(content, encoding);
	            out.putNextEntry(new java.util.zip.ZipEntry(name));
	
	            int count;
	            while ((count = in.read(buffer)) > 0) {
	              out.write(buffer, 0, count);
	            }
	          } finally {
	            if (in != null) in.close();
	          }
	        }
	      }
	    }
	  } finally {
	    if (out != null) {
	      out.closeEntry();
	      out.close();
	    }
	    if (baos != null) buffer = baos.toByteArray();
	  }
	
	  return convert(buffer, mode);
	}
	
	// decompresses the given zip archive
	public static IData[] decompress(Object zip, String encoding, String mode) throws java.io.IOException {
	  if (zip == null) return null;
	
	  byte[] buffer = new byte[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	  java.util.zip.ZipInputStream in = null;
	  java.util.List<IData> contents = new java.util.ArrayList<IData>();
	
	  try {
	    if (zip instanceof String) zip = tundra.base64.decode((String)zip);
	    in = new java.util.zip.ZipInputStream(tundra.stream.normalize(zip));
	
	    java.util.zip.ZipEntry entry = in.getNextEntry();
	
	    while(entry != null) {
	      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
	
	      int count;
	      while((count = in.read(buffer)) > 0) {
	        out.write(buffer, 0, count);
	      }
	      out.close();
	
	      IData item = IDataFactory.create();
	      IDataCursor cursor = item.getCursor();
	      IDataUtil.put(cursor, "name", entry.getName());
	      IDataUtil.put(cursor, "content", tundra.object.convert(out.toByteArray(), encoding, mode));
	      cursor.destroy();
	      contents.add(item);
	
	      entry = in.getNextEntry();
	    }
	
	  } finally {
	    if (in != null) in.close();
	  }
	
	  return (IData[])contents.toArray(new IData[0]);
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

