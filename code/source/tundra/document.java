package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-20 08:13:28 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataXMLParser;
import permafrost.tundra.data.ReadOnlyIDataMap;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ClassHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.math.IntegerHelper;
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    IData[] amendments = IDataUtil.getIDataArray(cursor, "$amendments");
		
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.amend(document, amendments, pipeline));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.blankify(document, recurse));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String[] keys = IDataUtil.getStringArray(cursor, "$preserve");
		
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
		    IData input = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
		    if (input != null) {
		        IData output = IDataHelper.compact(input, recurse);
		        if (output == null) output = IDataFactory.create();
		        IDataUtil.put(cursor, "$document", output);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String source = IDataUtil.getString(cursor, "$key.source");
		    String target = IDataUtil.getString(cursor, "$key.target");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$key.literal?"));
		    
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.copy(document, source, target, literal));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String key = IDataUtil.getString(cursor, "$key");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$key.literal?"));
		    
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.drop(document, key, literal));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$duplicate", IDataHelper.duplicate(document, recurse));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String keyInput = IDataUtil.getString(cursor, "$key.input");
		    String valueInput = IDataUtil.getString(cursor, "$value.input");
		    String valueClass = IDataUtil.getString(cursor, "$value.class");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    boolean scoped = scope != null;
		
		    each(document, service, scoped ? scope: pipeline, keyInput, valueInput, valueClass == null? null : Class.forName(valueClass), recurse);
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    if (document != null) IDataUtil.put(cursor, "$content", ObjectHelper.convert(IDataXMLParser.getInstance().emit(document, charset), charset, mode));
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
		    IData x = IDataUtil.getIData(cursor, "$document.x");
		    IData y = IDataUtil.getIData(cursor, "$document.y");
		
		    IDataUtil.put(cursor, "$equal?", BooleanHelper.emit(ObjectHelper.equal(x, y)));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    if (document != null) {
		        IDataCursor dc = document.getCursor();
		        if (dc.first()) {
		            String key = dc.getKey();
		            Object value = dc.getValue();
		            IDataUtil.put(cursor, "$key", key);
		            IDataUtil.put(cursor, "$value", value);
		        }
		        dc.destroy();
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		    boolean includeNulls = BooleanHelper.parse(IDataUtil.getString(cursor, "$nulls?"));
		
		    // support $key for backwards compatibility
		    if (keys == null) {
		        String key = IDataUtil.getString(cursor, "$key");
		        if (key != null) keys = new String[] { key };
		    }
		
		    Object[] values = IDataHelper.flatten(document, includeNulls, keys);
		
		    if (values != null) IDataUtil.put(cursor, "$values", values);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    if (document != null) IDataUtil.put(cursor, "$document", ReadOnlyIDataMap.of(document));
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
		// [i] object:0:optional $default.object
		// [i] field:0:optional $default.string
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String key = IDataUtil.getString(cursor, "$key");
		    Object defaultObject = IDataUtil.get(cursor, "$default.object");
		    if (defaultObject == null) defaultObject = IDataUtil.getString(cursor, "$default.string");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$key.literal?"));
		
		    Object value = IDataHelper.get(pipeline, document, key, defaultObject, literal);
		
		    if (value != null) IDataUtil.put(cursor, "$value", value);
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
		// [i] field:0:optional $nulls? {"false","true"}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String valueSeparator = IDataUtil.getString(cursor, "$separator.value");
		    String listSeparator = IDataUtil.getString(cursor, "$separator.list");
		    String itemSeparator = IDataUtil.getString(cursor, "$separator.item");
		    boolean includeNulls = BooleanHelper.parse(IDataUtil.getString(cursor, "$nulls?"), false);
		
		    if (document != null) IDataUtil.put(cursor, "$result", IDataHelper.join(document, itemSeparator, listSeparator, valueSeparator, includeNulls));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		
		    IDataUtil.put(cursor, "$keys", IDataHelper.getKeys(document, pattern));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    if (document != null) {
		        IDataCursor dc = document.getCursor();
		        if (dc.last()) {
		            String key = dc.getKey();
		            Object value = dc.getValue();
		            IDataUtil.put(cursor, "$key", key);
		            IDataUtil.put(cursor, "$value", value);
		        }
		        dc.destroy();
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String[] classes = IDataUtil.getStringArray(cursor, "$classes");
		
		    Object[] values = IDataHelper.getLeafValues(document, ClassHelper.forName(classes));
		
		    if (values != null && values.length > 0) IDataUtil.put(cursor, "$values", values);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    IDataUtil.put(cursor, "$length", IntegerHelper.emit(IDataHelper.size(document)));
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
		    String key = IDataUtil.getString(cursor, "$key");
		    IData scope = IDataUtil.getIData(cursor, "$scope");
		    boolean scoped = scope != null;
		
		    if (scoped) {
		        scope = IDataHelper.duplicate(scope);
		    } else {
		        scope = pipeline;
		    }
		
		    scope = IDataHelper.arrayify(scope, key);
		
		    if (scoped) IDataUtil.put(cursor, "$scope", scope);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String service = IDataUtil.getString(cursor, "$service");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String keyInput = IDataUtil.getString(cursor, "$key.input");
		    String keyOutput = IDataUtil.getString(cursor, "$key.output");
		    String valueInput = IDataUtil.getString(cursor, "$value.input");
		    String valueOutput = IDataUtil.getString(cursor, "$value.output");
		    String valueClass = IDataUtil.getString(cursor, "$value.class");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    boolean scoped = scope != null;
		
		    IDataUtil.put(cursor, "$document", map(document, service, scoped ? scope: pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass == null? null : Class.forName(valueClass), recurse));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] record:1:optional $documents
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] documents = IDataUtil.getIDataArray(cursor, "$documents");
		    IDataUtil.put(cursor, "$document", IDataHelper.merge(documents));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    IDataUtil.put(cursor, "$document", IDataHelper.normalize(document));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.nullify(document, recurse));
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
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		
		    if (content != null) IDataUtil.put(cursor, "$document", IDataXMLParser.getInstance().parse(InputStreamHelper.normalize(content, charset)));
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
		// [o] - object:0:required value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$pivot", IDataHelper.pivot(document, recurse));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String key = IDataUtil.getString(cursor, "$key");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$key.literal?"));
		    Object value = IDataUtil.get(cursor, "$value");
		
		    IDataUtil.put(cursor, "$document", IDataHelper.put(document, key, value, literal));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String source = IDataUtil.getString(cursor, "$key.source");
		    String target = IDataUtil.getString(cursor, "$key.target");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$key.literal?"));
		    
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.rename(document, source, target, literal));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.sort(document, recurse));
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
		    IData input = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		
		    if (input != null) {
		        IData output = IDataHelper.squeeze(input, recurse);
		        if (output == null) output = IDataFactory.create();
		        IDataUtil.put(cursor, "$document", output);
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (document != null) IDataUtil.put(cursor, "$document", IDataHelper.stringify(document, recurse));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String defaultValue = IDataUtil.getString(cursor, "$default");
		    EnumSet<SubstitutionType> mode = SubstitutionType.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    IDataUtil.put(cursor, "$document", SubstitutionHelper.substitute(document, defaultValue, scope == null ? pipeline : scope, true, mode));
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
		    IData document = IDataUtil.getIData(cursor, "$document");
		    if (document != null) IDataUtil.put(cursor, "$values", IDataHelper.getValues(document));
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
	                IDataUtil.put(oc, tuple.key, tuple.value);
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
	        IDataUtil.put(cursor, keyInput, tuple.key);
	        IDataUtil.put(cursor, valueInput, tuple.value);
	        cursor.destroy();
	
	        pipeline = tundra.service.invoke(service, pipeline);
	
	        // clean up the input pipeline
	        cursor = pipeline.getCursor();
	        tuple.key = IDataUtil.getString(cursor, keyOutput);
	        tuple.value = (T)IDataUtil.get(cursor, valueOutput);
	        IDataUtil.remove(cursor, keyInput);
	        if (!keyInput.equals(keyOutput)) IDataUtil.remove(cursor, keyOutput);
	        IDataUtil.remove(cursor, valueInput);
	        if (!valueInput.equals(valueOutput)) IDataUtil.remove(cursor, valueOutput);
	        cursor.destroy();
	    }
	    return tuple;
	}
	
	// invokes the given service for each {key, value} pair in the given IData document
	public static void each(IData input, String service, IData pipeline, String keyInput, String valueInput, Class valueClass, boolean recurse) throws ServiceException {
	    map(input, service, pipeline, keyInput, null, valueInput, null, valueClass, recurse);
	}
	// --- <<IS-END-SHARED>> ---
}

