package de.tub.ise.benchfoundry.analyzer.process;

import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.stream.StreamFactory;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public interface IStatisticsBuilder {

    void setStreamFactory(StreamFactory factory);

    StreamFactory getStreamFactory();

    Statistics process();

}
