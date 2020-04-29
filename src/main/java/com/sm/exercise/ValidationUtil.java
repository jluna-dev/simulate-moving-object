package com.sm.exercise;

import com.sm.exercise.exception.ValidationException;
import com.sm.exercise.model.MatrixObject;
import com.sm.exercise.enums.Shape;
import com.sm.exercise.model.Table;

import java.util.List;

public class ValidationUtil {

    /**
     * Input size should be 4
     * @param input
     * @param reqSize
     * @throws ValidationException if input size is not 4
     */
    public static void isValidSize(List<Integer> input, int reqSize) throws ValidationException {
        if(input.size() != reqSize){
            throw new ValidationException("Invalid input. Matrix input size should be 4.");
        }
    }

    /**
     * Command inputs should be 0-4
     *
     * @param commands
     * @throws ValidationException if command input range is invalid
     */
    public static void validateCommand(List<Integer> commands) throws ValidationException {
        if(commands.stream().allMatch(i -> i >= 0  && i <= 4) != true){
            throw new ValidationException("Invalid command input. Command inputs should be between 0 to 4 only.");
        }
    }

    /**
     * Validates the following user input
     * - Input size is 4
     * - Valid table size input
     * - Valid initial object position input
     * @param input
     * @throws ValidationException if input is invalid
     */
    public static void validateMatrixInput(List<Integer> input) throws ValidationException {
        ValidationUtil.isValidSize(input, 4);

        // Check table input is valid size
        if (input.get(0) == 0 || input.get(1) == 0) {
            throw new ValidationException("Invalid table size input.");
        }

        // Check if initial object position exceeds table size
        boolean isInputPosXOutside = (input.get(2) > input.get(0)) || (input.get(2) < 0);
        boolean isInputPosYOutside = (input.get(3) > input.get(1)) || (input.get(3) < 0);

        if (isInputPosXOutside || isInputPosYOutside) {
            throw new ValidationException("Invalid initial object position input.");
        }
    }

    /**
     * Check object position result if it exceeds the table
     * @param obj
     * @param table
     * @param shape
     * @return true if moving object position is inside table
     */
    public static boolean checkObjectPosition(MatrixObject obj, Table table, Shape shape) {
        boolean isValidPos = false;
        if(shape == Shape.RECTANGLE){
            boolean isPosXOutside = obj.getPositionX() > table.getMaxPositionX() || obj.getPositionX() < 0;
            boolean isPosYOutside = obj.getPositionY() > table.getMaxPositionY() || obj.getPositionY() < 0;

            if(!isPosXOutside && !isPosYOutside){
                 isValidPos = true;
            }
        }
        // Boundary may vary per shapes property. It can be added here in the future.
        return isValidPos;
    }

}
