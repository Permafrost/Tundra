package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-01-09 09:46:25.376
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
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    int count = IntegerHelper.parse(IDataUtil.getString(cursor, "$count"));
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

		    NormalDistributionEstimator estimator = ServiceHelper.benchmark(service, scope == null? pipeline : scope, count, raise);

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
		    List<NSService> stack = ServiceHelper.getCallStack();
		    if (stack.size() > 0) stack.remove(stack.size() - 1); // remove call to this service

		    String caller = "";
		    if (stack.size() > 1) {
		        NSService service = stack.get(stack.size() - 2);
		        if (service != null) caller = ObjectHelper.stringify(service);
		    }

		    IDataUtil.put(cursor, "$callstack", CollectionHelper.arrayify(CollectionHelper.stringify(stack), String.class));
		    IDataUtil.put(cursor, "$callers", IterableHelper.join(stack, " \u2192 "));
		    IDataUtil.put(cursor, "$caller", caller);
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

		    DeferHelper.defer(service, scope == null ? pipeline : scope);
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
		    String tryService = IDataUtil.getString(cursor, "$service");
		    String catchService = IDataUtil.getString(cursor, "$catch");
		    String finallyService = IDataUtil.getString(cursor, "$finally");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    boolean scoped = scope != null;

		    scope = ServiceHelper.ensure(tryService, catchService, finallyService, scoped ? scope : pipeline);

		    if (scoped) IDataUtil.put(cursor, "$pipeline", scope);
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
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");

		    IDataUtil.put(cursor, "$thread", ServiceHelper.fork(service, scope == null ? pipeline : scope));
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
		    List<NSService> stack = ServiceHelper.getCallStack();
		    if (stack.size() > 0) stack.remove(stack.size() - 1); // remove call to this service

		    IDataUtil.put(cursor, "$initiator?", BooleanHelper.emit(stack.size() <= 1));
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
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String mode = IDataUtil.getString(cursor, "$mode");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"), true);

		    boolean scoped = scope != null;

		    if (mode != null && mode.equals("asynchronous")) {
		        // support asynchronous mode for backwards compatiblity
		        IDataUtil.put(cursor, "$thread", ServiceHelper.fork(service, scoped ? scope : pipeline));
		    } else {
		        long start = System.currentTimeMillis();
		        scope = ServiceHelper.invoke(service, scoped ? scope : pipeline, raise);
		        long end = System.currentTimeMillis();

		        if (scoped) IDataUtil.put(cursor, "$pipeline", scope);
		        IDataUtil.put(cursor, "$duration", DurationHelper.format(end - start, DurationPattern.XML));
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
		    ServiceThread thread = (ServiceThread)IDataUtil.get(cursor, "$thread");

		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise"), true);
		    if (thread != null) IDataUtil.put(cursor, "$pipeline", ServiceHelper.join(thread, raise));
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
		    IData properties = ServiceHelper.reflect(service);
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
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));

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
		    NSService self = ServiceHelper.self();
		    if (self != null) IDataUtil.put(cursor, "$self", self.toString());
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
		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(ServiceHelper.exists(service, raise)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

