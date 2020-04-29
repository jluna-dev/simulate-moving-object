package com.sm.exercise.model;

public class MatrixObject {

    private Integer positionX;
    private Integer positionY;
    private Integer direction;

    public MatrixObject() {
    }

    public MatrixObject(Integer positionX, Integer positionY, Integer direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
}
