package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2022-03-04 05:14:07 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.security.HMACHelper;
// --- <<IS-END-IMPORTS>> ---

public final class hmac

{
	// ---( internal utility methods )---

	final static hmac _instance = new hmac();

	static hmac _newInstance() { return new hmac(); }

	static hmac _cast(Object o) { return (hmac)o; }

	// ---( server methods )---




	public static final void authenticate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(authenticate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required $content
		// [i] field:0:optional $content.encoding
		// [i] field:0:required $content.authentication.key
		// [i] field:0:required $content.authentication.algorithm {&quot;HmacSHA256&quot;,&quot;HmacSHA1&quot;,&quot;HmacMD5&quot;}
		// [i] object:0:required $content.authentication.code
		// [o] object:0:required $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset contentEncoding = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		    String key = IDataHelper.get(cursor, "$content.authentication.key", String.class);
		    String algorithm = IDataHelper.get(cursor, "$content.authentication.algorithm", String.class);
		    Object codeObject = IDataHelper.get(cursor, "$content.authentication.code");
		
		    byte[] contentBytes;
		    if (content instanceof InputStream) {
		        Map.Entry<? extends InputStream, byte[]> result = InputStreamHelper.readFullyThenReset((InputStream)content);
		        content = result.getKey();
		        contentBytes = result.getValue();
		    } else {
		        contentBytes = (byte[])ObjectHelper.convert(content, contentEncoding, ObjectConvertMode.BYTES);
		    }
		
		    byte[] code;
		    if (codeObject instanceof String) {
		        code = BytesHelper.hexDecode((String)codeObject);
		    } else {
		        code = (byte[])ObjectHelper.convert(codeObject, "bytes");
		    }
		
		    HMACHelper.authenticate(code, contentBytes, key, algorithm);
		
		    IDataHelper.put(cursor, "$content", content, true);
		} catch(Exception ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void calculate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required $content
		// [i] field:0:optional $content.encoding
		// [i] field:0:required $content.authentication.key
		// [i] field:0:required $content.authentication.algorithm {&quot;HmacSHA256&quot;,&quot;HmacSHA1&quot;,&quot;HmacMD5&quot;}
		// [i] field:0:optional $content.authentication.code.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;hex&quot;}
		// [o] object:0:required $content
		// [o] object:0:required $content.authentication.code
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset contentEncoding = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		    String key = IDataHelper.get(cursor, "$content.authentication.key", String.class);
		    String algorithm = IDataHelper.get(cursor, "$content.authentication.algorithm", String.class);
		    ObjectConvertMode codeMode = IDataHelper.get(cursor, "$content.authentication.code.mode", ObjectConvertMode.class);
		
		    byte[] contentBytes;
		    if (content instanceof InputStream) {
		        Map.Entry<? extends InputStream, byte[]> result = InputStreamHelper.readFullyThenReset((InputStream)content);
		        content = result.getKey();
		        contentBytes = result.getValue();
		    } else {
		        contentBytes = (byte[])ObjectHelper.convert(content, contentEncoding, ObjectConvertMode.BYTES);
		    }
		
		    byte[] authenticationCode = HMACHelper.calculate(contentBytes, key, algorithm);
		
		    IDataHelper.put(cursor, "$content", content, true);
		    IDataHelper.put(cursor, "$content.authentication.code", ObjectHelper.convert(authenticationCode, codeMode), true);
		} catch(Exception ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void verify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(verify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required $content
		// [i] field:0:optional $content.encoding
		// [i] field:0:required $content.authentication.key
		// [i] field:0:required $content.authentication.algorithm {&quot;HmacSHA256&quot;,&quot;HmacSHA1&quot;,&quot;HmacMD5&quot;}
		// [i] object:0:required $content.authentication.code
		// [o] object:0:required $content
		// [o] field:0:required $content.authentication.verified? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset contentEncoding = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		    String key = IDataHelper.get(cursor, "$content.authentication.key", String.class);
		    String algorithm = IDataHelper.get(cursor, "$content.authentication.algorithm", String.class);
		    Object codeObject = IDataHelper.get(cursor, "$content.authentication.code");
		
		    byte[] contentBytes;
		    if (content instanceof InputStream) {
		        Map.Entry<? extends InputStream, byte[]> result = InputStreamHelper.readFullyThenReset((InputStream)content);
		        content = result.getKey();
		        contentBytes = result.getValue();
		    } else {
		        contentBytes = (byte[])ObjectHelper.convert(content, contentEncoding, ObjectConvertMode.BYTES);
		    }
		
		    byte[] code;
		    if (codeObject instanceof String) {
		        code = BytesHelper.hexDecode((String)codeObject);
		    } else {
		        code = (byte[])ObjectHelper.convert(codeObject, "bytes");
		    }
		
		    boolean verified = HMACHelper.verify(code, (byte[])ObjectHelper.convert(content, contentEncoding, "bytes"), key, algorithm);
		
		    IDataHelper.put(cursor, "$content", content, true);
		    IDataHelper.put(cursor, "$content.authentication.verified?", verified, String.class);
		} catch(Exception ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

