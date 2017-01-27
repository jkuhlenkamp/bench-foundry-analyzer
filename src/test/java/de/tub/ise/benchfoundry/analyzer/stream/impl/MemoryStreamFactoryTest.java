package de.tub.ise.benchfoundry.analyzer.stream.impl;

import de.tub.ise.benchfoundry.analyzer.model.Measurement;
import de.tub.ise.benchfoundry.analyzer.model.enums.ResultType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class MemoryStreamFactoryTest {

    private List<Measurement> measurements;
    private MemoryStreamFactory factory;

    @Before
    public void beforeTest() {
        measurements = new ArrayList<>();
        measurements.add(new Measurement("node1", 1L, 1L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001000L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 1L, 2L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001400L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 1L, 3L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.SUCCESSFUL, new ArrayList<>()));
        measurements.add(new Measurement("node1", 2L, 1L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.FAILED, new ArrayList<>()));
        measurements.add(new Measurement("node1", 3L, 1L, 1L, 1L, 1485400001000L, 1485400001100L, 1485400001200L, ResultType.TIMEOUT, new ArrayList<>()));
        factory = new MemoryStreamFactory();
        measurements.stream().forEach((m) -> factory.add(m));
    }

    @Test
    public void testCreate() throws Exception {
        List<Measurement> fromStream = new ArrayList<>();
        factory.create().forEach((m) -> fromStream.add(m));
        assertEquals(fromStream.get(1).getNode_id(), "node1");
        assertEquals(fromStream.get(1).getProcess_id(), 1L, 0L);
    }
}
