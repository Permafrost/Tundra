package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-12-15 11:39:11 GMT+10:00
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
		// [i] field:0:optional $id
		// [i] field:0:optional $name
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String id = IDataUtil.getString(cursor, "$id");
		    String name = IDataUtil.getString(cursor, "$name");

		    if (id != null) {
		        IDataHelper.put(cursor, "$exists?", BooleanHelper.emit(ScheduleHelper.exists(id)));
		    } else {
		        IDataHelper.put(cursor, "$exists?", BooleanHelper.emit(ScheduleHelper.existsByName(name)));
		    }
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
		// [i] field:0:optional $id
		// [i] field:0:optional $name
		// [o] record:0:optional $schedule
		// [o] - field:0:required id
		// [o] - field:0:optional name
		// [o] - field:0:optional description
		// [o] - field:0:required type {"complex","once","repeat"}
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
		    String id = IDataUtil.getString(cursor, "$id");
		    String name = IDataUtil.getString(cursor, "$name");

		    if (id != null) {
		        IDataHelper.put(cursor, "$schedule", ScheduleHelper.get(id), true);
		    } else if (name != null) {
		        IDataHelper.put(cursor, "$schedule", ScheduleHelper.getByName(name), true);
		    }
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
		// [i] field:0:optional $name
		// [i] field:0:optional $service
		// [i] field:0:optional $filter
		// [o] record:1:optional $schedules
		// [o] - field:0:required id
		// [o] - field:0:optional name
		// [o] - field:0:optional description
		// [o] - field:0:required type {"complex","once","repeat"}
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
		    String name = IDataUtil.getString(cursor, "$name");
		    String service = IDataUtil.getString(cursor, "$service");
		    String filter = IDataUtil.getString(cursor, "$filter");

		    IDataHelper.put(cursor, "$schedules", ScheduleHelper.list(name, service, filter, pipeline), false, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

