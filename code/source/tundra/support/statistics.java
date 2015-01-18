package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-18 12:45:49 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class statistics

{
	// ---( internal utility methods )---

	final static statistics _instance = new statistics();

	static statistics _newInstance() { return new statistics(); }

	static statistics _cast(Object o) { return (statistics)o; }

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
	// Class for incrementally calculating the mean and standard deviation
	public static class IncrementalNormalDistributionEstimator {
	
	  protected long count;
	  protected double total, mean, sq, minimum, maximum;
	  protected String unit = "";
	
	  /**
	   * Constructs a new estimator object.
	   */
	  public IncrementalNormalDistributionEstimator() {
	    reset();
	  }
	
	  /**
	   * Constructs a new estimator object.
	   * @param unit The unit of measurement related to the measured samples.
	   */
	  public IncrementalNormalDistributionEstimator(String unit) {
	    this();
	    this.unit = unit;
	  }  
	
	  /**
	   * Constructs a new estimator object seeded with the given samples.
	   *
	   * @param samples One or more initial samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(double... samples) {
	    this();
	    append(samples);
	  }
	
	  /**
	   * Constructs a new estimator object seeded with the given samples.
	   *
	   * @param unit The unit of measurement related to the measured samples.
	   * @param samples One or more initial samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(String unit, double... samples) {
	    this(unit);
	    append(samples);
	  }
	
	  /**
	   * Constructs a new estimator object seeded with the given collection of
	   * samples.
	   *
	   * @param samples An initial collection of samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(java.util.Collection<Double> samples) {
	    this();
	    append(samples);
	  }
	
	  /**
	   * Constructs a new estimator object seeded with the given collection of
	   * samples.
	   *
	   * @param unit The unit of measurement related to the measured samples.
	   * @param samples An initial collection of samples to seed the estimator with.
	   */
	  public IncrementalNormalDistributionEstimator(String unit, java.util.Collection<Double> samples) {
	    this(unit);
	    append(samples);
	  }
	
	  /**
	   * Appends the given sample to the list of samples in the estimator.
	   *
	   * @param sample The sample to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(double sample) {
	    total += sample;
	    double next = mean + (sample - mean) / ++count;
	    sq += (sample - mean) * (sample - next);
	    mean = next;
	    if (sample < minimum) minimum = sample;
	    if (sample > maximum) maximum = sample;
	
	    return this;
	  }
	
	  /**
	   * Adds one or more samples to the estimator.
	   *
	   * @param samples One or more samples to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(double... samples) {
	    for (double sample : samples) {
	      append(sample);
	    }
	
	    return this;
	  }
	
	  /**
	   * Adds a collection of samples to the estimator.
	   *
	   * @param samples A collection of samples to be added to the estimator.
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator append(java.util.Collection<Double> samples) {
	    for (double sample : samples) {
	      append(sample);
	    }
	
	    return this;
	  }
	
	  /**
	   * Returns the number of samples seen by this estimator.
	   *
	   * @return The number of samples seen by this estimator.
	   */
	  public long count() {
	    return count;
	  }
	
	  /**
	   * Returns the total of all samples seen by this estimator.
	   *
	   * @return The total of all samples seen by this estimator.
	   */
	  public double total() {
	    return total;
	  }
	
	  /**
	   * Returns the mean of the samples.
	   *
	   * @return The mean of the samples.
	   */
	  public double mean() {
	    return mean;
	  }
	
	  /**
	   * Returns the minimum of the samples.
	   *
	   * @return The minimum of the samples.
	   */
	  public double minimum() {
	    return minimum;
	  }
	
	  /**
	   * Returns the maximum of the samples.
	   *
	   * @return The maximum of the samples.
	   */
	  public double maximum() {
	    return maximum;
	  }
	
	  /**
	   * Returns the maximum likelihood estimate of the variance of the samples.
	   *
	   * @return Maximum likelihood variance estimate.
	   */
	  public double variance() {
	    return count > 1 && mean > 0 ? sq / mean : 0.0;
	  }
	
	  /**
	   * Returns the maximum likelihood estimate of the standard deviation of the
	   * samples.
	   *
	   * @return Maximum likelihood standard deviation estimate.
	   */
	  public double standardDeviation() {
	    return Math.sqrt(variance());
	  }
	
	  /**
	   * Returns the unit of measure related to the measured samples.
	   *
	   * @return Maximum likelihood standard deviation estimate.
	   */
	  public String unit() {
	    return unit;
	  }
	
	  /**
	   * Resets the estimator back to zero samples.
	   *
	   * @return The Estimator object itself, to support method chaining.
	   */
	  public final IncrementalNormalDistributionEstimator reset() {
	    count = 0;
	    total = 0.0;
	    mean = 0.0;
	    sq = 0.0;
	    minimum = Double.POSITIVE_INFINITY;
	    maximum = Double.NEGATIVE_INFINITY;
	
	    return this;
	  }
	
	  /**
	   * Returns a string-based representation of the mean, standard deviation and
	   * number of samples for this estimator.
	   *
	   * @return String-based representation of this estimator.
	   */
	  @Override
	  public String toString() {
	    return String.format("\u03BC = %.3f %s, \u03C3 = %.3f %s, \u22C0 = %.3f %s, \u22C1 = %.3f %s, \u2211 = %.3f %s, n = %d", mean(), unit(), standardDeviation(), unit(), minimum(), unit(), maximum(), unit(), total(), unit(), count());
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

