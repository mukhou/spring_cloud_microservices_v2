/* 
User: Urmi
Date: 10/11/2021 
Time: 10:01 PM
*/

package com.in28minutes.microservices.limitsservice.model;

public class Limits {

    public Limits(int minimum, int maximum) {
        super();
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Limits() {
        super();
    }

    private int minimum;
    private int maximum;

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }



}
