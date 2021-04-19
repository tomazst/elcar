package org.elcar.app.dto;

import java.util.Set;
import java.util.TreeSet;

public class SimpleBarChartDTO {
    private Set<String> categories;
    private Set<SeriesDTO> series;

    public SimpleBarChartDTO() {
        series = new TreeSet<>();
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<SeriesDTO> getSeries() {
        return series;
    }

    public void setSeries(Set<SeriesDTO> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "SimpleBarChartDTO{" +
                "categories=" + categories +
                ", series=" + series +
                '}';
    }
}
