package org.elcar.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeriesDTO implements Comparable {
    private String name;
    private List<Long> data;

    public SeriesDTO() {
        data = new ArrayList<>();
    }

    public SeriesDTO(String name) {
        this.name = name;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriesDTO seriesDTO = (SeriesDTO) o;
        return name.equals(seriesDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SeriesDTO{" +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o != null ) {
            SeriesDTO seriesDTO2 = (SeriesDTO) o;
            return (this.name).compareTo(seriesDTO2.name);
        }
        return -1;
    }
}
