package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-10-01T05:54:49.454
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
import com.wm.lang.ns.NSService;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import javax.xml.datatype.Duration;
import org.apache.log4j.Level;
import permafrost.tundra.collection.CollectionHelper;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.IterableHelper;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.lang.ThreadHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.math.gauss.ServiceEstimator;
import permafrost.tundra.net.http.HTTPHelper;
import permafrost.tundra.server.invoke.DeferHelper;
import permafrost.tundra.server.invoke.RetryableServiceProcessor;
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
		// [i] field:0:optional $raise? {"false","true"}
		// [o] record:0:required $benchmark.results
		// [o] - field:0:required description
		// [o] - field:0:required subject
		// [o] - field:0:required unit
		// [o] - object:0:required minimum
		// [o] - field:0:required minimum.formatted
		// [o] - object:0:required average
		// [o] - field:0:required average.formatted
		// [o] - object:0:required deviation.standard
		// [o] - field:0:required deviation.standard.formatted
		// [o] - object:0:required maximum
		// [o] - field:0:required maximum.formatted
		// [o] - object:0:required cumulative
		// [o] - field:0:required cumulative.formatted
		// [o] - object:0:required count.total
		// [o] - field:0:required count.total.formatted
		// [o] - object:0:required count.successes
		// [o] - field:0:required count.successes.formatted
		// [o] - object:0:required count.failures
		// [o] - field:0:required count.failures.formatted
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    if (scope == null) scope = IDataHelper.clone(pipeline, "$service", "$count", "$raise?");
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    int count = IDataHelper.get(cursor, "$count", Integer.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    ServiceEstimator.Results results = ServiceHelper.benchmark(service, scope, count, raise);

		    IDataHelper.put(cursor, "$benchmark.results", results.getIData());
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
		// [o] field:0:required $caller.initiator
		IDataCursor cursor = pipeline.getCursor();

		try {
		    List<NSService> stack = ServiceHelper.getCallStack();
		    if (stack.size() > 0) stack.remove(stack.size() - 1); // remove call to this service

		    String caller = "", initiator = "";
		    if (stack.size() > 1) {
		        NSService callingService = stack.get(stack.size() - 2);
		        if (callingService != null) caller = ObjectHelper.stringify(callingService);

		        NSService initiatingService = stack.get(0);
		        if (initiatingService != null) initiator = ObjectHelper.stringify(initiatingService);
		    }

		    IDataHelper.put(cursor, "$callstack", CollectionHelper.arrayify(CollectionHelper.stringify(stack), String.class));
		    IDataHelper.put(cursor, "$callers", IterableHelper.join(stack, " \u2192 "));
		    IDataHelper.put(cursor, "$caller", caller);
		    IDataHelper.put(cursor, "$caller.initiator", initiator);
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
		    String packageName = IDataHelper.get(cursor, "$package", String.class);
		    String serviceName = IDataHelper.get(cursor, "$service", String.class);

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
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    if (scope == null) scope = IDataHelper.clone(pipeline, "$service");
		    String service = IDataHelper.get(cursor, "$service", String.class);

		    DeferHelper.defer(service, scope);
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
		// [i] record:0:optional $pipeline.catch
		// [i] record:0:optional $pipeline.finally
		// [i] field:0:optional $thread.priority
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();

		Thread currentThread = Thread.currentThread();
		int currentThreadPriority = currentThread.getPriority();
		boolean changeThreadPriority = false;

		try {
		    String tryService = IDataHelper.get(cursor, "$service", String.class);
		    String catchService = IDataHelper.get(cursor, "$catch", String.class);
		    String finallyService = IDataHelper.get(cursor, "$finally", String.class);
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);
		    IData catchPipeline = IDataHelper.get(cursor, "$pipeline.catch", IData.class);
		    IData finallyPipeline = IDataHelper.get(cursor, "$pipeline.finally", IData.class);
		    BigDecimal newThreadPriority = IDataHelper.get(cursor, "$thread.priority", BigDecimal.class);
		    boolean scoped = scope != pipeline;

		    // remove this service's input arguments from the pipeline if unscoped
		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service", "$catch", "$finally", "$pipeline.catch", "$pipeline.finally");

		    if (newThreadPriority != null) {
		        int priority = ThreadHelper.normalizePriority(newThreadPriority.intValue());
		        changeThreadPriority = priority != currentThreadPriority;
		        if (changeThreadPriority) currentThread.setPriority(priority);
		    }

		    scope = ServiceHelper.ensure(tryService, catchService, finallyService, scope, catchPipeline, finallyPipeline);

		    if (scoped) {
		        IDataHelper.put(cursor, "$pipeline", scope);
		    } else {
		        IDataHelper.mergeInto(pipeline, scope);
		    }
		} finally {
		    if (changeThreadPriority) currentThread.setPriority(currentThreadPriority);
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void fork (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fork)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [o] object:0:required $thread
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    if (scope == null) scope = IDataHelper.clone(pipeline, "$service");
		    String service = IDataHelper.get(cursor, "$service", String.class);

		    IDataHelper.put(cursor, "$thread", ServiceHelper.fork(service, scope));
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
		// [o] field:0:required $initiator? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    List<NSService> stack = ServiceHelper.getCallStack();
		    if (stack.size() > 0) stack.remove(stack.size() - 1); // remove call to this service

		    IDataHelper.put(cursor, "$initiator?", stack.size() <= 1, String.class);
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
		// [i] field:0:optional $raise? {"true","false"}
		// [i] field:0:optional $thread.priority
		// [o] record:0:optional $pipeline
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();

		Thread currentThread = Thread.currentThread();
		int currentThreadPriority = currentThread.getPriority();
		boolean changeThreadPriority = false;

		try {
		    IData scope = IDataHelper.remove(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;
		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service", "$mode", "$raise?");

		    String service = IDataHelper.get(cursor, "$service", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, true);
		    BigDecimal newThreadPriority = IDataHelper.get(cursor, "$thread.priority", BigDecimal.class);

		    if (mode != null && mode.equals("asynchronous")) {
		        // support asynchronous mode for backwards compatiblity
		        IDataHelper.put(cursor, "$thread", ServiceHelper.fork(service, scope));
		    } else {
		        if (newThreadPriority != null) {
		            int priority = ThreadHelper.normalizePriority(newThreadPriority.intValue());
		            changeThreadPriority = priority != currentThreadPriority;
		            if (changeThreadPriority) currentThread.setPriority(priority);
		        }

		        long start = System.nanoTime();
		        scope = ServiceHelper.invoke(service, scope, raise, true, true);
		        long end = System.nanoTime();

		        if (scoped) {
		            IDataHelper.put(cursor, "$pipeline", scope);
		        } else {
		            IDataHelper.mergeInto(pipeline, scope);
		        }
		        IDataHelper.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.NANOSECONDS, DurationPattern.XML_MILLISECONDS));
		    }
		} finally {
		    if (changeThreadPriority) currentThread.setPriority(currentThreadPriority);
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
		// [i] field:0:optional $raise? {"true","false"}
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();

		try {
		    ServiceThread thread = IDataHelper.get(cursor, "$thread", ServiceThread.class);

		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise", Boolean.class, true);

		    if (thread != null) IDataHelper.put(cursor, "$pipeline", ServiceHelper.join(thread, raise));
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
		// [o] - record:0:required references
		// [o] -- field:1:required packages
		// [o] -- field:0:required packages.length
		// [o] -- record:1:required nodes
		// [o] --- field:0:required package
		// [o] --- field:0:required node
		// [o] -- field:0:required nodes.length
		// [o] -- field:1:required unresolved
		// [o] -- field:0:required unresolved.length
		// [o] - record:0:required dependents
		// [o] -- field:1:required packages
		// [o] -- field:0:required packages.length
		// [o] -- record:1:required nodes
		// [o] --- field:0:required package
		// [o] --- field:0:required node
		// [o] -- field:0:required nodes.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IDataHelper.put(cursor, "$service.properties", ServiceHelper.reflect(service), false);
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
		// [i] field:0:optional $response.code
		// [i] field:0:optional $response.message
		// [i] record:0:optional $response.headers
		// [i] object:0:optional $response.content
		// [i] field:0:optional $response.content.type
		// [i] field:0:optional $response.content.encoding
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int code = IDataHelper.firstOrDefault(cursor, Integer.class, 200, "$response.code", "$code");
		    String message = IDataHelper.first(cursor, String.class, "$response.message", "$message");
		    IData headers = IDataHelper.first(cursor, IData.class, "$response.headers", "$headers");
		    Object content = IDataHelper.first(cursor, Object.class, "$response.content", "$content");
		    String contentType = IDataHelper.first(cursor, String.class, "$response.content.type", "$content.type");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$response.content.encoding", "$content.encoding", "$encoding");

		    ServiceHelper.respond(code, message, headers, InputStreamHelper.normalize(content, charset), contentType, charset);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void restful (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(restful)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();

		try {
		    ServiceHelper.restful(pipeline);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void retry (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(retry)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $retry.limit
		// [i] field:0:optional $retry.wait
		// [i] field:0:optional $retry.factor
		// [i] field:0:optional $thread.priority
		// [o] record:0:optional $pipeline
		// [o] field:0:optional $duration
		// [o] field:0:optional $retry.count
		IDataCursor cursor = pipeline.getCursor();

		Thread currentThread = Thread.currentThread();
		int currentThreadPriority = currentThread.getPriority();
		boolean changeThreadPriority = false;

		try {
		    IData scope = IDataHelper.remove(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;
		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service", "$mode", "$raise?", "$retry.limit", "$retry.wait", "$retry.factor");

		    String service = IDataHelper.get(cursor, "$service", String.class);
		    int retryLimit = IDataHelper.getOrDefault(cursor, "$retry.limit", Integer.class, 0);
		    Duration retryWait = IDataHelper.get(cursor, "$retry.wait", Duration.class);
		    float retryFactor = IDataHelper.getOrDefault(cursor, "$retry.factor", Float.class, 1.0f);
		    BigDecimal newThreadPriority = IDataHelper.get(cursor, "$thread.priority", BigDecimal.class);

		    if (newThreadPriority != null) {
		        int priority = ThreadHelper.normalizePriority(newThreadPriority.intValue());
		        changeThreadPriority = priority != currentThreadPriority;
		        if (changeThreadPriority) currentThread.setPriority(priority);
		    }

		    int retryCount = 0;
		    long retryWaitMilliseconds = retryWait == null ? 0 : retryWait.getTimeInMillis(Calendar.getInstance());

		    long start = System.nanoTime();
		    while(!Thread.interrupted()) {
		        IData invokePipeline = IDataHelper.clone(scope);
		        try {
		            invokePipeline = ServiceHelper.invoke(service, invokePipeline, true, false, true);
		            scope = invokePipeline;
		            break;
		        } catch(ServiceException ex) {
		            if (retryCount < retryLimit) {
		                long wait = retryWaitMilliseconds * Double.valueOf(Math.pow(retryFactor, retryCount - 1)).longValue();
		                retryCount++;
		                Thread.sleep(wait);
		            } else {
		                throw ex;
		            }
		        }
		    }
		    long end = System.nanoTime();

		    if (scoped) {
		        IDataHelper.put(cursor, "$pipeline", scope);
		    } else {
		        IDataHelper.mergeInto(pipeline, scope);
		    }
		    IDataHelper.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.NANOSECONDS, DurationPattern.XML_MILLISECONDS));
		    IDataHelper.put(cursor, "$retry.count", retryCount, String.class);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		    ExceptionHelper.raiseUnchecked(ex);
		} finally {
		    if (changeThreadPriority) currentThread.setPriority(currentThreadPriority);
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void retryable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(retryable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RetryableServiceProcessor.getInstance().register();
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
		    IDataHelper.put(cursor, "$self", ServiceHelper.self(), String.class, false);
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
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		tundra.thread.sleep(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void synchronize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(synchronize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $pipeline
		// [o] field:0:optional $service.duration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;

		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service");

		    long start = System.nanoTime();
		    scope = ServiceHelper.synchronize(service, scope, false);
		    long end = System.nanoTime();

		    if (scoped) {
		        IDataHelper.put(cursor, "$pipeline", scope);
		    } else {
		        IDataHelper.mergeInto(pipeline, scope);
		    }
		    IDataHelper.put(cursor, "$service.duration", DurationHelper.format(end - start, DurationPattern.NANOSECONDS, DurationPattern.XML));
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
		// [i] field:0:optional $raise? {"false","true"}
		// [o] field:0:required $valid? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    IDataHelper.put(cursor, "$valid?", ServiceHelper.exists(service, raise), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

