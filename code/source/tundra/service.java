package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-08-04T10:55:02.211
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
import com.wm.lang.ns.NSService;
import java.nio.charset.Charset;
import java.util.List;
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
import permafrost.tundra.math.NormalDistributionEstimator;
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
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $duration.average
		// [o] field:0:required $duration.standard.deviation
		// [o] field:0:required $duration.minimum
		// [o] field:0:required $duration.maximum
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    if (scope == null) scope = IDataHelper.clone(pipeline, "$service", "$count", "$raise?");
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    int count = IDataHelper.get(cursor, "$count", Integer.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    NormalDistributionEstimator estimator = ServiceHelper.benchmark(service, scope, count, raise);

		    IDataHelper.put(cursor, "$duration.average", DurationHelper.format(estimator.getMean()/1000.0, 6, DurationPattern.XML));
		    IDataHelper.put(cursor, "$duration.standard.deviation", DurationHelper.format(estimator.getStandardDeviation()/1000.0, 6, DurationPattern.XML));
		    IDataHelper.put(cursor, "$duration.minimum", DurationHelper.format(estimator.getMinimum()/1000.0, 6, DurationPattern.XML));
		    IDataHelper.put(cursor, "$duration.maximum", DurationHelper.format(estimator.getMaximum()/1000.0, 6, DurationPattern.XML));
		    IDataHelper.put(cursor, "$message", service + " benchmark results: " + estimator.toString());
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
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String tryService = IDataHelper.get(cursor, "$service", String.class);
		    String catchService = IDataHelper.get(cursor, "$catch", String.class);
		    String finallyService = IDataHelper.get(cursor, "$finally", String.class);
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);
		    IData catchPipeline = IDataHelper.get(cursor, "$pipeline.catch", IData.class);
		    IData finallyPipeline = IDataHelper.get(cursor, "$pipeline.finally", IData.class);
		    boolean scoped = scope != pipeline;

		    // remove this service's input arguments from the pipeline if unscoped
		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service", "$catch", "$finally", "$pipeline.catch", "$pipeline.finally");

		    scope = ServiceHelper.ensure(tryService, catchService, finallyService, scope, catchPipeline, finallyPipeline);

		    if (scoped) {
		        IDataHelper.put(cursor, "$pipeline", scope);
		    } else {
		        IDataHelper.mergeInto(pipeline, scope);
		    }
		} finally {
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
		// [o] field:0:required $initiator? {&quot;false&quot;,&quot;true&quot;}
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
		// [i] field:0:optional $raise? {&quot;true&quot;,&quot;false&quot;}
		// [o] record:0:optional $pipeline
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData scope = IDataHelper.remove(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;
		    if (!scoped) scope = IDataHelper.clone(pipeline, "$service", "$mode", "$raise?");

		    String service = IDataHelper.get(cursor, "$service", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, true);

		    if (mode != null && mode.equals("asynchronous")) {
		        // support asynchronous mode for backwards compatiblity
		        IDataHelper.put(cursor, "$thread", ServiceHelper.fork(service, scope));
		    } else {
		        long start = System.currentTimeMillis();
		        scope = ServiceHelper.invoke(service, scope, raise, true, true);
		        long end = System.currentTimeMillis();

		        if (scoped) {
		            IDataHelper.put(cursor, "$pipeline", scope);
		        } else {
		            IDataHelper.mergeInto(pipeline, scope);
		        }
		        IDataHelper.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.XML));
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
		// [i] field:0:optional $raise? {&quot;true&quot;,&quot;false&quot;}
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
		// [i] field:0:required $code
		// [i] field:0:optional $message
		// [i] record:0:optional $headers
		// [i] object:0:optional $content
		// [i] field:0:optional $content.type
		// [i] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int code = IDataHelper.get(cursor, "$code", Integer.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		    IData headers = IDataHelper.get(cursor, "$headers", IData.class);
		    Object content = IDataHelper.get(cursor, "$content");
		    String contentType = IDataHelper.get(cursor, "$content.type", String.class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);

		    ServiceHelper.respond(code, message, headers, InputStreamHelper.normalize(content, charset), contentType, charset);
		} finally {
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
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		tundra.thread.sleep(pipeline);
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
		// [o] field:0:required $valid? {&quot;false&quot;,&quot;true&quot;}
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

