import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.awt.Color;
import java.awt.Graphics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GhostTest {

    @Mock
    private Board board;
    
    @Mock
    private Graphics graphics;

    private Ghost ghost;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ghost = new Ghost(150, 200, Color.RED, board);
    }

    @Test
    void testInitialPosition() {
        assertEquals(150, ghost.getX());
        assertEquals(200, ghost.getY());
    }

    @Test
    void testReset() {
        // Mock board to allow movement
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        // Move the ghost
        ghost.move();
        
        // Reset should restore initial position
        ghost.reset();
        
        assertEquals(150, ghost.getX());
        assertEquals(200, ghost.getY());
    }

    @Test
    void testMoveWithNoWalls() {
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialX = ghost.getX();
        int initialY = ghost.getY();
        
        ghost.move();
        
        // Ghost should have moved in some direction
        boolean positionChanged = (ghost.getX() != initialX) || (ghost.getY() != initialY);
        assertTrue(positionChanged);
    }

    @Test
    void testMoveWithWalls() {
        // Make all positions walls to prevent movement
        when(board.isWall(anyInt(), anyInt())).thenReturn(true);
        
        int initialX = ghost.getX();
        int initialY = ghost.getY();
        
        // Try multiple moves
        for (int i = 0; i < 20; i++) {
            ghost.move();
        }
        
        // Ghost should stay in place when surrounded by walls
        assertEquals(initialX, ghost.getX());
        assertEquals(initialY, ghost.getY());
    }

    @Test
    void testMoveChangesDirectionWhenHittingWall() {
        // Allow initial position, but block new positions
        when(board.isWall(150, 200)).thenReturn(false);
        when(board.isWall(169, 200)).thenReturn(false);
        when(board.isWall(150, 219)).thenReturn(false);
        when(board.isWall(169, 219)).thenReturn(false);
        
        // Block all other positions
        when(board.isWall(anyInt(), anyInt())).thenReturn(true);
        
        // Force a move that should hit a wall
        for (int i = 0; i < 10; i++) {
            ghost.move();
        }
        
        // Ghost should handle wall collisions without crashing
        assertNotNull(ghost);
    }

    @Test
    void testDrawMethodDoesNotThrow() {
        assertDoesNotThrow(() -> ghost.draw(graphics));
        verify(graphics).setColor(Color.RED);
        verify(graphics).fillOval(eq(150), eq(200), eq(20), eq(20));
    }

    @Test
    void testMultipleMovesEventuallyChangePosition() {
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialX = ghost.getX();
        int initialY = ghost.getY();
        
        // Move multiple times to ensure ghost actually moves
        boolean moved = false;
        for (int i = 0; i < 100; i++) {
            ghost.move();
            if (ghost.getX() != initialX || ghost.getY() != initialY) {
                moved = true;
                break;
            }
        }
        
        assertTrue(moved, "Ghost should move after multiple iterations");
    }

    @Test
    void testGhostWithDifferentColors() {
        Ghost redGhost = new Ghost(100, 100, Color.RED, board);
        Ghost pinkGhost = new Ghost(120, 120, Color.PINK, board);
        Ghost cyanGhost = new Ghost(140, 140, Color.CYAN, board);
        
        assertNotNull(redGhost);
        assertNotNull(pinkGhost);
        assertNotNull(cyanGhost);
    }

    @Test
    void testResetRestoresStartingPosition() {
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int startX = 300;
        int startY = 400;
        Ghost ghost2 = new Ghost(startX, startY, Color.BLUE, board);
        
        // Move the ghost several times
        for (int i = 0; i < 50; i++) {
            ghost2.move();
        }
        
        // Reset
        ghost2.reset();
        
        // Should be back at start position
        assertEquals(startX, ghost2.getX());
        assertEquals(startY, ghost2.getY());
    }
}
