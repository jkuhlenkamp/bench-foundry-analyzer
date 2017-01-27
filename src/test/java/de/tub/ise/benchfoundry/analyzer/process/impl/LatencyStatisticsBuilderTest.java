package de.tub.ise.benchfoundry.analyzer.process.impl;

import de.tub.ise.benchfoundry.analyzer.model.Measurement;
import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.model.enums.ResultType;
import de.tub.ise.benchfoundry.analyzer.stream.impl.MemoryStreamFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class LatencyStatisticsBuilderTest {

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
    public void testMin() {
        statistics = builder.process();
        // Ordered latencies [0,100,100,100,200]

        assertNotNull(statistics);
        assertTrue(statistics.containsKey("min") && statistics.get("min") != null);
        assertEquals(0L, Long.valueOf(statistics.get("min").getValue()), 0L);
    }

    @Test
    public void testMax() {
        statistics = builder.process();
        // Ordered latencies [0,100,100,100,200]

        assertNotNull(statistics);
        assertTrue(statistics.containsKey("max") && statistics.get("max") != null);
        assertEquals(200L, Long.valueOf(statistics.get("max").getValue()), 0L);
    }

    @Test
    public void testArithmeticMean(){
        statistics = builder.process();
        // Ordered latencies [0,100,100,100,200]

        assertNotNull(statistics);
        assertTrue(statistics.containsKey("arithmetic_mean") && statistics.get("arithmetic_mean") != null);
        assertEquals(100D, Double.valueOf(statistics.get("arithmetic_mean").getValue()), 0D);
    }

    @Test
    public void testPercentile() {
        statistics = builder.process();
        // Ordered latencies [0,100,100,100,200]

        assertNotNull(statistics);
        assertTrue(statistics.containsKey("0th_percentile") && statistics.get("0th_percentile") != null);
        assertEquals(0D, Double.valueOf(statistics.get("0th_percentile").getValue()), 0D);
        assertTrue(statistics.containsKey("50th_percentile") && statistics.get("50th_percentile") != null);
        assertEquals(100D, Double.valueOf(statistics.get("50th_percentile").getValue()), 0D);
        assertTrue(statistics.containsKey("100th_percentile") && statistics.get("100th_percentile") != null);
        assertEquals(200D, Double.valueOf(statistics.get("100th_percentile").getValue()), 0D);
    }
}