package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2023-12-12 05:26:51 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.net.smtp.SMTPHelper;
// --- <<IS-END-IMPORTS>> ---

public final class smtp

{
	// ---( internal utility methods )---

	final static smtp _instance = new smtp();

	static smtp _newInstance() { return new smtp(); }

	static smtp _cast(Object o) { return (smtp)o; }

	// ---( server methods )---




	public static final void send (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(send)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:required $email.message
		// [i] - field:0:optional uri
		// [i] - field:1:optional to
		// [i] - field:1:optional cc
		// [i] - field:1:optional bcc
		// [i] - field:0:optional from
		// [i] - field:0:optional subject
		// [i] - field:0:optional body
		// [i] - field:0:optional content.type
		// [i] - record:0:optional headers
		// [i] - record:1:optional attachments
		// [i] -- field:0:required name
		// [i] -- object:0:required content
		// [i] -- field:0:optional content.type
		// [i] -- field:0:optional compression {&quot;none&quot;,&quot;gzip&quot;,&quot;zip&quot;}
		// [i] -- record:0:optional headers
		// [i] - record:0:optional transport
		// [i] -- field:0:optional host
		// [i] -- field:0:optional port
		// [i] -- record:0:optional auth
		// [i] --- field:0:optional type {&quot;Basic&quot;,&quot;Bearer&quot;}
		// [i] --- field:0:optional user
		// [i] --- field:0:optional password
		// [i] --- field:0:optional token
		// [i] -- record:0:optional secure
		// [i] --- field:0:optional encryption {&quot;none&quot;,&quot;explicit&quot;,&quot;implicit&quot;}
		// [i] --- field:0:optional truststore
		// [o] record:0:required $email.response
		// [o] - field:0:required message.content
		// [o] - field:0:required message.content.type
		// [o] - field:0:required transport.uri
		// [o] - field:0:optional transport.log
		// [o] - field:0:required transport.status
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData message = IDataHelper.get(cursor, "$email.message", IData.class);
		    if (message != null) {
		        IData response = SMTPHelper.send(message);
		        IDataHelper.put(cursor, "$email.response", response);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

