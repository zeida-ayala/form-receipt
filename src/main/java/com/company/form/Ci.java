package com.company.form;

import com.company.enums.CiFrom;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Ci {
    public static final int CI_MIN_NUMBER = 1_000_000;

    @NotNull(message = "{pattern.ci.number}")
    @Min(value = CI_MIN_NUMBER, message = "{size.ci.number}")
    private int number;

    private CiFrom from;

    public int getNumber() {
        return number;
    }

    public CiFrom getFrom() {
        return from;
    }

    public void setFrom(CiFrom from) {
        this.from = from;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
