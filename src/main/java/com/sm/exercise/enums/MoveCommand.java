package com.sm.exercise.enums;

public enum MoveCommand {
    QUIT (0),
    FORWARD (1),
    BACKWARD (2),
    CLOCKWISE (3),
    COUNTER_CLOCKWISE (4);

    private final int value;

    MoveCommand(int value) {
        this.value = value;
    }

    public int getCommand() {
        return this.value;
    }
}
