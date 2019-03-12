package com.company.sales.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@NamePattern("%s %s|state,value")
@Table(name = "SALES_ORDER_DETAILS")
@Entity(name = "sales_OrderDetails")
public class OrderDetails extends StandardEntity {
    @Column(name = "STATE")
    protected String state;

    @Column(name = "VALUE_")
    protected BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}