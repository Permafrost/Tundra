package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-22 10:43:14.488
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
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
		// [i] field:0:optional $encoding
		// [i] field:0:optional $algorithm {&quot;SHA-512&quot;,&quot;SHA-384&quot;,&quot;SHA-256&quot;,&quot;SHA&quot;,&quot;MD5&quot;,&quot;MD2&quot;}
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;base64&quot;}
		// [o] object:0:optional $content
		// [o] object:0:optional $digest
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String charsetName = IDataUtil.getString(cursor, "$encoding");
		    String algorithmName = IDataUtil.getString(cursor, "$algorithm");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (content != null) {
		        Map.Entry<? extends Object, byte[]> digest = MessageDigestHelper.digest(MessageDigestHelper.normalize(algorithmName), content, CharsetHelper.normalize(charsetName));
		        if (digest != null) {
		            IDataUtil.put(cursor, "$content", digest.getKey());
		            IDataUtil.put(cursor, "$digest", ObjectHelper.convert(digest.getValue(), mode));
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

