package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-02-24 15:43:56 EST
// -----( ON-HOST: 172.16.189.144

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




	public static final void _ (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(_)>> ---
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
	      } catch(java.lang.IllegalArgumentException ex) {
	        // work around java's weird handling of file://server/path style URIs on Windows, by changing the URI
	        // to be file:////server/path
	        if (filename.toLowerCase().startsWith("file://") && !filename.toLowerCase().startsWith("file:///")) {
	          file = construct("file:////" + filename.substring(6, filename.length()));
	        } else {
	          tundra.exception.raise(ex);
	        }
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
	    if (file != null) normalize = tundra.uri.normalize(file.getCanonicalFile().toURI().toString());
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

