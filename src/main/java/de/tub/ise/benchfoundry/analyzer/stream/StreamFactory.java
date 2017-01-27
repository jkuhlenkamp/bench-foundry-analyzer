package de.tub.ise.benchfoundry.analyzer.stream;

import de.tub.ise.benchfoundry.analyzer.model.Measurement;

import java.util.stream.Stream;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 * Created by on 26.01.17.
 */
public interface StreamFactory {

    Stream<Measurement> create();

}