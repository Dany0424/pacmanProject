import java.awt.*;
import java.util.Random;

public class Ghost {
    private int x, y;
    private int startX, startY;
    private Direction direction;
    private Color color;
    private Random random = new Random();
    private Board board;
    private static final int SIZE = 20;

    public Ghost(int x, int y, Color color, Board board) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.color = color;
        this.board = board;
        this.direction = Direction.values()[random.nextInt(4)];
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {
        if (random.nextInt(10) == 0) {
            direction = Direction.values()[random.nextInt(4)];
        }
        
        int newX = x;
        int newY = y;
        
        switch (direction) {
            case LEFT: newX -= 4; break;
            case RIGHT: newX += 4; break;
            case UP: newY -= 4; break;
            case DOWN: newY += 4; break;
        }
        
        // Verificar colisión con paredes (verificar las 4 esquinas del sprite)
        if (!board.isWall(newX, newY) && !board.isWall(newX + SIZE - 1, newY) && 
            !board.isWall(newX, newY + SIZE - 1) && !board.isWall(newX + SIZE - 1, newY + SIZE - 1)) {
            x = newX;
            y = newY;
        } else {
            // Cambiar de dirección si choca con una pared
            direction = Direction.values()[random.nextInt(4)];
        }
    }
    
    public void reset() {
        this.x = startX;
        this.y = startY;
        this.direction = Direction.values()[random.nextInt(4)];
    }
}