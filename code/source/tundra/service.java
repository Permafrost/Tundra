package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-15 17:40:31.468
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void callstack (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(callstack)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:1:required $callstack
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$callstack", callstack());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void ensure (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ensure)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $service
		// [i] field:0:optional $catch
		// [i] field:0:optional $finally
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String $service = IDataUtil.getString(cursor, "$service");
		  String $catch = IDataUtil.getString(cursor, "$catch");
		  String $finally = IDataUtil.getString(cursor, "$finally");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  boolean scoped = scope != null;
		
		  scope = ensure($service, scoped ? scope : pipeline, $catch, $finally);
		
		  if (scoped) IDataUtil.put(cursor, "$pipeline", scope);
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
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $mode {&quot;synchronous&quot;,&quot;asynchronous&quot;}
		// [o] record:0:optional $pipeline
		// [o] object:0:optional $thread
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (mode == null) mode = "synchronous";
		  boolean scoped = scope != null;
		
		  Object value = tundra.service.invoke(service, scoped ? scope : pipeline, mode);
		  String key = mode.equals("asynchronous")? "$thread" : "$pipeline";
		
		  if (scoped || mode.equals("asynchronous")) {
		    IDataUtil.put(cursor, key, value);
		  }
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
		// [i] object:0:optional $thread
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  com.wm.app.b2b.server.ServiceThread thread = (com.wm.app.b2b.server.ServiceThread)IDataUtil.get(cursor, "$thread");
		
		  if (thread != null) IDataUtil.put(cursor, "$pipeline", join(thread));
		} catch(Exception ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String duration = IDataUtil.getString(cursor, "$duration");
		  sleep(duration);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the invocation call stack
	private static String[] callstack() {
	  java.util.Iterator stack = com.wm.app.b2b.server.InvokeState.getCurrentState().getCallStack().iterator();
	  java.util.List<String> services = new java.util.ArrayList<String>();
	  while (stack.hasNext()) {
	    services.add(stack.next().toString());
	  }
	  // remove call to tundra.invoke:callstack from the stack
	  if (services.size() > 0) services.remove(services.size() - 1);
	  return (String[])services.toArray(new String[services.size()]);
	}
	
	// invokes the given service synchronously
	public static IData invoke(String service, IData pipeline) throws ServiceException {  
	  return (IData)invoke(service, pipeline, "synchronous");
	}
	
	// invokes the given service either synchronously or asynchronously
	public static Object invoke(String service, IData pipeline, String mode) throws ServiceException {  
	  Object result = null;
	  if (mode.equals("synchronous")) {
	    result = invoke.synchronous(service, pipeline);
	  } else if (mode.equals("asynchronous")) {
	    result = invoke.asynchronous(service, pipeline);
	  } else {
	    throw new IllegalArgumentException("mode must be either 'synchronous' or 'asynchronous': " + mode);
	  }
	  return result;
	}
	
	public static class invoke {
	  // invokes a service asynchronously
	  public static com.wm.app.b2b.server.ServiceThread asynchronous(String service, IData pipeline) {
	    if (pipeline == null) pipeline = IDataFactory.create();
	    if (service == null) return null;
	    
	    IData scope = IDataUtil.clone(pipeline);
	    com.wm.lang.ns.NSName name = com.wm.lang.ns.NSName.create(service);  
	    com.wm.app.b2b.server.ServiceThread thread = com.wm.app.b2b.server.Service.doThreadInvoke(name, scope);
	
	    return thread;
	  }
	
	  // invokes a service synchronously
	  public static IData synchronous(String service, IData pipeline) throws ServiceException {
	    if (pipeline == null) pipeline = IDataFactory.create();
	    if (service == null) return pipeline;
	    
	    IData scope = IDataUtil.clone(pipeline);
	    com.wm.lang.ns.NSName name = com.wm.lang.ns.NSName.create(service);
	
	    try {
	      scope = com.wm.app.b2b.server.Service.doInvoke(name, scope);
	    } catch (Exception ex) {
	      tundra.exception.raise(ex);
	    }
	
	    IDataUtil.merge(scope, pipeline);
	    
	    return pipeline;
	  }
	}
	
	// waits for an asynchronously invoked service to complete
	public static IData join(com.wm.app.b2b.server.ServiceThread thread) throws ServiceException {
	  IData pipeline = IDataFactory.create();
	
	  if (thread != null) {
	    try {
	      pipeline = thread.getIData();
	    } catch (Exception ex) {
	      tundra.exception.raise(ex);
	    }
	  }
	  
	  return pipeline;
	}
	
	// sleeps the current thread for the given duration
	public static void sleep(String duration) throws ServiceException {
	  sleep(tundra.duration.parse(duration).getTimeInMillis(new java.util.Date()));
	}
	
	// sleeps the current thread for the given duration
	public static void sleep(long milliseconds) throws ServiceException {
	  try {
	    Thread.sleep(milliseconds);
	  } catch(InterruptedException ex) {
	    tundra.exception.raise(ex);
	  }
	}
	
	public static IData ensure(String service, IData pipeline) throws ServiceException {
	  return ensure(service, pipeline, null, null);
	}
	
	public static IData ensure(String service, IData pipeline, String catchService, String finallyService) throws ServiceException {
	  if (catchService == null) {
	    pipeline = tryFinally(service, pipeline, finallyService);
	  } else {
	    pipeline = tryCatchFinally(service, pipeline, catchService, finallyService);
	  }
	  return pipeline;
	}
	
	private static IData tryFinally(String service, IData pipeline, String finallyService) throws ServiceException {
	  try {
	    pipeline = invoke.synchronous(service, pipeline);
	  } finally {
	    if (finallyService != null) pipeline = ensure(finallyService, pipeline);
	  }
	
	  return pipeline;
	}
	
	private static IData tryCatchFinally(String service, IData pipeline, String catchService, String finallyService) throws ServiceException {
	  try {
	    pipeline = invoke.synchronous(service, pipeline);
	  } catch (Throwable t) {
	    IDataCursor cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, "$exception", t);
	    IDataUtil.put(cursor, "$exception.class", t.getClass().getName());
	    IDataUtil.put(cursor, "$exception.message", t.getMessage());
	    IDataUtil.put(cursor, "$exception.stack", tundra.exception.stack(t));
	    cursor.destroy();
	
	    pipeline = ensure(catchService, pipeline);
	  } finally {
	    if (finallyService != null) pipeline = ensure(finallyService, pipeline);
	  }
	
	  return pipeline;
	}
	// --- <<IS-END-SHARED>> ---
}

