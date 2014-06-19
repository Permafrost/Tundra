package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-06-19 15:14:03.259
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void _ (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(_)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// returns the nearest ancestor class for the given set of classes
	public static Class<?> nearestAncestor(java.util.Collection<Class<?>> classes) {
	  Class<?> nearest = null;

	  java.util.Set<Class<?>> ancestors = ancestors(classes);
	  if (ancestors.size() > 0) nearest = ancestors.iterator().next();

	  return nearest;
	}

	// returns the nearest ancestor class for the given set of classes
	public static Class<?> nearestAncestor(Class<?>... classes) {
	  return nearestAncestor(java.util.Arrays.asList(classes));
	}

	// returns the nearest ancestor class for the given set of class names
	public static Class<?> nearestAncestor(String... classNames) throws ClassNotFoundException {
	  return nearestAncestor(toClassArray(classNames));
	}

	// returns all the common ancestor classes for the given set of classes
	public static java.util.Set<Class<?>> ancestors(java.util.Collection<Class<?>> classes) {
	  java.util.Set<Class<?>> intersection = new java.util.LinkedHashSet<Class<?>>();

	  for (Class<?> klass : classes) {
	    if (intersection.size() == 0) {
	      intersection.addAll(ancestors(klass));
	    } else {
	      intersection.retainAll(ancestors(klass));
	    }
	  }

	  return intersection;
	}

	// returns all the common ancestor classes for the given set of classes
	public static Class<?>[] ancestors(Class<?>... classes) {
	  return ancestors(java.util.Arrays.asList(classes)).toArray(new Class<?>[0]);
	}

	// returns all the common ancestor classes for the given set of class names
	public static Class<?>[] ancestors(String... classNames) throws ClassNotFoundException {
	  return ancestors(toClassArray(classNames));
	}

	// converts a list of class names to a list of class objects
	protected static Class<?>[] toClassArray(String[] classNames) throws ClassNotFoundException {
	  if (classNames == null) return null;

	  Class<?>[] classes = new Class<?>[classNames.length];
	  for (int i = 0; i < classes.length; i++) {
	    classes[i] = Class.forName(classNames[i]);
	  }
	  return classes;
	}

	// returns all ancestors from nearest to furthest for the given class
	protected static java.util.Set<Class<?>> ancestors(Class<?> klass) {
	  java.util.Set<Class<?>> ancestors = new java.util.LinkedHashSet<Class<?>>();
	  java.util.Set<Class<?>> parents = new java.util.LinkedHashSet<Class<?>>();

	  parents.add(klass);

	  do {
	    ancestors.addAll(parents);

	    java.util.Set<Class<?>> children = new java.util.LinkedHashSet<Class<?>>(parents);
	    parents.clear();

	    for (Class<?> child : children) {
	      Class<?> parent = child.getSuperclass();
	      if (parent != null) parents.add(parent);
	      for (Class<?> parentInterface : child.getInterfaces()) parents.add(parentInterface);
	    }

	  } while (!parents.isEmpty());

	  return ancestors;
	}
	// --- <<IS-END-SHARED>> ---
}

