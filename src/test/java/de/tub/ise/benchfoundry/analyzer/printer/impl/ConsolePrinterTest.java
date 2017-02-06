package de.tub.ise.benchfoundry.analyzer.printer.impl;

import de.tub.ise.benchfoundry.analyzer.model.Measurement;
import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.model.enums.ResultType;
import de.tub.ise.benchfoundry.analyzer.process.impl.LatencyStatisticsBuilder;
import de.tub.ise.benchfoundry.analyzer.stream.impl.MemoryStreamFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 06.02.17.
 */
public class ConsolePrinterTest {

    private MemoryStreamFactory factory;
    private LatencyStatisticsBuilder builder;
    private Statistics statistics;

    @Before
    public void beforeTest() {
        List<Measurement> measurements = new ArrayList<>();
        measurements.add(new Measurement("node1", 1L, 1L, 1L, 1L, 1485400001000L, 1485400001000L, 1485400001000L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 1L, 2L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001300L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 1L, 3L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 2L, 1L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.FAILED, new ArrayList<>()));
        measurements.add(new Measurement("node1", 3L, 1L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.TIMEOUT, new ArrayList<>()));
        factory = new MemoryStreamFactory();
        measurements.stream().forEach((m) -> factory.add(m));
        builder = new LatencyStatisticsBuilder(factory);
    }

    @Test
    public void testConsolePrinter() {
        statistics = builder.process();
        ConsolePrinter printer = new ConsolePrinter(statistics);
        printer.print();
    }
}
