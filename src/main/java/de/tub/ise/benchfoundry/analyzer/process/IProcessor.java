package de.tub.ise.benchfoundry.analyzer.process;

import de.tub.ise.benchfoundry.analyzer.model.Statistics;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public interface IProcessor {

    void addStatisticsBuilder(IStatisticsBuilder builder);

    Statistics merge();

}
