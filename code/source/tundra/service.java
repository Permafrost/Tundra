package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-03 17:06:17 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.math.NormalDistributionEstimator;
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
		// [o] field:0:required $duration.standard.deviation
		// [o] field:0:required $duration.minimum
		// [o] field:0:required $duration.maximum
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  int count = Integer.parseInt(IDataUtil.getString(cursor, "$count"));
		
		  NormalDistributionEstimator estimator = benchmark(service, scope == null? pipeline : scope, count);
		
		  IDataUtil.put(cursor, "$duration.average", tundra.duration.format("" + estimator.getMean(), "milliseconds", "xml"));
		  IDataUtil.put(cursor, "$duration.standard.deviation", tundra.duration.format("" + estimator.getStandardDeviation(), "milliseconds", "xml"));
		  IDataUtil.put(cursor, "$duration.minimum", tundra.duration.format("" + estimator.getMinimum(), "milliseconds", "xml"));
		  IDataUtil.put(cursor, "$duration.maximum", tundra.duration.format("" + estimator.getMaximum(), "milliseconds", "xml"));
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
		
		  create(packageName, serviceName);
		} catch(com.wm.app.b2b.server.ServiceSetupException ex) {
		  tundra.exception.raise(ex);
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
		// [i] field:0:optional $mode {"synchronous","asynchronous"}
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
		
		  String start = tundra.datetime.now();
		  Object value = invoke(service, scoped ? scope : pipeline, mode);
		  String end = tundra.datetime.now();
		
		  String key = mode.equals("asynchronous")? "$thread" : "$pipeline";
		
		  if (scoped || mode.equals("asynchronous")) {
		    IDataUtil.put(cursor, key, value);
		  }
		  if (mode.equals("synchronous")) IDataUtil.put(cursor, "$duration", tundra.datetime.duration(start, end));
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
		// [i] field:0:optional $raise? {"false","true"}
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
	// creates a new service in the given package with the given name and the given type
	public static void create(String packageName, String serviceName, String type, String subtype) throws com.wm.app.b2b.server.ServiceSetupException {
	  com.wm.app.b2b.server.Package pack = com.wm.app.b2b.server.PackageManager.getPackage(packageName);
	  if (pack == null) throw new IllegalArgumentException("package does not exist: " + packageName);
	
	  com.wm.lang.ns.NSName svcName = com.wm.lang.ns.NSName.create(serviceName);
	  if (com.wm.app.b2b.server.ns.Namespace.current().nodeExists(svcName)) throw new IllegalArgumentException("node already exists: " + serviceName);
	
	  if (type == null) type = com.wm.lang.ns.NSServiceType.SVC_FLOW;
	  if (subtype == null) subtype = com.wm.lang.ns.NSServiceType.SVCSUB_UNKNOWN;
	  com.wm.lang.ns.NSServiceType serviceType = com.wm.lang.ns.NSServiceType.create(type, subtype);
	
	  com.wm.app.b2b.server.ServerAPI.registerService(packageName, svcName, true, serviceType, null, null, null);
	}
	
	// creates a new flow service in the given package with the given name
	public static void create(String packageName, String serviceName) throws com.wm.app.b2b.server.ServiceSetupException {
	  create(packageName, serviceName, null, null);
	}
	
	// sets the response headers and body for the current service invocation
	public static void respond(int code, String message, IData headers, Object content, String contentType, String encoding) throws ServiceException {
	  try {
	    com.wm.net.HttpHeader response = com.wm.app.b2b.server.Service.getHttpResponseHeader();
	
	    if (response == null) { 
	      // not invoked via HTTP, so throw an exception instead for HTTP statuses >= 400
	      if (code >= 400) ExceptionHelper.raise(StringHelper.normalize(content, encoding));
	    } else {
	      if (message == null) message = tundra.http.response.status(code);
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
	    byte[] body = permafrost.tundra.lang.BytesHelper.normalize(content, encoding);
	    com.wm.app.b2b.server.Service.setResponse(body);
	  } catch (java.io.IOException ex) {
	    ExceptionHelper.raise(ex);
	  } catch (javax.activation.MimeTypeParseException ex) {
	    ExceptionHelper.raise(ex);
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
	
	// sleeps the current thread for the given duration
	public static void sleep(String duration) throws ServiceException {
	  sleep(tundra.duration.parse(duration).getTimeInMillis(new java.util.Date()));
	}
	
	// sleeps the current thread for the given duration
	public static void sleep(long milliseconds) throws ServiceException {
	  try {
	    Thread.sleep(milliseconds);
	  } catch(InterruptedException ex) {
	    ExceptionHelper.raise(ex);
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
	
	    IDataUtil.put(cursor, "$exception.stack", tundra.exception.stack(t));
	
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
	  NormalDistributionEstimator estimator = new NormalDistributionEstimator("ms");
	
	  validate(service, true);
	
	  try { 
	    for (int i = 0; i < count; i++) {
	      long start = System.currentTimeMillis();
	      tundra.service.invoke.synchronous(service, pipeline);
	      long end = System.currentTimeMillis();
	
	      estimator.add(end - start);
	    }
	  } catch (ServiceException ex) {
	    // ignore exceptions
	  }
	
	  return estimator;
	}
	// --- <<IS-END-SHARED>> ---
}

