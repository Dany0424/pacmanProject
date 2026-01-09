import javax.swing.JFrame;

public class Game extends JFrame {
    public Game() {
        add(new Board());
        setTitle("Pac-Man");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Ajustar al tamaño preferido del Board
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }
}