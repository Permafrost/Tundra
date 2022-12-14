package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2022-12-14 17:49:18 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.server.ScheduleHelper;
// --- <<IS-END-IMPORTS>> ---

public final class schedule

{
	// ---( internal utility methods )---

	final static schedule _instance = new schedule();

	static schedule _newInstance() { return new schedule(); }

	static schedule _cast(Object o) { return (schedule)o; }

	// ---( server methods )---




	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $schedule.id
		// [i] field:0:optional $schedule.name
		// [o] field:0:required $schedule.exists? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String id = IDataHelper.get(cursor, "$schedule.id", String.class);
		    String name = IDataHelper.get(cursor, "$schedule.name", String.class);
		    String outputKey = "$schedule.exists?";

		    if (id == null && name == null) {
		        id = IDataHelper.get(cursor, "$id", String.class);
		        name = IDataHelper.get(cursor, "$name", String.class);
		        outputKey = "$exists?";
		    }

		    if (id != null) {
		        IDataHelper.put(cursor, outputKey, ScheduleHelper.exists(id), String.class);
		    } else {
		        IDataHelper.put(cursor, outputKey, ScheduleHelper.existsByName(name), String.class);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void expedite (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(expedite)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $schedule.id
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String identity = IDataHelper.get(cursor, "$schedule.id", String.class);
		    ScheduleHelper.expedite(identity);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $schedule.id
		// [i] field:0:optional $schedule.name
		// [o] record:0:optional $schedule
		// [o] - field:0:required id
		// [o] - field:0:optional name
		// [o] - field:0:optional description
		// [o] - field:0:required type {&quot;complex&quot;,&quot;once&quot;,&quot;repeat&quot;}
		// [o] - field:0:required service
		// [o] - field:0:optional package
		// [o] - field:0:required target
		// [o] - field:0:required target.logical
		// [o] - field:0:required user
		// [o] - field:0:optional start
		// [o] - field:0:optional end
		// [o] - field:0:required overlap?
		// [o] - record:0:optional lateness
		// [o] -- field:0:required duration
		// [o] -- field:0:required action
		// [o] - record:0:optional repeat
		// [o] -- field:0:required interval
		// [o] - record:0:optional complex
		// [o] -- field:1:optional months
		// [o] -- field:1:optional days
		// [o] -- field:1:optional weekdays
		// [o] -- field:1:optional hours
		// [o] -- field:1:optional minutes
		// [o] - record:0:optional pipeline
		// [o] - field:0:required status
		// [o] - field:0:optional next
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String id = IDataHelper.get(cursor, "$schedule.id", String.class);
		    String name = IDataHelper.get(cursor, "$schedule.name", String.class);

		    if (id == null && name == null) {
		        id = IDataHelper.get(cursor, "$id", String.class);
		        name = IDataHelper.get(cursor, "$name", String.class);
		    }

		    if (id != null) {
		        IDataHelper.put(cursor, "$schedule", ScheduleHelper.get(id), false);
		    } else if (name != null) {
		        IDataHelper.put(cursor, "$schedule", ScheduleHelper.getByName(name), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void invoke (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invoke)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $schedule.id
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String identity = IDataHelper.get(cursor, "$schedule.id", String.class);
		    IData output = ScheduleHelper.invoke(identity);
		    IDataUtil.merge(output, pipeline);
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
		// [i] field:0:optional $schedule.name
		// [i] field:0:optional $schedule.service
		// [i] field:0:optional $schedule.filter
		// [o] record:1:optional $schedules
		// [o] - field:0:required id
		// [o] - field:0:optional name
		// [o] - field:0:optional description
		// [o] - field:0:required type {&quot;complex&quot;,&quot;once&quot;,&quot;repeat&quot;}
		// [o] - field:0:required service
		// [o] - field:0:optional package
		// [o] - field:0:required target
		// [o] - field:0:required target.logical
		// [o] - field:0:required user
		// [o] - field:0:optional start
		// [o] - field:0:optional end
		// [o] - field:0:required overlap?
		// [o] - record:0:optional lateness
		// [o] -- field:0:required duration
		// [o] -- field:0:required action
		// [o] - record:0:optional repeat
		// [o] -- field:0:required interval
		// [o] - record:0:optional complex
		// [o] -- field:1:optional months
		// [o] -- field:1:optional days
		// [o] -- field:1:optional weekdays
		// [o] -- field:1:optional hours
		// [o] -- field:1:optional minutes
		// [o] - record:0:optional pipeline
		// [o] - field:0:required status
		// [o] - field:0:optional next
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String name = IDataHelper.get(cursor, "$schedule.name", String.class);
		    String service = IDataHelper.get(cursor, "$schedule.service", String.class);
		    String filter = IDataHelper.get(cursor, "$schedule.filter", String.class);

		    if (name == null && service == null && filter == null) {
		        name = IDataHelper.get(cursor, "$name", String.class);
		        service = IDataHelper.get(cursor, "$service", String.class);
		        filter = IDataHelper.get(cursor, "$filter", String.class);
		    }

		    IDataHelper.put(cursor, "$schedules", ScheduleHelper.list(name, service, filter, pipeline), false, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void runnable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(runnable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $schedule.id
		// [o] field:0:required $schedule.runnable? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String id = IDataHelper.get(cursor, "$schedule.id", String.class);
		    IDataHelper.put(cursor, "$schedule.runnable?", ScheduleHelper.isRunnable(id), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

