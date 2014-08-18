package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-18 15:53:43.526
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class thread

{
	// ---( internal utility methods )---

	final static thread _instance = new thread();

	static thread _newInstance() { return new thread(); }

	static thread _cast(Object o) { return (thread)o; }

	// ---( server methods )---




	public static final void current (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(current)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $thread
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required description
		// [o] - field:0:required state
		// [o] - field:0:required priority
		// [o] - field:0:optional group
		// [o] - field:0:required alive?
		// [o] - field:0:required daemon?
		// [o] - field:0:required interrupted?
		// [o] - record:1:optional stack
		// [o] -- field:0:required description
		// [o] -- field:0:optional file
		// [o] -- field:0:required class
		// [o] -- field:0:required method
		// [o] -- field:0:required line
		// [o] -- field:0:required native?
		// [o] - object:0:required thread
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IDataUtil.put(cursor, "$thread", current());
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
		// [o] record:1:required $threads
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required description
		// [o] - field:0:required state
		// [o] - field:0:required priority
		// [o] - field:0:optional group
		// [o] - field:0:required alive?
		// [o] - field:0:required daemon?
		// [o] - field:0:required interrupted?
		// [o] - record:1:optional stack
		// [o] -- field:0:required description
		// [o] -- field:0:optional file
		// [o] -- field:0:required class
		// [o] -- field:0:required method
		// [o] -- field:0:optional line
		// [o] -- field:0:required native?
		// [o] - object:0:required thread
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IDataUtil.put(cursor, "$threads", list());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// returns the currently executing thread in an IData representation
	public static IData current() {
	  return toIData(Thread.currentThread());
	}

	// returns a list of all threads in the current context in IData format
	public static IData[] list() {
	  ThreadGroup root = root();

	  int count = root.activeCount() + 100;
	  Thread[] list = new Thread[count];

	  count = root.enumerate(list, true);
	  list = java.util.Arrays.copyOf(list, count);

	  return toIDataArray(list);
	}

	// returns the root thread group
	public static ThreadGroup root() {
	  return root(null);
	}

	// walks up the thread group tree from the given leaf and returns the root
	private static ThreadGroup root(ThreadGroup group) {
	  if (group == null) group = Thread.currentThread().getThreadGroup();
	  if (group == null) return null; // should never happen

	  ThreadGroup parent = group.getParent();
	  if (parent == null) return group;

	  return root(parent);
	}

	// converts the given Thread[] to an IData[] representation
	public static IData[] toIDataArray(Thread[] input) {
	  if (input == null) return null;

	  IData[] output = new IData[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = toIData(input[i]);
	  }

	  return output;
	}

	// converts a Thread to an IData representation
	public static IData toIData(Thread thread) {
	  if (thread == null) return null;

	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();

	  IDataUtil.put(cursor, "id", "" + thread.getId());
	  IDataUtil.put(cursor, "name", thread.getName());
	  IDataUtil.put(cursor, "description", thread.toString());
	  IDataUtil.put(cursor, "state", thread.getState().toString());
	  IDataUtil.put(cursor, "priority", "" + thread.getPriority());

	  ThreadGroup group = thread.getThreadGroup();
	  if (group != null) IDataUtil.put(cursor, "group", group.getName());

	  IDataUtil.put(cursor, "alive?", "" + thread.isAlive());
	  IDataUtil.put(cursor, "interrupted?", "" + thread.isInterrupted());
	  IDataUtil.put(cursor, "daemon?", "" + thread.isDaemon());

	  IData[] stack = toIDataArray(thread.getStackTrace());
	  if (stack != null) IDataUtil.put(cursor, "stack", stack);

	  IDataUtil.put(cursor, "thread", thread);

	  cursor.destroy();

	  return output;
	}

	// converts a StackTraceElement[] to an IData[] representation
	public static IData[] toIDataArray(StackTraceElement[] input) {
	  if (input == null || input.length == 0) return null;

	  IData[] output = new IData[input.length];

	  for (int i = 0; i < input.length; i++) {
	    output[i] = toIData(input[i]);
	  }

	  return output;
	}

	// converts a StackTraceElement to an IData representation
	public static IData toIData(StackTraceElement input) {
	  if (input == null) return null;

	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();

	  IDataUtil.put(cursor, "description", input.toString());

	  String file = input.getFileName();
	  if (file != null) IDataUtil.put(cursor, "file", file);

	  IDataUtil.put(cursor, "class", input.getClassName());
	  IDataUtil.put(cursor, "method", input.getMethodName());

	  int line = input.getLineNumber();
	  if (line >= 0) IDataUtil.put(cursor, "line", "" + line);

	  IDataUtil.put(cursor, "native?", "" + input.isNativeMethod());

	  cursor.destroy();

	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

