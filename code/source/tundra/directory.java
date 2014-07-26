package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-07-26 21:14:28 EST
// -----( ON-HOST: 172.16.189.176

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
		// [i] field:0:optional $raise? {&quot;true&quot;,&quot;false&quot;}
		IDataCursor cursor = pipeline.getCursor();
		boolean raise = true;
		
		try {
		  String dir = IDataUtil.getString(cursor, "$directory");
		  String raiseString = IDataUtil.getString(cursor, "$raise?");
		  if (raiseString != null) raise = tundra.bool.parse(raiseString);
		  create(dir, raise);
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



	public static final void join (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(join)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $parent
		// [i] field:0:required $child
		// [o] field:0:required $uri
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String parent = IDataUtil.getString(cursor, "$parent");
		  String child = IDataUtil.getString(cursor, "$child");
		
		  IDataUtil.put(cursor, "$uri", join(parent, child));
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
		// [i] field:0:required $directory
		// [o] field:1:required $list
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



	public static final void purge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(purge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:required $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $count
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String pattern = IDataUtil.getString(cursor, "$duration.pattern");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		
		  long count = purge(directory, duration, pattern, recurse);
		
		  IDataUtil.put(cursor, "$count", "" + count);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [o] record:0:required $directory.properties
		// [o] - field:0:required exists?
		// [o] - field:0:optional parent
		// [o] - field:0:optional name
		// [o] - field:0:optional modified
		// [o] - field:0:required uri
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String directory = IDataUtil.getString(cursor, "$directory");
		  IDataUtil.put(cursor, "$directory.properties", reflect(directory));
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
	  create(directory, true);
	}
	
	// creates a new directory
	public static void create(java.io.File directory, boolean raise) throws ServiceException {
	  if (directory != null) {
	    if (raise || !exists(directory)) {
	      if (!directory.mkdirs()) {
	        tundra.exception.raise("Unable to create directory: " + tundra.support.file.normalize(directory));
	      }
	    }
	  }
	}
	
	// creates a new directory
	public static void create(String directory) throws ServiceException {
	  create(directory, true);
	}
	
	// creates a new directory
	public static void create(String directory, boolean raise) throws ServiceException {
	  create(tundra.support.file.construct(directory), raise);
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
	
	// directory lister which supports regex and wildcard file name filtering
	public static class Lister {
	  protected java.io.FilenameFilter isDirectory = new tundra.support.file.DirectoryFilter();
	  protected java.io.FilenameFilter directoryFilter;
	  protected java.io.FilenameFilter fileFilter;
	
	  String[] files;
	  String[] directories;
	
	  public Lister(java.io.File directory, String pattern, boolean patternIsRegularExpression, boolean recurse) throws ServiceException {
	    directoryFilter = new tundra.support.file.DirectoryFilter();
	    fileFilter = new tundra.support.file.FileFilter();
	
	    if (pattern != null) {
	      java.io.FilenameFilter filter = null;
	      if (patternIsRegularExpression) {
	        filter = new tundra.support.file.RegularExpressionFilter(pattern);
	      } else {
	        filter = new tundra.support.file.WildcardFilter(pattern);
	      }
	      directoryFilter = new tundra.support.file.ChainFilter(filter, directoryFilter);
	      fileFilter = new tundra.support.file.ChainFilter(filter, fileFilter);
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
	
	// creates a new path given a parent directory and child item
	public static String join(String parent, String child) throws ServiceException {
	  return join(tundra.support.file.construct(parent), child);
	}
	
	// creates a new path given a parent directory and child item
	public static String join(java.io.File parent, String child) throws ServiceException {
	  return tundra.support.file.normalize(new java.io.File(parent, child));
	}
	
	// returns only the name component of the given directory
	public static String name(String directory) throws ServiceException {
	  return name(tundra.support.file.construct(directory));
	}
	
	// returns only the name component of the given directory
	public static String name(java.io.File directory) throws ServiceException {
	  String name = directory.getName();
	  return directory.equals("") ? null : name;
	}
	
	// returns only the parent directory containing the given directory
	public static String parent(String directory) throws ServiceException {
	  return parent(tundra.support.file.construct(directory));
	}
	
	// returns only the parent directory containing the given directory
	public static String parent(java.io.File directory) throws ServiceException {
	  return tundra.support.file.normalize(directory.getParent());
	}
	
	// returns the last modified datetime of the given directory as an ISO8601 formatted datetime string
	public static String modified(String directory) throws ServiceException {
	  return modified(tundra.support.file.construct(directory));
	}
	
	// returns the last modified datetime of the given directory as an ISO8601 formatted datetime string
	public static String modified(java.io.File directory) throws ServiceException {
	  return tundra.datetime.emit(new java.util.Date(directory.lastModified()));
	}
	
	// returns an IData document containing the properties of the given directory
	public static IData reflect(String directory) throws ServiceException {
	  return reflect(tundra.support.file.construct(directory));
	}
	
	// returns an IData document containing the properties of the given directory
	public static IData reflect(java.io.File directory) throws ServiceException {
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	
	  boolean exists = exists(directory);
	
	  IDataUtil.put(cursor, "exists?", "" + exists);
	
	  String parent = parent(directory);
	  if (parent != null) IDataUtil.put(cursor, "parent", parent);
	
	  String name = name(directory);
	  if (name != null) IDataUtil.put(cursor, "name", name(directory));
	
	  if (exists) IDataUtil.put(cursor, "modified", modified(directory));
	
	  IDataUtil.put(cursor, "uri", tundra.support.file.normalize(directory));
	
	  cursor.destroy();
	
	  return output;
	}
	
	// deletes all files in the given directory, and sub-directories if recurse
	// is true, older than the given duration; returns the number of files deleted
	public static long purge(String directory, String duration, String pattern, boolean recurse) throws ServiceException {
	  return purge(tundra.support.file.construct(directory), tundra.duration.parse(duration, pattern), recurse);
	}
	
	// deletes all files in the given directory, and sub-directories if recurse
	// is true, older than the given duration; returns the number of files deleted
	public static long purge(java.io.File directory, javax.xml.datatype.Duration duration, boolean recurse) throws ServiceException {
	  return purge(directory, tundra.datetime.earlier(duration), recurse);
	}
	
	// deletes all files in the given directory, and sub-directories if recurse
	// is true, older than the given duration; returns the number of files deleted
	public static long purge(java.io.File directory, java.util.Calendar age, boolean recurse) throws ServiceException {
	  long count = 0;
	
	  for (String item : ls(directory)) {
	    java.io.File child = tundra.support.file.construct(tundra.directory.join(directory, item));
	    if (child.exists()) {
	      if (child.isFile()) {
	        java.util.Calendar modified = java.util.Calendar.getInstance();
	        modified.setTime(new java.util.Date(child.lastModified()));
	        if (modified.compareTo(age) <= 0 && child.delete()) count += 1;
	      } else if (recurse && child.isDirectory()) {
	        count += purge(child, age, recurse);
	      }
	    }
	  }
	
	  return count;
	}
	// --- <<IS-END-SHARED>> ---
}

