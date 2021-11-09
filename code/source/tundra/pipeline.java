package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-11-10 05:52:42 EST
// -----( ON-HOST: -

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
import permafrost.tundra.server.PackageHelper;
import permafrost.tundra.server.ServerLogHelper;
import permafrost.tundra.server.ServerLogLevel;
// --- <<IS-END-IMPORTS>> ---

public final class pipeline

{
	// ---( internal utility methods )---

	final static pipeline _instance = new pipeline();

	static pipeline _newInstance() { return new pipeline(); }

	static pipeline _cast(Object o) { return (pipeline)o; }

	// ---( server methods )---




	public static final void capture (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capture)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$pipeline", IDataHelper.duplicate(pipeline, false));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $preserve
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] keys = IDataHelper.get(cursor, "$preserve", String[].class);
		    IDataHelper.clear(pipeline, keys);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    IDataHelper.copy(pipeline, source, target, literal);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void denormalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(denormalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData copy = IDataHelper.denormalize(pipeline);
		    IDataHelper.clear(pipeline);
		    merge(pipeline, copy);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void drop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(drop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    IDataHelper.drop(pipeline, key, literal);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $content.class {&quot;xml&quot;,&quot;json&quot;,&quot;yaml&quot;}
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    // remove input arguments so that they are not included in serialization of the pipeline
		    String contentClass = IDataHelper.remove(cursor, "$content.class", String.class);
		    Charset charset = IDataHelper.remove(cursor, "$content.encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.remove(cursor, "$content.mode", ObjectConvertMode.class);
		
		    Object content;
		
		    if (contentClass == null || contentClass.equals("xml")) {
		        content = ObjectHelper.convert(new IDataXMLParser().emit(pipeline, charset), charset, mode);
		    } else if (contentClass.equals("json")) {
		        content = ObjectHelper.convert(new IDataJSONParser().emit(pipeline, charset), charset, mode);
		    } else if (contentClass.equals("yaml")) {
		        content = ObjectHelper.convert(new IDataYAMLParser().emit(pipeline, charset), charset, mode);
		    } else {
		        throw new IllegalArgumentException(MessageFormat.format("$content.class must be either \"xml\", \"json\", or \"yaml\": {0}", contentClass));
		    }
		
		    IDataHelper.put(cursor, "$content", content);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void first (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(first)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		try {
		    if (cursor.first()) {
		        IDataHelper.put(cursor, "$key", cursor.getKey());
		        IDataHelper.put(cursor, "$value", cursor.getValue());
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void flatten (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flatten)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $keys
		// [i] field:0:optional $nulls? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		    boolean includeNulls = IDataHelper.getOrDefault(cursor, "$nulls?", Boolean.class, false);
		
		    Object[] values = IDataHelper.flatten(pipeline, includeNulls, keys);
		
		    IDataHelper.put(cursor, "$values", values, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] object:0:optional $default.object
		// [i] field:0:optional $default.string
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    Object defaultObject = IDataHelper.get(cursor, "$default.object");
		    if (defaultObject == null) defaultObject = IDataHelper.get(cursor, "$default.string", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    Object value = IDataHelper.get(pipeline, key, defaultObject, literal);
		    
		    IDataHelper.put(cursor, "$value", value, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void last (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(last)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		try {
		    if (cursor.last()) {
		        IDataHelper.put(cursor, "$key", cursor.getKey());
		        IDataHelper.put(cursor, "$value", cursor.getValue());
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$length", IDataHelper.size(pipeline), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void listify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $key
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    IDataHelper.arrayify(pipeline, key);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void log (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(log)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $log.level {&quot;Fatal&quot;,&quot;Error&quot;,&quot;Warn&quot;,&quot;Info&quot;,&quot;Debug&quot;,&quot;Trace&quot;,&quot;Off&quot;}
		// [i] field:0:optional $log.message
		// [i] field:0:optional $log.prefix? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:0:optional $log.name
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    ServerLogLevel level = IDataHelper.remove(cursor, "$log.level", ServerLogLevel.class);
		    if (level == null) level = IDataHelper.get(cursor, "$level", ServerLogLevel.class);
		    String message = IDataHelper.remove(cursor, "$log.message", String.class);
		    boolean addPrefix = IDataHelper.removeOrDefault(cursor, "$log.prefix?", Boolean.class, true);
		    String name = IDataHelper.remove(cursor, "$log.name", String.class);
		
		    if (name == null) {
		       // infer log name as the invoking service's package
		       Package invokingPackage = PackageHelper.self();
		       if (invokingPackage != null) name = invokingPackage.getName();
		    }
		
		    ServerLogHelper.log(name, level, message, pipeline, addPrefix);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void merge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(merge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    merge(pipeline, IDataHelper.get(cursor, "$document", IData.class));
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData copy = IDataHelper.normalize(pipeline);
		    IDataHelper.clear(pipeline);
		    merge(pipeline, copy);
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
		// [i] field:0:optional $content.class {&quot;xml&quot;,&quot;json&quot;,&quot;yaml&quot;}
		// [i] field:0:optional $content.encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    String contentClass = IDataHelper.get(cursor, "$content.class", String.class);
		    Charset charset = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		
		    if (contentClass == null || contentClass.equals("xml")) {
		        merge(pipeline, new IDataXMLParser().parse(InputStreamHelper.normalize(content, charset)));
		    } else if (contentClass.equals("json")) {
		        merge(pipeline, new IDataJSONParser().parse(InputStreamHelper.normalize(content, charset)));
		    } else if (contentClass.equals("yaml")) {
		        merge(pipeline, new IDataYAMLParser().parse(InputStreamHelper.normalize(content, charset)));
		    } else {
		        throw new IllegalArgumentException(MessageFormat.format("$content.class must be either \"xml\", \"json\", or \"yaml\": {0}", contentClass));
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$value");
		    
		    IDataHelper.put(pipeline, key, value, literal);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void rename (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rename)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    IDataHelper.rename(pipeline, source, target, literal);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sanitize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sanitize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service.signature.direction {&quot;input&quot;,&quot;output&quot;}
		// [i] field:0:optional $pipeline.sanitize.recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    InputOutputSignature direction = IDataHelper.remove(cursor, "$service.signature.direction", InputOutputSignature.class);
		    boolean recurse = IDataHelper.removeOrDefault(cursor, "$pipeline.sanitize.recurse?", Boolean.class, false);
		    PipelineHelper.sanitize(pipeline, direction, recurse);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sort (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sort)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		sort(pipeline, false);
		// --- <<IS-END>> ---

                
	}



	public static final void squeeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(squeeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData copy = Transformer.transform(pipeline, new Squeezer(true));
		    IDataHelper.clear(pipeline);
		    merge(pipeline, copy);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData copy = SubstitutionHelper.substitute(pipeline, null, true, true, null, pipeline);
		    IDataHelper.clear(pipeline);
		    merge(pipeline, copy);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void trim (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(trim)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData copy = Transformer.transform(pipeline, new Trimmer(true));
		    IDataHelper.clear(pipeline);
		    merge(pipeline, copy);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $service.signature.direction {&quot;input&quot;,&quot;output&quot;}
		// [i] field:0:optional $validation.raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $validation.result?
		// [o] field:0:optional $validation.message
		// [o] record:1:optional $validation.errors
		// [o] - field:0:optional code
		// [o] - field:0:optional message
		// [o] - field:0:optional key
		// [o] - object:0:optional value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    InputOutputSignature direction = IDataHelper.remove(cursor, "$service.signature.direction", InputOutputSignature.class);
		    boolean raise = IDataHelper.removeOrDefault(cursor, "$validation.raise?", Boolean.class, false);
		
		    ValidationResult result = PipelineHelper.validate(pipeline, direction);
		
		    result.raiseIfInvalid(raise);
		
		    IDataHelper.put(cursor, "$validation.result?", result.isValid(), String.class);
		    IDataHelper.put(cursor, "$validation.message", result.getMessage(), false);
		    IDataHelper.put(cursor, "$validation.errors", result.getErrors(), false);
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

