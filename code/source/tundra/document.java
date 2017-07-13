package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-07-13 16:50:57 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.Locale;
import permafrost.tundra.data.CaseInsensitiveIData;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataXMLParser;
import permafrost.tundra.data.ImmutableIData;
import permafrost.tundra.data.transform.TransformerMode;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ClassHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.LocaleHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.lang.Sanitization;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.server.ServiceHelper;
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void amend (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(amend)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:1:optional $amendments
		// [i] - field:0:required key
		// [i] - field:0:optional value
		// [i] - field:0:optional condition
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IData[] amendments = IDataHelper.get(cursor, "$amendments", IData[].class);
		
		    if (document != null) IDataHelper.put(cursor, "$document", IDataHelper.amend(document, amendments, pipeline));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void blankify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(blankify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (document != null) IDataHelper.put(cursor, "$document", IDataHelper.blankify(document, recurse));
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
		// [i] record:0:optional $document
		// [i] field:1:optional $preserve
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$preserve", String[].class);
		
		    IDataHelper.clear(document, keys);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void compact (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compact)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData input = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (input != null) {
		        IData output = IDataHelper.compact(input, recurse);
		        if (output == null) output = IDataFactory.create();
		        IDataHelper.put(cursor, "$document", output);
		    }
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
		// [i] record:0:optional $document
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.copy(document, source, target, literal), false);
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
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.denormalize(document), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    
		    IDataHelper.put(cursor, "$document", IDataHelper.drop(document, key, literal), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void duplicate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(duplicate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $duplicate
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$duplicate", IDataHelper.duplicate(document, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void each (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(each)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    String keyInput = IDataHelper.get(cursor, "$key.input", String.class);
		    String valueInput = IDataHelper.get(cursor, "$value.input", String.class);
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean scoped = scope != null;
		
		    each(document, service, scoped ? scope: pipeline, keyInput, valueInput, valueClass, recurse);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    if (document != null) {
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(IDataXMLParser.getInstance().emit(document, charset), charset, mode));
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document.x
		// [i] record:0:optional $document.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData x = IDataHelper.get(cursor, "$document.x", IData.class);
		    IData y = IDataHelper.get(cursor, "$document.y", IData.class);
		
		    IDataHelper.put(cursor, "$equal?", ObjectHelper.equal(x, y), String.class);
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
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		
		    if (document != null) {
		        IDataCursor documentCursor = document.getCursor();
		        if (documentCursor.first()) {
		            IDataHelper.put(cursor, "$key", documentCursor.getKey());
		            IDataHelper.put(cursor, "$value", documentCursor.getValue());
		        }
		        documentCursor.destroy();
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
		// [i] record:0:optional $document
		// [i] field:1:optional $keys
		// [i] field:0:optional $nulls? {"false","true"}
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		    boolean includeNulls = IDataHelper.getOrDefault(cursor, "$nulls?", Boolean.class, false);
		
		    // support $key for backwards compatibility
		    if (keys == null) {
		        String key = IDataHelper.get(cursor, "$key", String.class);
		        if (key != null) keys = new String[] { key };
		    }
		
		    Object[] values = IDataHelper.flatten(document, includeNulls, keys);
		
		    IDataHelper.put(cursor, "$values", values, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void flip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flip)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.flip(document), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void freeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(freeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    if (document != null) IDataHelper.put(cursor, "$document", new ImmutableIData(document));
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {"false","true"}
		// [i] object:0:optional $value.default
		// [i] object:1:optional $value.list.default
		// [o] object:0:optional $value
		// [o] object:1:optional $value.list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    Object defaultValue = IDataHelper.get(cursor, "$value.default", "$value.list.default", "$default.object", "$default.string");
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    Object value = IDataHelper.get(pipeline, document, key, defaultValue, literal);
		
		    IDataHelper.put(cursor, "$value", value, false);
		    IDataHelper.put(cursor, "$value.list", value, Object[].class, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void join (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(join)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $separator.value
		// [i] field:0:optional $separator.item
		// [i] field:0:optional $separator.list
		// [i] field:0:optional $sanitization {"remove nulls","remove nulls and blanks"}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String valueSeparator = IDataHelper.get(cursor, "$separator.value", String.class);
		    String listSeparator = IDataHelper.get(cursor, "$separator.list", String.class);
		    String itemSeparator = IDataHelper.get(cursor, "$separator.item", String.class);
		    Sanitization sanitization = IDataHelper.get(cursor, "$sanitization", Sanitization.class);
		
		    if (document != null) IDataHelper.put(cursor, "$result", IDataHelper.join(document, itemSeparator, listSeparator, valueSeparator, sanitization));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $pattern
		// [o] field:1:required $keys
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    IDataHelper.put(cursor, "$keys", IDataHelper.getKeys(document, pattern));
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
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		
		    if (document != null) {
		        IDataCursor documentCursor = document.getCursor();
		        if (documentCursor.last()) {
		            IDataHelper.put(cursor, "$key", documentCursor.getKey());
		            IDataHelper.put(cursor, "$value", documentCursor.getValue());
		        }
		        documentCursor.destroy();
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void leaves (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(leaves)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:1:optional $classes
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String[] classes = IDataHelper.get(cursor, "$classes", String[].class);
		
		    Object[] values = IDataHelper.getLeafValues(document, ClassHelper.forName(classes));
		
		    IDataHelper.put(cursor, "$values", values, false, false);
		} catch(ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] record:0:optional $document
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$length", IDataHelper.size(document), String.class);
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
		// [i] record:0:optional $scope
		// [o] record:0:optional $scope
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    IData scope = IDataHelper.get(cursor, "$scope", IData.class);
		    boolean scoped = scope != null;
		
		    if (scoped) {
		        scope = IDataHelper.duplicate(scope);
		    } else {
		        scope = pipeline;
		    }
		
		    scope = IDataHelper.arrayify(scope, key);
		
		    if (scoped) IDataHelper.put(cursor, "$scope", scope);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void lowercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lowercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.lowercase(document, locale, mode, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void map (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(map)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $key.output
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.output
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    String keyInput = IDataHelper.get(cursor, "$key.input", String.class);
		    String keyOutput = IDataHelper.get(cursor, "$key.output", String.class);
		    String valueInput = IDataHelper.get(cursor, "$value.input", String.class);
		    String valueOutput = IDataHelper.get(cursor, "$value.output", String.class);
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean scoped = scope != null;
		
		    IDataHelper.put(cursor, "$document", map(document, service, scoped ? scope: pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (operands == null) {
		        // support $documents IData[] argument for backwards compatibility
		        IData[] documents = IDataHelper.get(cursor, "$documents", IData[].class);
		        IDataHelper.put(cursor, "$document", IDataHelper.merge(documents, recurse));
		    } else {
		        IDataHelper.put(cursor, "$document", IDataHelper.merge(operands, recurse));
		    }
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
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.normalize(document), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void nullify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nullify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.nullify(document, recurse), false);
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
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		
		    IDataHelper.put(cursor, "$document", IDataXMLParser.getInstance().parse(InputStreamHelper.normalize(content, charset)), false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void pivot (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(pivot)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $pivot
		// [o] - field:0:required key
		// [o] - object:0:optional value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$pivot", IDataHelper.pivot(document, recurse), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {"false","true"}
		// [i] object:0:optional $value
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$value");
		
		    IDataHelper.put(cursor, "$document", IDataHelper.put(document, key, value, literal));
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
		// [i] record:0:optional $document
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.rename(document, source, target, literal), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void replace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replace)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [i] field:0:optional $replacement
		// [i] field:0:optional $replacement.literal? {"false","true"}
		// [i] field:0:optional $occurrence.first? {"false","true"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    String replacement = IDataHelper.get(cursor, "$replacement", String.class);
		    boolean literalReplacement = IDataHelper.getOrDefault(cursor, "$replacement.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.replace(document, pattern, literalPattern, replacement, literalReplacement, firstOccurrence, mode, recurse), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.sort(document, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void squeeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(squeeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData input = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (input != null) {
		        IData output = IDataHelper.squeeze(input, recurse);
		        if (output == null) output = IDataFactory.create();
		        IDataHelper.put(cursor, "$document", output);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void stringify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.stringify(document, recurse), false);
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
		// [i] record:0:optional $document
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $default
		// [i] field:0:optional $mode {"local","global","all"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);
		    String defaultValue = IDataHelper.get(cursor, "$default", String.class);
		    EnumSet<SubstitutionType> mode = SubstitutionType.normalize(IDataHelper.get(cursor, "$mode", String.class));
		
		    IDataHelper.put(cursor, "$document", SubstitutionHelper.substitute(document, defaultValue, true, mode, scope), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.trim(document, mode, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void uncase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uncase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.getOrDefault(cursor, "$locale", Locale.class, Locale.getDefault());
		
		    if (document != null) IDataHelper.put(cursor, "$document", new CaseInsensitiveIData(IDataHelper.duplicate(document, true), locale));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void uppercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uppercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.uppercase(document, locale, mode, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void values (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(values)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$values", IDataHelper.getValues(document), false, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// visits each element in the given IData document, calls the given service to convert the element, and creates a new IData document with the resulting elements
	public static IData map(IData input, String service, IData pipeline, String keyInput, String keyOutput, String valueInput, String valueOutput, Class valueClass, boolean recurse) throws ServiceException {
	    IData output = null;
	
	    if (input != null && service != null) {
	        IDataCursor ic = input.getCursor();
	        output = IDataFactory.create();
	        IDataCursor oc = output.getCursor();
	
	        if (keyInput == null) keyInput = "$key";
	        if (keyOutput == null) keyOutput = keyInput;
	        if (valueInput == null) valueInput = "$value";
	        if (valueOutput == null) valueOutput = valueInput;
	        if (pipeline == null) pipeline = IDataFactory.create();
	
	        try {
	            while(ic.next()) {
	                Tuple<Object> tuple = new Tuple<Object>(ic.getKey(), ic.getValue());
	
	                if (recurse && tuple.value != null) {
	                    if (tuple.value instanceof IData) {
	                        tuple.value = map((IData)tuple.value, service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse);
	                    } else if (tuple.value instanceof IData[] || tuple.value instanceof com.wm.util.Table) {
	                        IData[] iary = tuple.value instanceof IData[] ? (IData[])tuple.value : ((com.wm.util.Table)tuple.value).getValues();
	                        IData[] oary = new IData[iary.length];
	                        for (int i = 0; i < iary.length; i++) {
	                            oary[i] = map(iary[i], service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse);
	                        }
	                        tuple.value = oary;
	                    } else if (tuple.value instanceof String[][]) {
	                        String[][] iary = (String[][])tuple.value;
	                        String[][] oary = new String[iary.length][];
	                        for (int i = 0; i < iary.length; i++) {
	                            if (iary[i] != null) {
	                                oary[i] = new String[iary[i].length];
	                                for (int j = 0; j < iary[i].length; j++) {
	                                    Tuple<String> t = map(new Tuple<String>(tuple.key, iary[i][j]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	                                    oary[i][j] = t.value;
	                                }
	                            }
	                        }
	                        tuple.value = oary;
	                    } else if (tuple.value instanceof String[]) {
	                        String[] iary = (String[])tuple.value;
	                        String[] oary = new String[iary.length];
	                        for (int i = 0; i < iary.length; i++) {
	                            Tuple<String> t = map(new Tuple<String>(tuple.key, iary[i]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	                            oary[i] = t.value;
	                        }
	                        tuple.value = oary;
	                    } else if (tuple.value instanceof Object[]) {
	                        Object[] iary = (Object[])tuple.value;
	                        Object[] oary = new Object[iary.length];
	                        for (int i = 0; i < iary.length; i++) {
	                            Tuple<Object> t = map(new Tuple<Object>(tuple.key, iary[i]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	                            oary[i] = t.value;
	                        }
	                        tuple.value = oary;
	                    }
	                }
	                tuple = map(tuple, service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	                IDataHelper.put(oc, tuple.key, tuple.value);
	            }
	        } finally {
	            ic.destroy();
	            oc.destroy();
	        }
	    } else {
	        output = IDataHelper.duplicate(input, recurse);
	    }
	
	    return output;
	}
	
	// wrapper class for {key, value} tuples
	protected static class Tuple<T> {
	    public String key;
	    public T value;
	    public Tuple(String key, T value) {
	        this.key = key;
	        this.value = value;
	    }
	}
	
	// converts the given element by calling the given service, and returns the result
	protected static <T> Tuple<T> map(Tuple<T> tuple, String service, IData pipeline, String keyInput, String keyOutput, String valueInput, String valueOutput, Class valueClass) throws ServiceException {
	    if (tuple.value == null || valueClass == null || valueClass.isInstance(tuple.value)) {
	        IDataCursor cursor = pipeline.getCursor();
	        IDataHelper.put(cursor, keyInput, tuple.key);
	        IDataHelper.put(cursor, valueInput, tuple.value);
	        cursor.destroy();
	
	        pipeline = ServiceHelper.invoke(service, pipeline);
	
	        // clean up the input pipeline
	        cursor = pipeline.getCursor();
	        tuple.key = IDataHelper.get(cursor, keyOutput, String.class);
	        tuple.value = (T)IDataHelper.get(cursor, valueOutput);
	        IDataHelper.remove(cursor, keyInput);
	        if (!keyInput.equals(keyOutput)) IDataHelper.remove(cursor, keyOutput);
	        IDataHelper.remove(cursor, valueInput);
	        if (!valueInput.equals(valueOutput)) IDataHelper.remove(cursor, valueOutput);
	        cursor.destroy();
	    }
	    return tuple;
	}
	
	// invokes the given service for each {key, value} pair in the given IData document
	public static <T> void each(IData input, String service, IData pipeline, String keyInput, String valueInput, Class valueClass, boolean recurse) throws ServiceException {
	    map(input, service, pipeline, keyInput, null, valueInput, null, valueClass, recurse);
	}
	// --- <<IS-END-SHARED>> ---
}

