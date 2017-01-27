package de.tub.ise.benchfoundry.analyzer.process.impl;

import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.process.IStatisticsBuilder;
import de.tub.ise.benchfoundry.analyzer.stream.StreamFactory;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public abstract class StatisticsBuilder implements IStatisticsBuilder {

    private StreamFactory factory;

    StatisticsBuilder(StreamFactory factory) {
        this.factory = factory;
    }

    @Override
    public void setStreamFactory(StreamFactory factory) {
        this.factory = factory;
    }

    StreamFactory getFactory() {
        return factory;
    }
}
