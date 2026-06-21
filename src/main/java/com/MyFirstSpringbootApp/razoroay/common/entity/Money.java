package com.MyFirstSpringbootApp.razoroay.common.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Money {

    private int amountUnits;
    private String currency;


    public static Money of(int amountUnits, String currency){
        return new Money(amountUnits, currency);
    }

    public static Money inr(int amountUnits){
        return new Money(amountUnits, "INR");
    }

    public  Money add(Money other) throws IllegalAccessException {
        if (!this.currency.equals(other.currency)){
            throw new IllegalAccessException("Cannot add Money with different currencies");
        }
        return new Money(this.amountUnits+ other.amountUnits,this.currency);
    }


    public  Money subtract(Money other) throws IllegalAccessException {
        if (!this.currency.equals(other.currency)){
            throw new IllegalAccessException("Cannot add Money with different currencies");
        }
        return new Money(this.amountUnits - other.amountUnits,this.currency);
    }


}
