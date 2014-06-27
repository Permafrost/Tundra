package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-06-27 16:07:19.178
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class csv

{
	// ---( internal utility methods )---

	final static csv _instance = new csv();

	static csv _newInstance() { return new csv(); }

	static csv _cast(Object o) { return (csv)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (list != null) IDataUtil.put(cursor, "$content", emit(list, mode, encoding));
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
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  if (content != null) {
		    IData[] list = parse(tundra.stream.normalize(content, encoding), encoding);
		    if (list != null) IDataUtil.put(cursor, "$list", list);
		  }
		} catch (java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// parses CSV content to an appropriate webMethods compatible representation
	public static IData[] parse(java.io.InputStream in) throws java.io.IOException {
	  return parse(in, null);
	}
	
	// parses CSV content to an appropriate webMethods compatible representation
	public static IData[] parse(java.io.InputStream in, String encoding) throws java.io.IOException {
	  if (in == null) return null;
	  if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;
	
	  java.io.Reader reader = new java.io.InputStreamReader(in, encoding);
	  org.apache.commons.csv.CSVParser parser = org.apache.commons.csv.CSVFormat.DEFAULT.withHeader().parse(reader);
	
	  java.util.Set<String> keys = parser.getHeaderMap().keySet();
	  java.util.List<IData> output = new java.util.ArrayList<IData>();
	
	  for (org.apache.commons.csv.CSVRecord record : parser) {
	    IData document = IDataFactory.create();
	    IDataCursor cursor = document.getCursor();
	    for (String key : keys) {
	      IDataUtil.put(cursor, key, record.get(key));
	    }
	    cursor.destroy();
	    output.add(document);
	  }
	
	  return output.toArray(new IData[0]);
	}
	
	// serializes an IData[] document list to a CSV representation
	public static Object emit(IData[] input, String mode, String encoding) throws java.io.IOException {
	  Object output = null;
	
	  StringBuilder builder = new StringBuilder();
	  org.apache.commons.csv.CSVFormat format = org.apache.commons.csv.CSVFormat.DEFAULT.withHeader(keys(input));
	  org.apache.commons.csv.CSVPrinter printer = new org.apache.commons.csv.CSVPrinter(builder, format);
	
	  for (IData document : input) {
	    if (document != null) printer.printRecord(tundra.document.valueset(document));
	  }
	
	  output = builder.toString();
	
	  if (mode == null || mode.equals("stream")) {
	    output = tundra.stream.normalize(output, encoding);
	  } else if (mode.equals("bytes")) {
	    output = tundra.bytes.normalize(output, encoding);
	  }
	
	  return output; 
	}
	
	public static String[] keys(IData[] input) {
	  java.util.Set<String> keys = new java.util.LinkedHashSet<String>();
	
	  for (IData document : input) {
	    if (document != null) {
	      IDataCursor cursor = document.getCursor();
	
	      while(cursor.next()) {
	        keys.add(cursor.getKey());
	      }
	
	      cursor.destroy();     
	    }
	  }
	
	  return keys.toArray(new String[0]);
	}
	// --- <<IS-END-SHARED>> ---
}

