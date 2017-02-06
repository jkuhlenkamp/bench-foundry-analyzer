package de.tub.ise.benchfoundry.analyzer.printer.impl;

import de.tub.ise.benchfoundry.analyzer.model.Metric;
import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.printer.IPrinter;

import java.text.MessageFormat;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 06.02.17.
 */
public class ConsolePrinter implements IPrinter {

    private Statistics statistics;
    private String template = "- Sample: {0};  Metric: {1};  Value: {2}";

    public ConsolePrinter() {}

    public ConsolePrinter(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void print() {
        if(statistics == null) throw new IllegalStateException("No Statistics object set!");

        System.out.println( MessageFormat.format(
                "Analyzis completed! Successfully computed {0} aggregates:\n",
                statistics.size()
        ));

/*        for(Metric m :statistics.values()) {
            System.out.println( MessageFormat.format(
                template, m.getSample(), m.getName(), m.getValue()
            ));
        }*/
        System.out.format( "%-50s%-20s%-20s%n",
                "Sample", "Metric", "Value"
        );

        System.out.format( "%-50s%-20s%-20s%n",
                "==================================================", "====================", "===================="
        );

        for(Metric m :statistics.values()) {
            System.out.format( "%-50s%-20s%-20s%n",
                    m.getSample(), m.getName(), m.getValue()
            );
        }
    }
}
