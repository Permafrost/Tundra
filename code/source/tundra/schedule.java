package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-12-14 13:31:51 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.LongHelper;
import permafrost.tundra.server.SchedulerHelper;
import permafrost.tundra.server.ServiceHelper;
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
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
		        IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(exists(id)));
		    } else {
		        IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(existsByName(name)));
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
		        IData schedule = get(id);
		        if (schedule != null) IDataUtil.put(cursor, "$schedule", schedule);
		    } else if (name != null) {
		        IData schedule = getByName(name);
		        if (schedule != null) IDataUtil.put(cursor, "$schedule", schedule);
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
		
		    IData[] schedules = list(name, service, filter, pipeline);
		
		    if (schedules != null && schedules.length > 0) IDataUtil.put(cursor, "$schedules", schedules);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the scheduled task identified by the given name, or null if no
	// task for that name exists
	public static IData getByName(String name) throws ServiceException {
	    IData[] results = list(name, null, null, null);
	    return results != null && results.length > 0 ? results[0] : null;
	}
	
	// returns the scheduled task identified by the given id, or null if no
	// task for that id exists
	public static IData get(String id) throws ServiceException {
	    if (id == null || !exists(id)) return null;
	
	    IData pipeline = IDataFactory.create();
	    IDataCursor cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, "taskID", id);
	    cursor.destroy();
	
	    pipeline = ServiceHelper.invoke("pub.scheduler:getTaskInfo", pipeline);
	
	    cursor = pipeline.getCursor();
	    String type = IDataUtil.getString(cursor, "type");
	    String user = IDataUtil.getString(cursor, "runAsUser");
	    String target = IDataUtil.getString(cursor, "target");
	    String description = IDataUtil.getString(cursor, "description");
	    String lateness = IDataUtil.getString(cursor, "lateness");
	    String latenessAction = IDataUtil.getString(cursor, "latenessAction");
	    String service = IDataUtil.getString(cursor, "service");
	    long next = Long.parseLong(IDataUtil.getString(cursor, "nextRun"));
	    int state = Integer.parseInt(IDataUtil.getString(cursor, "execState"));
	    IData inputs = IDataUtil.getIData(cursor, "inputs");
	
	    boolean overlap = true;
	
	    String startDate = null, startTime = null, startDateTime = null, endDate = null, endTime = null, endDateTime = null;
	    IData info = null;
	
	    if (type.equals("repeat")) {
	        info = IDataUtil.getIData(cursor, "repeatingTaskInfo");
	        IDataCursor ic = info.getCursor();
	
	        String intervalSeconds = IDataUtil.getString(ic, "interval");
	        overlap = !Boolean.valueOf(IDataUtil.getString(ic, "doNotOverlap"));
	
	        startDate = IDataUtil.getString(ic, "startDate");
	        startTime = IDataUtil.getString(ic, "startTime");
	
	        endDate = IDataUtil.getString(ic, "endDate");
	        endTime = IDataUtil.getString(ic, "endTime");
	
	        ic.destroy();
	
	        info = IDataFactory.create();
	        ic = info.getCursor();
	
	        IDataUtil.put(ic, "interval", DurationHelper.format(intervalSeconds, DurationPattern.SECONDS, DurationPattern.XML));
	
	        ic.destroy();
	    } else if (type.equals("complex")) {
	        info = IDataUtil.getIData(cursor, "complexTaskInfo");
	        IDataCursor ic = info.getCursor();
	
	        overlap = !Boolean.valueOf(IDataUtil.getString(ic, "doNotOverlap"));
	
	        startDate = IDataUtil.getString(ic, "startDate");
	        startTime = IDataUtil.getString(ic, "startTime");
	
	        endDate = IDataUtil.getString(ic, "endDate");
	        endTime = IDataUtil.getString(ic, "endTime");
	
	        String[] minutes = IDataUtil.getStringArray(ic, "minutes");
	        String[] hours = IDataUtil.getStringArray(ic, "hours");
	        String[] months = IDataUtil.getStringArray(ic, "months");
	        String[] daysOfMonth = IDataUtil.getStringArray(ic, "daysOfMonth");
	        String[] daysOfWeek = IDataUtil.getStringArray(ic, "daysOfWeek");
	
	        ic.destroy();
	
	        info = IDataFactory.create();
	        ic = info.getCursor();
	
	        if (months != null) IDataUtil.put(ic, "months", months);
	        if (daysOfMonth != null) IDataUtil.put(ic, "days", daysOfMonth);
	        if (daysOfWeek != null) IDataUtil.put(ic, "weekdays", daysOfWeek);
	        if (hours != null) IDataUtil.put(ic, "hours", hours);
	        if (minutes != null) IDataUtil.put(ic, "minutes", minutes);
	
	        ic.destroy();
	    } else {
	        info = IDataUtil.getIData(cursor, "oneTimeTaskInfo");
	        IDataCursor ic = info.getCursor();
	        startDate = endDate = IDataUtil.getString(ic, "date");
	        startTime = endTime = IDataUtil.getString(ic, "time");
	        ic.destroy();
	        info = null;
	    }
	    cursor.destroy();
	
	    IData output = IDataFactory.create();
	    cursor = output.getCursor();
	
	    IDataUtil.put(cursor, "id", id);
	    if (inputs != null) {
	        IDataCursor inputCursor = inputs.getCursor();
	        String name = IDataUtil.getString(inputCursor, "$schedule.name");
	        inputCursor.destroy();
	
	        if (name != null) IDataUtil.put(cursor, "name", name);
	    }
	
	    if (description != null) IDataUtil.put(cursor, "description", description);
	    IDataUtil.put(cursor, "type", type);
	    IDataUtil.put(cursor, "service", service);
	    String packageName = getPackageName(service);
	    if (packageName != null) IDataUtil.put(cursor, "package", packageName);
	    IDataUtil.put(cursor, "target", target);
	
	    if (SchedulerHelper.self().equalsIgnoreCase(target)) {
	        IDataUtil.put(cursor, "target.logical", "$self");
	    } else {
	        IDataUtil.put(cursor, "target.logical", target);
	    }
	
	    IDataUtil.put(cursor, "user", user);
	
	    String pattern = "yyyy/MM/dd HH:mm:ss";
	
	    if (startDate != null && startTime != null) {
	        startDateTime = DateTimeHelper.format(startDate + " " + startTime, pattern, "datetime");
	        IDataUtil.put(cursor, "start", startDateTime);
	    }
	
	    if (endDate != null && endTime != null) {
	        endDateTime = DateTimeHelper.format(endDate + " " + endTime, pattern, "datetime");
	        IDataUtil.put(cursor, "end", endDateTime);
	    }
	
	    IDataUtil.put(cursor, "overlap?", BooleanHelper.emit(overlap));
	
	    if (lateness != null) {
	        IData late = IDataFactory.create();
	        IDataCursor lc = late.getCursor();
	
	        IDataUtil.put(lc, "duration", DurationHelper.format(lateness, DurationPattern.MINUTES, DurationPattern.XML));
	
	        if (latenessAction.equals("0") || latenessAction.equalsIgnoreCase("run immediately")) {
	            latenessAction = "run immediately";
	        } else if (latenessAction.equals("1") || latenessAction.equalsIgnoreCase("skip and run at next scheduled interval")) {
	            latenessAction = "skip and run at next scheduled interval";
	        } else if (latenessAction.equals("2") || latenessAction.equalsIgnoreCase("suspend")) {
	            latenessAction = "suspend";
	        }
	
	        IDataUtil.put(lc, "action", latenessAction);
	        lc.destroy();
	
	        IDataUtil.put(cursor, "lateness", late);
	    }
	
	    if (info != null) IDataUtil.put(cursor, type, info);
	    if (inputs != null && IDataHelper.size(inputs) > 0) IDataUtil.put(cursor, "pipeline", inputs);
	
	    String stateString = null;
	    switch(state) {
	        case com.wm.app.b2b.server.scheduler.ScheduledTask.STATE_READY:
	            stateString = "waiting";
	            break;
	        case com.wm.app.b2b.server.scheduler.ScheduledTask.STATE_RUNNING:
	            stateString = "running";
	            break;
	        case com.wm.app.b2b.server.scheduler.ScheduledTask.STATE_SUSPENDED:
	            stateString = "suspended";
	            break;
	        case com.wm.app.b2b.server.scheduler.ScheduledTask.STATE_DELETED:
	            stateString = "cancelled";
	            break;
	        default:
	            ExceptionHelper.raise("Scheduled task '" + id + "' has unsupported status: " + state);
	            break;
	    }
	    IDataUtil.put(cursor, "status", stateString);
	
	    if (next > 0) IDataUtil.put(cursor, "next", DateTimeHelper.format(LongHelper.emit(next), "milliseconds", "datetime"));
	
	    cursor.destroy();
	
	    return output;
	}
	
	// returns the name of the package the given service is a member of
	protected static String getPackageName(String service) {
	    String packageName = null;
	    try {
	        com.wm.app.b2b.server.BaseService bs = com.wm.app.b2b.server.ns.Namespace.getService(com.wm.lang.ns.NSName.create(service));
	        packageName = bs.getPackageName();
	    } catch (Exception ex) {}
	    return packageName;
	}
	
	// returns true if a scheduled task with the given id exists, false otherwise
	public static boolean exists(String id) throws ServiceException {
	    if (id == null) return false;
	
	    String[] ids = listIDs(false);
	
	    return ids != null && ids.length > 0 && java.util.Arrays.binarySearch(ids, id) >= 0;
	}
	
	// returns true if a scheduled task with the given id exists, false otherwise
	public static boolean existsByName(String name) throws ServiceException {
	    if (name == null) return false;
	    return getByName(name) != null;
	}
	
	// returns all scheduled tasks matching the given filter condition
	public static IData[] list(String filter, IData pipeline) throws ServiceException {
	    return list(null, filter, pipeline);
	}
	
	// returns all scheduled tasks matching the given service and filter condition
	public static IData[] list(String service, String filter, IData pipeline) throws ServiceException {
	    return list(null, service, filter, pipeline);
	}
	
	// returns all scheduled tasks matching the given service and filter condition
	public static IData[] list(String name, String service, String filter, IData pipeline) throws ServiceException {
	    if (pipeline == null) pipeline = IDataFactory.create();
	
	    String[] ids = listIDs();
	
	    java.util.List<IData> tasks = new java.util.ArrayList<IData>(ids.length);
	
	    for (int i = 0; i < ids.length; i++) {
	        IData task = get(ids[i]);
	
	        boolean matched = true;
	
	        if (name != null) {
	            IDataCursor cursor = task.getCursor();
	            String taskName = IDataUtil.getString(cursor, "name");
	            cursor.destroy();
	    
	            matched = matched && name.equals(taskName);
	        }
	
	        if (service != null) {
	            IDataCursor cursor = task.getCursor();
	            String taskService = IDataUtil.getString(cursor, "service");
	            cursor.destroy();
	    
	            matched = matched && service.equals(taskService);
	        }
	
	        if (filter != null) {
	            IData scope = IDataUtil.clone(pipeline);
	            IDataCursor cursor = scope.getCursor();
	            IDataUtil.put(cursor, "$schedule", task);
	            cursor.destroy();
	
	            matched = matched && ConditionEvaluator.evaluate(filter, scope);
	        }
	
	        if (matched) tasks.add(task);
	    }
	
	    return tasks.toArray(new IData[tasks.size()]);
	}
	
	// returns all scheduled task IDs
	protected static String[] listIDs() throws ServiceException {
	    return listIDs(false);
	}
	
	// returns all scheduled task IDs, or only those that are currently running if the
	// given boolean is true
	protected static String[] listIDs(boolean running) throws ServiceException {
	    IData pipeline = IDataFactory.create();
	
	    IDataCursor cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, "running", BooleanHelper.emit(running));
	    cursor.destroy();
	
	    pipeline = ServiceHelper.invoke("pub.scheduler:getTaskIDs", pipeline);
	
	    cursor = pipeline.getCursor();
	    String[] list = IDataUtil.getStringArray(cursor, "taskIDs");
	    cursor.destroy();
	
	    if (list != null && list.length > 1) java.util.Arrays.sort(list);
	
	    return list;
	}
	// --- <<IS-END-SHARED>> ---
}

