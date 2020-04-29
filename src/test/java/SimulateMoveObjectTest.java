import com.sm.exercise.SimulateMoveObject;
import com.sm.exercise.ValidationUtil;
import com.sm.exercise.enums.Shape;
import com.sm.exercise.model.MatrixObject;
import com.sm.exercise.model.Table;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimulateMoveObjectTest {

    @Test
    public void testSimulationSuccess() {
        SimulateMoveObject simulateMoveObject = new SimulateMoveObject();
        List<Integer> input = Arrays.asList(4,4,2,2);
        List<Integer> commands = Arrays.asList(1,4,1,3,2,3,2,4,1,0);
        Shape shape = Shape.RECTANGLE;

        Table table = simulateMoveObject.setTableInput(input, shape);
        MatrixObject object = simulateMoveObject.setMatrixObject(input, shape);
        MatrixObject resultObj = simulateMoveObject.executeCommands(table, object, commands);

        List<Integer> expected = Arrays.asList(0,1);
        List<Integer> result = Arrays.asList(resultObj.getPositionX(), resultObj.getPositionY());
        assertEquals(expected, result);
    }

    @Test
    public void testSimulationFailed() {
        SimulateMoveObject simulateMoveObject = new SimulateMoveObject();

        List<Integer> input = Arrays.asList(4,4,2,2);
        List<Integer> commands = Arrays.asList(1,4,1,3,2,3,2,4,1,1,1,1,1,1,1,0);
//        List<Integer> commands = Arrays.asList(1,4,1,3,2,3,2,4,1,0);    //test data to fail this test method

        Shape shape = Shape.RECTANGLE;
        Table table = simulateMoveObject.setTableInput(input, shape);
        MatrixObject object = simulateMoveObject.setMatrixObject(input, shape);
        MatrixObject resultObj = simulateMoveObject.executeCommands(table, object, commands);

        List<Integer> expectedFailed = Arrays.asList(-1,-1);
        List<Integer> result;
        boolean isResultObjInside = ValidationUtil.checkObjectPosition(resultObj, table, shape);

        if(!isResultObjInside){
            result = Arrays.asList(-1,-1);
        } else{
            result = Arrays.asList(resultObj.getPositionX(), resultObj.getPositionY());
        }

        assertTrue(!isResultObjInside);
        assertEquals(expectedFailed, result);
    }


}
