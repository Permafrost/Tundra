package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-03 13:44:55 EST
// -----( ON-HOST: 172.16.189.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class id

{
	// ---( internal utility methods )---

	final static id _instance = new id();

	static id _newInstance() { return new id(); }

	static id _cast(Object o) { return (id)o; }

	// ---( server methods )---




	public static final void generate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $id
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$id", "" + java.util.UUID.randomUUID());
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

	// --- <<IS-START-SHARED>> ---
	// converts the given identifier name to a legal java identifier
	public static String normalize(String input) {
	  if (input == null) return null;
	
	  char[] characters = input.toCharArray();
	  StringBuilder output = new StringBuilder();
	
	  for (int i = 0; i < characters.length; i++) {
	    char character = characters[i];
	    if ((i == 0 && !Character.isJavaIdentifierStart(character))||(i > 0 && !Character.isJavaIdentifierPart(character))) {
	      character = '_';
	    }
	    output.append(character);
	  }
	
	  return output.toString();
	}
	// --- <<IS-END-SHARED>> ---
}

