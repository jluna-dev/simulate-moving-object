package com.sm.exercise.model;

import com.sm.exercise.enums.Shape;

public class Table {

    private Integer maxPositionX;
    private Integer maxPositionY;
    private Shape shape;
    private Integer direction;

    public Table() {
    }

    public Table(Integer maxPositionX, Integer maxPositionY, Shape shape, Integer direction) {
        this.maxPositionX = maxPositionX;
        this.maxPositionY = maxPositionY;
        this.shape = shape;
        this.direction = direction;
    }

    public Integer getMaxPositionX() {
        return maxPositionX;
    }

    public void setMaxPositionX(Integer maxPositionX) {
        this.maxPositionX = maxPositionX;
    }

    public Integer getMaxPositionY() {
        return maxPositionY;
    }

    public void setMaxPositionY(Integer maxPositionY) {
        this.maxPositionY = maxPositionY;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
}

