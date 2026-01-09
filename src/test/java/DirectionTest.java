import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testLeftDirection() {
        Direction direction = Direction.LEFT;
        assertEquals(180, direction.getAngle());
    }

    @Test
    void testRightDirection() {
        Direction direction = Direction.RIGHT;
        assertEquals(0, direction.getAngle());
    }

    @Test
    void testUpDirection() {
        Direction direction = Direction.UP;
        assertEquals(90, direction.getAngle());
    }

    @Test
    void testDownDirection() {
        Direction direction = Direction.DOWN;
        assertEquals(270, direction.getAngle());
    }

    @Test
    void testEnumValues() {
        Direction[] values = Direction.values();
        assertEquals(4, values.length);
        assertEquals(Direction.LEFT, values[0]);
        assertEquals(Direction.RIGHT, values[1]);
        assertEquals(Direction.UP, values[2]);
        assertEquals(Direction.DOWN, values[3]);
    }

    @Test
    void testValueOf() {
        assertEquals(Direction.LEFT, Direction.valueOf("LEFT"));
        assertEquals(Direction.RIGHT, Direction.valueOf("RIGHT"));
        assertEquals(Direction.UP, Direction.valueOf("UP"));
        assertEquals(Direction.DOWN, Direction.valueOf("DOWN"));
    }
}
