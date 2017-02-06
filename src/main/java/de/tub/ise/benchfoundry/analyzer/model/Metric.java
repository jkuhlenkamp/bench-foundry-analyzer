package de.tub.ise.benchfoundry.analyzer.model;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 26.01.17.
 */
public class Metric {

    private final String sample;
    private final String name;
    private final String value;

    public Metric(String sample, String name, String value) {
        this.name = name;
        this.value = value;
        this.sample = sample;
    }

    public String getSample() { return sample; }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
