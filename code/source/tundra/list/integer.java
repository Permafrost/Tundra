package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-12-13T12:23:35.115
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigInteger;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.math.BigIntegerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class integer

{
	// ---( internal utility methods )---

	final static integer _instance = new integer();

	static integer _newInstance() { return new integer(); }

	static integer _cast(Object o) { return (integer)o; }

	// ---( server methods )---




	public static final void add (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(add)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $values
		// [i] record:0:optional $operands
		// [o] field:1:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] values = IDataHelper.get(cursor, "$values", String[].class);
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);

		    BigInteger[] results = BigIntegerHelper.add(BigIntegerHelper.normalize(values), BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));

		    IDataHelper.put(cursor, "$results", BigIntegerHelper.emit(results), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void divide (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(divide)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $dividends
		// [i] field:0:optional $divisor
		// [o] field:1:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] dividends = IDataHelper.get(cursor, "$dividends", String[].class);
		    BigInteger divisor = IDataHelper.get(cursor, "$divisor", BigInteger.class);

		    BigInteger[] results = BigIntegerHelper.divide(BigIntegerHelper.normalize(dividends), divisor);

		    IDataHelper.put(cursor, "$results", BigIntegerHelper.emit(results), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void multiply (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(multiply)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $values
		// [i] record:0:optional $operands
		// [o] field:1:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] values = IDataHelper.get(cursor, "$values", String[].class);
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);

		    BigInteger[] results = BigIntegerHelper.multiply(BigIntegerHelper.normalize(values), BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));

		    IDataHelper.put(cursor, "$results", BigIntegerHelper.emit(results), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void remainder (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remainder)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $dividends
		// [i] field:0:optional $divisor
		// [o] field:1:optional $remainders
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] dividends = IDataHelper.get(cursor, "$dividends", String[].class);
		    BigInteger divisor = IDataHelper.get(cursor, "$divisor", BigInteger.class);

		    BigInteger[] remainders = BigIntegerHelper.remainder(BigIntegerHelper.normalize(dividends), divisor);

		    IDataHelper.put(cursor, "$remainders", BigIntegerHelper.emit(remainders), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void subtract (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(subtract)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $minuends
		// [i] field:0:optional $subtrahend
		// [o] field:1:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String[] minuends = IDataHelper.get(cursor, "$minuends", String[].class);
		    BigInteger subtrahend = IDataHelper.get(cursor, "$subtrahend", BigInteger.class);

		    BigInteger[] results = BigIntegerHelper.subtract(BigIntegerHelper.normalize(minuends), subtrahend);

		    IDataHelper.put(cursor, "$results", BigIntegerHelper.emit(results), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

