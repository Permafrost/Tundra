package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-05-26 11:52:04 EST
// -----( ON-HOST: 172.16.189.135

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




	public static final void chain (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(chain)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required $services
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] services = IDataUtil.getStringArray(cursor, "$services");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  boolean scoped = scope != null;
		
		  scope = chain(services, scoped ? scope : pipeline);
		
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
		// [i] record:1:optional $invocations
		// [i] - field:0:required service
		// [i] - record:0:optional pipeline
		// [i] field:0:optional $mode {&quot;synchronous&quot;,&quot;asynchronous&quot;}
		// [i] field:0:optional $concurrency
		// [o] record:1:optional $invocations
		// [o] - field:0:required service
		// [o] - record:0:optional pipeline
		// [o] - object:0:optional thread
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] invocations = IDataUtil.getIDataArray(cursor, "$invocations");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  String concurrency = IDataUtil.getString(cursor, "$concurrency");
		
		  IDataUtil.put(cursor, "$invocations", invoke(invocations, mode, concurrency));
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
		// [i] object:1:optional $threads
		// [o] record:1:optional $pipelines
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] threads = IDataUtil.getObjectArray(cursor, "$threads");
		  IDataUtil.put(cursor, "$pipelines", join(threads));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// invokes a list of services with a shared pipeline
	public static IData chain(String[] services, IData pipeline) throws ServiceException {
	  if (services != null) {
	    for (int i = 0; i < services.length; i++) {
	      pipeline = tundra.service.invoke(services[i], pipeline);
	    }
	  }
	  return pipeline;
	}
	
	// invokes a list of services either synchronously or asynchronously
	public static IData[] invoke(IData[] invocations, String mode, String concurrency) throws ServiceException {
	  return invoke(invocations, mode, concurrency == null ? 1 : Integer.parseInt(concurrency));
	}
	
	// invokes a list of services either synchronously or asynchronously
	public static IData[] invoke(IData[] invocations, String mode, int concurrency) throws ServiceException {
	  IData[] results = null;
	  if (mode == null || mode.equals("synchronous")) {
	    results = invoke.synchronous(invocations, concurrency);
	  } else if (mode.equals("asynchronous")) {
	    results = invoke.asynchronous(invocations);
	  } else {
	    throw new IllegalArgumentException("mode must be either 'synchronous' or 'asynchronous': " + mode);
	  }
	  return results;
	}
	
	public static class invoke {
	  // invokes a list of services asynchronously
	  public static IData[] asynchronous(IData[] invocations) throws ServiceException {
	    if (invocations != null) {
	      Throwable[] errors = new Throwable[invocations.length];
	      boolean hasError = false;
	
	      for (int i = 0; i < invocations.length; i++) {
	        IDataCursor cursor = invocations[i].getCursor();
	        try {
	          String service = IDataUtil.getString(cursor, "service");
	          IData pipeline = IDataUtil.getIData(cursor, "pipeline");
	          IDataUtil.put(cursor, "thread", tundra.service.invoke.asynchronous(service, pipeline));
	        } catch (Exception ex) {
	          hasError = true;
	          errors[i] = ex;
	        } finally {
	          cursor.destroy();
	        }
	      }
	
	      if (hasError) tundra.exception.raise(errors);
	    }
	
	    return invocations;
	  }
	
	  // invokes a list of services synchronously
	  public static IData[] synchronous(IData[] invocations, int concurrency) throws ServiceException {
	    if (concurrency <= 1) {
	      invocations = synchronous(invocations);
	    } else if (invocations != null) {
	      if (concurrency > invocations.length) concurrency = invocations.length;
	
	      IData[][] table = partition(invocations, concurrency, IData.class, IData[].class);
	      com.wm.app.b2b.server.ServiceThread[] threads = new com.wm.app.b2b.server.ServiceThread[concurrency];
	
	      for (int i = 0; i < concurrency; i++) {
	        IData scope = IDataFactory.create();
	        IDataCursor cursor = scope.getCursor();
	
	        IDataUtil.put(cursor, "$invocations", table[i]);
	        IDataUtil.put(cursor, "$mode", "synchronous");
	
	        threads[i] = tundra.service.invoke.asynchronous("tundra.list.service:invoke", scope);
	      }
	
	      join(threads);
	    }
	
	    return invocations;
	  }
	
	  public static <T> T[][] partition(T[] list, int count, Class<T> elementClass, Class<T[]> arrayClass) {
	    java.util.ArrayList<T[]> table = new java.util.ArrayList<T[]>();
	
	    for (int i = 0; i < count; i++) {
	      java.util.ArrayList<T> copy = new java.util.ArrayList<T>();
	      for (int j = i; j < list.length; j = j + count) {
	        copy.add(list[j]);
	      }
	      table.add((T[])copy.toArray((T[])java.lang.reflect.Array.newInstance(elementClass, 0)));
	    }
	
	    return (T[][])table.toArray((T[][])java.lang.reflect.Array.newInstance(arrayClass, 0));
	  }
	
	  // invokes a list of services synchronously
	  public static IData[] synchronous(IData[] invocations) throws ServiceException {
	    if (invocations != null) {
	      Throwable[] errors = new Throwable[invocations.length];
	      boolean hasError = false;
	
	      for (int i = 0; i < invocations.length; i++) {
	        IDataCursor cursor = invocations[i].getCursor();
	        try {
	          String service = IDataUtil.getString(cursor, "service");
	          IData pipeline = IDataUtil.getIData(cursor, "pipeline");
	          IDataUtil.put(cursor, "pipeline", tundra.service.invoke.synchronous(service, pipeline));
	        } catch (Exception ex) {
	          hasError = true;
	          errors[i] = ex;
	        } finally {
	          cursor.destroy();
	        }
	      }
	
	      if (hasError) tundra.exception.raise(errors);
	    }
	
	    return invocations;
	  }  
	}
	
	// waits for a list of asynchronously invoked services to complete
	public static IData[] join(com.wm.app.b2b.server.ServiceThread[] threads) throws ServiceException {
	  IData[] pipelines = null;
	  boolean hasError = false;
	
	  if (threads != null) {
	    pipelines = new IData[threads.length];
	    Throwable[] errors = new Throwable[threads.length];
	
	    for (int i = 0; i < threads.length; i++) {
	      try {
	        if (threads[i] != null) pipelines[i] = threads[i].getIData();
	      } catch (Exception ex) {
	        hasError = true;
	        errors[i] = ex;
	      }
	    }
	
	    if (hasError) tundra.exception.raise(errors);
	  }
	  
	  return pipelines;
	}
	
	// waits for a list of asynchronously invoked services to complete
	public static IData[] join(Object[] threads) throws ServiceException {
	  IData[] pipelines = null;
	  if (threads != null) {
	    pipelines = join(java.util.Arrays.copyOf(threads, threads.length, com.wm.app.b2b.server.ServiceThread[].class));
	  }
	  return pipelines;
	}
	// --- <<IS-END-SHARED>> ---
}

