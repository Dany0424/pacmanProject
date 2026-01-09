import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacmanTest {

    @Mock
    private Board board;
    
    @Mock
    private KeyEvent keyEvent;

    private Pacman pacman;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pacman = new Pacman(100, 200, board);
    }

    @Test
    void testInitialPosition() {
        assertEquals(100, pacman.getX());
        assertEquals(200, pacman.getY());
    }

    @Test
    void testInitialScore() {
        assertEquals(0, pacman.getScore());
    }

    @Test
    void testInitialLives() {
        assertEquals(3, pacman.getLives());
    }

    @Test
    void testAddScore() {
        pacman.addScore(10);
        assertEquals(10, pacman.getScore());
        
        pacman.addScore(50);
        assertEquals(60, pacman.getScore());
    }

    @Test
    void testLoseLife() {
        assertEquals(3, pacman.getLives());
        pacman.loseLife();
        assertEquals(2, pacman.getLives());
        pacman.loseLife();
        assertEquals(1, pacman.getLives());
    }

    @Test
    void testAddLife() {
        pacman.addLife();
        assertEquals(4, pacman.getLives());
    }

    @Test
    void testAddLifeMaxLimit() {
        // Add lives up to max
        for (int i = 0; i < 10; i++) {
            pacman.addLife();
        }
        // Should not exceed max of 9
        assertEquals(9, pacman.getLives());
    }

    @Test
    void testIsGameOverWhenLivesPositive() {
        assertFalse(pacman.isGameOver());
    }

    @Test
    void testIsGameOverWhenLivesZero() {
        pacman.loseLife();
        pacman.loseLife();
        pacman.loseLife();
        assertTrue(pacman.isGameOver());
    }

    @Test
    void testReset() {
        pacman.addScore(100);
        pacman.loseLife();
        
        pacman.reset(150, 250);
        
        assertEquals(150, pacman.getX());
        assertEquals(250, pacman.getY());
        // Score and lives should remain unchanged
        assertEquals(100, pacman.getScore());
        assertEquals(2, pacman.getLives());
    }

    @Test
    void testKeyPressedLeft() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        pacman.keyPressed(keyEvent);
        // Direction is set, but we can't directly test it without a getter
        // We can verify movement behavior instead
        verify(keyEvent).getKeyCode();
    }

    @Test
    void testKeyPressedRight() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        pacman.keyPressed(keyEvent);
        verify(keyEvent).getKeyCode();
    }

    @Test
    void testKeyPressedUp() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        pacman.keyPressed(keyEvent);
        verify(keyEvent).getKeyCode();
    }

    @Test
    void testKeyPressedDown() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        pacman.keyPressed(keyEvent);
        verify(keyEvent).getKeyCode();
    }

    @Test
    void testMoveLeftWithNoWall() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        pacman.keyPressed(keyEvent);
        
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialX = pacman.getX();
        pacman.move();
        
        assertEquals(initialX - 4, pacman.getX());
    }

    @Test
    void testMoveRightWithNoWall() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        pacman.keyPressed(keyEvent);
        
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialX = pacman.getX();
        pacman.move();
        
        assertEquals(initialX + 4, pacman.getX());
    }

    @Test
    void testMoveUpWithNoWall() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        pacman.keyPressed(keyEvent);
        
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialY = pacman.getY();
        pacman.move();
        
        assertEquals(initialY - 4, pacman.getY());
    }

    @Test
    void testMoveDownWithNoWall() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        pacman.keyPressed(keyEvent);
        
        when(board.isWall(anyInt(), anyInt())).thenReturn(false);
        
        int initialY = pacman.getY();
        pacman.move();
        
        assertEquals(initialY + 4, pacman.getY());
    }

    @Test
    void testMoveWithWallBlocksMovement() {
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        pacman.keyPressed(keyEvent);
        
        when(board.isWall(anyInt(), anyInt())).thenReturn(true);
        
        int initialX = pacman.getX();
        int initialY = pacman.getY();
        pacman.move();
        
        // Position should not change when hitting a wall
        assertEquals(initialX, pacman.getX());
        assertEquals(initialY, pacman.getY());
    }

    @Test
    void testDrawMethodDoesNotThrow() {
        java.awt.Graphics graphics = mock(java.awt.Graphics.class);
        assertDoesNotThrow(() -> pacman.draw(graphics));
    }
}
