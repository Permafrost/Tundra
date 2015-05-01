package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 13:12:20 EST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.flow.ConditionEvaluator;
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
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String id = IDataUtil.getString(cursor, "$id");
		  IDataUtil.put(cursor, "$exists?", "" + exists(id));
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
		// [o] record:0:optional $schedule
		// [o] - field:0:required id
		// [o] - field:0:required type {"complex","once","repeat"}
		// [o] - field:0:required service
		// [o] - field:0:optional package
		// [o] - field:0:optional description
		// [o] - field:0:required target
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
		  IData schedule = get(id);
		  if (schedule != null) IDataUtil.put(cursor, "$schedule", schedule);
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
		// [i] field:0:optional $filter
		// [o] record:1:optional $schedules
		// [o] - field:0:required id
		// [o] - field:0:required type {"complex","once","repeat"}
		// [o] - field:0:required service
		// [o] - field:0:optional package
		// [o] - field:0:optional description
		// [o] - field:0:required target
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
		  String filter = IDataUtil.getString(cursor, "$filter");
		  IData[] schedules = list(filter, pipeline);
		  if (schedules != null && schedules.length > 0) IDataUtil.put(cursor, "$schedules", schedules);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the scheduled task identified by the given id, or null if no
	// task for that id exists
	public static IData get(String id) throws ServiceException {
	  if (id == null || !exists(id)) return null;
	
	  IData pipeline = IDataFactory.create();
	  IDataCursor cursor = pipeline.getCursor();
	  IDataUtil.put(cursor, "taskID", id);
	  cursor.destroy();
	
	  pipeline = tundra.service.invoke("pub.scheduler:getTaskInfo", pipeline);
	
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
	
	    IDataUtil.put(ic, "interval", tundra.duration.format(intervalSeconds, "seconds", "xml"));
	    
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
	  IDataUtil.put(cursor, "type", type);
	  IDataUtil.put(cursor, "service", service);
	  String packageName = getPackageName(service);
	  if (packageName != null) IDataUtil.put(cursor, "package", packageName);
	  if (description != null) IDataUtil.put(cursor, "description", description);
	  IDataUtil.put(cursor, "target", target);
	  IDataUtil.put(cursor, "user", user);
	
	  String pattern = "yyyy/MM/dd HH:mm:ss";
	
	  if (startDate != null && startTime != null) {
	    startDateTime = tundra.datetime.format(startDate + " " + startTime, pattern, "datetime");
	    IDataUtil.put(cursor, "start", startDateTime);
	  }
	
	  if (endDate != null && endTime != null) {
	    endDateTime = tundra.datetime.format(endDate + " " + endTime, pattern, "datetime");
	    IDataUtil.put(cursor, "end", endDateTime);
	  }
	
	  IDataUtil.put(cursor, "overlap?", "" + overlap);
	
	  if (lateness != null) {
	    IData late = IDataFactory.create();
	    IDataCursor lc = late.getCursor();
	
	    IDataUtil.put(lc, "duration", "" + tundra.duration.format(lateness, "minutes", "xml"));
	
	    if (latenessAction.equals("0") || latenessAction.equalsIgnoreCase("run immediately")) {
	      latenessAction = "run immediately";
	    } else if (latenessAction.equals("1") || latenessAction.equalsIgnoreCase("skip and run at next scheduled interval")) {
	      latenessAction = "skip and run at next scheduled interval";
	    } else if (latenessAction.equals("2") || latenessAction.equalsIgnoreCase("suspend")) {
	      latenessAction = "suspend";
	    }
	
	    IDataUtil.put(lc, "action", "" + latenessAction);
	    lc.destroy();
	
	    IDataUtil.put(cursor, "lateness", late);
	  }  
	
	  if (info != null) IDataUtil.put(cursor, type, info);
	  if (inputs != null && tundra.document.size(inputs) > 0) IDataUtil.put(cursor, "pipeline", inputs);
	
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
	      tundra.exception.raise("Scheduled task '" + id + "' has unsupported status: " + state);
	      break;
	  }
	  IDataUtil.put(cursor, "status", stateString);  
	
	  if (next > 0) IDataUtil.put(cursor, "next", tundra.datetime.format("" + next, "milliseconds", "datetime"));
	
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
	
	// returns all scheduled tasks matching the given filter condition
	public static IData[] list(String filter, IData pipeline) throws ServiceException {
	  if (pipeline == null) pipeline = IDataFactory.create();
	
	  String[] ids = listIDs();
	  
	  java.util.List<IData> tasks = new java.util.ArrayList<IData>(ids.length);
	
	  for (int i = 0; i < ids.length; i++) {
	    IData task = get(ids[i]);
	
	    if (filter == null ) {
	      tasks.add(task);
	    } else {
	      IData scope = IDataUtil.clone(pipeline);
	      IDataCursor cursor = scope.getCursor();
	      IDataUtil.put(cursor, "$schedule", task);
	      cursor.destroy();
	
	      if (ConditionEvaluator.evaluate(filter, scope)) {
	        tasks.add(task);
	      }
	    }
	  }
	
	  return tasks.toArray(new IData[0]);
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
	  IDataUtil.put(cursor, "running", "" + running);
	  cursor.destroy();
	  
	  pipeline = tundra.service.invoke("pub.scheduler:getTaskIDs", pipeline);
	
	  cursor = pipeline.getCursor();
	  String[] list = IDataUtil.getStringArray(cursor, "taskIDs");
	  cursor.destroy();
	
	  if (list != null && list.length > 1) java.util.Arrays.sort(list);
	
	  return list;
	}
	// --- <<IS-END-SHARED>> ---
}

