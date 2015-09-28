package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-09-29 08:52:47 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.MathContext;
import permafrost.tundra.math.BigDecimalHelper;
import permafrost.tundra.measure.MeasureHelper;
import permafrost.tundra.measure.UnitHelper;
// --- <<IS-END-IMPORTS>> ---

public final class measure

{
	// ---( internal utility methods )---

	final static measure _instance = new measure();

	static measure _newInstance() { return new measure(); }

	static measure _cast(Object o) { return (measure)o; }

	// ---( server methods )---




	public static final void convert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(convert)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $value
		// [i] field:0:required $unit.input {&quot;t&quot;,&quot;kg&quot;,&quot;g&quot;,&quot;lb&quot;,&quot;oz&quot;,&quot;km&quot;,&quot;m&quot;,&quot;cm&quot;,&quot;mm&quot;,&quot;mi&quot;,&quot;yd&quot;,&quot;ft&quot;,&quot;in&quot;}
		// [i] field:0:required $unit.output {&quot;t&quot;,&quot;kg&quot;,&quot;g&quot;,&quot;lb&quot;,&quot;oz&quot;,&quot;km&quot;,&quot;m&quot;,&quot;cm&quot;,&quot;mm&quot;,&quot;mi&quot;,&quot;yd&quot;,&quot;ft&quot;,&quot;in&quot;}
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String value = IDataUtil.getString(cursor, "$value");
		    String inputUnit = IDataUtil.getString(cursor, "$unit.input");
		    String outputUnit = IDataUtil.getString(cursor, "$unit.output");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    if (value != null) IDataUtil.put(cursor, "$result", BigDecimalHelper.emit(BigDecimalHelper.round(MeasureHelper.convert(BigDecimalHelper.parse(value), UnitHelper.parse(inputUnit), UnitHelper.parse(outputUnit), MathContext.DECIMAL128), precision, rounding)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

