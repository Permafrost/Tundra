package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-09-07 15:12:54 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class execution

{
	// ---( internal utility methods )---

	final static execution _instance = new execution();

	static execution _newInstance() { return new execution(); }

	static execution _cast(Object o) { return (execution)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $context
		// [o] - field:0:required monitoring.start
		// [o] - field:0:required monitoring.duration
		// [o] - object:0:required invocations.started
		// [o] - object:0:required invocations.errored
		// [o] - record:1:optional invocations.current
		// [o] -- object:0:required thread.id
		// [o] -- field:0:required thread.name
		// [o] -- object:0:required thread.object
		// [o] -- record:1:optional callstack
		// [o] --- field:0:required service
		// [o] --- field:0:required package
		// [o] --- record:0:optional pipeline
		// [o] --- object:0:required pipeline.length
		// [o] --- field:0:required start
		// [o] --- field:0:required duration
		// [o] -- object:0:required callstack.length
		// [o] - object:0:required invocations.current.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$context", ServiceExecutionContext.getInstance().getIData());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void shutdown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shutdown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceExecutionContext.getInstance().stop();
		// --- <<IS-END>> ---

                
	}



	public static final void startup (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(startup)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceExecutionContext.getInstance().start();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// represents a single currently executing invocation of a service
	public static class ServiceExecution implements com.wm.util.coder.IDataCodable {
	  private long startTime;
	  private com.wm.app.b2b.server.BaseService service;
	  protected IData pipeline;
	
	  public ServiceExecution(String service, IData pipeline) {
	    this(com.wm.lang.ns.NSName.create(service), pipeline);
	  }
	
	  public ServiceExecution(com.wm.lang.ns.NSName service, IData pipeline) {
	    this(com.wm.app.b2b.server.ns.Namespace.getService(service), pipeline);
	  }
	
	  public ServiceExecution(com.wm.app.b2b.server.BaseService service, IData pipeline) {
	    this.service = service;
	    this.pipeline = pipeline == null ? IDataFactory.create() : IDataUtil.clone(pipeline);
	    this.startTime = System.currentTimeMillis();
	  }
	
	  // returns the name of the service being invoked
	  public String getServiceName() {
	    return service.getNSName().getFullName();
	  }
	
	  // returns the name of the package in which the service being invoked resides
	  public String getPackageName() {
	    return service.getPackageName();
	  }
	
	  // returns the pipeline associated with this invocation
	  public IData getPipeline() {
	    return pipeline;
	  }
	
	  // returns the pipeline associated with this invocation
	  public int getPipelineSize() {
	    IDataCursor cursor = pipeline.getCursor();
	    int size = IDataUtil.size(cursor);
	    cursor.destroy();
	    return size;
	  }
	
	  // returns the invocation duration in milliseconds
	  public long getDuration() {
	    return System.currentTimeMillis() - startTime;
	  }
	
	  // returns an IData representation of this object
	  public IData getIData() {
	    IData output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();
	
	    IDataUtil.put(cursor, "service", getServiceName());
	    IDataUtil.put(cursor, "package", getPackageName());
	    IDataUtil.put(cursor, "pipeline", getPipeline());
	    IDataUtil.put(cursor, "pipeline.length", getPipelineSize());
	    IDataUtil.put(cursor, "start", tundra.datetime.format("" + startTime, "milliseconds", "datetime"));
	    IDataUtil.put(cursor, "duration", tundra.duration.format("" + getDuration(), "milliseconds", "xml"));
	    cursor.destroy();
	
	    return output;
	  }
	
	  // sets values from the given IData - not implemented
	  public void setIData(IData input) {
	    throw new UnsupportedOperationException("setIData not implemented");
	  }
	}
	
	// represents a single currently executing invocation call stack
	public static class ServiceExecutionThread implements com.wm.util.coder.IDataCodable {
	  private Thread thread = null;
	  private java.util.Deque<ServiceExecution> stack = null;
	
	  // creates a new ServiceExecutionThread
	  public ServiceExecutionThread(Thread thread) {
	    this.thread = thread;
	    this.stack = new java.util.ArrayDeque<ServiceExecution>();
	  }
	
	  public void push(ServiceExecution invocation) {
	    stack.push(invocation);
	  }
	
	  public ServiceExecution pop() {
	    return stack.pop();
	  }
	
	  // returns an IData representation of this invocation tree
	  public IData getIData() {
	    IData output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();
	
	    IDataUtil.put(cursor, "thread.id", thread.getId());
	    IDataUtil.put(cursor, "thread.name", thread.getName());
	    IDataUtil.put(cursor, "thread.object", thread);
	
	    if (stack.size() > 0) {
	      ServiceExecution[] list = stack.toArray(new ServiceExecution[0]);
	      IData[] services = new IData[list.length];
	      for(int i = 0; i < list.length; i++) services[i] = list[i].getIData();
	      IDataUtil.put(cursor, "callstack", services);
	      IDataUtil.put(cursor, "callstack.length", services.length);
	    }
	
	    cursor.destroy();
	
	    return output;
	  }
	
	  // sets values from the given IData - not implemented
	  public void setIData(IData input) {
	    throw new UnsupportedOperationException("setIData not implemented");
	  }
	}
	
	public final static class ServiceExecutionContext implements com.wm.app.b2b.server.invoke.InvokeChainProcessor, com.wm.util.coder.IDataCodable {
	  private static ServiceExecutionContext instance = null;
	  private long startTime, totalInvocations, totalErrors;
	  private java.util.Map<Long, ServiceExecutionThread> currentThreads = null;
	
	  // creates a new ServiceExecutionContext object
	  private ServiceExecutionContext() {
	    startTime = System.currentTimeMillis();
	    totalInvocations = 0;
	    totalErrors = 0;
	    currentThreads = new java.util.concurrent.ConcurrentHashMap<Long, ServiceExecutionThread>();
	  }
	
	  // returns the singleton instance of this class
	  public static ServiceExecutionContext getInstance() {
	    if (instance == null) {
	      instance = new ServiceExecutionContext();
	    }
	    return instance;
	  }
	
	  // records each currently executing invocation
	  public void process(java.util.Iterator chain, com.wm.app.b2b.server.BaseService service, IData pipeline, com.wm.app.b2b.server.invoke.ServiceStatus status) throws com.wm.util.ServerException {
	    long id = Thread.currentThread().getId();
	    ServiceExecutionThread thread = null;
	
	    try {
	      totalInvocations++;
	
	      if (status.isTopService()) {
	        thread = new ServiceExecutionThread(Thread.currentThread());
	        currentThreads.put(id, thread);
	      } else {
	        thread = currentThreads.get(id);
	      }
	      if (thread != null) thread.push(new ServiceExecution(service, pipeline));
	
	      if (chain.hasNext()) ((com.wm.app.b2b.server.invoke.InvokeChainProcessor)chain.next()).process(chain, service, pipeline, status);
	    } catch (com.wm.util.ServerException ex) {
	      totalErrors++;
	      throw ex;
	    } finally {
	      if (thread != null) {
	        thread.pop(); // invocation finished, so remove from call stack
	        if (status.isTopService()) currentThreads.remove(id); // top-level invocation finished, so remove from currently executing threads
	      }
	    }
	  }
	
	  // returns the current invocation context of the server as an IData[] document list
	  public IData getIData() {
	    IData output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();
	
	    IDataUtil.put(cursor, "monitoring.start", tundra.datetime.format("" + startTime, "milliseconds", "datetime"));
	    IDataUtil.put(cursor, "monitoring.duration", tundra.duration.format("" + getDuration(), "milliseconds", "xml"));
	    IDataUtil.put(cursor, "invocations.started", totalInvocations);
	    IDataUtil.put(cursor, "invocations.errored", totalErrors);
	
	    java.util.List<IData> threads = new java.util.ArrayList<IData>(currentThreads.size());
	    java.util.Iterator<ServiceExecutionThread> iterator = currentThreads.values().iterator();
	    while(iterator.hasNext()) threads.add(iterator.next().getIData());
	    IDataUtil.put(cursor, "invocations.current", (IData[])threads.toArray(new IData[0]));
	    IDataUtil.put(cursor, "invocations.current.length", threads.size());           
	
	    cursor.destroy();
	
	    return output;
	  }
	
	  // sets values from the given IData - not implemented
	  public void setIData(IData input) {
	    throw new UnsupportedOperationException("setIData not implemented");
	  }
	
	  // returns the duration in milliseconds this context object has been monitoring the execution state
	  public long getDuration() {
	    return System.currentTimeMillis() - startTime;
	  }
	
	  // registers this processor with the invocation manager
	  public void start() {
	    com.wm.app.b2b.server.invoke.InvokeManager invokeManager = com.wm.app.b2b.server.invoke.InvokeManager.getDefault();
	    invokeManager.registerProcessor(this);
	  }
	
	  // deregisters this processor with the invocation manager
	  public void stop() {
	    com.wm.app.b2b.server.invoke.InvokeManager invokeManager = com.wm.app.b2b.server.invoke.InvokeManager.getDefault();
	    invokeManager.unregisterProcessor(this);
	    instance = null; // free the singleton instance for garbage collection
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

