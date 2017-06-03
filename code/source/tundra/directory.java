package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-03 18:11:53 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.io.FilenameFilter;
import java.math.BigInteger;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.DirectoryHelper;
import permafrost.tundra.io.DirectoryLister;
import permafrost.tundra.io.DirectoryListing;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.io.filter.AndFilenameFilter;
import permafrost.tundra.io.filter.ConditionalFilenameFilter;
import permafrost.tundra.io.filter.ExclusionFilenameFilter;
import permafrost.tundra.io.filter.InclusionFilenameFilter;
import permafrost.tundra.io.filter.FilenameFilterType;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.BigIntegerHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.math.LongHelper;
import permafrost.tundra.time.DurationHelper;
// --- <<IS-END-IMPORTS>> ---

public final class directory

{
	// ---( internal utility methods )---

	final static directory _instance = new directory();

	static directory _newInstance() { return new directory(); }

	static directory _cast(Object o) { return (directory)o; }

	// ---( server methods )---




	public static final void compact (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compact)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $delete? {"false","true"}
		// [i] field:0:optional $raise? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		boolean raise = false;
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    boolean deleteSelf = IDataHelper.getOrDefault(cursor, "$delete?", Boolean.class, false);
		    raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    DirectoryHelper.compact(FileHelper.construct(directory), deleteSelf);
		} catch(IOException ex) {
		   if (raise) ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $raise? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    DirectoryHelper.create(directory, raise);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    IDataHelper.put(cursor, "$exists?", DirectoryHelper.exists(directory), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void gzip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(gzip)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:1:optional $filter.inclusions
		// [i] field:1:optional $filter.exclusions
		// [i] field:0:optional $filter.type {"regular expression","wildcard","literal"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $replace? {"false","true"}
		// [o] field:0:required $count
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String pattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		    String[] inclusions = IDataHelper.get(cursor, "$filter.inclusions", String[].class);
		    String[] exclusions = IDataHelper.get(cursor, "$filter.exclusions", String[].class);
		    FilenameFilterType type = IDataHelper.get(cursor, "$filter.type", FilenameFilterType.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean replace = IDataHelper.getOrDefault(cursor, "$replace?", Boolean.class, false);
		
		    ConditionalFilenameFilter filter = null;
		
		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(type, inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(type, exclusions));
		        }
		    }
		
		    long count = DirectoryHelper.gzip(FileHelper.construct(directory), DurationHelper.parse(duration, pattern), filter, recurse, replace);
		
		    IDataHelper.put(cursor, "$count", count, String.class);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] field:0:optional $parent
		// [i] field:1:optional $children
		// [o] field:0:optional $uri
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String parent = IDataHelper.get(cursor, "$parent", String.class);
		    String[] children = IDataHelper.get(cursor, "$children", String[].class);
		
		    if (children == null) {
		        String child = IDataHelper.get(cursor, "$child", String.class);
		        if (child != null) {
		            children = new String[1];
		            children[0] = child;
		        }
		    }
		
		    if (parent != null) {
		        children = ArrayHelper.prepend(children, parent, String.class);
		    }
		
		    String path = DirectoryHelper.join(children);
		
		    IDataHelper.put(cursor, "$uri", path, false);
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
		// [i] field:1:optional $filter.inclusions
		// [i] field:1:optional $filter.exclusions
		// [i] field:0:optional $filter.type {"regular expression","wildcard","literal"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] field:1:required $directories
		// [o] field:0:required $directories.length
		// [o] field:1:required $files
		// [o] field:0:required $files.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    String[] inclusions = IDataHelper.get(cursor, "$filter.inclusions", String[].class);
		    String[] exclusions = IDataHelper.get(cursor, "$filter.exclusions", String[].class);
		    FilenameFilterType type = IDataHelper.get(cursor, "$filter.type", FilenameFilterType.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    ConditionalFilenameFilter filter = null;
		
		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(type, inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(type, exclusions));
		        }
		    } else {
		        // support $pattern and $mode for backwards-compatibility
		        String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		        FilenameFilterType mode = IDataHelper.get(cursor, "$mode", FilenameFilterType.class);
		        if (pattern != null) {
		            filter = new InclusionFilenameFilter(mode, pattern);
		        }
		    }
		
		    DirectoryLister lister = new DirectoryLister(directory, filter, recurse);
		
		    DirectoryListing listing = lister.list();
		    String[] directories = listing.listDirectoriesAsStringArray();
		    String[] files = listing.listFilesAsStringArray();
		        
		    IDataHelper.put(cursor, "$directories", directories);
		    IDataHelper.put(cursor, "$directories.length", directories.length, String.class);
		    IDataHelper.put(cursor, "$files", files);
		    IDataHelper.put(cursor, "$files.length", files.length, String.class);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    IDataHelper.put(cursor, "$list", DirectoryHelper.list(directory));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    IDataHelper.put(cursor, "$directory", FileHelper.normalize(directory));
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
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:1:optional $filter.inclusions
		// [i] field:1:optional $filter.exclusions
		// [i] field:0:optional $filter.type {"regular expression","wildcard","literal"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] field:0:required $count
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String pattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		    String[] inclusions = IDataHelper.get(cursor, "$filter.inclusions", String[].class);
		    String[] exclusions = IDataHelper.get(cursor, "$filter.exclusions", String[].class);
		    FilenameFilterType type = IDataHelper.get(cursor, "$filter.type", FilenameFilterType.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    ConditionalFilenameFilter filter = null;
		
		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(type, inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(type, exclusions));
		        }
		    }
		
		    long count = DirectoryHelper.purge(directory, DurationHelper.parse(duration, pattern), filter, recurse);
		
		    IDataHelper.put(cursor, "$count", count, String.class);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    IDataHelper.put(cursor, "$directory.properties", FileHelper.getPropertiesAsIData(directory));
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
		// [i] field:0:optional $recurse? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    DirectoryHelper.remove(directory, recurse);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String source = IDataHelper.get(cursor, "$directory.source", String.class);
		    String target = IDataHelper.get(cursor, "$directory.target", String.class);
		
		    DirectoryHelper.rename(source, target);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void size (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(size)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] field:0:required $size
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    BigInteger totalSize = DirectoryHelper.size(directory, recurse);
		
		    IDataHelper.put(cursor, "$size", totalSize, String.class);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void squeeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(squeeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:required $size.required
		// [i] field:1:optional $filter.inclusions
		// [i] field:1:optional $filter.exclusions
		// [i] field:0:optional $filter.type {"regular expression","wildcard","literal"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] field:0:required $size.squeezed
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String directory = IDataHelper.get(cursor, "$directory", String.class);
		    BigInteger size = IDataHelper.get(cursor, "$size.required", BigInteger.class);
		    // support $size for backwards-compatibility
		    if (size == null) size = IDataHelper.get(cursor, "$size", BigInteger.class);
		    String[] inclusions = IDataHelper.get(cursor, "$filter.inclusions", String[].class);
		    String[] exclusions = IDataHelper.get(cursor, "$filter.exclusions", String[].class);
		    FilenameFilterType type = IDataHelper.get(cursor, "$filter.type", FilenameFilterType.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    ConditionalFilenameFilter filter = null;
		
		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(type, inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(type, exclusions));
		        }
		    }
		
		    size = DirectoryHelper.squeeze(directory, size, filter, recurse);
		
		    IDataHelper.put(cursor, "$size.squeezed", size, String.class);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

