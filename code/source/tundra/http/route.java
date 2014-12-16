package tundra.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-17 06:50:16 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class route

{
	// ---( internal utility methods )---

	final static route _instance = new route();

	static route _newInstance() { return new route(); }

	static route _cast(Object o) { return (route)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		tundra.http.route.handler.clear();
		// --- <<IS-END>> ---

                
	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:1:required $routes
		// [o] - field:0:required directive
		// [o] - record:1:required routes
		// [o] -- field:0:required method
		// [o] -- field:0:required uri
		// [o] -- field:0:required target
		// [o] -- field:0:optional description
		// [o] -- field:0:optional source
		// [o] - field:0:required routes.length
		// [o] field:0:required $routes.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = handler.list();
		  IDataUtil.put(cursor, "$routes",list);
		  IDataUtil.put(cursor, "$routes.length", "" + list.length);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void refresh (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(refresh)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		handler.refresh();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final Handler handler = new Handler();
	
	// Custom HTTP handler to allow arbitrary HTTP request routing
	public static class Handler implements com.wm.app.b2b.server.HTTPHandler {
	  protected com.wm.app.b2b.server.HTTPInvokeHandler invokeHandler = new com.wm.app.b2b.server.HTTPInvokeHandler();
	  protected com.wm.app.b2b.server.HTTPDocHandler defaultHandler = new com.wm.app.b2b.server.HTTPDocHandler();
	  protected volatile RouteTable routes = new RouteTable();
	
	  // handle an HTTP request
	  public final boolean process(com.wm.app.b2b.server.ProtocolState state) throws java.io.IOException, com.wm.app.b2b.server.AccessException {
	    com.wm.app.b2b.server.ContentHandler contentHandler = com.wm.app.b2b.server.ServerAPI.getContentHandler(state.getContentType());
	    MatchResult matchResult = routes.match(state.getRequestType(), "/" + state.getHttpRequestUrl());
	    boolean result = false;
	
	    if (matchResult != null) {
	      try {
	        // convert capture URI parameters to query string, so they get added to input pipeline
	        // of the invoked service
	        IData parameters = matchResult.getParameters();
	        String queryString = state.getHttpRequestUrlQuery();
	        if (queryString != null) {
	          IData queryParameters = tundra.uri.query.parse(queryString, true);
	          IDataUtil.merge(parameters, queryParameters);
	          parameters = queryParameters;
	        }
	        state.setHttpRequestUrlQuery(tundra.uri.query.emit(parameters, true));
	      } catch(ServiceException ex) {}
	
	      Route route = matchResult.getRoute();
	
	      if (route.isInvoke()) {
	        result = invokeHandler._process(state, contentHandler, matchResult.getRoute().getService());
	      } else {
	        String target = route.getTarget();
	        if (target.startsWith("/")) target = target.substring(1, target.length());
	        state.setHttpRequestUrl(target);
	        result = defaultHandler.process(state);
	      }
	    } else {
	      // return forbidden error
	      int code = 403;
	      state.setResponse(code, tundra.http.response.status(code));
	      result = true;
	    }
	
	    return result;
	  }
	
	  public void refresh() throws ServiceException {
	    synchronized(this) {
	      RouteTable newRoutes = RouteTable.create();
	      RouteTable oldRoutes = routes;
	
	      routes = newRoutes;
	      refreshDirectives(oldRoutes.getDirectives(), newRoutes.getDirectives());
	    }
	  }
	
	  protected void refreshDirectives(java.util.Set<String> oldDirectives, java.util.Set<String> newDirectives) {
	    java.util.Set<String> toBeAdded = new java.util.TreeSet<String>(newDirectives);
	    toBeAdded.removeAll(oldDirectives);
	    register(toBeAdded);
	
	    java.util.Set<String> toBeRemoved = new java.util.TreeSet<String>(oldDirectives);
	    toBeRemoved.removeAll(newDirectives);
	    unregister(toBeRemoved);
	  }
	
	  // registers the given directive with the IS HTTP request dispatcher
	  protected void register(String directive) {
	    com.wm.app.b2b.server.HTTPDispatch.addHandler(directive, this);
	  }
	
	  // registers the given directive with the IS HTTP request dispatcher
	  protected void register(java.util.Collection<String> directives) {
	    for (String directive : directives) {
	      register(directive);
	    }
	  }
	
	  // unregisters the given directive from the IS HTTP request dispatcher
	  protected void unregister(String directive) {
	    com.wm.app.b2b.server.HTTPDispatch.removeHandler(directive);
	  }
	
	  // unregisters the given directive from the IS HTTP request dispatcher
	  protected void unregister(java.util.Collection<String> directives) {
	    for (String directive : directives) {
	      unregister(directive);
	    }
	  }
	
	  // unregisters the handler for all directives from the IS HTTP request dispatcher
	  // and clears all routes
	  public void clear() {
	    synchronized(this) {
	      unregister(routes.getDirectives());
	      routes = new RouteTable();
	    }
	  }
	
	  // returns the HTTP routing table an an IData[] document list
	  public IData[] list() {
	    return routes.list();
	  }
	}
	
	// represents a table of routing instructions, ordered by HTTP dispatch directive
	public static class RouteTable extends java.util.TreeMap<String, RouteList> {
	  protected final static java.util.regex.Pattern routePattern = java.util.regex.Pattern.compile("(?i)(?m)^[ \\t]*(get|put|post|head|connect|options|delete|trace)[ \\t]+(\\/?([^{}\\s\\/]+)(\\/\\S+)?)[ \\t]+(\\S+)([ \\t]+(.*)[ \\t]*)?$");
	  protected final static String configurationFileName = "http-routes.cnf";
	  protected final static java.util.Set<String> prohibitedDirectives = new java.util.TreeSet<String>();
	
	  static {
	    // set up prohibited directives so we don't break built-in IS functionality
	    prohibitedDirectives.add("invoke");
	    prohibitedDirectives.add(com.wm.util.Config.getProperty("invoke", "watt.server.invokeDirective"));
	    prohibitedDirectives.add(com.wm.app.b2b.server.SOAP.getSOAPdirective());
	    prohibitedDirectives.add("web");
	    prohibitedDirectives.add("wm-message");
	    prohibitedDirectives.add("ws");
	  }
	
	  public java.util.Set<String> getDirectives() {
	    return keySet();
	  }
	
	  // add an HTTP route instruction to the given routing table
	  public void put(Route route) {
	    String directive = route.getDirective();
	    if (!prohibitedDirectives.contains(directive)) {
	      RouteList list = get(directive);
	      put(directive, RouteList.add(list, route));
	    }
	  }
	
	  public static RouteTable create() throws ServiceException {
	    RouteTable table = null;
	
	    java.util.List<java.io.File> files = new java.util.ArrayList<java.io.File>();
	    files.add(new java.io.File(com.wm.app.b2b.server.ServerAPI.getServerConfigDir(), configurationFileName));
	
	    String[] packageNames = com.wm.app.b2b.server.ServerAPI.getEnabledPackages();
	    java.util.Arrays.sort(packageNames); // make sure list is in a predicatable order
	    for (String packageName : packageNames) {
	      files.add(new java.io.File(com.wm.app.b2b.server.ServerAPI.getPackageConfigDir(packageName), configurationFileName));
	    }
	
	    java.util.Map<java.io.File, String> contents = new java.util.LinkedHashMap<java.io.File, String>();
	    for (java.io.File file : files) {
	      if (file.exists() && file.isFile()) contents.put(file, (String)tundra.file.read(file, "string", null));
	    }
	
	    return table.create(contents);
	  }
	
	  protected static RouteTable create(java.util.Map<java.io.File, String> contents) {
	    RouteTable table = new RouteTable();
	
	    if (contents != null) {
	      for (java.util.Map.Entry<java.io.File, String> entry : contents.entrySet()) {
	        java.io.File source = entry.getKey();
	        String content = entry.getValue();
	
	        java.util.regex.Matcher matcher = routePattern.matcher(content);
	        while (matcher.find()) {
	          String method = matcher.group(1);
	          String uri = matcher.group(2);
	          String target = matcher.group(5);
	          String description = matcher.group(7);
	          if (description != null && description.equals("")) description = null;
	          try {
	            table.put(new Route(method, uri, target, description, source));
	          } catch(Exception ex) {}
	        }
	      }
	    }
	
	    return table;
	  }
	
	  // returns the matching route for the given HTTP request method and URI, if found
	  public MatchResult match(Method method, String uri) {
	    String directive = Route.getDirective(uri);
	    RouteList routes = get(directive);
	    MatchResult result = null;
	
	    if (routes != null) result = routes.match(method, uri);
	
	    return result;
	  }
	
	  // returns the matching route for the given HTTP request method and URI, if found
	  public MatchResult match(String method, String uri) {
	    return match(Method.valueOf(method.toUpperCase()), uri);
	  }
	
	  // returns the matching route for the given HTTP request method and URI, if found
	  public MatchResult match(int method, String uri) {
	    return match(Method.valueOf(method), uri);
	  }
	
	  // returns the HTTP routing table an an IData[] document list
	  public IData[] list() {
	    java.util.List<IData> output = new java.util.ArrayList<IData>(size());
	
	    for (String directive : getDirectives()) {
	      IData item = IDataFactory.create();
	      IDataCursor cursor = item.getCursor();
	      IDataUtil.put(cursor, "directive", directive);
	      IData[] list = get(directive).list();
	      if (list != null) {
	        IDataUtil.put(cursor, "routes", list);
	        IDataUtil.put(cursor, "routes.length", "" + list.length);
	      }
	      cursor.destroy();
	
	      output.add(item);
	    }
	
	    return (IData[])output.toArray(new IData[0]);
	  }
	}
	
	// a list of routes
	public static class RouteList extends java.util.ArrayList<Route> {
	  public static RouteList add(RouteList list, Route route) {
	    if (list == null) list = new RouteList();
	    list.add(route);
	    return list;
	  }
	
	  public IData[] list() {
	    java.util.List<IData> output = new java.util.ArrayList<IData>(size());
	    for (Route route : this) output.add(route.getIData());
	    return (IData[])output.toArray(new IData[0]);
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public MatchResult match(Method method, String uri) {
	    for (Route route : this) {
	      IData parameters = route.match(method, uri);
	      if (parameters != null) {
	        return new MatchResult(route, parameters);
	      }     
	    }
	    return null;
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public MatchResult match(String method, String uri) {
	    return match(Method.valueOf(method.toUpperCase()), uri);
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public MatchResult match(int method, String uri) {
	    return match(Method.valueOf(method), uri);
	  }
	}
	
	// represents a matched route and the captured URI parameters
	public static class MatchResult {
	  protected Route route;
	  protected IData parameters;
	
	  public MatchResult(Route route, IData parameters) {
	    this.route = route;
	    this.parameters = parameters;
	  }
	
	  public Route getRoute() {
	    return route;
	  }
	
	  public IData getParameters() {
	    return parameters;
	  }
	}
	
	// An individual routing instruction which maps an HTTP method and URI template to an implementation service
	public static class Route implements com.wm.util.coder.IDataCodable {
	  protected static final java.util.regex.Pattern SERVICE_PATTERN = java.util.regex.Pattern.compile("^(([^\\s\\.\\/:]+)(\\.[^\\s\\.\\/:]+)*)(:([^\\s\\.\\/:]+))$");
	  protected Method method;
	  protected org.springframework.web.util.UriTemplate uri;
	  protected boolean isInvoke;
	  protected com.wm.lang.ns.NSName service;
	  protected String target;
	  protected String description;
	  protected java.io.File source;
	
	  // construct a new routing instruction
	  public Route(Method method, String uri, String target, String description, java.io.File source) {
	    initialize(method, uri, target, description, source);
	  }
	
	  // construct a new routing instruction
	  public Route(String method, String uri, String target, String description, java.io.File source) {
	    initialize(method, uri, target, description, source);
	  }
	
	  // construct a new routing instruction
	  public Route(int method, String uri, String target, String description, java.io.File source) {
	    initialize(method, uri, target, description, source);
	  }
	
	  // construct a new routing instruction from the given IData document
	  public Route(IData input) {
	    setIData(input);
	  }
	
	  // returns the HTTP request method this routing instruction matches
	  public Method getMethod() {
	    return method;
	  }
	
	  // returns the URI template this routing instruction matches
	  public org.springframework.web.util.UriTemplate getURI() {
	    return uri;
	  }
	
	  // returns the target which is executed for this routing instruction
	  public String getTarget() {
	    return target;
	  }
	
	  // returns true if the target is a service invocation
	  public boolean isInvoke() {
	    return isInvoke;
	  }
	
	  // returns true if the target is a document
	  public boolean isDocument() {
	    return !isInvoke;
	  }
	
	  // returns the service which is invoked for this routing instruction
	  public com.wm.lang.ns.NSName getService() {
	    return service;
	  }
	
	  // returns the description of this routing instruction
	  public String getDescription() {
	    return description;
	  }
	
	  // returns the source file for this routing instruction
	  public java.io.File getSource() {
	    return source;
	  }  
	
	  // returns the HTTP request dispatcher directive for this HTTP routing instruction
	  public String getDirective() {
	    return getDirective(uri.toString());
	  }
	
	  // returns the HTTP request dispatcher directive for this given URI string
	  public static String getDirective(String uri) {
	    if (uri == null) return null;
	
	    uri = uri.trim();
	    if (uri.startsWith("/")) uri = uri.substring(1, uri.length());
	    int index = uri.indexOf("/");
	    if (index >= 0) uri = uri.substring(0, index);
	
	    return uri;
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public boolean matches(Method method, String uri) {
	    return this.method != null && this.method == method && this.uri != null && this.uri.matches(uri);
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public boolean matches(String method, String uri) {
	    return matches(Method.valueOf(method.toUpperCase()), uri);
	  }
	
	  // returns true if the given HTTP request method and URI match this routing instruction
	  public boolean matches(int method, String uri) {
	    return matches(Method.valueOf(method), uri);
	  }
	
	  // returns an IData document containing matched URI template parameters extracted from the given URI
	  public IData match(Method method, String uri) {
	    IData output = null;
	
	    if (matches(method, uri)) {
	      output = IDataFactory.create();
	      IDataCursor cursor = output.getCursor();
	      java.util.Map<String, String> parameters = this.uri.match(uri);
	      for (String key : parameters.keySet()) {
	        String value = parameters.get(key);
	        if (value != null) IDataUtil.put(cursor, key, value);
	      }
	      cursor.destroy();
	    }
	
	    return output;
	  }
	
	  // returns an IData document containing matched URI template parameters extracted from the given URI
	  public IData match(String method, String uri) {
	    return match(Method.valueOf(method.toUpperCase()), uri);
	  }
	
	  // returns an IData document containing matched URI template parameters extracted from the given URI
	  public IData match(int method, String uri) {
	    return match(Method.valueOf(method), uri);
	  }
	
	  // returns this HTTP routing instruction as an IData document
	  public IData getIData() {
	    IData output = IDataFactory.create();
	
	    IDataCursor cursor = output.getCursor();
	    IDataUtil.put(cursor, "method", method.name().toLowerCase());
	    IDataUtil.put(cursor, "uri", uri.toString());
	    IDataUtil.put(cursor, "target", target);
	    if (description != null) IDataUtil.put(cursor, "description", description);
	    if (source != null) {
	      try {
	        IDataUtil.put(cursor, "source", tundra.support.file.normalize(source));
	      } catch(ServiceException ex) {
	        IDataUtil.put(cursor, "source", source.toURI().toString());
	      }
	    }
	    cursor.destroy();
	
	    return output;
	  }
	
	  // initializes this HTTP routing instruction from the given IData
	  public void setIData(IData input) {
	    if (input == null) return;
	
	    IDataCursor cursor = input.getCursor();
	    String method = IDataUtil.getString(cursor, "method");
	    String uri = IDataUtil.getString(cursor, "uri");
	    String target = IDataUtil.getString(cursor, "target");
	    String description = IDataUtil.getString(cursor, "description");
	    String source = IDataUtil.getString(cursor, "source");
	    cursor.destroy();
	
	    initialize(method, uri, target, description, source);
	  }
	
	  // construct a new routing instruction
	  protected void initialize(int method, String uri, String target, String description, java.io.File source) {
	    initialize(Method.valueOf(method), uri, target, description, source);
	  }
	
	  // construct a new routing instruction
	  protected void initialize(String method, String uri, String target, String description, String source) {
	    initialize(Method.valueOf(method.toUpperCase()), uri, target, description, new java.io.File(source));
	  }
	
	  // construct a new routing instruction
	  protected void initialize(String method, String uri, String target, String description, java.io.File source) {
	    initialize(Method.valueOf(method.toUpperCase()), uri, target, description, source);
	  }
	
	  // construct a new routing instruction
	  protected void initialize(Method method, String uri, String target, String description, java.io.File source) {
	    initialize(method, new org.springframework.web.util.UriTemplate(uri), target, description, source);
	  }
	
	  // construct a new routing instruction
	  protected void initialize(Method method, org.springframework.web.util.UriTemplate uri, String target, String description, java.io.File source) {
	    this.method = method;
	    this.uri = uri;
	    this.target = target;
	
	    java.util.regex.Matcher matcher = SERVICE_PATTERN.matcher(target);
	    isInvoke = matcher.matches();
	
	    if (isInvoke) {
	      service = com.wm.lang.ns.NSName.create(target);
	    }
	
	    this.description = description;
	    this.source = source;
	  }
	
	  // compares this object with another object for equality
	  public boolean equals(Object obj) {
	    boolean result = false;
	    if (obj != null) {
	      if (obj.getClass() == this.getClass()) {
	        Route other = (Route)obj;
	        result = this.getMethod() == other.getMethod() && this.getURI().toString().equals(other.getURI().toString()) && this.getTarget().equals(other.getTarget());
	      }
	    }
	    return result;
	  }
	}
	
	// Represents all the possible allowed HTTP request methods
	public enum Method {
	  GET(0), PUT(1), POST(2), HEAD(3), CONNECT(4), OPTIONS(5), DELETE(6), TRACE(7);
	
	  private int value;
	  private static java.util.Map<Integer, Method> map = new java.util.HashMap<Integer, Method>();
	
	  private Method(int value) {
	    this.value = value;
	  }
	
	  static {
	    for (Method method : Method.values()) {
	      map.put(method.value, method);
	    }
	  }
	
	  // returns the HTTP request method for the given ordinal value
	  public static Method valueOf(int value) {
	    return map.get(value);
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

