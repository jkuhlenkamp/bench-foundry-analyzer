package de.tub.ise.benchfoundry.analyzer.process.impl;

import de.tub.ise.benchfoundry.analyzer.model.Measurement;
import de.tub.ise.benchfoundry.analyzer.model.Metric;
import de.tub.ise.benchfoundry.analyzer.model.Statistics;
import de.tub.ise.benchfoundry.analyzer.process.collectors.LongArithmeticMean;
import de.tub.ise.benchfoundry.analyzer.process.collectors.LongValueAtPositionFromOrderedStream;
import de.tub.ise.benchfoundry.analyzer.stream.StreamFactory;

/**
 *  Generates the following metrics:
 *  - min
 *  - max
 *  - arithmetic_mean
 *
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class LatencyStatisticsBuilder extends StatisticsBuilder {

    private final static String sample = "BusinessOperation_Request-Response_Latencies";

    public LatencyStatisticsBuilder(StreamFactory factory) {
        super(factory);
    }

    private int getPositionOfPercentile(long n, int perc) {
        int position = (int) Math.ceil(n * perc / 100 + 0.5);
        if ( position > n ) {       // Account for the case that the position is the last element in the ordered stream.
            position = position-1;
        }
        return position;
    }

    @Override
    public StreamFactory getStreamFactory() {
        return super.getFactory();
    }

    @Override
    public Statistics process() {
        Statistics statistics = new Statistics();

        // Total number of measurements in the dataset
        long count = getStreamFactory().create()
                .count();

        // Minimum latency of all measurements in the dataset
        long min = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .min()
                .getAsLong();
        statistics.put("min", new Metric(sample, "min", String.valueOf(min)));

        // Maximum latency of all measurements in the dataset
        long max = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .max()
                .getAsLong();
        statistics.put("max", new Metric(sample, "max", String.valueOf(max)));

        // Arithmetic mean latency of all measurements in the dataset
        double mean = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .boxed()
                .collect(new LongArithmeticMean());
        statistics.put("arithmetic_mean", new Metric(sample, "arithmetic_mean", String.valueOf(mean)));

        // 0th percentile latency of all measurements in the dataset
        long percentile_0th = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .boxed()
                .sorted()
                .collect(new LongValueAtPositionFromOrderedStream(getPositionOfPercentile(count, 0)));
        statistics.put("0th_percentile", new Metric(sample, "0th_percentile", String.valueOf(percentile_0th)));

        // 50th percentile latency of all measurements in the dataset
        long percentile_50th = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .boxed()
                .sorted()
                .collect(new LongValueAtPositionFromOrderedStream(getPositionOfPercentile(count, 50)));
        statistics.put("50th_percentile", new Metric(sample, "50th_percentile", String.valueOf(percentile_50th)));

        // 100th percentile latency of all measurements in the dataset
        long percentile_100th = getStreamFactory().create()
                .mapToLong(Measurement::getRequestResponseLatency)
                .boxed()
                .sorted()
                .collect(new LongValueAtPositionFromOrderedStream(getPositionOfPercentile(count, 100)));
        statistics.put("100th_percentile", new Metric(sample, "100th_percentile", String.valueOf(percentile_100th)));

        return statistics;
    }
}