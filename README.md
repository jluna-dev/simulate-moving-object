### About
- The purpose of this test is to cover general practices, design and structure as well as algorithmic solutions for a smaller project.
- You can make assumptions if nothing is specifically stated, but be sure to document these.
- The end result should be a command line based program that will perform simple simulations of a moving object. 
- The program will read from stdin and write to stdout according to a certain protocol.
  * Developer Notes: Used Java equivalent of stdin and stdout 
- When using client-side Javascript use the browser console instead of the command line.


### Task
- The task is to accept a set of commands and then simulate whether an object can move according to these commands without falling off the table it stands on.
- The table can be seen as a matrix where the object will have an x and y position.
- The object always occupies exactly one cell and can be seen as a point without mass. Origo is at the top left.


### Protocol
- First, your solution reads a header from stdin like this:
  * The size of the table as two integers [width, height]
  * The objects starting position as two integers [x, y]
- This is followed by an arbitrarily long stream of commands of integers.
- When the simulation is complete, your program outputs the answer to stout as per below:
  * If the simulation **succeeded**: The objects final position as two integers `[x, y]`.
  * If the simulation **failed** (the object falls off the table): `[-1, -1]` should be returned.

### Commands
- The object always has a direction (north, east, south or west). 
- A simulation always starts with direction north. 
- North means that if the object sits on [2, 4] and moves forward one step, the object will now stand on [2, 3].
- The commands are:
```
  0 = quit simulation and print results to stout
  1 = move forward one step
  2 = move backwards one step
  3 = rotate clockwise 90 degrees (eg north to east)
  4 = rotate counterclockwise 90 degrees (eg west to south)>` 
```

### Example
- If the program gets `4,4,2,2`  as input, the table is initiated to size 4 x 4 with the object in position [2, 2] with direction north. 
- Then, commands `1,4,1,3,2,3,2,4,1,0` are read from stdin and executed. The final output would then be the end position of the object, in this case`[0, 1]`.

# Developer Notes / Documentation

### Validations
Added validation exception handling for the following validations:
- Input size should be 4.
- Commands input range should be between 0 to 4 only.
- Valid table size.
- Object position initial or result should not exceed table size.


### Developer Notes
- Program done in Java.
- Uses BufferedReader to read user input from the command prompt. This can be flexible in changing user input i.e. read a file as JSON input. 
- Created separate method to set table object from input to allow flexibility in unit testing.
- Created separate method to set matrix object from input to allow flexibility in unit testing.
- Added unit tests. Test class can be found in `src/test/java/SimulateMoveObjectTest.java`

### Expanded functionality in the future
- Handle different shapes than a rectangle
  * MatrixObject model has "direction" variable to handle this functionality in the future. Logic can be added to current code to handle such function.
- Add more commands like rotating the table instead of the object
  * Table model has "direction" variable to handle this functionality in the future. Logic can be added to current code to handle such function.
- Change the binary form of the protocol to JSON
  * In SimulateMoveObject.readInput() method, BufferedReader can be used together with FileReader to read a JSON file source format for the input.