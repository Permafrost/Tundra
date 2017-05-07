package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 10:55:49 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import permafrost.tundra.data.IDataHelper;
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
		// [i] field:0:required $unit.input {"t","kg","g","lb","oz","km","m","cm","mm","mi","yd","ft","in"}
		// [i] field:0:required $unit.output {"t","kg","g","lb","oz","km","m","cm","mm","mi","yd","ft","in"}
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigDecimal value = IDataHelper.get(cursor, "$value", BigDecimal.class);
		    String inputUnit = IDataHelper.get(cursor, "$unit.input", String.class);
		    String outputUnit = IDataHelper.get(cursor, "$unit.output", String.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    if (value != null) {
		        IDataHelper.put(cursor, "$result", BigDecimalHelper.round(MeasureHelper.convert(value, UnitHelper.parse(inputUnit), UnitHelper.parse(outputUnit), MathContext.DECIMAL128), precision, rounding), String.class);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

