package de.tub.ise.benchfoundry.analyzer.model.impl;

import de.tub.ise.benchfoundry.analyzer.model.enums.TimestampPrecision;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class TimestampMetric extends LongMetric {

    private final TimestampPrecision precision;

    public TimestampMetric(String name, Long value, TimestampPrecision precision) {
        super(name, value);
        this.precision = precision;
    }

    public TimestampPrecision getPrecision() {
        return precision;
    }
}
