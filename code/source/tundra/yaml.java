package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-03 16:00:04 EST
// -----( ON-HOST: 172.16.189.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class yaml

{
	// ---( internal utility methods )---

	final static yaml _instance = new yaml();

	static yaml _newInstance() { return new yaml(); }

	static yaml _cast(Object o) { return (yaml)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (document != null) IDataUtil.put(cursor, "$content", emit(document, mode, encoding));
		} catch (java.io.IOException ex) {
		  tundra.exception.raise(ex);
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
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  if (content != null) {
		    Object output = parse(tundra.stream.normalize(content, encoding), encoding);
		    if (output != null) {
		      if (!(output instanceof IData)) {
		        // wrap an array in an outer IData document containing recordWithNoID array
		        IData document = IDataFactory.create();
		        IDataCursor dc = document.getCursor();
		        IDataUtil.put(dc, "recordWithNoID", output);
		        dc.destroy();
		        output = document;
		      }
		      IDataUtil.put(cursor, "$document", output);
		    }
		  }
		} catch (java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// parses YAML content to an appropriate webMethods compatible representation
	public static Object parse(java.io.InputStream in) throws java.io.IOException {
	  return parse(in, null);
	}
	
	// parses YAML content to an appropriate webMethods compatible representation
	public static Object parse(java.io.InputStream in, String encoding) throws java.io.IOException {
	  if (in == null) return null;
	
	  org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
	  Object output = yaml.load(tundra.string.normalize(in, encoding));
	
	  if (output instanceof java.util.Map) {
	    output = tundra.support.document.toIData((java.util.Map)output);
	  } else if (output instanceof java.util.List) {
	    output = tundra.support.document.toIDataArray((java.util.List)output);
	  }
	
	  return output;
	}
	
	// serializes an IData document to a YAML representation
	public static Object emit(IData input, String mode, String encoding) throws java.io.IOException {
	  org.yaml.snakeyaml.DumperOptions options = new org.yaml.snakeyaml.DumperOptions();
	  options.setDefaultFlowStyle(org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK);
	
	  org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(options);
	
	  IDataCursor cursor = input.getCursor();
	  IData[] array = IDataUtil.getIDataArray(cursor, "recordWithNoID");
	  cursor.destroy();
	
	  Object object = null;
	  if (array != null) {
	    object = tundra.support.document.toList(array);
	  } else {
	    object = tundra.support.document.toMap(input);
	  }
	
	  Object output = yaml.dump(object);
	
	  if (mode == null || mode.equals("stream")) {
	    output = tundra.stream.normalize(output, encoding);
	  } else if (mode.equals("bytes")) {
	    output = tundra.bytes.normalize(output, encoding);
	  }
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

