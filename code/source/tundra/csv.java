package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-07-03 14:40:31.709
// -----( ON-HOST: -

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
		// [i] record:0:optional $document
		// [i] - record:1:optional recordWithNoID
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String delimiter = IDataUtil.getString(cursor, "$delimiter");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");

		  if (document != null) {
		    IDataCursor dc = document.getCursor();
		    IData[] list = IDataUtil.getIDataArray(dc, "recordWithNoID");
		    dc.destroy();

		    if (list != null) IDataUtil.put(cursor, "$content", emit(list, delimiter, mode, encoding));
		  }
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
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		// [o] - record:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();

		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String delimiter = IDataUtil.getString(cursor, "$delimiter");
		  String encoding = IDataUtil.getString(cursor, "$encoding");

		  if (content != null) {
		    IData[] list = parse(tundra.stream.normalize(content, encoding), delimiter, encoding);
		    if (list != null) {
		      IData document = IDataFactory.create();
		      IDataCursor dc = document.getCursor();
		      IDataUtil.put(dc, "recordWithNoID", list);
		      dc.destroy();
		      IDataUtil.put(cursor, "$document", document);
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
	// parses CSV content to an appropriate webMethods compatible representation
	public static IData[] parse(java.io.InputStream in, String delimiter, String encoding) throws java.io.IOException {
	  if (in == null) return null;
	  if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;

	  java.io.Reader reader = new java.io.InputStreamReader(in, encoding);

	  org.apache.commons.csv.CSVFormat format = org.apache.commons.csv.CSVFormat.DEFAULT.withHeader();
	  if (delimiter != null && delimiter.length() > 0) format = format.withDelimiter(delimiter.charAt(0));

	  org.apache.commons.csv.CSVParser parser = format.parse(reader);

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
	public static Object emit(IData[] input, String delimiter, String mode, String encoding) throws java.io.IOException {
	  Object output = null;

	  StringBuilder builder = new StringBuilder();
	  org.apache.commons.csv.CSVFormat format = org.apache.commons.csv.CSVFormat.DEFAULT.withHeader(tundra.list.document.keys(input));
	  if (delimiter != null && delimiter.length() > 0) format = format.withDelimiter(delimiter.charAt(0));

	  org.apache.commons.csv.CSVPrinter printer = new org.apache.commons.csv.CSVPrinter(builder, format);

	  if (input != null) {
	    for (IData document : input) {
	      if (document != null) printer.printRecord(tundra.document.valueset(document));
	    }
	  }

	  output = builder.toString();

	  if (mode == null || mode.equals("stream")) {
	    output = tundra.stream.normalize(output, encoding);
	  } else if (mode.equals("bytes")) {
	    output = tundra.bytes.normalize(output, encoding);
	  }

	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

