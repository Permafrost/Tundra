package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-04 20:47:27 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.flow.ConditionEvaluator;
// --- <<IS-END-IMPORTS>> ---

public final class condition

{
	// ---( internal utility methods )---

	final static condition _instance = new condition();

	static condition _newInstance() { return new condition(); }

	static condition _cast(Object o) { return (condition)o; }

	// ---( server methods )---




	public static final void evaluate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(evaluate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] field:0:required $result?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String condition = IDataUtil.getString(cursor, "$condition");
		    IData scope = IDataUtil.getIData(cursor, "$scope");
		    
		    IDataUtil.put(cursor, "$result?", "" + ConditionEvaluator.evaluate(condition, scope == null? pipeline : scope));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

