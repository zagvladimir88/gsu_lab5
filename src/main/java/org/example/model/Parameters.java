package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private Long paramId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products products;

    @Override
    public String toString() {
        return "Parameters{" +
                "paramId=" + paramId +
                ", paramName='" + paramName + '\'' +
                ", paramGroup='" + paramGroup + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }

    @Column(name = "param_name", nullable = false)
    private String paramName;

    @Column(name = "param_group", nullable = false)
    private String paramGroup;

    @Column(name = "param_value")
    private String paramValue;

    @Column(name = "unit")
    private String unit;

    // Default constructor
    public Parameters() {}

    // Constructor with parameters
    public Parameters(Products products, String paramName, String paramGroup, String paramValue, String unit) {
        this.products = products;
        this.paramName = paramName;
        this.paramGroup = paramGroup;
        this.paramValue = paramValue;
        this.unit = unit;
    }

    // Getters and setters
    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public Products getProduct() {
        return products;
    }

    public void setProduct(Products products) {
        this.products = products;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
