package org.elcar.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A RegionSales.
 */
@Document(collection = "region_sales")
public class RegionSales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    @Field("region")
    private String region;

    @NotNull
    @Min(value = 0L)
    @Field("sale")
    private Long sale;

    @NotNull
    @Min(value = 1900)
    @Max(value = 2050)
    @Field("year")
    private Integer year;

    @NotNull
    @Size(max = 100)
    @Field("car_type")
    private String carType;

    @Field("comment")
    private String comment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RegionSales id(String id) {
        this.id = id;
        return this;
    }

    public String getRegion() {
        return this.region;
    }

    public RegionSales region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getSale() {
        return this.sale;
    }

    public RegionSales sale(Long sale) {
        this.sale = sale;
        return this;
    }

    public void setSale(Long sale) {
        this.sale = sale;
    }

    public Integer getYear() {
        return this.year;
    }

    public RegionSales year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCarType() {
        return this.carType;
    }

    public RegionSales carType(String carType) {
        this.carType = carType;
        return this;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getComment() {
        return this.comment;
    }

    public RegionSales comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegionSales)) {
            return false;
        }
        return id != null && id.equals(((RegionSales) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegionSales{" +
            "id=" + getId() +
            ", region='" + getRegion() + "'" +
            ", sale=" + getSale() +
            ", year=" + getYear() +
            ", carType='" + getCarType() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
