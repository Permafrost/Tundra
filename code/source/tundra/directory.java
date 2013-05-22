package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-15 15:23:54.883
// -----( ON-HOST: 172.16.70.129

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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:required $directories
		// [o] field:1:required $files
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		
		  IDataUtil.put(cursor, "$directories", list.directories(directory, pattern, recurse));
		  IDataUtil.put(cursor, "$files", list.files(directory, pattern, recurse));
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
		// [i] field:0:optional $directory
		// [o] field:0:optional $directory
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
		// [i] field:0:optional $directory
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
	  return directory == null ? false : directory.exists() && directory.isDirectory();
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
	
	// lists a directory
	public static class list {
	  // lists all files in a directory
	  public static java.io.File[] files(java.io.File directory, String pattern, boolean recurse) throws ServiceException {
	    return list(directory, pattern == null? new FileFilter() : new RegularExpressionFileFilter(pattern), recurse);
	  }
	
	  // lists all files in a directory
	  public static String[] files(String directory, String pattern, boolean recurse) throws ServiceException {
	    return toStringList(files(tundra.support.file.construct(directory), pattern, recurse));
	  }
	
	  // lists all directories in a directory
	  public static java.io.File[] directories(java.io.File directory, String pattern, boolean recurse) throws ServiceException {
	    return list(directory, pattern == null? new DirectoryFilter() : new RegularExpressionDirectoryFilter(pattern), recurse);    
	  }
	
	  // lists all directories in a directory
	  public static String[] directories(String directory, String pattern, boolean recurse) throws ServiceException {
	    return toStringList(directories(tundra.support.file.construct(directory), pattern, recurse));
	  }
	
	  // lists all objects in a directory
	  protected static java.io.File[] list(java.io.File directory, java.io.FileFilter filter, boolean recurse) throws ServiceException {
	    java.io.File[] list = null;
	
	    if (directory != null) {
	      if (!exists(directory)) tundra.exception.raise("Unable to list directory as it does not exist: " + tundra.support.file.normalize(directory));
	
	      list = directory.listFiles(filter);
	
	      if (recurse) {
	        java.io.File[] directories = directory.listFiles(new DirectoryFilter());
	        for (int i = 0; i < directories.length; i++) {
	          java.io.File[] children = list(directories[i], filter, recurse);
	          list = tundra.list.object.concatenate(list, children);
	        }
	      }
	    }
	
	    return list;
	  }
	
	  // returns a list of files as a list of normalized file URIs
	  protected static String[] toStringList(java.io.File[] files) throws ServiceException {
	    String[] strings = null;
	
	    if (files != null) {
	      strings = new String[files.length];
	      for (int i = 0; i < files.length; i++) {
	        strings[i] = tundra.support.file.normalize(files[i]);
	      }
	    }
	
	    return strings;
	  }
	
	  // filter that only accepts files
	  public static class FileFilter implements java.io.FileFilter {
	    public boolean accept(java.io.File file) {
	      return file.isFile();
	    }
	  }
	
	  // filter that only accepts directories
	  public static class DirectoryFilter implements java.io.FileFilter {
	    public boolean accept(java.io.File file) {
	      return file.isDirectory();
	    }
	  }
	
	  // filter that only accepts objects whose names match the given regular expression
	  public static class RegularExpressionFilter implements java.io.FileFilter {
	    protected java.util.regex.Pattern pattern;
	
	    public RegularExpressionFilter(String pattern) {
	      this.pattern = java.util.regex.Pattern.compile(pattern);
	    }
	
	    public boolean accept(java.io.File file) {
	      return pattern.matcher(file.getName()).matches();
	    }
	  }
	
	  // filter that only accepts files whose names match the given regular expression
	  public static class RegularExpressionFileFilter extends RegularExpressionFilter {
	    public RegularExpressionFileFilter(String pattern) {
	      super(pattern);
	    }
	
	    public boolean accept(java.io.File file) {
	      return file.isFile() && super.accept(file);
	    }
	  }
	
	  // filter that only accepts directories whose names match the given regular expression
	  public static class RegularExpressionDirectoryFilter extends RegularExpressionFilter {
	    public RegularExpressionDirectoryFilter(String pattern) {
	      super(pattern);
	    }
	
	    public boolean accept(java.io.File file) {
	      return file.isDirectory() && super.accept(file);
	    }
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

