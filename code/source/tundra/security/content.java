package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:58:45 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.security.MessageDigestHelper;
// --- <<IS-END-IMPORTS>> ---

public final class content

{
	// ---( internal utility methods )---

	final static content _instance = new content();

	static content _newInstance() { return new content(); }

	static content _cast(Object o) { return (content)o; }

	// ---( server methods )---




	public static final void digest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(digest)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $digest.algorithm {&quot;SHA-512&quot;,&quot;SHA-384&quot;,&quot;SHA-256&quot;,&quot;SHA&quot;,&quot;MD5&quot;,&quot;MD2&quot;}
		// [i] field:0:optional $digest.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;base64&quot;,&quot;hex&quot;}
		// [o] object:0:optional $content
		// [o] object:0:optional $digest
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$digest.mode", "$mode");
		    MessageDigest algorithm = IDataHelper.firstOrDefault(cursor, MessageDigest.class, MessageDigestHelper.DEFAULT_ALGORITHM, "$digest.algorithm", "$algorithm");
		
		    if (content != null) {
		        Map.Entry<? extends Object, byte[]> digest = MessageDigestHelper.digest(algorithm, content, charset);
		        if (digest != null) {
		            IDataHelper.put(cursor, "$content", digest.getKey());
		            IDataHelper.put(cursor, "$digest", ObjectHelper.convert(digest.getValue(), mode));
		        }
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} catch(NoSuchAlgorithmException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

