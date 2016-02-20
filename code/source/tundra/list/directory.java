package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-02-20 21:53:22 EST
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
		// [i] field:0:optional $delete? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataUtil.getStringArray(cursor, "$directories");
		    boolean deleteSelf = BooleanHelper.parse(IDataUtil.getString(cursor, "$delete?"));
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		
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
		// [i] - field:0:optional duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [i] - field:1:optional filter.inclusions
		// [i] - field:1:optional filter.exclusions
		// [i] - field:0:optional filter.type {&quot;regular expression&quot;,&quot;wildcard&quot;,&quot;literal&quot;}
		// [i] - field:0:optional recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:optional $counts
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] directories = IDataUtil.getIDataArray(cursor, "$directories");
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    if (directories != null) {
		        List<String> counts = new ArrayList<String>(directories.length);
		        for (IData document : directories) {
		            if (document != null) {
		                IDataCursor dc = document.getCursor();
		                try {
		                    String directory = IDataUtil.getString(dc, "directory");
		                    String duration = IDataUtil.getString(dc, "duration");
		                    String pattern = IDataUtil.getString(dc, "duration.pattern");
		                    String[] inclusions = IDataUtil.getStringArray(dc, "filter.inclusions");
		                    String[] exclusions = IDataUtil.getStringArray(dc, "filter.exclusions");
		                    String type = IDataUtil.getString(dc, "filter.type");
		                    boolean recurse = BooleanHelper.parse(IDataUtil.getString(dc, "recurse?"));
		
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
		
		                    counts.add("" + DirectoryHelper.purge(directory, DurationHelper.parse(duration, pattern), filter, recurse));
		                } catch(IOException ex) {
		                    exceptions.add(ex);
		                } finally {
		                    dc.destroy();
		                }
		            }
		        }
		        IDataUtil.put(cursor, "$counts", counts.toArray(new String[counts.size()]));
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataUtil.getStringArray(cursor, "$directories");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
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
		// [i] - field:0:optional filter.type {&quot;regular expression&quot;,&quot;wildcard&quot;,&quot;literal&quot;}
		// [i] - field:0:optional recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:optional $sizes.squeezed
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] directories = IDataUtil.getIDataArray(cursor, "$directories");
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    if (directories != null) {
		        List<String> sizes = new ArrayList<String>(directories.length);
		        for (IData document : directories) {
		            if (document != null) {
		                IDataCursor dc = document.getCursor();
		
		                try {
		                    String directory = IDataUtil.getString(dc, "directory");
		                    BigInteger size = BigIntegerHelper.parse(IDataUtil.getString(cursor, "size.required"));
		                    String[] inclusions = IDataUtil.getStringArray(dc, "filter.inclusions");
		                    String[] exclusions = IDataUtil.getStringArray(dc, "filter.exclusions");
		                    String type = IDataUtil.getString(dc, "filter.type");
		                    boolean recurse = BooleanHelper.parse(IDataUtil.getString(dc, "recurse?"));
		
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
		
		                    sizes.add(BigIntegerHelper.emit(DirectoryHelper.squeeze(directory, size, filter, recurse)));
		                } catch(IOException ex) {
		                    exceptions.add(ex);
		                } finally {
		                    dc.destroy();
		                }
		            }
		        }
		        IDataUtil.put(cursor, "$sizes.squeezed", sizes.toArray(new String[sizes.size()]));
		    }
		
		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

