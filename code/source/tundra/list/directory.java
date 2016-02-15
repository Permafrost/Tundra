package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-02-15 11:53:21 EST
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
		// [i] field:1:required $directories
		// [i] field:0:required $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataUtil.getStringArray(cursor, "$directories");
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String pattern = IDataUtil.getString(cursor, "$duration.pattern");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    for (String directory : directories) {
		        try {
		            DirectoryHelper.purge(directory, DurationHelper.parse(duration, pattern), recurse);
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
		// [i] field:1:required $directories
		// [i] field:0:optional $size
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] directories = IDataUtil.getStringArray(cursor, "$directories");
		    BigInteger size = BigIntegerHelper.parse(IDataUtil.getString(cursor, "$size"));
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    for (String directory : directories) {
		        try {
		            DirectoryHelper.squeeze(directory, size, recurse);
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
}

