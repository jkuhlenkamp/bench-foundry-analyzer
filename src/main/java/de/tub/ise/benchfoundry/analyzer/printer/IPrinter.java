package de.tub.ise.benchfoundry.analyzer.printer;

import de.tub.ise.benchfoundry.analyzer.model.Statistics;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 06.02.17.
 */
public interface IPrinter {

    /**
     * Sets a new {@link Statistics} object.
     *
     * @param statistics
     */
    void setStatistics(Statistics statistics);


    /**
     * Prints statistics for a set {@link Statistics} object.
     */
    void print();
}
