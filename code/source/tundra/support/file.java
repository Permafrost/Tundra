package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-07-24 19:25:04 EST
// -----( ON-HOST: 172.16.189.250

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
	
	// filter that lets you chain together other filters
	public static class ChainFilter implements java.io.FilenameFilter {
	  protected java.io.FilenameFilter[] filters;
	
	  public ChainFilter(java.io.FilenameFilter ... filters) {
	    this.filters = filters;
	  }
	
	  public boolean accept(java.io.File dir, String name) {
	    boolean accept = true;
	
	    if (filters != null) {
	      for (java.io.FilenameFilter filter : filters) {
	        if (filter != null) {
	          accept = accept && filter.accept(dir, name);
	          if (!accept) break; // short circuit chain if file rejected            
	        }
	      }
	    }
	    return accept;
	  }
	}
	
	// filter that only accepts files
	public static class FileFilter implements java.io.FilenameFilter {
	  public boolean accept(java.io.File dir, String name) {
	    return (new java.io.File(dir, name)).isFile();
	  }
	}
	
	// filter that only accepts directories
	public static class DirectoryFilter implements java.io.FilenameFilter {
	  public boolean accept(java.io.File dir, String name) {
	    return (new java.io.File(dir, name)).isDirectory();
	  }
	}
	
	// filter that only accepts objects whose names match the given regular expression
	public static class RegularExpressionFilter implements java.io.FilenameFilter {
	  protected java.util.regex.Pattern pattern;
	
	  public RegularExpressionFilter(String pattern) {
	    if (caseInsensitive()) pattern = "(?i)" + pattern;
	    this.pattern = java.util.regex.Pattern.compile(pattern);
	  }
	
	  public boolean accept(java.io.File dir, String name) {
	    return pattern.matcher(name).matches();
	  }
	
	  protected static boolean caseInsensitive() {
	    return (new java.io.File("TUNDRA")).equals(new java.io.File("tundra"));
	  }
	}
	
	// filter that only accepts objects that match the given wildcard expression
	public static class WildcardFilter extends RegularExpressionFilter {
	  public WildcardFilter(String pattern) {
	    super(convertWildcardToRegex(pattern));
	  }
	
	  public static String convertWildcardToRegex(String pattern) {
	    StringBuilder buffer = new StringBuilder();
	    char[] characters = pattern.toCharArray();
	
	    for (int i = 0; i < characters.length; ++i) {
	      if (characters[i] == '*') {
	        buffer.append(".*");
	      } else if (characters[i] == '?') {
	        buffer.append(".");
	      } else if ("+()^$.{}[]|\\".indexOf(characters[i]) != -1) {
	        buffer.append('\\').append(characters[i]);
	      } else {
	        buffer.append(characters[i]);
	      }
	    }
	    return buffer.toString();
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

