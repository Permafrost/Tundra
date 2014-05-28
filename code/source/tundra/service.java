package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-05-28 11:03:44.117
// -----( ON-HOST: -

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




	public static final void benchmark (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(benchmark)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:required $count
		// [o] field:0:required $duration.average
		// [o] field:0:required $duration.stdev
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  int count = Integer.parseInt(IDataUtil.getString(cursor, "$count"));

		  IncrementalNormalDistributionEstimator estimator = benchmark(service, scope == null? pipeline : scope, count);

		  IDataUtil.put(cursor, "$duration.average", tundra.duration.format("" + estimator.mean(), "milliseconds", "xml"));
		  IDataUtil.put(cursor, "$duration.stdev", tundra.duration.format("" + estimator.standardDeviation(), "milliseconds", "xml"));
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  String mode = IDataUtil.getString(cursor, "$mode");

		  if (mode == null) mode = "synchronous";
		  boolean scoped = scope != null;

		  Object value = invoke(service, scoped ? scope : pipeline, mode);
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

		  respond(code, message, headers, content, contentType, encoding);
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
		  IDataUtil.put(cursor, "$self", self());
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
		  boolean raise = tundra.bool.parse(IDataUtil.getString(cursor, "$raise?"));
		  IDataUtil.put(cursor, "$valid?", "" + validate(service, raise));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// sets the response headers and body for the current service invocation
	public static void respond(int code, String message, IData headers, Object content, String contentType, String encoding) throws ServiceException {
	  try {
	    com.wm.net.HttpHeader response = com.wm.app.b2b.server.Service.getHttpResponseHeader();

	    if (response == null) {
	      // not invoked via HTTP, so throw an exception instead for HTTP statuses >= 400
	      if (code >= 400) tundra.exception.raise(tundra.string.normalize(content, encoding));
	    } else {
	      if (message == null) message = tundra.support.http.status(code);
	      response.setResponse(code, message);

	      if (contentType == null) contentType = "application/octet-stream";
	      if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;

	      javax.activation.MimeType mimeType = new javax.activation.MimeType(contentType);
	      mimeType.setParameter("charset", encoding);

	      response.clearField("Content-Type");
	      response.addField("Content-Type", mimeType.toString());

	      if (headers != null) {
	        IDataCursor hc = headers.getCursor();

	        while(hc.next()) {
	          String key = hc.getKey();
	          Object value = hc.getValue();

	          if (key != null && value != null) {
	            response.clearField(key);
	            response.addField(key, value.toString());
	          }
	        }
	        hc.destroy();
	      }
	    }

	    if (content == null) content = "";
	    byte[] body = tundra.bytes.normalize(content, encoding);
	    com.wm.app.b2b.server.Service.setResponse(body);
	  } catch (java.io.IOException ex) {
	    tundra.exception.raise(ex);
	  } catch (javax.activation.MimeTypeParseException ex) {
	    tundra.exception.raise(ex);
	  }
	}

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
	  boolean valid = tundra.node.exists(service) && "service".equals(tundra.node.type(service));

	  if (raise && !valid) throw new ServiceException("Service does not exist: " + service);

	  return valid;
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

	// returns the name of the current service
	public static String self() {
	  String self = null;
	  String[] callstack = callstack();
	  if (callstack.length > 0) self = tundra.list.object.get(callstack, -1); // last element in list
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
	    IDataUtil.put(cursor, "$exception.stack", tundra.exception.stack(t));
	    cursor.destroy();

	    if (catchService == null) {
	      tundra.exception.raise(t);
	    } else {
	      pipeline = invoke(catchService, pipeline);
	    }
	  } finally {
	    if (finallyService != null) pipeline = invoke(finallyService, pipeline);
	  }

	  return pipeline;
	}

	// invokes the given service a given number of times, and returns execution duration statistics
	public static IncrementalNormalDistributionEstimator benchmark(String service, IData pipeline, int count) {
	  IncrementalNormalDistributionEstimator estimator = new IncrementalNormalDistributionEstimator("ms");

	  try {
	    for (int i = 0; i < count; i++) {
	      long start = System.currentTimeMillis();
	      tundra.service.invoke.synchronous(service, pipeline);
	      long end = System.currentTimeMillis();

	      estimator.append(end - start);
	    }
	  } catch (ServiceException ex) {
	    // ignore exceptions
	  }

	  return estimator;
	}

	// Class for incrementally calculating the mean and standard deviation
	public static class IncrementalNormalDistributionEstimator {

	  protected long count;
	  protected double mean, sq;
	  protected String unit = "";

	  /**
	   * Constructs a new estimator object.
	   */
	  public IncrementalNormalDistributionEstimator() {
	    reset();
	  }

	  /**
	   * Constructs a new estimator object.
	   * @param unit The unit of measurement related to the measured samples.
	   */
	  public IncrementalNormalDistributionEstimator(String unit) {
	    this();
	    this.unit = unit;
	  }

	  /**
	   * Constructs a new estimator object seeded with the given samples.
	   *
	   * @param samples One or more initial samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(double... samples) {
	    this();
	    append(samples);
	  }

	  /**
	   * Constructs a new estimator object seeded with the given samples.
	   *
	   * @param unit The unit of measurement related to the measured samples.
	   * @param samples One or more initial samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(String unit, double... samples) {
	    this(unit);
	    append(samples);
	  }

	  /**
	   * Constructs a new estimator object seeded with the given collection of
	   * samples.
	   *
	   * @param samples An initial collection of samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(java.util.Collection<Double> samples) {
	    this();
	    append(samples);
	  }

	  /**
	   * Constructs a new estimator object seeded with the given collection of
	   * samples.
	   *
	   * @param unit The unit of measurement related to the measured samples.
	   * @param samples An initial collection of samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(String unit, java.util.Collection<Double> samples) {
	    this(unit);
	    append(samples);
	  }

	  /**
	   * Appends the given sample to the list of samples in the estimator.
	   *
	   * @param sample The sample to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(double sample) {
	    double next = mean + (sample - mean) / ++count;
	    sq += (sample - mean) * (sample - next);
	    mean = next;

	    return this;
	  }

	  /**
	   * Adds one or more samples to the estimator.
	   *
	   * @param samples One or more samples to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(double... samples) {
	    for (double sample : samples) {
	      append(sample);
	    }

	    return this;
	  }

	  /**
	   * Adds a collection of samples to the estimator.
	   *
	   * @param samples A collection of samples to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(java.util.Collection<Double> samples) {
	    for (double sample : samples) {
	      append(sample);
	    }

	    return this;
	  }

	  /**
	   * Returns the number of samples seen by this estimator.
	   *
	   * @return The number of samples seen by this estimator.
	   */
	  public long count() {
	    return count;
	  }

	  /**
	   * Returns the mean of the samples.
	   *
	   * @return The mean of the samples.
	   */
	  public double mean() {
	    return mean;
	  }

	  /**
	   * Returns the maximum likelihood estimate of the variance of the samples.
	   *
	   * @return Maximum likelihood variance estimate.
	   */
	  public double variance() {
	    return count > 1 ? sq / mean : 0.0;
	  }

	  /**
	   * Returns the maximum likelihood estimate of the standard deviation of the
	   * samples.
	   *
	   * @return Maximum likelihood standard deviation estimate.
	   */
	  public double standardDeviation() {
	    return Math.sqrt(variance());
	  }

	  /**
	   * Returns the unit of measure related to the measured samples.
	   *
	   * @return Maximum likelihood standard deviation estimate.
	   */
	  public String unit() {
	    return unit;
	  }

	  /**
	   * Resets the estimator back to zero samples.
	   *
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator reset() {
	    count = 0;
	    mean = 0.0;
	    sq = 0.0;

	    return this;
	  }

	  /**
	   * Returns a string-based representation of the mean, standard deviation and
	   * number of samples for this estimator.
	   *
	   * @return String-based representation of this estimator.
	   */
	  @Override
	  public String toString() {
	    return String.format("\u03BC = %.3f %s, \u03C3 = %.3f %s, n = %d", mean(), unit(), standardDeviation(), unit(), count());
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

