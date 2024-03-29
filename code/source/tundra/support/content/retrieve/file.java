package tundra.support.content.retrieve;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:36:22 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.DirectoryHelper;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
// --- <<IS-END-IMPORTS>> ---

public final class file

{
	// ---( internal utility methods )---

	final static file _instance = new file();

	static file _newInstance() { return new file(); }

	static file _cast(Object o) { return (file)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		DIRECTORY_PURGES.clear();
		// --- <<IS-END>> ---

                
	}



	public static final void purgable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(purgable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $directory
		// [i] field:0:required $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:required $purgable?
		// [o] field:0:optional $purge.last
		// [o] field:0:required $purge.next
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    File directory = IDataHelper.get(cursor, "$directory", File.class);
		    String durationString = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		
		    Duration duration = DurationHelper.parse(durationString, durationPattern);
		
		    IDataHelper.put(cursor, "$purgable?", shouldPurge(directory, duration), String.class);
		    IDataHelper.put(cursor, "$purge.last", DIRECTORY_PURGES.get(directory), String.class, false);
		    IDataHelper.put(cursor, "$purge.next", nextPurge(directory, duration), String.class);
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
		// [o] field:0:required $purged?
		// [o] field:0:optional $count
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    File directory = IDataHelper.get(cursor, "$directory", File.class);
		    String durationString = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		
		    Duration duration = DurationHelper.parse(durationString, durationPattern);
		    boolean shouldPurge = shouldPurge(directory, duration);
		
		    if (shouldPurge) {
		        long count = DirectoryHelper.purge(directory, duration, null, false);
		        hasPurged(directory);
		        IDataHelper.put(cursor, "$count", count, String.class);
		    }
		
		    IDataHelper.put(cursor, "$purged?", shouldPurge, String.class);
		} catch(FileNotFoundException ex) {
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
		// [o] record:1:required $directory.purge.context
		// [o] - field:0:required directory
		// [o] - field:0:required purge.last
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$directory.purge.context", reflect());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * A cache of purged directories and the datetime they were last purged, used as a factor in determining when those
	 * directories will next be purged.
	 */
	private static final Map<File, Calendar> DIRECTORY_PURGES = new ConcurrentHashMap<File, Calendar>();
	/**
	 * The maximum allowed purge frequency for directories.
	 */
	private static final Duration MAXIMUM_DIRECTORY_PURGE_FREQUENCY = DurationHelper.parse("P1D");
	/**
	 * The factor applied to a directory's file retention period to determine frequency of purging.
	 */
	private static final BigDecimal DIRECTORY_PURGE_FREQUENCY_FACTOR = new BigDecimal("0.5");
	
	/**
	 * Returns true if the given directory is eligible for purging, taking into account the last time it was purged
	 * and the given retention period.
	 * 
	 * @param directory The directory to be purged.
	 * @param duration  The retention period for files in the given directory.
	 * @return          Whether the directory is eligible for purging.
	 */
	private static boolean shouldPurge(File directory, Duration duration) {
	    Calendar lastPurge = DIRECTORY_PURGES.get(directory);
	    return lastPurge == null || lastPurge.compareTo(DateTimeHelper.earlier(calculateFrequency(duration))) <= 0;
	}
	
	/**
	 * Returns the datetime when the given directory is next eligible for purging, taking into account the last time it 
	 * was purged and the given retention period.
	 *
	 * @param directory The directory to be purged.
	 * @param duration  The retention period for files in the given directory.
	 * @return          The datetime the directory is next eligible for purging.
	 */
	private static Calendar nextPurge(File directory, Duration duration) {
	    Calendar lastPurge = DIRECTORY_PURGES.get(directory);
	    Calendar nextPurge;
	
	    if (lastPurge == null) {
	        nextPurge = Calendar.getInstance();
	    } else {
	        nextPurge = DateTimeHelper.add(lastPurge, calculateFrequency(duration));
	    }
	
	    return nextPurge;
	}
	
	/**
	 * Returns the frequency for purging a directory given the retention period for files within it.
	 * 
	 * @param duration  The retention period for files in a directory.
	 * @return          How often the directory should be purged.
	 */
	private static Duration calculateFrequency(Duration duration) {
	    Duration frequency = duration.normalizeWith(Calendar.getInstance()).multiply(DIRECTORY_PURGE_FREQUENCY_FACTOR);
	    if (frequency.isLongerThan(MAXIMUM_DIRECTORY_PURGE_FREQUENCY)) frequency = MAXIMUM_DIRECTORY_PURGE_FREQUENCY;
	    return frequency;
	}
	
	/**
	 * Records when a directory has been purged, which is then used as a factor in determining when it will next be 
	 * purged.
	 * 
	 * @param directory The directory which was purged.
	 */
	private static void hasPurged(File directory) {
	    DIRECTORY_PURGES.put(directory, Calendar.getInstance());
	}
	
	/**
	 * Returns the internal directory purge state for diagnostic purposes.
	 *
	 * @return The internal directory purge state.
	 */
	private static IData[] reflect() {
	    List<IData> output = new ArrayList<IData>(DIRECTORY_PURGES.size());
	
	    for (Map.Entry<File, Calendar> entry : DIRECTORY_PURGES.entrySet()) {
	        IData document = IDataFactory.create();
	        IDataCursor cursor = document.getCursor();
	
	        try {
	            File directory = entry.getKey();
	            IDataHelper.put(cursor, "directory", FileHelper.normalize(directory));
	            IDataHelper.put(cursor, "purge.last", DateTimeHelper.emit(entry.getValue()));
	        } finally {
	            cursor.destroy();
	        }
	        
	        output.add(document);
	    }
	
	    return output.toArray(new IData[0]);
	}
	// --- <<IS-END-SHARED>> ---
}

