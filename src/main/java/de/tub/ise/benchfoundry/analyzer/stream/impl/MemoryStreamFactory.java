package de.tub.ise.benchfoundry.analyzer.stream.impl;

import de.tub.ise.benchfoundry.analyzer.stream.StreamFactory;
import de.tub.ise.benchfoundry.analyzer.model.Measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Creates a stream of <object>Measurement</object> objects based on a list of previously added <object>Measurement</object> objects that are stored in memory.
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class MemoryStreamFactory implements StreamFactory{

    private final List<Measurement> measurements = new ArrayList<>();

    /**
     * Adds a single <object>Measurement</object> object to the current list of <object>Measurement</object> objects.
     * @param m
     */
    public void add(Measurement m) {
        this.measurements.add(m);
    }

    /**
     * Create a new <object>Stream</object> object from the current list of <object>Measurement</object> objects.
     * @return
     */
    public Stream<Measurement> create() {
        return measurements.stream();
    }
}
