package tundra;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.Package;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.EnumSet;
import permafrost.tundra.content.ValidationResult;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataJSONParser;
import permafrost.tundra.data.IDataXMLParser;
import permafrost.tundra.data.IDataYAMLParser;
import permafrost.tundra.data.transform.string.Squeezer;
import permafrost.tundra.data.transform.string.Trimmer;
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.flow.PipelineHelper;
import permafrost.tundra.flow.InputOutputSignature;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.server.InvokeStateHelper;
import permafrost.tundra.server.PackageHelper;
import permafrost.tundra.server.ServerLogHelper;
import permafrost.tundra.server.ServerLogLevel;
// --- <<IS-END-IMPORTS>> ---

public final class transport

{
	// ---( internal utility methods )---

	final static transport _instance = new transport();

	static transport _newInstance() { return new transport(); }

	static transport _cast(Object o) { return (transport)o; }

	// ---( server methods )---




	public static final void log (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(log)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $log.level {"Fatal","Error","Warn","Info","Debug","Trace","Off"}
		// [i] field:0:optional $log.message
		// [i] field:0:optional $log.prefix? {"true","false"}
		// [i] field:0:optional $log.name
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    ServerLogLevel level = IDataHelper.get(cursor, "$log.level", ServerLogLevel.class);
		    String message = IDataHelper.get(cursor, "$log.message", String.class);
		    boolean addPrefix = IDataHelper.getOrDefault(cursor, "$log.prefix?", Boolean.class, true);
		    String name = IDataHelper.get(cursor, "$log.name", String.class);
		
		    if (name == null) {
		       // infer log name as the invoking service's package
		       Package invokingPackage = PackageHelper.self();
		       if (invokingPackage != null) name = invokingPackage.getName();
		    }
		
		    ServerLogHelper.log(name, level, message, InvokeStateHelper.currentRedactedTransport(), addPrefix);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// merges the contents of the given document into the given pipeline
	public static void merge(IData target, IData source) {
	    if (target != null && source != null) IDataUtil.merge(source, target);
	}
	
	// sorts the elements in the pipeline by its keys in natural ascending order
	public static void sort(IData pipeline, boolean recurse) {
	    IData sorted = IDataHelper.sort(pipeline, recurse);
	    IDataHelper.clear(pipeline);
	    IDataUtil.append(sorted, pipeline);
	}
	// --- <<IS-END-SHARED>> ---
}

