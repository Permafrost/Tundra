package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-12-14T14:14:52.183
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
import java.lang.reflect.Array;
import java.util.Arrays;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
import permafrost.tundra.server.ServiceHelper;
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
		// [i] field:1:optional $services
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $pipeline
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] services = IDataHelper.get(cursor, "$services", String[].class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;

		    long start = System.nanoTime();
		    scope = ServiceHelper.chain(services, scoped ? scope : pipeline);
		    long end = System.nanoTime();

		    if (scoped) IDataHelper.put(cursor, "$pipeline", scope);
		    IDataHelper.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.NANOSECONDS, DurationPattern.XML));
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
		// [i] field:1:optional $services
		// [i] field:0:optional $catch
		// [i] field:0:optional $finally
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] $services = IDataHelper.get(cursor, "$services", String[].class);
		    String $catch = IDataHelper.get(cursor, "$catch", String.class);
		    String $finally = IDataHelper.get(cursor, "$finally", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;

		    scope = ensure($services, scoped ? scope : pipeline, $catch, $finally);

		    if (scoped) IDataHelper.put(cursor, "$pipeline", scope);
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
		    IData[] invocations = IDataHelper.get(cursor, "$invocations", IData[].class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		    String concurrency = IDataHelper.get(cursor, "$concurrency", String.class);

		    IDataHelper.put(cursor, "$invocations", invoke(invocations, mode, concurrency));
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
		    Object[] threads = IDataHelper.get(cursor, "$threads", Object[].class);
		    IDataHelper.put(cursor, "$pipelines", join(threads), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// provides a try/catch/finally pattern for chained flow services
	public static IData ensure(String[] services, IData pipeline, String catchService, String finallyService) throws ServiceException {
	    try {
	        pipeline = ServiceHelper.chain(services, pipeline);
	    } catch (Throwable exception) {
	        pipeline = ServiceHelper.rescue(catchService, pipeline, exception);
	    } finally {
	        pipeline = ServiceHelper.invoke(finallyService, pipeline);
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
	                    String service = IDataHelper.get(cursor, "service", String.class);
	                    IData pipeline = IDataHelper.get(cursor, "pipeline", IData.class);
	                    IDataHelper.put(cursor, "thread", ServiceHelper.fork(service, pipeline));
	                } catch (Exception ex) {
	                    hasError = true;
	                    errors[i] = ex;
	                } finally {
	                    cursor.destroy();
	                }
	            }

	            if (hasError) ExceptionHelper.raise(errors);
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
	            ServiceThread[] threads = new ServiceThread[concurrency];

	            for (int i = 0; i < concurrency; i++) {
	                IData scope = IDataFactory.create();
	                IDataCursor cursor = scope.getCursor();

	                IDataHelper.put(cursor, "$invocations", table[i]);
	                IDataHelper.put(cursor, "$mode", "synchronous");

	                threads[i] = ServiceHelper.fork("tundra.list.service:invoke", scope);
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
	            table.add((T[])copy.toArray((T[])Array.newInstance(elementClass, 0)));
	        }

	        return (T[][])table.toArray((T[][])Array.newInstance(arrayClass, 0));
	    }

	    // invokes a list of services synchronously
	    public static IData[] synchronous(IData[] invocations) throws ServiceException {
	        if (invocations != null) {
	            Throwable[] errors = new Throwable[invocations.length];
	            boolean hasError = false;

	            for (int i = 0; i < invocations.length; i++) {
	                IDataCursor cursor = invocations[i].getCursor();
	                try {
	                    String service = IDataHelper.get(cursor, "service", String.class);
	                    IData pipeline = IDataHelper.get(cursor, "pipeline", IData.class);
	                    IDataHelper.put(cursor, "pipeline", ServiceHelper.invoke(service, pipeline));
	                } catch (Exception ex) {
	                    hasError = true;
	                    errors[i] = ex;
	                } finally {
	                    cursor.destroy();
	                }
	            }

	            if (hasError) ExceptionHelper.raise(errors);
	        }

	        return invocations;
	    }
	}

	// waits for a list of asynchronously invoked services to complete
	public static IData[] join(ServiceThread[] threads) throws ServiceException {
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

	        if (hasError) ExceptionHelper.raise(errors);
	    }

	    return pipelines;
	}

	// waits for a list of asynchronously invoked services to complete
	public static IData[] join(Object[] threads) throws ServiceException {
	    IData[] pipelines = null;
	    if (threads != null) {
	        pipelines = join(Arrays.copyOf(threads, threads.length, ServiceThread[].class));
	    }
	    return pipelines;
	}
	// --- <<IS-END-SHARED>> ---
}

