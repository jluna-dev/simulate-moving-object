package com.sm.exercise;

import com.sm.exercise.enums.Direction;
import com.sm.exercise.enums.MoveCommand;
import com.sm.exercise.enums.Shape;
import com.sm.exercise.exception.ValidationException;
import com.sm.exercise.model.MatrixObject;
import com.sm.exercise.model.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimulateMoveObject {

    private static final String SIM_FAILED = "[-1, -1]";
    private static final String INPUT_LABEL = "Input Matrix and Object Position [tableWidth, tableHeight, positionX, positionY]: ";
    private static final String COMMAND_LABEL = "Input Commands (comma separated): ";
    private static final Direction START_DIRECTION =  Direction.NORTH; //simulation always starts with direction north

    private static int positionX, positionY, curr_direction;
    private static Shape shape;

    public static void main(String[] args) throws ValidationException {
        List<Integer> inputs = getInput();
        List<Integer> commands = getCommands();
        shape = Shape.RECTANGLE;    //pre defined shape for this exercise. Other shapes options can handled in the future.
        doSimulateMovingObject(inputs, commands, shape);
    }

    private static void doSimulateMovingObject(List<Integer> inputs, List<Integer> commands, Shape shape ) {
        Table table = setTableInput(inputs,shape);
        MatrixObject obj = setMatrixObject(inputs,shape);
        MatrixObject result = executeCommands(table,obj,commands);
        printSimulationResult(result, table);
    }

    /**
     * Get the string input (table size and starting position) and validate
     * @return matrix
     * @throws ValidationException
     */
    private static  List<Integer> getInput() throws ValidationException {
        List<Integer> input = readInput(INPUT_LABEL);
        ValidationUtil.validateMatrixInput(input);
        return input;
    }

    /**
     * Get the input commands and validate
     * - arbitrarily long stream of commands of integers
     * @return commands
     * @throws ValidationException
     */
    private static List<Integer> getCommands() throws ValidationException {
        List<Integer> commands = readInput(COMMAND_LABEL);
        ValidationUtil.validateCommand(commands);
        return commands;
    }

    /**
     * Read user input from the command prompt
     * @param label
     * @return list integer of user input
     */
    private static List<Integer> readInput(String label) {
        List<Integer> input = new ArrayList();
        // Can change the binary form of the protocol to JSON
        // - BufferedReader can be used with FileReader to read a json file input format
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println(label);
            input = Stream.of(br.readLine().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return input;
    }

    /**
     * Set table object
     * - Size of the table as two integers [width, height]
     * @param input
     * @param shape
     * @return table
     */
    public static Table setTableInput(List<Integer> input, Shape shape) {
        Table table = new Table();
        if(shape == Shape.RECTANGLE){
            table.setMaxPositionX(input.get(0));
            table.setMaxPositionY(input.get(1));
            table.setShape(shape);
            table.setDirection(START_DIRECTION.getValue());
        }
        // Area boundary for other shapes options can be added here in the future
        return table;
    }

    /**
     * Set matrix object.
     * - Objects starting position as two integers [x, y]
     * @param input
     * @param shape
     * @return
     */
    public static MatrixObject setMatrixObject(List<Integer> input, Shape shape) {
        MatrixObject object = new MatrixObject();
        if(shape == Shape.RECTANGLE){
            object.setPositionX(input.get(2));
            object.setPositionY(input.get(3));
            object.setDirection(START_DIRECTION.getValue());
        }

        // Initial positions of XY reference may vary per shapes property. It can be added here in the future.
        return object;
    }

    /**
     * Execute simulation commands
     *
     * @param table
     * @param object
     * @param commands
     * @return result
     */
    public static MatrixObject executeCommands(Table table, MatrixObject object, List<Integer> commands) {
        positionX = object.getPositionX();
        positionY = object.getPositionY();
        curr_direction = object.getDirection();

        // Loop and evaluate each commands
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i) == MoveCommand.QUIT.getCommand()) {
                break;
            } else if (commands.get(i) == MoveCommand.FORWARD.getCommand()) {
                moveObjectForward();
            } else if (commands.get(i) == MoveCommand.BACKWARD.getCommand()) {
                moveObjectBackward();
            } else if (commands.get(i) == MoveCommand.CLOCKWISE.getCommand()) {
                moveObjectClockwise();
            } else if (commands.get(i) == MoveCommand.COUNTER_CLOCKWISE.getCommand()) {
                moveObjectCounterClockwise();
            }
        }

        return new MatrixObject(positionX, positionY, curr_direction);
    }

    /**
     * Print the simulation result
     * - If the simulation succeeded: The objects final position as two integers [x, y].
     * - If the simulation failed (the object falls off the table): [-1, -1]
     * @param result
     * @param table
     */
    private static void printSimulationResult(MatrixObject result, Table table) {
        // validate if objects current result position is not outside the table
        if(ValidationUtil.checkObjectPosition(result, table, shape)){
            List<Integer> simResult = Arrays.asList(result.getPositionX(), result.getPositionY());
            System.out.println(simResult);
        } else {
            System.out.println(SIM_FAILED);
        }
    }

    /**
     * Move object forward one step
     */
    private static void moveObjectForward() {
        Direction direction = Direction.valueOf(curr_direction);
        switch(direction)
        {
            case NORTH: positionY--; break;
            case EAST: positionX++; break;
            case SOUTH: positionY++; break;
            case WEST: positionX--; break;
            default: break;
        }
    }

    /**
     * Move object backward one step
     */
    private static void moveObjectBackward() {
        Direction direction = Direction.valueOf(curr_direction);
        switch(direction)
        {
            case NORTH: positionY++; break;
            case EAST:  positionX--; break;
            case SOUTH: positionY--; break;
            case WEST:  positionX++; break;
            default: break;
        }
    }

    /**
     * Rotate object clockwise 90 degrees
     */
    private static void moveObjectClockwise() {
        curr_direction++;
        if(curr_direction > 3){
            curr_direction = 0;
        }
    }

    /**
     * Rotate object counter clockwise 90 degrees
     */
    private static void moveObjectCounterClockwise() {
        curr_direction--;
        if(curr_direction < 0){
            curr_direction = 3;
        }
    }

}
