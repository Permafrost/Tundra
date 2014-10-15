package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-10-15 09:06:36.148
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class defer

{
	// ---( internal utility methods )---

	final static defer _instance = new defer();

	static defer _newInstance() { return new defer(); }

	static defer _cast(Object o) { return (defer)o; }

	// ---( server methods )---




	public static final void shutdown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shutdown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		shutdown();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static final long SHUTDOWN_TIMEOUT_MILLISECONDS = 5 * 60 * 1000;
	private static java.util.concurrent.ExecutorService executor = null;
	
	// creates a new thread pool for executing deferred services
	public static synchronized java.util.concurrent.ExecutorService getExecutor() {
	  if (executor == null) executor = java.util.concurrent.Executors.newSingleThreadExecutor(new ServerThreadFactory(com.wm.app.b2b.server.InvokeState.getCurrentState()));
	  return executor;
	}
	
	// initiates a shutdown and waits for up to 5 minutes for all tasks to complete
	public static void shutdown() {
	  try {
	    java.util.concurrent.ExecutorService executor = getExecutor();
	    executor.shutdown();
	    executor.awaitTermination(SHUTDOWN_TIMEOUT_MILLISECONDS, java.util.concurrent.TimeUnit.MILLISECONDS);
	  } catch(java.lang.InterruptedException ex) {
	    // ignore exception
	  }
	}
	
	// queues a service for execution on the defer thread pool
	public static void enqueue(String service, IData pipeline) {
	  getExecutor().submit(new CallableService(service, com.wm.app.b2b.server.Service.getSession(), pipeline));
	}
	
	// wraps a call to an IS service with a standard java.util.concurrent.callable interface, so that it can
	// be used by java.util.concurrent executors
	public static class CallableService implements java.util.concurrent.Callable<IData> {
	  protected com.wm.lang.ns.NSName service;
	  protected IData input;
	  protected com.wm.app.b2b.server.Session session;
	
	  public CallableService(String service, com.wm.app.b2b.server.Session session, IData input) {
	    this(com.wm.lang.ns.NSName.create(service), session, input);
	  }
	
	  public CallableService(com.wm.lang.ns.NSName service, com.wm.app.b2b.server.Session session, IData input) {
	    this.service = service;
	    if (input == null) {
	      this.input = IDataFactory.create();
	    } else {
	      this.input = IDataUtil.clone(input);
	    }
	    this.session = session;
	  }
	
	  public IData call() throws Exception {
	    return com.wm.app.b2b.server.Service.doInvoke(service, session, input);
	  }
	} 
	
	// a thread factory which creates IS threads with the given invoke state
	public static class ServerThreadFactory implements java.util.concurrent.ThreadFactory {
	  protected com.wm.app.b2b.server.InvokeState state;
	
	  public ServerThreadFactory(com.wm.app.b2b.server.InvokeState state) {
	    this.state = state;
	  }
	
	  public Thread newThread(Runnable r) {
	    com.wm.app.b2b.server.ServerThread thread = new com.wm.app.b2b.server.ServerThread(r);
	    thread.setInvokeState(cloneInvokeStateWithStack());
	    thread.setName("Tundra/Defer Thread");
	    return thread;
	  }
	
	  protected com.wm.app.b2b.server.InvokeState cloneInvokeStateWithStack() {
	    com.wm.app.b2b.server.InvokeState outputState = (com.wm.app.b2b.server.InvokeState)state.clone();
	    java.util.Stack stack = (java.util.Stack)state.getCallStack().clone();
	    while(!stack.empty()) {
	      com.wm.lang.ns.NSService service = (com.wm.lang.ns.NSService)stack.remove(0);
	      outputState.pushService(service);
	    }
	    return outputState;
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

