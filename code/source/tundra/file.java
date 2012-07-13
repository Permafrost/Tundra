package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-13 15:54:36.913
// -----( ON-HOST: TNFDEVWAP103.test.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class file

{
	// ---( internal utility methods )---

	final static file _instance = new file();

	static file _newInstance() { return new file(); }

	static file _cast(Object o) { return (file)o; }

	// ---( server methods )---




	public static final void type (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(type)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $file
		// [o] field:0:required $type
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String filename = IDataUtil.getString(cursor, "$file");
		
		  IDataUtil.put(cursor, "$type", type(filename));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns a java.io.File object that represents the given filename
	// filename can be either a path, or a file: uri
	public static java.io.File file(String filename) throws ServiceException {
	  java.io.File file = null;
	  if (filename != null) {
	    if (filename.toLowerCase().startsWith("file:")) {
	      try {
	        file = new java.io.File(new java.net.URI(filename));
	      } catch(java.net.URISyntaxException ex) {
	        tundra.exception.raise(ex);
	      }
	    } else {
	      file = new java.io.File(filename);
	    }
	  }
	  return file;
	}
	
	// returns the mime type for the given file
	public static String type(java.io.File file) throws ServiceException {
	  String type = null;
	  if (file != null) type = com.wm.app.b2b.server.MimeTypes.getTypeFromName(file.getName());
	  if (type == null) type = "application/octet-stream";
	  return type;
	}
	
	// returns the mime type for the given filename
	public static String type(String filename) throws ServiceException {
	  return type(file(filename));
	}
	// --- <<IS-END-SHARED>> ---
}

