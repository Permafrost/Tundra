package tundra.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-05-27 07:53:49 EST
// -----( ON-HOST: 192.168.66.129

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
		// [i] field:0:optional $encoding
		// [i] field:0:optional $algorithm {"SHA-512","SHA-384","SHA-256","SHA","MD5","MD2"}
		// [i] field:0:optional $mode {"stream","bytes","base64"}
		// [o] object:0:optional $content
		// [o] object:0:optional $digest
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    MessageDigest algorithm = MessageDigestHelper.normalize(IDataUtil.getString(cursor, "$algorithm"));
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    if (content != null) {
		        Map.Entry<? extends Object, byte[]> digest = MessageDigestHelper.digest(algorithm, content, charset);
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

