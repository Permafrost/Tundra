package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-07-24 08:05:57 EST
// -----( ON-HOST: 172.16.189.250

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class directory

{
	// ---( internal utility methods )---

	final static directory _instance = new directory();

	static directory _newInstance() { return new directory(); }

	static directory _cast(Object o) { return (directory)o; }

	// ---( server methods )---




	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  create(IDataUtil.getString(cursor, "$directory"));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  IDataUtil.put(cursor, "$exists?", "" + exists(directory));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $pattern
		// [i] field:0:optional $mode {&quot;regex&quot;,&quot;wildcard&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:required $directories
		// [o] field:1:required $files
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		
		  boolean regex = (mode == null || mode.equalsIgnoreCase("regex"));
		
		  Lister lister = new Lister(directory, pattern, regex, recurse);
		
		  IDataUtil.put(cursor, "$directories", lister.directories());
		  IDataUtil.put(cursor, "$files", lister.files());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void ls (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ls)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  IDataUtil.put(cursor, "$list", ls(directory));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [o] field:0:required $directory
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$directory", tundra.support.file.normalize(IDataUtil.getString(cursor, "$directory")));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  remove(directory, recurse);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void rename (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rename)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory.source
		// [i] field:0:required $directory.target
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String source = IDataUtil.getString(cursor, "$directory.source");
		  String target = IDataUtil.getString(cursor, "$directory.target");
		  rename(source, target);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// creates a new directory
	public static void create(java.io.File directory) throws ServiceException {
	  if (directory != null && !directory.mkdirs()) tundra.exception.raise("Unable to create directory: " + tundra.support.file.normalize(directory));
	}
	
	// creates a new directory
	public static void create(String directory) throws ServiceException {
	  create(tundra.support.file.construct(directory));
	}
	
	// returns whether the given file exists
	public static boolean exists(java.io.File directory) throws ServiceException {
	  return directory != null && directory.exists() && directory.isDirectory();
	}
	
	// returns whether the given file exists
	public static boolean exists(String directory) throws ServiceException {
	  return exists(tundra.support.file.construct(directory));
	}
	
	// deletes a directory
	public static void remove(java.io.File directory, boolean recurse) throws ServiceException {
	  if (exists(directory)) {
	    if (recurse) {
	      java.io.File[] list = directory.listFiles();
	      for (int i = 0; i < list.length; i++) {
	        if (list[i].isFile()) {
	          tundra.file.remove(list[i]);
	        } else {
	          remove(list[i], recurse);
	        }
	      }
	    }
	    if (!directory.delete()) tundra.exception.raise("Unable to remove directory: " + tundra.support.file.normalize(directory));
	  }
	}
	
	// deletes a directory
	public static void remove(String directory, boolean recurse) throws ServiceException {
	  remove(tundra.support.file.construct(directory), recurse);
	}
	
	// renames a directory
	public static void rename(java.io.File source, java.io.File target) throws ServiceException {
	  if (source != null && target != null) {
	    if (!exists(source) || exists(target) || !source.renameTo(target)) {
	      tundra.exception.raise("Unable to rename directory " + tundra.support.file.normalize(source) + " to " + tundra.support.file.normalize(target));
	    }
	  }
	}
	
	// renames a directory
	public static void rename(String source, String target) throws ServiceException {
	  rename(tundra.support.file.construct(source), tundra.support.file.construct(target));
	}
	
	public static class Lister {
	  protected java.io.FilenameFilter isDirectory = new DirectoryFilter();
	  protected java.io.FilenameFilter directoryFilter;
	  protected java.io.FilenameFilter fileFilter;
	
	  String[] files;
	  String[] directories;
	
	  public Lister(java.io.File directory, String pattern, boolean patternIsRegularExpression, boolean recurse) throws ServiceException {
	    directoryFilter = new DirectoryFilter();
	    fileFilter = new FileFilter();
	
	    if (pattern != null) {
	      java.io.FilenameFilter filter = null;
	      if (patternIsRegularExpression) {
	        filter = new RegularExpressionFilter(pattern);
	      } else {
	        filter = new WildcardFilter(pattern);
	      }
	      directoryFilter = new ChainFilter(filter, directoryFilter);
	      fileFilter = new ChainFilter(filter, fileFilter);
	    }
	
	    String[][] listing = list(directory, recurse);
	
	    files = listing[0];
	    directories = listing[1];
	  }
	
	  public Lister(String directory, String pattern, boolean patternIsRegularExpression, boolean recurse) throws ServiceException {
	    this(tundra.support.file.construct(directory), pattern, patternIsRegularExpression, recurse);
	  }
	
	  public String[] files() {
	    return files;
	  }
	
	  public String[] directories() {
	    return directories;
	  }
	
	  protected String[][] list(java.io.File directory, boolean recurse) throws ServiceException {
	    if (!exists(directory)) tundra.exception.raise("Unable to list directory as it does not exist: " + tundra.support.file.normalize(directory));
	
	    String[][] result = new String[2][];
	
	    String[] listing = directory.list();
	
	    if (listing == null || listing.length == 0) {
	      result[0] = new String[0];
	      result[1] = new String[0];
	    } else {
	      // if listing is a reasonable size, just use that for the initial capacity for our list
	      // of files and directories; otherwise set capacity to 1000 and let it grow as needed
	      int capacity = listing.length > 1000? 1000 : listing.length;
	      java.util.List<String> files = new java.util.ArrayList<String>(capacity);    
	      java.util.List<String> directories = new java.util.ArrayList<String>(capacity);
	
	      for (String item : listing) {
	        if (fileFilter.accept(directory, item)) {
	          files.add(tundra.support.file.normalize(new java.io.File(directory, item)));
	        } else if (directoryFilter.accept(directory, item)) {
	          directories.add(tundra.support.file.normalize(new java.io.File(directory, item)));
	        }
	
	        if (recurse && isDirectory.accept(directory, item)) {
	          String[][] children = list(new java.io.File(directory, item), recurse);
	          files.addAll(java.util.Arrays.asList(children[0]));        
	          directories.addAll(java.util.Arrays.asList(children[1]));
	        }
	      }
	
	      result[0] = (String[])files.toArray(new String[0]);
	      result[1] = (String[])directories.toArray(new String[0]);
	    }
	
	    return result;
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
	      super(convertToRegex(pattern));
	    }
	
	    protected static String convertToRegex(String pattern) {
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
	}
	
	// returns a raw directory listing with no additional processing: useful for when performance
	// takes priority over ease of use; for example, when the directory contains hundreds of 
	// thousands or more files
	public static String[] ls(String directory) throws ServiceException {
	  return ls(tundra.support.file.construct(directory));
	}
	
	// returns a raw directory listing with no additional processing: useful for when performance
	// takes priority over ease of use; for example, when the directory contains hundreds of 
	// thousands or more files
	public static String[] ls(java.io.File directory) throws ServiceException {
	  if (!tundra.directory.exists(directory)) tundra.exception.raise("Unable to list directory as it does not exist: " + tundra.support.file.normalize(directory));
	  return directory.list();
	}
	// --- <<IS-END-SHARED>> ---
}

