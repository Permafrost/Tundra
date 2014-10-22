package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-10-22 12:05:02.649
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class system

{
	// ---( internal utility methods )---

	final static system _instance = new system();

	static system _newInstance() { return new system(); }

	static system _cast(Object o) { return (system)o; }

	// ---( server methods )---




	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $system
		// [o] - field:0:required version
		// [o] - record:0:required environment
		// [o] - record:0:required properties
		// [o] - record:0:required directories
		// [o] -- field:0:required root
		// [o] -- field:0:required config
		// [o] -- field:0:required datastore
		// [o] -- field:0:required jobs
		// [o] -- field:0:required lib
		// [o] -- field:0:required logs
		// [o] -- field:0:required packages
		// [o] -- field:0:required recycle
		// [o] -- field:0:required replicate
		// [o] -- field:0:required replicate.inbound
		// [o] -- field:0:required replicate.outbound
		// [o] -- field:0:required replicate.salvage
		// [o] - record:0:required memory
		// [o] -- field:0:required used
		// [o] -- field:0:required free
		// [o] -- field:0:required total
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$system", reflect());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns information about Integration Server such as the software version,
	// environment settings, Java properties, well-known directory locations, and 
	// memory usage
	public static IData reflect() throws ServiceException {
	  IData system = IDataFactory.create();
	  IDataCursor cursor = system.getCursor();
	
	  IDataUtil.put(cursor, "version", com.wm.app.b2b.server.Build.getVersion());
	  IDataUtil.put(cursor, "environment", environment());
	  IDataUtil.put(cursor, "properties", properties());
	  IDataUtil.put(cursor, "directories", directories());
	  IDataUtil.put(cursor, "memory", memory());
	
	  cursor.destroy();
	
	  return system;
	}
	
	// returns all environment settings for currently JVM executing process
	public static IData environment() {
	  return mapToIData(System.getenv());
	}
	
	// returns all java properties for currently JVM executing process
	public static IData properties() {
	  java.util.Properties properties = System.getProperties();
	  String mailFrom = properties.getProperty("mail.from");
	  if (mailFrom == null || mailFrom.equals("")) {
	    String domain = "unknown";
	    try {
	      java.net.InetAddress address = java.net.InetAddress.getLocalHost();
	      domain = address.getCanonicalHostName().toLowerCase();
	    } catch (java.net.UnknownHostException ex) { }
	    properties.setProperty("mail.from", "Integration-Server@" + domain);
	  }
	
	  return mapToIData(properties);
	}
	
	// returns locations of well-known Integration Server directories
	public static IData directories() throws ServiceException {
	  com.wm.app.b2b.server.Resources resources = com.wm.app.b2b.server.Server.getResources();
	
	  IData directories = IDataFactory.create();
	  IDataCursor cursor = directories.getCursor();
	
	  IDataUtil.put(cursor, "root", tundra.support.file.normalize(resources.getRootDir()));
	  IDataUtil.put(cursor, "config", tundra.support.file.normalize(resources.getConfigDir()));
	  IDataUtil.put(cursor, "datastore", tundra.support.file.normalize(resources.getDatastoreDir()));
	  IDataUtil.put(cursor, "jobs", tundra.support.file.normalize(resources.getJobsDir()));
	  IDataUtil.put(cursor, "lib", tundra.support.file.normalize(resources.getLibDir()));
	  IDataUtil.put(cursor, "logs", tundra.support.file.normalize(resources.getLogDir()));
	  IDataUtil.put(cursor, "packages", tundra.support.file.normalize(resources.getPackagesDir()));
	  IDataUtil.put(cursor, "recycle", tundra.support.file.normalize(resources.getRecycleDir()));
	  IDataUtil.put(cursor, "replicate", tundra.support.file.normalize(resources.getReplicateDir()));
	  IDataUtil.put(cursor, "replicate.inbound", tundra.support.file.normalize(resources.getReplicateInDir()));
	  IDataUtil.put(cursor, "replicate.outbound", tundra.support.file.normalize(resources.getReplicateOutDir()));
	  IDataUtil.put(cursor, "replicate.salvage", tundra.support.file.normalize(resources.getReplicateSaveDir()));
	
	  cursor.destroy();
	
	  return directories;
	}
	
	// returns the Java heap memory usage for the currently executing JVM process
	public static IData memory() {
	  Runtime r = Runtime.getRuntime();
	
	  long free = r.freeMemory();
	  long total = r.totalMemory();
	  long used = total - free;
	
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	  IDataUtil.put(cursor, "used", "" + used);
	  IDataUtil.put(cursor, "free", "" + free);
	  IDataUtil.put(cursor, "total", "" + total);
	  cursor.destroy();
	
	  return output;
	}
	
	// converts a java.util.Map to an IData document
	protected static IData mapToIData(java.util.Map map) {
	  map = new java.util.TreeMap(map);
	  java.util.Iterator iterator = map.keySet().iterator();
	
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	  while(iterator.hasNext()) {
	    String key = iterator.next().toString();
	    Object value = map.get(key);
	    IDataUtil.put(cursor, key, value);
	  }
	  cursor.destroy();
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

