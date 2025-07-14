package com.iliasdev.model.score_model;

import lombok.Getter;

@Getter
public enum Point {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    private final String pointCode;

    Point(String pointCode) {
        this.pointCode = pointCode;
    }


    public Point next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Can't call next() on ADVANTAGE");
        } else {
            return Point.values()[this.ordinal() + 1];
        }
    }
}
