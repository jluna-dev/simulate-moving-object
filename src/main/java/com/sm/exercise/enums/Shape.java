package com.sm.exercise.enums;

public enum Shape {
    RECTANGLE(0);

    private final int shapeCode;

    Shape(int shapeCode) {
        this.shapeCode = shapeCode;
    }

    public int getShapeCode() {
        return this.shapeCode;
    }
}
