package tundra.support.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2022-09-07 06:23:57 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.lang.ns.NSField;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSRecord;
import com.wm.lang.ns.NSService;
import com.wm.lang.ns.NSSignature;
import java.util.Locale;
import javax.activation.MimeType;
import permafrost.tundra.data.CaseInsensitiveIData;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.mime.MIMETypeHelper;
import permafrost.tundra.net.uri.URIHelper;
import permafrost.tundra.server.ServiceHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
// --- <<IS-END-IMPORTS>> ---

public final class client

{
	// ---( internal utility methods )---

	final static client _instance = new client();

	static client _newInstance() { return new client(); }

	static client _cast(Object o) { return (client)o; }

	// ---( server methods )---




	public static final void request (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(request)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required url
		// [i] field:0:required method {&quot;get&quot;,&quot;post&quot;,&quot;head&quot;,&quot;put&quot;,&quot;delete&quot;,&quot;options&quot;,&quot;trace&quot;}
		// [i] field:0:optional loadAs {&quot;bytes&quot;,&quot;stream&quot;}
		// [i] record:0:required data
		// [i] - record:0:optional args
		// [i] - field:2:optional table
		// [i] - field:0:optional string
		// [i] - object:0:optional bytes
		// [i] - object:0:optional mimeStream
		// [i] - object:0:optional stream
		// [i] - field:0:optional encoding
		// [i] record:0:optional auth
		// [i] - field:0:optional type
		// [i] - field:0:optional user
		// [i] - field:0:optional pass
		// [i] record:0:optional headers
		// [i] field:0:optional timeout
		// [i] field:0:optional connectTimeout
		// [i] field:0:optional maxKeepAliveConnections
		// [i] field:0:optional keepAliveTimeout
		// [i] field:0:optional newSession {&quot;no&quot;,&quot;yes&quot;}
		// [i] field:0:optional proxyAlias
		// [i] field:0:optional useJSSE {&quot;no&quot;,&quot;yes&quot;}
		// [o] field:0:required encodedURL
		// [o] record:0:optional header
		// [o] - record:0:required lines
		// [o] - field:0:required status
		// [o] - field:0:required statusMessage
		// [o] record:0:required body
		// [o] - object:0:optional bytes
		// [o] - object:0:optional stream
		IDataCursor cursor = pipeline.getCursor();
		boolean useJSSE = false;
		
		try {
		    String url = IDataHelper.get(cursor, "url", String.class);
		    boolean isJSSESpecified = IDataHelper.get(cursor, "useJSSE", String.class) != null;
		
		    useJSSE = !isJSSESpecified && isJSSESupported && url != null && url.toLowerCase().startsWith("https");
		    
		    // try making the request using JSSE for TLS 1.1 and 1.2 support as the default
		    if (useJSSE) IDataHelper.put(cursor, "useJSSE", "yes");
		    
		    pub.clientimpl.http(pipeline);
		} catch(ServiceException ex) {
		    String message = ex.getMessage();
		    boolean isUnauthorized = message != null && message.contains("ISC.0064.9314");
		
		    if (useJSSE && !isUnauthorized) {
		        // fallback to non-JSSE request
		        IDataHelper.put(cursor, "useJSSE", "no");
		        try {
		            pub.clientimpl.http(pipeline);
		        } catch(ServiceException fe) {
		            throw ex;
		        }
		    } else {
		        throw ex;
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final boolean isJSSESupported = isJSSESupported();
	
	private static boolean isJSSESupported() {
	    boolean isJSSESupported = true;
	
	    NSService service = Namespace.getService(NSName.create("pub.client", "http"));
	    if (service != null) {
	        NSSignature signature = service.getSignature();
	        if (signature != null) {
	            NSRecord input = signature.getInput();
	            if (input != null) {
	                NSField field = input.getFieldByName("useJSSE");
	                isJSSESupported = field != null;
	            }
	        }
	    }
	
	    return isJSSESupported;
	}
	
	public static void client(IData pipeline) throws ServiceException {
	    long startTime = System.nanoTime();
	
	    IDataCursor cursor = pipeline.getCursor();
	
	    try {
	        IData request = IDataHelper.get(cursor, "$request", IData.class);
	        String responseHandler = IDataHelper.getOrDefault(cursor, "$response.handler", String.class, "tundra.http.response:handle");
	        String connectTimeout = IDataHelper.get(cursor, "connectTimeout", String.class);
	        String maxKeepAliveConnections = IDataHelper.get(cursor, "maxKeepAliveConnections", String.class);
	        String keepAliveTimeout = IDataHelper.get(cursor, "keepAliveTimeout", String.class);
	        String proxyAlias = IDataHelper.get(cursor, "proxyAlias", String.class);
	        String useJSSE = IDataHelper.get(cursor, "useJSSE", String.class);
	
	        IDataCursor requestCursor = request.getCursor();
	        String uri = IDataHelper.get(requestCursor, "uri", String.class);
	        String method = IDataHelper.getOrDefault(requestCursor, "method", String.class, "get");
	        Object content = IDataHelper.get(requestCursor, "content");
	        String encoding = IDataHelper.get(requestCursor, "encoding", String.class);
	        
	        IData authority = IDataHelper.get(requestCursor, "authority", IData.class);
	        String authorityType = null, authorityUser = null, authorityPassword = null, authorityToken = null;
	        if (authority != null) {
	            IDataCursor authorityCursor = authority.getCursor();
	            authorityType = IDataHelper.get(authorityCursor, "type", String.class);
	            authorityUser = IDataHelper.get(authorityCursor, "user", String.class);
	            authorityPassword = IDataHelper.get(authorityCursor, "password", String.class);
	            authorityToken = IDataHelper.get(authorityCursor, "token", String.class);
	            authorityCursor.destroy();
	            if (authorityUser != null || authorityPassword != null) {
	                if (authorityUser == null) {
	                    authorityUser = "";
	                } else if (authorityPassword == null) {
	                    authorityPassword = "";
	                }
	            }
	        }
	
	        IData headers = IDataHelper.get(requestCursor, "headers", IData.class);
	        if (headers != null) {
	            headers = new CaseInsensitiveIData(IDataHelper.duplicate(headers, true), Locale.getDefault());
	        }
	        IData query = IDataHelper.get(requestCursor, "query", IData.class);
	        String timeout = IDataHelper.getOrDefault(requestCursor, "timeout", String.class, "PT60S");
	        String session = IDataHelper.getOrDefault(requestCursor, "session", String.class, "new");
	        requestCursor.destroy();
	
	
	        boolean emitURI = false;
	        IData parsedURI = URIHelper.parse(uri);
	        IDataCursor parsedURICursor = parsedURI.getCursor();
	
	        IData parsedURIServer = IDataHelper.get(parsedURI, "authority/server", IData.class);
	        if (parsedURIServer != null) {
	            IDataCursor parsedURIServerCursor = parsedURIServer.getCursor();
	
	            int port = IDataHelper.getOrDefault(parsedURIServerCursor, "port", Integer.class, -1);
	            if (port == 80 || port == 443) {
	                // remove default port for http or https schemes
	                String scheme = IDataHelper.get(parsedURICursor, "scheme", String.class);
	                if ((port == 80 && "http".equals(scheme)) || (port == 443 && "https".equals(scheme))) {
	                    IDataHelper.remove(parsedURIServerCursor, "port");
	                    emitURI = true;
	                }
	            }
	
	            String parsedURIUser = IDataHelper.remove(parsedURIServerCursor, "user", String.class);
	            String parsedURIPassword = IDataHelper.remove(parsedURIServerCursor, "password", String.class);
	            if (parsedURIUser != null || parsedURIPassword != null) {
	                if (authorityUser == null && authorityPassword == null) {
	                    authorityUser = parsedURIUser;
	                    authorityPassword = parsedURIPassword;
	                    if (authorityType == null) {
	                        authorityType = "Basic";
	                    }
	                }
	                emitURI = true;
	            }
	
	            parsedURIServerCursor.destroy();
	        }
	
	        if (IDataHelper.size(query) > 0) {
	            IData parsedQuery = IDataHelper.get(parsedURICursor, "query", IData.class);
	            IData mergedQuery = IDataHelper.merge(parsedQuery, query);
	            IDataHelper.put(parsedURICursor, "query", mergedQuery);
	            emitURI = true;        
	        }
	
	        parsedURICursor.destroy();
	
	        if (emitURI) {
	            uri = URIHelper.emit(parsedURI);
	        }
	
	        // build scope used to execute request
	        IData scope = IDataFactory.create();
	        IDataCursor scopeCursor = scope.getCursor();
	
	        IDataHelper.put(scopeCursor, "url", uri);
	        IDataHelper.put(scopeCursor, "method", method);
	        IDataHelper.put(scopeCursor, "loadAs", "stream");
	        IDataHelper.put(scopeCursor, "timeout", DurationHelper.format(timeout, "xml", "milliseconds"));
	
	        if (authorityType != null || authorityUser != null || authorityPassword != null || authorityToken != null) {
	            IData auth = IDataFactory.create();
	            IDataCursor authCursor = auth.getCursor();
	            IDataHelper.put(authCursor, "type", authorityType);
	            IDataHelper.put(authCursor, "user", authorityUser);
	            IDataHelper.put(authCursor, "pass", authorityPassword);
	            IDataHelper.put(authCursor, "token", authorityToken);
	            authCursor.destroy();
	
	            IDataHelper.put(scopeCursor, "auth", auth, false);    
	        }
	
	        if (headers != null) {
	            IDataHelper.put(scopeCursor, "headers", headers);
	        }
	
	        IData data = IDataFactory.create();
	        IDataCursor dataCursor = data.getCursor();
	        if (content instanceof IData) {
	            IDataHelper.put(dataCursor, "args", content);
	        } else {
	            // add charset parameter to content type header
	            if (encoding != null && headers != null && content instanceof String) {
	                String contentType = IDataHelper.get(headers, "Content-Type", String.class);
	                if (contentType != null) {
	                    MimeType mimeType = MIMETypeHelper.of(contentType);
	                    mimeType.setParameter("charset", encoding);
	                    IDataHelper.put(headers, "Content-Type", mimeType.toString());
	                }
	            }
	            IDataHelper.put(dataCursor, "bytes", BytesHelper.normalize(content, CharsetHelper.normalize(encoding)));
	        }
	        IDataHelper.put(dataCursor, "encoding", encoding);
	        dataCursor.destroy();
	
	        IDataHelper.put(scopeCursor, "data", data);
	
	        IDataHelper.put(scopeCursor, "newSession", "new".equals(session) ? "yes" : "no");
	        IDataHelper.put(scopeCursor, "connectTimeout", connectTimeout, false);
	        IDataHelper.put(scopeCursor, "maxKeepAliveConnections", maxKeepAliveConnections, false);
	        IDataHelper.put(scopeCursor, "keepAliveTimeout", keepAliveTimeout, false);
	        IDataHelper.put(scopeCursor, "proxyAlias", proxyAlias, false);
	        IDataHelper.put(scopeCursor, "useJSSE", useJSSE, false);
	        scopeCursor.destroy();
	
	        // execute request
	        tundra.support.http.client.request(scope);
	
	        // process response
	        scopeCursor = scope.getCursor();
	        String responseURI = IDataHelper.get(scopeCursor, "encodedURL", String.class);
	        IData responseHeader = IDataHelper.get(scopeCursor, "header", IData.class);
	        IData responseHeaderLines = null;
	        String responseHeaderStatus = null, responseHeaderStatusMessage = null;
	        if (responseHeader != null) {
	            IDataCursor responseHeaderCursor = responseHeader.getCursor();
	            responseHeaderLines = IDataHelper.get(responseHeaderCursor, "lines", IData.class);
	            responseHeaderStatus = IDataHelper.get(responseHeaderCursor, "status", String.class);
	            responseHeaderStatusMessage = IDataHelper.get(responseHeaderCursor, "statusMessage", String.class);
	            responseHeaderCursor.destroy();
	        }
	
	        IData responseBody = IDataHelper.get(scopeCursor, "body", IData.class);
	        Object responseBytes = null, responseStream = null;
	        if (responseBody != null) {
	            IDataCursor responseBodyCursor = responseBody.getCursor();
	            responseBytes = IDataHelper.get(responseBodyCursor, "bytes");
	            responseStream = IDataHelper.get(responseBodyCursor, "stream");
	            responseBodyCursor.destroy();
	        }
	
	        // build response data structure
	        IData response = IDataFactory.create();
	        IDataCursor responseCursor = response.getCursor();
	        IDataHelper.put(responseCursor, "uri", responseURI);
	        IDataHelper.put(responseCursor, "content", responseStream == null ? responseBytes : responseStream, false);
	        IDataHelper.put(responseCursor, "headers", responseHeaderLines, false);
	        IData responseStatus = IDataFactory.create();
	        IDataCursor responseStatusCursor = responseStatus.getCursor();
	        IDataHelper.put(responseStatusCursor, "code", responseHeaderStatus);
	        IDataHelper.put(responseStatusCursor, "message", responseHeaderStatusMessage);
	        responseStatusCursor.destroy();
	        IDataHelper.put(responseCursor, "status", responseStatus);
	        IDataHelper.put(responseCursor, "duration", DurationHelper.format((System.nanoTime() - startTime) / 1000000000.0, DurationPattern.XML_MILLISECONDS));
	        responseCursor.destroy();
	
	        IData responsePipeline = IDataFactory.create();
	        IDataHelper.put(responsePipeline, "$response", response);
	
	        responsePipeline = ServiceHelper.invoke(responseHandler, responsePipeline);
	
	        // return response as an output
	        IDataHelper.put(cursor, "$response", IDataHelper.get(responsePipeline, "$response", IData.class));
	    } catch(Exception ex) {
	        ExceptionHelper.raise(ex);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

