package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-23 11:00:56 EST
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class datetime

{
	// ---( internal utility methods )---

	final static datetime _instance = new datetime();

	static datetime _newInstance() { return new datetime(); }

	static datetime _cast(Object o) { return (datetime)o; }

	// ---( server methods )---




	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $datetimes
		// [i] field:0:optional $pattern.input {&quot;datetime&quot;,&quot;date&quot;,&quot;time&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $pattern.output {&quot;datetime&quot;,&quot;date&quot;,&quot;time&quot;,&quot;milliseconds&quot;}
		// [o] field:1:optional $datetimes
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] datetimes = IDataUtil.getStringArray(cursor, "$datetimes");
		  String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		  String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		  IDataUtil.put(cursor, "$datetimes", format(datetimes, inPattern, outPattern));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// formats a list datetime strings to the given pattern
	public static String[] format(String[] inputs, String inPattern, String outPattern) {
	  String[] outputs = null;
	  if (inputs != null) {
	    outputs = new String[inputs.length];
	    for (int i = 0; i < inputs.length; i++) {
	      outputs[i] = tundra.datetime.format(inputs[i], inPattern, outPattern);
	    }
	  }
	  return outputs;
	}
	// --- <<IS-END-SHARED>> ---
}

