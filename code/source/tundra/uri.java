package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-22 13:37:34.628
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class uri

{
	// ---( internal utility methods )---

	final static uri _instance = new uri();

	static uri _newInstance() { return new uri(); }

	static uri _cast(Object o) { return (uri)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $encoding
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  if (string != null) IDataUtil.put(cursor, "$string", decode(string, encoding));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $uri
		// [i] - field:0:optional scheme
		// [i] - field:0:optional body
		// [i] - record:0:optional authority
		// [i] -- field:0:optional registry
		// [i] -- record:0:optional server
		// [i] --- field:0:optional user
		// [i] --- field:0:optional password
		// [i] --- field:0:required host
		// [i] --- field:0:optional port
		// [i] - field:1:optional path
		// [i] - field:0:optional file
		// [i] - record:0:optional query
		// [i] - field:0:optional fragment
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData uri = IDataUtil.getIData(cursor, "$uri");
		  if (uri != null) IDataUtil.put(cursor, "$string", emit(uri, true));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void encode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $encoding
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  if (string != null) IDataUtil.put(cursor, "$string", encode(string, encoding));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", normalize(string));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] record:0:optional $uri
		// [o] - field:0:optional scheme
		// [o] - field:0:optional body
		// [o] - record:0:optional authority
		// [o] -- field:0:optional registry
		// [o] -- record:0:optional server
		// [o] --- field:0:optional user
		// [o] --- field:0:optional password
		// [o] --- field:0:required host
		// [o] --- field:0:optional port
		// [o] - field:1:optional path
		// [o] - field:0:optional file
		// [o] - record:0:optional query
		// [o] - field:0:optional fragment
		// [o] - field:0:required absolute?
		// [o] - field:0:required opaque?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$uri", parse(string));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final String DEFAULT_CHARACTER_ENCODING = "US-ASCII";
	
	// parses a URI string into an IData representation
	public static IData parse(String input) throws ServiceException {
	  if (input == null) return null;
	
	  // treat Windows UNC file URIs as server-based rather than path-based
	  if (input.toLowerCase().startsWith("file:////")) {
	    input = "file://" + input.substring(9, input.length());
	  }
	
	  IData output = IDataFactory.create();
	  IDataCursor oc = output.getCursor();
	
	  try {
	    java.net.URI uri = new java.net.URI(input);
	    uri.normalize();
	    
	    String scheme = uri.getScheme();
	    // schemes are case-insensitive, according to RFC 2396
	    if (scheme != null) scheme = scheme.toLowerCase();
	    if (scheme != null) IDataUtil.put(oc, "scheme", scheme);
	
	    IData query = null;
	    String ssp = uri.getSchemeSpecificPart();
	
	    if (uri.isOpaque()) { 
	      if (ssp.contains("?")) {
	        query = tundra.uri.query.parse(ssp.substring(ssp.indexOf("?") + 1, ssp.length()), false);
	        ssp = ssp.substring(0, ssp.indexOf("?"));
	      }
	      if (ssp != null) IDataUtil.put(oc, "body", ssp);
	    } else {
	      String authority = uri.getAuthority();
	      if (authority != null) {
	        IData server = IDataFactory.create();
	        IDataCursor sc = server.getCursor();
	
	        // parse user-info component according to the format user:[password]
	        String user = uri.getUserInfo();
	        String password = null;
	        String userInfoSeparator = ":";
	        if (user != null && user.contains(userInfoSeparator)) {
	          password = user.substring(user.indexOf(userInfoSeparator) + 1, user.length());
	          user = user.substring(0, user.indexOf(userInfoSeparator));
	        }
	        if (user != null) IDataUtil.put(sc, "user", user);
	        if (password != null) IDataUtil.put(sc, "password", password);
	
	        // hosts are case-insensitive, according to RFC 2396
	        String host = uri.getHost();
	        if (host != null) IDataUtil.put(sc, "host", host.toLowerCase());
	
	        // if port is -1, then it wasn't specified in the URI
	        int port = uri.getPort();
	        if (port >= 0) IDataUtil.put(sc, "port", "" + uri.getPort());
	
	        sc.destroy();
	
	        // if host is null, then this URI must be registry-based
	        IData authorityDocument = IDataFactory.create();
	        IDataCursor ac = authorityDocument.getCursor();
	        if (host == null) {
	          IDataUtil.put(ac, "registry", authority); 
	        } else {
	          IDataUtil.put(ac, "server", server);  
	        }
	        ac.destroy();
	
	        IDataUtil.put(oc, "authority", authorityDocument);
	      }
	
	      String path = uri.getPath();
	      String[] paths = tundra.uri.path.parse(path);
	      
	      String file = null;
	      if (!path.endsWith("/")) {
	        file = tundra.list.object.get(paths, -1); 
	        paths = tundra.list.object.drop(paths, -1);
	      }
	
	      if (paths != null && paths.length > 0) IDataUtil.put(oc, "path", paths);
	      if (file != null && !file.equals("")) IDataUtil.put(oc, "file", file);
	
	      query = tundra.uri.query.parse(uri.getRawQuery(), true);
	    }
	    
	    if (query != null) IDataUtil.put(oc, "query", query);
	
	    String fragment = uri.getFragment();
	    if (fragment != null) IDataUtil.put(oc, "fragment", fragment);
	
	    IDataUtil.put(oc, "absolute?", "" + uri.isAbsolute());
	    IDataUtil.put(oc, "opaque?", "" + uri.isOpaque());
	  } catch(java.net.URISyntaxException ex) {
	    tundra.exception.raise(ex);
	  } finally {
	    oc.destroy();
	  }
	
	  return output;
	}
	
	// emits a URI as a string
	public static String emit(IData input, boolean whatever) throws ServiceException {
	  if (input == null) return null;
	
	  String output = null;
	  IDataCursor cursor = input.getCursor();
	
	  try {
	    java.net.URI uri = null;
	    String type = IDataUtil.getString(cursor, "type");
	    
	    String scheme = IDataUtil.getString(cursor, "scheme");
	    // schemes are case-insensitive, according to RFC 2396
	    if (scheme != null) scheme = scheme.toLowerCase();
	
	    String fragment = IDataUtil.getString(cursor, "fragment");
	    IData queryDocument = IDataUtil.getIData(cursor, "query");
	    String query = tundra.uri.query.emit(queryDocument, true);
	
	    String body = IDataUtil.getString(cursor, "body");
	    IData authority = IDataUtil.getIData(cursor, "authority");
	    String[] paths = IDataUtil.getStringArray(cursor, "path");
	    String file = IDataUtil.getString(cursor, "file");
	
	    if (body == null && !(authority == null && paths == null && file == null)) {
	      // create an hierarchical URI
	      String path = "";
	      if (paths != null) {
	        path = "/" + tundra.list.object.join(paths, "/");
	      }
	      path = path + "/";
	      if (file != null) {
	        path = path + file;
	      }
	  
	      if (authority == null) {
	        uri = new java.net.URI(scheme, null, path, null, null);
	      } else {
	        IDataCursor ac = authority.getCursor();
	        String registry = IDataUtil.getString(ac, "registry");
	        IData server = IDataUtil.getIData(ac, "server");
	        ac.destroy();
	
	        if (registry == null) {
	          IDataCursor sc = server.getCursor();
	          String host = IDataUtil.getString(sc, "host");
	          // hosts are case-insensitive, according to RFC 2396
	          if (host != null) host = host.toLowerCase();
	
	          String portString = IDataUtil.getString(sc, "port");
	          int port = -1;
	          if (portString != null) port = Integer.parseInt(portString);
	
	          String userinfo = IDataUtil.getString(sc, "user");
	          if (userinfo != null && !(userinfo.equals(""))) {
	            String password = IDataUtil.getString(sc, "password");
	            if (password != null && !(password.equals(""))) userinfo = userinfo + ":" + password;
	          } else {
	            userinfo = null; // ignore empty strings
	          }
	
	          sc.destroy();
	          uri = new java.net.URI(scheme, userinfo, host, port, path, null, null);  
	        } else {
	          uri = new java.net.URI(scheme, registry, path, null, null);
	        }
	      }
	    } else {
	      uri = new java.net.URI(scheme, body, null);
	    }
	    output = uri.normalize().toASCIIString();
	    if (query != null) output = output + "?" + query;
	    if (fragment != null && !(fragment.equals(""))) output = output + "#" + tundra.uri.encode(fragment);
	
	    // support Windows UNC file URIs, and work-around java bug with 
	    // file URIs where scheme is followed by ':/' rather than '://'
	    if (output.startsWith("file:") && uri.getHost() == null) {
	      output = "file://" + output.substring(5, output.length());  
	    }
	  } catch(java.net.URISyntaxException ex) {
	    tundra.exception.raise(ex);
	  } finally {
	    cursor.destroy(); 
	  }
	  
	  return output;
	}
	
	// normalizes a URI string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html#normalize()>
	public static String normalize(String input) throws ServiceException {
	  return emit(parse(input), true);
	}
	
	// URL encodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
	public static String encode(String input) throws ServiceException {
	  return encode(input, DEFAULT_CHARACTER_ENCODING);
	}
	
	// URL encodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
	public static String encode(String input, String encoding) throws ServiceException {
	  if (input == null) return null;
	  if (encoding == null) encoding = DEFAULT_CHARACTER_ENCODING;  
	
	  String output = null;
	  try {
	    output =  java.net.URLEncoder.encode(input, encoding).replace("+", "%20");
	  } catch(java.io.UnsupportedEncodingException ex) {
	    tundra.exception.raise(ex);
	  }
	  return output;
	}
	
	// URL encodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
	public static String[] encode(String[] input) throws ServiceException {
	  return encode(input, DEFAULT_CHARACTER_ENCODING);
	}
	
	// URL encodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
	public static String[] encode(String[] input, String encoding) throws ServiceException {
	  if (input == null) return null;
	
	  String[] output = new String[input.length];
	  
	  for (int i = 0; i < input.length; i++) {
	    output[i] = encode(input[i], encoding);
	  }
	
	  return output;
	}
	
	// URL decodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
	public static String decode(String input) throws ServiceException {
	  return decode(input, DEFAULT_CHARACTER_ENCODING);
	}
	
	// URL decodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
	public static String decode(String input, String encoding) throws ServiceException {
	  if (input == null) return null;
	  if (encoding == null) encoding = DEFAULT_CHARACTER_ENCODING;
	
	  String output = null;
	  try {
	    output = java.net.URLDecoder.decode(input, encoding);
	  } catch(java.io.UnsupportedEncodingException ex) {
	    tundra.exception.raise(ex);
	  }
	  return output;
	}
	
	// URL decodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
	public static String[] decode(String[] input) throws ServiceException {
	  return decode(input, DEFAULT_CHARACTER_ENCODING);
	}
	
	// URL decodes a string, refer <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
	public static String[] decode(String[] input, String encoding) throws ServiceException {
	  if (input == null) return null;
	
	  String[] output = new String[input.length];
	
	  for (int i = 0; i < input.length; i++) {
	    output[i] = decode(input[i], encoding);
	  }
	
	  return output;
	}
	
	public static class path {
	  public static final java.util.regex.Pattern PATH_PATTERN = java.util.regex.Pattern.compile("/+");
	
	  // parses a URI path string, with support for variable substitution strings
	  public static String[] parse(String input) throws ServiceException {
	    if (input == null || input.equals("") || input.equals("/")) return null;
	    if (input.startsWith("/")) input = input.substring(1, input.length());
	    if (input.endsWith("/")) input = input.substring(0, input.length() - 1);
	
	    java.util.List<String> list = new java.util.ArrayList<String>();
	    java.util.regex.Matcher substitutionMatcher = tundra.string.SUBSTITUTION_PATTERN.matcher(input);
	
	    int index = 0;
	    while(substitutionMatcher.find()) {
	      int start = substitutionMatcher.start();
	      int end = substitutionMatcher.end();
	
	      if (index <= start) split(input.substring(index, start), list);
	      append(substitutionMatcher.group(), list);
	
	      index = end;
	    }
	    if (index <= input.length()) split(input.substring(index), list);
	
	    return tundra.list.object.compact((String[])list.toArray(new String[0]));
	  }
	
	  protected static void append(String item, java.util.List<String> list) {
	    int i = list.size() - 1;
	    if (i < 0) {
	      list.add(item);
	    } else {
	      list.set(i, list.get(i) + item);
	    }
	  }
	
	  protected static void split(String input, java.util.List<String> list) {
	    java.util.regex.Matcher matcher = PATH_PATTERN.matcher(input);
	
	    int index = 0;
	    while(matcher.find()) {
	      int start = matcher.start();
	      int end = matcher.end();
	
	      if (index <= start) append(input.substring(index, start), list);
	      list.add("");
	
	      index = end;
	    }
	    if (index <= input.length()) append(input.substring(index), list);
	  }
	}
	
	public static class query {
	  // parses a query string
	  public static IData parse(String input, boolean decode) throws ServiceException {
	    return parse(input, null, decode);
	  }
	
	  // parses a query string
	  public static IData parse(String input, String encoding, boolean decode) throws ServiceException {
	    if (input == null) return null;
	    if (encoding == null) encoding = DEFAULT_CHARACTER_ENCODING;
	
	    IData output = IDataFactory.create();
	    IDataCursor cursor = output.getCursor();
	
	    for (String pair : input.split("&")) {
	      String[] tokens = pair.split("=");
	      String name = tokens.length > 0 ? tokens[0] : "";
	      String value = tokens.length > 1 ? tokens[1] : "";
	
	      if (decode) {
	        name = tundra.uri.decode(name, encoding);
	        value = tundra.uri.decode(value, encoding);
	      }
	
	      Object existing = IDataUtil.get(cursor, name);
	      if (existing == null) {
	        IDataUtil.put(cursor, name, value);
	      } else {
	        // support lists in query strings: a=1&a=2&a=3 should be parsed to a[] = { 1, 2, 3 }
	        String[] array = null;
	        if (existing instanceof String) {
	          array = new String[2];
	          array[0] = (String)existing;
	          array[1] = value;
	        } else if (existing instanceof String[]) {
	          array = tundra.list.object.append((String[])existing, value, String.class);
	        }
	        IDataUtil.put(cursor, name, array);
	      }      
	    }
	
	    cursor.destroy();
	
	    return output;
	  }
	
	  // emits a query string given a name and value
	  public static String emit(String name, Object value, String encoding, boolean encode) throws ServiceException {
	    if (encode) {
	      name = tundra.uri.encode(name, encoding);
	      value = tundra.uri.encode(value.toString(), encoding);
	    }
	    return name + "=" + value;
	  }
	
	  // emits a query string given a name and array of values
	  public static String emit(String name, Object[] values, String encoding, boolean encode) throws ServiceException {
	    StringBuilder output = new StringBuilder();
	    for(Object value : values) {
	      if (output.length() > 0) output.append("&");
	      output.append(emit(name, value, encoding, encode));
	    }
	    return output.toString();
	  }
	
	  // emits a query string given an IData containing name/value pairs
	  public static String emit(IData input, boolean encode) throws ServiceException {
	    return emit(input, null, encode);
	  }
	
	  // emits a query string given an IData containing name/value pairs
	  public static String emit(IData input, String encoding, boolean encode) throws ServiceException {
	    if (input == null) return null;
	    if (encoding == null) encoding = DEFAULT_CHARACTER_ENCODING;
	
	    StringBuilder output = new StringBuilder();
	
	    IDataCursor cursor = input.getCursor();
	    while(cursor.next()) {
	      String key = cursor.getKey();
	      Object value = cursor.getValue();
	
	      if (value != null) {
	        if (output.length() > 0) output.append("&");
	        if (value instanceof Object[]) {
	          output.append(emit(key, (Object[])value, encoding, encode));
	        } else {
	          output.append(emit(key, value, encoding, encode));
	        }
	      }
	    }
	
	    return output.toString();
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

