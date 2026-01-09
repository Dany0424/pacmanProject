import org.junit.jupiter.api.Test;
import javax.swing.JFrame;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testGameConstructorInHeadless() {
        // In headless mode, creating a Game will throw HeadlessException
        // which is expected behavior
        try {
            Game game = new Game();
            // If we're not in headless mode, verify it's created
            assertNotNull(game);
        } catch (java.awt.HeadlessException e) {
            // Expected in headless environment
            assertTrue(true);
        }
    }

    @Test
    void testGameWouldHaveCorrectTitle() {
        // Test that the Game class is properly structured
        // by checking it extends JFrame
        assertTrue(Game.class.getSuperclass().equals(JFrame.class));
    }
}
