package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 17:50:20 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.DirectoryHelper;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.io.filter.AndFilenameFilter;
import permafrost.tundra.io.filter.ConditionalFilenameFilter;
import permafrost.tundra.io.filter.ExclusionFilenameFilter;
import permafrost.tundra.io.filter.FilenameFilterType;
import permafrost.tundra.io.filter.InclusionFilenameFilter;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.BigIntegerHelper;
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
		// [i] field:1:required $directories
		// [i] field:0:optional $delete? {"false","true"}
		// [i] field:0:optional $raise? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataHelper.get(cursor, "$directories", String[].class);
		    boolean deleteSelf = IDataHelper.getOrDefault(cursor, "$delete?", Boolean.class, false);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    for (String directory : directories) {
		        try {
		            DirectoryHelper.compact(FileHelper.construct(directory), deleteSelf);
		        } catch(IOException ex) {
		            exceptions.add(ex);
		        }
		    }
		
		    if (raise && exceptions.size() > 0) ExceptionHelper.raise(exceptions);
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
		// [i] record:1:optional $directories
		// [i] - field:0:required directory
		// [i] - field:0:required duration
		// [i] - field:0:optional duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] - field:1:optional filter.inclusions
		// [i] - field:1:optional filter.exclusions
		// [i] - field:0:optional filter.type {"regular expression","wildcard","literal"}
		// [i] - field:0:optional recurse? {"false","true"}
		// [o] field:1:optional $counts
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] directories = IDataHelper.get(cursor, "$directories", IData[].class);
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    if (directories != null) {
		        List<String> counts = new ArrayList<String>(directories.length);
		        for (IData document : directories) {
		            if (document != null) {
		                IDataCursor dc = document.getCursor();
		                try {
		                    String directory = IDataHelper.get(dc, "directory", String.class);
		                    String duration = IDataHelper.get(dc, "duration", String.class);
		                    String pattern = IDataHelper.get(dc, "duration.pattern", String.class);
		                    String[] inclusions = IDataHelper.get(dc, "filter.inclusions", String[].class);
		                    String[] exclusions = IDataHelper.get(dc, "filter.exclusions", String[].class);
		                    FilenameFilterType type = IDataHelper.get(dc, "filter.type", FilenameFilterType.class);
		                    boolean recurse = IDataHelper.getOrDefault(dc, "recurse?", Boolean.class, false);
		
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
		
		                    counts.add(LongHelper.emit(DirectoryHelper.purge(directory, DurationHelper.parse(duration, pattern), filter, recurse)));
		                } catch(IOException ex) {
		                    exceptions.add(ex);
		                } finally {
		                    dc.destroy();
		                }
		            }
		        }
		        IDataHelper.put(cursor, "$counts", counts.toArray(new String[counts.size()]));
		    }
		
		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
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
		// [i] field:1:required $directories
		// [i] field:0:optional $recurse? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataHelper.get(cursor, "$directories", String[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    for (String directory : directories) {
		        try {
		            DirectoryHelper.remove(directory, recurse);
		        } catch(IOException ex) {
		            exceptions.add(ex);
		        }
		    }
		
		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
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
		// [i] record:1:optional $directories
		// [i] - field:0:required directory
		// [i] - field:0:required size.required
		// [i] - field:1:optional filter.inclusions
		// [i] - field:1:optional filter.exclusions
		// [i] - field:0:optional filter.type {"regular expression","wildcard","literal"}
		// [i] - field:0:optional recurse? {"false","true"}
		// [o] field:1:optional $sizes.squeezed
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] directories = IDataHelper.get(cursor, "$directories", IData[].class);
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    if (directories != null) {
		        List<String> sizes = new ArrayList<String>(directories.length);
		        for (IData document : directories) {
		            if (document != null) {
		                IDataCursor dc = document.getCursor();
		
		                try {
		                    String directory = IDataHelper.get(dc, "directory", String.class);
		                    BigInteger size = IDataHelper.get(cursor, "size.required", BigInteger.class);
		                    String[] inclusions = IDataHelper.get(dc, "filter.inclusions", String[].class);
		                    String[] exclusions = IDataHelper.get(dc, "filter.exclusions", String[].class);
		                    FilenameFilterType type = IDataHelper.get(dc, "filter.type", FilenameFilterType.class);
		                    boolean recurse = IDataHelper.getOrDefault(dc, "recurse?", Boolean.class, false);
		
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
		
		                    sizes.add(BigIntegerHelper.emit(DirectoryHelper.squeeze(directory, size, filter, recurse)));
		                } catch(IOException ex) {
		                    exceptions.add(ex);
		                } finally {
		                    dc.destroy();
		                }
		            }
		        }
		        IDataHelper.put(cursor, "$sizes.squeezed", sizes.toArray(new String[sizes.size()]));
		    }
		
		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

