package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-20 12:50:07.897
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.lang.ThreadHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.math.NormalDistributionEstimator;
import permafrost.tundra.net.http.HTTPHelper;
import permafrost.tundra.server.NodeHelper;
import permafrost.tundra.server.ServiceHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void benchmark (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(benchmark)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:required $count
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $duration.average
		// [o] field:0:required $duration.standard.deviation
		// [o] field:0:required $duration.minimum
		// [o] field:0:required $duration.maximum
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    int count = IntegerHelper.parse(IDataUtil.getString(cursor, "$count"));
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

		    NormalDistributionEstimator estimator = benchmark(service, scope == null? pipeline : scope, count, raise);

		    IDataUtil.put(cursor, "$duration.average", DurationHelper.format(estimator.getMean()/1000.0, 6, DurationPattern.XML));
		    IDataUtil.put(cursor, "$duration.standard.deviation", DurationHelper.format(estimator.getStandardDeviation()/1000.0, 6, DurationPattern.XML));
		    IDataUtil.put(cursor, "$duration.minimum", DurationHelper.format(estimator.getMinimum()/1000.0, 6, DurationPattern.XML));
		    IDataUtil.put(cursor, "$duration.maximum", DurationHelper.format(estimator.getMaximum()/1000.0, 6, DurationPattern.XML));
		    IDataUtil.put(cursor, "$message", service + " benchmark results: " + estimator.toString());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void callstack (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(callstack)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:1:required $callstack
		// [o] field:0:required $callers
		// [o] field:0:required $caller
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] stack = callstack();
		    String callers = tundra.list.object.join(stack, " \u2192 ");
		    IDataUtil.put(cursor, "$callstack", stack);
		    IDataUtil.put(cursor, "$callers", callers);
		    IDataUtil.put(cursor, "$caller", stack.length > 0 ? stack[0] : "");
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $package
		// [i] field:0:required $service
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String packageName = IDataUtil.getString(cursor, "$package");
		    String serviceName = IDataUtil.getString(cursor, "$service");

		    ServiceHelper.create(packageName, serviceName);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void defer (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(defer)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");

		    defer(service, scope == null ? pipeline : scope);
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



	public static final void initiator (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(initiator)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $initiator?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataUtil.put(cursor, "$initiator?", "" + initiator());
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
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (mode == null) mode = "synchronous";
		    boolean scoped = scope != null;

		    long start = System.currentTimeMillis();
		    Object value = invoke(service, scoped ? scope : pipeline, mode);
		    long end = System.currentTimeMillis();

		    String key = mode.equals("asynchronous")? "$thread" : "$pipeline";

		    if (scoped || mode.equals("asynchronous")) {
		        IDataUtil.put(cursor, key, value);
		    }
		    if (mode.equals("synchronous")) IDataUtil.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.XML));
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
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void nothing (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nothing)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---


	}



	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $service
		// [o] record:0:optional $service.properties
		// [o] - field:0:required name
		// [o] - field:0:required type
		// [o] - field:0:required package
		// [o] - field:0:optional description
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataUtil.getString(cursor, "$service");
		    IData properties = reflect(service);
		    if (properties != null) IDataUtil.put(cursor, "$service.properties", properties);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void respond (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(respond)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $code
		// [i] field:0:optional $message
		// [i] record:0:optional $headers
		// [i] object:0:optional $content
		// [i] field:0:optional $content.type
		// [i] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int code = Integer.parseInt(IDataUtil.getString(cursor, "$code"));
		    String message = IDataUtil.getString(cursor, "$message");
		    IData headers = IDataUtil.getIData(cursor, "$headers");
		    Object content = IDataUtil.get(cursor, "$content");
		    String contentType = IDataUtil.getString(cursor, "$content.type");
		    String encoding = IDataUtil.getString(cursor, "$encoding");

		    ServiceHelper.respond(code, message, headers, StreamHelper.normalize(content, encoding), contentType, CharsetHelper.normalize(encoding));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void self (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(self)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $self
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String self = self();
		    if (self != null) IDataUtil.put(cursor, "$self", self);
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
		    if (duration != null) ThreadHelper.sleep(DurationHelper.parse(duration));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $service
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataUtil.getString(cursor, "$service");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		    IDataUtil.put(cursor, "$valid?", "" + validate(service, raise));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// returns true if the calling service is the top-level initiating
	// service of the current thread
	public static boolean initiator() {
	    return callstack().length <= 1;
	}

	// returns true if the given string is a service and exists
	public static boolean validate(String service) throws ServiceException {
	    return validate(service, false);
	}

	// returns true if the given string is a service and exists
	public static boolean validate(String service, boolean raise) throws ServiceException {
	    boolean valid = NodeHelper.exists(service) && "service".equals(NodeHelper.getNodeType(service).toString());

	    if (raise && !valid) throw new ServiceException("Service does not exist: " + service);

	    return valid;
	}

	// queues the service for execution on a defer thread pool
	public static void defer(String service, IData pipeline) {
	    tundra.support.service.defer.enqueue(service, pipeline);
	}

	// returns the invocation call stack
	public static String[] callstack() {
	    java.util.Iterator stack = com.wm.app.b2b.server.InvokeState.getCurrentState().getCallStack().iterator();
	    java.util.List<String> services = new java.util.ArrayList<String>();
	    while (stack.hasNext()) {
	        services.add(stack.next().toString());
	    }
	    // remove call to tundra.invoke:callstack from the stack
	    if (services.size() > 0) services.remove(services.size() - 1);
	    return (String[])services.toArray(new String[services.size()]);
	}

	// returns the name of the current service, or null if invoked directly
	public static String self() {
	    String self = null;
	    java.util.Stack stack = com.wm.app.b2b.server.InvokeState.getCurrentState().getCallStack();
	    if (stack.size() > 1) {
	        self = stack.get(stack.size() - 2).toString(); // this will return this service's caller
	    }
	    return self;
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
	            ExceptionHelper.raise(ex);
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
	            ExceptionHelper.raise(ex);
	        }
	    }

	    return pipeline;
	}

	// provides a try/catch/finally pattern for flow services
	public static IData ensure(String service, IData pipeline, String catchService, String finallyService) throws ServiceException {

	    try {
	        pipeline = invoke.synchronous(service, pipeline);
	    } catch (Throwable t) {
	        IDataCursor cursor = pipeline.getCursor();
	        IDataUtil.put(cursor, "$exception", t);
	        IDataUtil.put(cursor, "$exception?", "true");
	        IDataUtil.put(cursor, "$exception.class", t.getClass().getName());
	        IDataUtil.put(cursor, "$exception.message", t.getMessage());

	        com.wm.app.b2b.server.InvokeState invokeState = com.wm.app.b2b.server.InvokeState.getCurrentState();
	        IData exceptionInfo = IDataHelper.duplicate(invokeState.getErrorInfoFormatted(), true);
	        IDataCursor ec = exceptionInfo.getCursor();
	        String exceptionService = IDataUtil.getString(ec, "service");
	        if (exceptionService != null) {
	            IDataUtil.put(cursor, "$exception.service", exceptionService);
	            com.wm.app.b2b.server.BaseService baseService = com.wm.app.b2b.server.ns.Namespace.getService(com.wm.lang.ns.NSName.create(exceptionService));
	            if (baseService != null) {
	                String packageName = baseService.getPackageName();
	                IDataUtil.put(ec, "package", packageName);
	                IDataUtil.put(cursor, "$exception.package", packageName);
	            }
	        }
	        ec.destroy();
	        IDataUtil.put(cursor, "$exception.info", exceptionInfo);

	        IDataUtil.put(cursor, "$exception.stack", ExceptionHelper.getStackTrace(t));

	        cursor.destroy();

	        if (catchService == null) {
	            ExceptionHelper.raise(t);
	        } else {
	            pipeline = invoke(catchService, pipeline);
	        }
	    } finally {
	        if (finallyService != null) pipeline = invoke(finallyService, pipeline);
	    }

	    return pipeline;
	}

	// returns information about the given service
	public static IData reflect(String serviceName) {
	    if (serviceName == null) return null;

	    com.wm.app.b2b.server.BaseService service = com.wm.app.b2b.server.ns.Namespace.getService(com.wm.lang.ns.NSName.create(serviceName));
	    if (service == null) return null;

	    IData output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();

	    IDataUtil.put(cursor, "name", serviceName);
	    IDataUtil.put(cursor, "type", service.getServiceType().getType());
	    IDataUtil.put(cursor, "package", service.getPackageName());

	    String description = service.getComment();
	    if (description != null) IDataUtil.put(cursor, "description", description);

	    cursor.destroy();

	    return output;
	}

	// invokes the given service a given number of times, and returns execution duration statistics
	public static NormalDistributionEstimator benchmark(String service, IData pipeline, int count) throws ServiceException {
	    return benchmark(service, pipeline, count, false);
	}

	// invokes the given service a given number of times, and returns execution duration statistics
	public static NormalDistributionEstimator benchmark(String service, IData pipeline, int count, boolean raise) throws ServiceException {
	    NormalDistributionEstimator estimator = new NormalDistributionEstimator("ms");

	    validate(service, true);

	    for (int i = 0; i < count; i++) {
	        long start = System.currentTimeMillis();
	        try {
	            tundra.service.invoke.synchronous(service, pipeline);
	        } catch (ServiceException ex) {
	            if (raise) throw ex;
	        } finally {
	            long end = System.currentTimeMillis();
	            estimator.add(end - start);
	        }
	    }

	    return estimator;
	}
	// --- <<IS-END-SHARED>> ---
}

