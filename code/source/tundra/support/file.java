package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-14 16:27:49.171
// -----( ON-HOST: 172.16.70.129

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




	public static final void noop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(noop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns a java.io.File object that represents the given filename
	// filename can be either a path, or a file: uri
	public static java.io.File construct(String filename) throws ServiceException {
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
	
	// returns the canonical file: URI representation of the given file
	public static String normalize(java.io.File file) throws ServiceException {
	  String normalize = null;
	  try {
	    if (file != null) normalize = file.getCanonicalFile().toURI().toString();
	  } catch (java.io.IOException ex) {
	    tundra.exception.raise(ex);
	  }
	  return normalize;
	}
	
	// returns the canonical file: URI representation of the given file
	public static String normalize(String filename) throws ServiceException {
	  return normalize(construct(filename));
	}
	// --- <<IS-END-SHARED>> ---
}

