package org.elcar.app.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class RegionSaleForm implements Serializable {
    private String id;
    private String country;
    private Long sale;
    private Integer year;
    private CarType carType;
    private String nonce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSale() {
        return sale;
    }

    public void setSale(Long sale) {
        this.sale = sale;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "ElectricCarForm{" +
                "country='" + country + '\'' +
                ", sale=" + sale +
                ", year=" + year +
                ", carType=" + carType +
                ", nonce=" + nonce +
                '}';
    }
}
