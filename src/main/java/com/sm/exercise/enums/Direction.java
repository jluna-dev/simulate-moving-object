package com.sm.exercise.enums;

import java.util.HashMap;
import java.util.Map;

public enum Direction {

    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private int value;
    private static Map map = new HashMap<>();

    Direction(int value) {
        this.value = value;
    }

    static {
        for (Direction direction : Direction.values()) {
            map.put(direction.value, direction);
        }
    }

    public static Direction valueOf(int direction) {
        return (Direction) map.get(direction);
    }

    public int getValue() {
        return value;
    }

}
