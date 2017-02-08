package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-02-09 09:13:40.699
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.xml.namespace.IDataNamespaceContext;
// --- <<IS-END-IMPORTS>> ---

public final class condition

{
	// ---( internal utility methods )---

	final static condition _instance = new condition();

	static condition _newInstance() { return new condition(); }

	static condition _cast(Object o) { return (condition)o; }

	// ---( server methods )---




	public static final void choose (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(choose)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [i] object:0:optional $result.true
		// [i] object:0:optional $result.false
		// [o] object:0:optional $result
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String condition = IDataUtil.getString(cursor, "$condition");
		    IData scope = IDataUtil.getIData(cursor, "$scope");
		    IData namespace = IDataUtil.getIData(cursor, "$namespace");
		    Object trueResult = IDataUtil.get(cursor, "$result.true");
		    Object falseResult = IDataUtil.get(cursor, "$result.false");

		    if (trueResult != null || falseResult != null) {
		        boolean result = ConditionEvaluator.evaluate(condition, scope == null? pipeline : scope, IDataNamespaceContext.of(namespace));
		        IDataHelper.put(cursor, "$result", result ? trueResult : falseResult, false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void evaluate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(evaluate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [o] field:0:required $result?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String condition = IDataUtil.getString(cursor, "$condition");
		    IData scope = IDataUtil.getIData(cursor, "$scope");
		    IData namespace = IDataUtil.getIData(cursor, "$namespace");

		    IDataUtil.put(cursor, "$result?", BooleanHelper.emit(ConditionEvaluator.evaluate(condition, scope == null? pipeline : scope, IDataNamespaceContext.of(namespace))));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

