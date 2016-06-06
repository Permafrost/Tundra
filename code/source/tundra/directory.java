package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-06 16:08:04.058
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.io.FilenameFilter;
import java.math.BigInteger;
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
		// [i] field:0:optional $delete? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		boolean raise = false;

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    boolean deleteSelf = BooleanHelper.parse(IDataUtil.getString(cursor, "$delete?"));
		    raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

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
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

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
		    String directory = IDataUtil.getString(cursor, "$directory");
		    IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(DirectoryHelper.exists(directory)));
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
		    String parent = IDataUtil.getString(cursor, "$parent");
		    String[] children = IDataUtil.getStringArray(cursor, "$children");

		    if (children == null) {
		        String child = IDataUtil.getString(cursor, "$child");
		        if (child != null) {
		            children = new String[1];
		            children[0] = child;
		        }
		    }

		    if (parent != null) {
		        children = ArrayHelper.prepend(children, parent, String.class);
		    }

		    String path = DirectoryHelper.join(children);

		    if (path != null) IDataUtil.put(cursor, "$uri", path);
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
		// [i] field:0:optional $filter.type {&quot;regular expression&quot;,&quot;wildcard&quot;,&quot;literal&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:required $directories
		// [o] field:0:required $directories.length
		// [o] field:1:required $files
		// [o] field:0:required $files.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    String[] inclusions = IDataUtil.getStringArray(cursor, "$filter.inclusions");
		    String[] exclusions = IDataUtil.getStringArray(cursor, "$filter.exclusions");
		    String type = IDataUtil.getString(cursor, "$filter.type");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

		    ConditionalFilenameFilter filter = null;

		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(FilenameFilterType.normalize(type), inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(FilenameFilterType.normalize(type), exclusions));
		        }
		    } else {
		        // support $pattern and $mode for backwards-compatibility
		        String pattern = IDataUtil.getString(cursor, "$pattern");
		        String mode = IDataUtil.getString(cursor, "$mode");
		        if (pattern != null) {
		            filter = new InclusionFilenameFilter(FilenameFilterType.normalize(mode), pattern);
		        }
		    }

		    DirectoryLister lister = new DirectoryLister(directory, filter, recurse);

		    DirectoryListing listing = lister.list();
		    String[] directories = listing.listDirectoriesAsStringArray();
		    String[] files = listing.listFilesAsStringArray();

		    IDataUtil.put(cursor, "$directories", directories);
		    IDataUtil.put(cursor, "$directories.length", IntegerHelper.emit(directories.length));
		    IDataUtil.put(cursor, "$files", files);
		    IDataUtil.put(cursor, "$files.length", IntegerHelper.emit(files.length));
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
		    String directory = IDataUtil.getString(cursor, "$directory");
		    IDataUtil.put(cursor, "$list", DirectoryHelper.list(directory));
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
		    IDataUtil.put(cursor, "$directory", FileHelper.normalize(IDataUtil.getString(cursor, "$directory")));
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
		// [i] field:1:optional $filter.inclusions
		// [i] field:1:optional $filter.exclusions
		// [i] field:0:optional $filter.type {&quot;regular expression&quot;,&quot;wildcard&quot;,&quot;literal&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $count
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String pattern = IDataUtil.getString(cursor, "$duration.pattern");
		    String[] inclusions = IDataUtil.getStringArray(cursor, "$filter.inclusions");
		    String[] exclusions = IDataUtil.getStringArray(cursor, "$filter.exclusions");
		    String type = IDataUtil.getString(cursor, "$filter.type");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

		    ConditionalFilenameFilter filter = null;

		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(FilenameFilterType.normalize(type), inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(FilenameFilterType.normalize(type), exclusions));
		        }
		    }

		    long count = DirectoryHelper.purge(directory, DurationHelper.parse(duration, pattern), filter, recurse);

		    IDataUtil.put(cursor, "$count", LongHelper.emit(count));
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
		    String directory = IDataUtil.getString(cursor, "$directory");
		    IDataUtil.put(cursor, "$directory.properties", FileHelper.getPropertiesAsIData(directory));
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
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

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
		    String source = IDataUtil.getString(cursor, "$directory.source");
		    String target = IDataUtil.getString(cursor, "$directory.target");

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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $size
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

		    BigInteger totalSize = DirectoryHelper.size(directory, recurse);

		    IDataUtil.put(cursor, "$size", BigIntegerHelper.emit(totalSize));
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
		// [i] field:0:optional $filter.type {&quot;regular expression&quot;,&quot;wildcard&quot;,&quot;literal&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $size.squeezed
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String directory = IDataUtil.getString(cursor, "$directory");
		    BigInteger size = BigIntegerHelper.parse(IDataUtil.getString(cursor, "$size.required"));
		    // support $size for backwards-compatibility
		    if (size == null) size = BigIntegerHelper.parse(IDataUtil.getString(cursor, "$size"));
		    String[] inclusions = IDataUtil.getStringArray(cursor, "$filter.inclusions");
		    String[] exclusions = IDataUtil.getStringArray(cursor, "$filter.exclusions");
		    String type = IDataUtil.getString(cursor, "$filter.type");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

		    ConditionalFilenameFilter filter = null;

		    if (inclusions != null || exclusions != null) {
		        filter = new AndFilenameFilter();
		        if (inclusions != null) {
		            filter.add(new InclusionFilenameFilter(FilenameFilterType.normalize(type), inclusions));
		        }
		        if (exclusions != null) {
		            filter.add(new ExclusionFilenameFilter(FilenameFilterType.normalize(type), exclusions));
		        }
		    }

		    size = DirectoryHelper.squeeze(directory, size, filter, recurse);

		    IDataUtil.put(cursor, "$size.squeezed", BigIntegerHelper.emit(size));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

