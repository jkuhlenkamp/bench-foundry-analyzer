package de.tub.ise.benchfoundry.analyzer.model.impl;

import de.tub.ise.benchfoundry.analyzer.model.Metric;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class LongMetric extends Metric {

    public LongMetric(String name, Long value) {
        super(name, String.valueOf(value));
    }

    public Long getLong() {
        return Long.parseLong(super.getValue());
    }
}
