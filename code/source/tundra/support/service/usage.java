package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-21 16:04:56 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
// --- <<IS-END-IMPORTS>> ---

public final class usage

{
	// ---( internal utility methods )---

	final static usage _instance = new usage();

	static usage _newInstance() { return new usage(); }

	static usage _cast(Object o) { return (usage)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $context
		// [o] - field:0:required monitoring.started?
		// [o] - field:0:optional monitoring.start
		// [o] - field:0:optional monitoring.duration
		// [o] - object:0:required invocations.started
		// [o] - object:0:required invocations.errored
		// [o] - record:1:required invocations.current
		// [o] -- object:0:required thread.id
		// [o] -- field:0:required thread.name
		// [o] -- object:0:required thread.object
		// [o] -- field:0:required thread.start
		// [o] -- field:0:required thread.duration
		// [o] -- record:1:optional callstack
		// [o] --- field:0:required service
		// [o] --- field:0:required package
		// [o] --- record:0:optional pipeline
		// [o] --- object:0:required pipeline.length
		// [o] --- field:0:required start
		// [o] --- field:0:required duration
		// [o] --- field:0:required session
		// [o] --- field:0:required user
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



	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceExecutionContext.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceExecutionContext.getInstance().stop();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// represents a single currently executing invocation of a service
	public static class ServiceExecution implements com.wm.util.coder.IDataCodable {
	    private long startTime;
	    private com.wm.app.b2b.server.BaseService service;
	    private IData pipeline;
	    private String user, session;
	
	    public ServiceExecution(String service, IData pipeline, com.wm.app.b2b.server.InvokeState state) {
	        this(com.wm.lang.ns.NSName.create(service), pipeline, state);
	    }
	
	    public ServiceExecution(com.wm.lang.ns.NSName service, IData pipeline, com.wm.app.b2b.server.InvokeState state) {
	        this(com.wm.app.b2b.server.ns.Namespace.getService(service), pipeline, state);
	    }
	
	    public ServiceExecution(com.wm.app.b2b.server.BaseService service, IData pipeline, com.wm.app.b2b.server.InvokeState state) {
	        this.service = service;
	        this.pipeline = pipeline == null ? IDataFactory.create() : IDataUtil.clone(pipeline);
	        this.startTime = System.currentTimeMillis();
	        this.session = state.getSession().getSessionID();
	        this.user = state.getUser().getName();
	    }
	
	    // returns the name of the service being invoked
	    public String getServiceName() {
	        return service.getNSName().getFullName();
	    }
	
	    // returns the name of the package in which the service being invoked resides
	    public String getPackageName() {
	        return service.getPackageName();
	    }
	
	    // returns the name of the user invoking the service
	    public String getUser() {
	        return user;
	    }
	
	    // returns the session name invoking the service
	    public String getSession() {
	        return session;
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
	        IDataUtil.put(cursor, "start", DateTimeHelper.format(startTime));
	        IDataUtil.put(cursor, "duration", DurationHelper.format(getDuration(), DurationPattern.XML));
	        IDataUtil.put(cursor, "session", getSession());
	        IDataUtil.put(cursor, "user", getUser());
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
	    long startTime;
	
	    // creates a new ServiceExecutionThread
	    public ServiceExecutionThread(Thread thread) {
	        this.thread = thread;
	        this.stack = new java.util.ArrayDeque<ServiceExecution>();
	        this.startTime = System.currentTimeMillis();
	    }
	
	    public void push(ServiceExecution invocation) {
	        stack.push(invocation);
	    }
	
	    public ServiceExecution pop() {
	        return stack.pop();
	    }
	
	    public int size() {
	        return stack.size();
	    }
	
	    // returns the invocation duration in milliseconds
	    public long getDuration() {
	        return System.currentTimeMillis() - startTime;
	    }
	
	    // returns an IData representation of this invocation tree
	    public IData getIData() {
	        IData output = IDataFactory.create();
	        IDataCursor cursor = output.getCursor();
	
	        IDataUtil.put(cursor, "thread.id", thread.getId());
	        IDataUtil.put(cursor, "thread.name", thread.getName());
	        IDataUtil.put(cursor, "thread.object", thread);
	        IDataUtil.put(cursor, "thread.start", DateTimeHelper.format(startTime));
	        IDataUtil.put(cursor, "thread.duration", DurationHelper.format(getDuration(), DurationPattern.XML));
	
	        ServiceExecution[] list = stack.toArray(new ServiceExecution[0]);
	        IData[] services = new IData[list.length];
	        for(int i = 0; i < list.length; i++) services[i] = list[i].getIData();
	        IDataUtil.put(cursor, "callstack", services);
	        IDataUtil.put(cursor, "callstack.length", services.length);
	
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
	    private long startTime = 0;
	    private java.util.concurrent.atomic.AtomicLong totalInvocations = new java.util.concurrent.atomic.AtomicLong(0);
	    private java.util.concurrent.atomic.AtomicLong totalErrors = new java.util.concurrent.atomic.AtomicLong(0);
	    private boolean started = false;
	    private java.util.Map<Long, ServiceExecutionThread> currentThreads = new java.util.concurrent.ConcurrentHashMap<Long, ServiceExecutionThread>();
	
	    // creates a new ServiceExecutionContext object
	    private ServiceExecutionContext() {
	    }
	
	    // returns the singleton instance of this class
	    public static ServiceExecutionContext getInstance() {
	        if (instance == null) instance = new ServiceExecutionContext();
	        return instance;
	    }
	
	    // records each currently executing invocation
	    public void process(java.util.Iterator chain, com.wm.app.b2b.server.BaseService service, IData pipeline, com.wm.app.b2b.server.invoke.ServiceStatus status) throws com.wm.util.ServerException {
	        Thread currentThread = Thread.currentThread();
	        Long id = currentThread.getId();
	
	        try {
	            // register this call in a try/catch so that any failures do not stop service invocation
	            try {
	                ServiceExecutionThread serviceThread = currentThreads.get(id);
	
	                if (serviceThread == null) {
	                    serviceThread = new ServiceExecutionThread(currentThread);
	                    currentThreads.put(id, serviceThread);
	                }
	                if (serviceThread != null) serviceThread.push(new ServiceExecution(service, pipeline, com.wm.app.b2b.server.InvokeState.getCurrentState()));
	
	                totalInvocations.incrementAndGet();
	            } catch (Throwable ex) {
	                // do nothing
	            }
	
	            if (chain.hasNext()) ((com.wm.app.b2b.server.invoke.InvokeChainProcessor)chain.next()).process(chain, service, pipeline, status);
	        } catch (com.wm.util.ServerException ex) {
	            totalErrors.incrementAndGet();
	            throw ex;
	        } finally {
	            try {
	                ServiceExecutionThread serviceThread = currentThreads.get(id);
	                if (serviceThread != null) {
	                    serviceThread.pop(); // invocation finished, so remove from call stack
	                    if (serviceThread.size() == 0) currentThreads.remove(id); // top-level invocation finished, so remove from currently executing threads
	                }
	            } catch(Throwable ex) {
	                // do nothing
	            }
	        }
	    }
	
	    // returns the current invocation context of the server as an IData[] document list
	    public IData getIData() {
	        IData output = IDataFactory.create();
	        IDataCursor cursor = output.getCursor();
	
	        IDataUtil.put(cursor, "monitoring.started?", "" + started);
	        if (started) {
	            IDataUtil.put(cursor, "monitoring.start", DateTimeHelper.format(startTime));
	            IDataUtil.put(cursor, "monitoring.duration", DurationHelper.format(getDuration(), DurationPattern.XML));
	        }
	        IDataUtil.put(cursor, "invocations.started", totalInvocations.longValue());
	        IDataUtil.put(cursor, "invocations.errored", totalErrors.longValue());
	
	        java.util.List<IData> threads = new java.util.ArrayList<IData>(currentThreads.size());
	        java.util.Iterator<Long> iterator = currentThreads.keySet().iterator();
	        while(iterator.hasNext()) {
	            Long key = iterator.next();
	            ServiceExecutionThread thread = currentThreads.get(key);
	            if (thread.size() > 0) {
	                threads.add(thread.getIData());
	            } else {
	                currentThreads.remove(key); // remove threads with no items in the call stack
	            }
	        }
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
	        synchronized(this) {
	            if (!started) {
	                startTime = System.currentTimeMillis();
	                started = true;
	                com.wm.app.b2b.server.invoke.InvokeManager invokeManager = com.wm.app.b2b.server.invoke.InvokeManager.getDefault();
	                invokeManager.registerProcessor(this);
	            }
	        }
	    }
	
	    // deregisters this processor with the invocation manager
	    public void stop() {
	        synchronized(this) {
	            if (started) {
	                started = false;
	                com.wm.app.b2b.server.invoke.InvokeManager invokeManager = com.wm.app.b2b.server.invoke.InvokeManager.getDefault();
	                invokeManager.unregisterProcessor(this);
	                instance = null; // free the singleton instance for garbage collection
	            }
	        }
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

