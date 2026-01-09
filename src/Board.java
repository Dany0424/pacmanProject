import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Pacman pacman;
    private Ghost[] ghosts;
    private int[][] maze;
    private int currentLevel = 1;
    private int totalDots = 0;
    private boolean gameOver = false;
    private static final int BLOCK_SIZE = 20;
    private static final int MAX_LEVELS = 3;
    private static final int COLLISION_THRESHOLD = 18;
    
    // Elementos del laberinto:
    // 0 = vacío, 1 = pared, 2 = punto (dot)
    
    // Nivel 1 - Laberinto simple
    private int[][] level1 = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,2,1,1,1,2,1,1,2,1,1,1,2,1,1,2,1},
        {1,2,1,1,2,1,1,1,2,1,1,2,1,1,1,2,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,2,1},
        {1,2,2,2,2,1,2,2,2,1,1,2,2,2,1,2,2,2,2,1},
        {1,1,1,1,2,1,1,1,0,1,1,0,1,1,1,2,1,1,1,1},
        {1,1,1,1,2,1,0,0,0,0,0,0,0,0,1,2,1,1,1,1},
        {1,2,2,2,2,2,0,1,1,0,0,1,1,0,2,2,2,2,2,1},
        {1,1,1,1,2,1,0,1,1,1,1,1,1,0,1,2,1,1,1,1},
        {1,1,1,1,2,1,0,0,0,0,0,0,0,0,1,2,1,1,1,1},
        {1,2,2,2,2,1,2,1,1,1,1,1,1,2,1,2,2,2,2,1},
        {1,2,1,1,2,2,2,2,2,1,1,2,2,2,2,2,1,1,2,1},
        {1,2,1,1,2,1,2,1,2,1,1,2,1,2,1,2,1,1,2,1},
        {1,2,2,2,2,1,2,2,2,2,2,2,2,2,1,2,2,2,2,1},
        {1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    
    // Nivel 2 - Laberinto con cruz central
    private int[][] level2 = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
        {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},
        {1,2,1,2,1,1,1,2,1,1,1,1,2,1,1,1,2,1,2,1},
        {1,2,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1},
        {1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
        {1,2,1,2,2,2,1,2,2,2,2,2,2,1,2,2,2,1,2,1},
        {1,2,1,1,1,2,1,2,1,1,1,1,2,1,2,1,1,1,2,1},
        {1,2,2,2,2,2,2,2,1,0,0,1,2,2,2,2,2,2,2,1},
        {1,2,1,1,1,2,1,2,1,1,1,1,2,1,2,1,1,1,2,1},
        {1,2,1,2,2,2,1,2,2,2,2,2,2,1,2,2,2,1,2,1},
        {1,2,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1},
        {1,2,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1},
        {1,2,1,2,1,1,1,2,1,1,1,1,2,1,1,1,2,1,2,1},
        {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,1},
        {1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    
    // Nivel 3 - Laberinto complejo
    private int[][] level3 = {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,2,1,1,2,1,1,1,1,2,2,1,1,1,1,2,1,1,2,1},
        {1,2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,1},
        {1,1,2,1,2,1,2,1,1,1,1,1,1,2,1,2,1,2,1,1},
        {1,2,2,2,2,1,2,2,2,2,2,2,2,2,1,2,2,2,2,1},
        {1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,2,1,1,1,0,1,1,0,1,1,1,2,1,1,1,1},
        {0,0,0,1,2,1,0,0,0,0,0,0,0,0,1,2,1,0,0,0},
        {1,1,1,1,2,1,0,1,1,1,1,1,1,0,1,2,1,1,1,1},
        {1,2,2,2,2,2,2,1,0,0,0,0,1,2,2,2,2,2,2,1},
        {1,2,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,1},
        {1,2,2,2,2,1,2,2,2,2,2,2,2,2,1,2,2,2,2,1},
        {1,1,2,1,2,1,2,1,1,1,1,1,1,2,1,2,1,2,1,1},
        {1,2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,1},
        {1,2,1,1,2,1,1,1,1,2,2,1,1,1,1,2,1,1,2,1},
        {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public Board() {
        setFocusable(true);
        setBackground(Color.BLACK);
        loadLevel(currentLevel);
        pacman = new Pacman(180, 300, this);
        ghosts = new Ghost[] {
            new Ghost(180, 180, Color.RED, this),
            new Ghost(200, 180, Color.PINK, this),
            new Ghost(180, 220, Color.CYAN, this)
        };
        timer = new Timer(40, this);
        timer.start();
        addKeyListener(new PacmanKeyAdapter());
    }
    
    private void loadLevel(int level) {
        switch (level) {
            case 1:
                maze = copyArray(level1);
                break;
            case 2:
                maze = copyArray(level2);
                break;
            case 3:
                maze = copyArray(level3);
                break;
            default:
                maze = copyArray(level1);
        }
        countDots();
    }
    
    private int[][] copyArray(int[][] source) {
        int[][] dest = new int[source.length][source[0].length];
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, dest[i], 0, source[i].length);
        }
        return dest;
    }
    
    private void countDots() {
        totalDots = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 2) {
                    totalDots++;
                }
            }
        }
    }
    
    public boolean isWall(int x, int y) {
        int col = x / BLOCK_SIZE;
        int row = y / BLOCK_SIZE;
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return true;
        }
        return maze[row][col] == 1;
    }
    
    public void checkDotCollision(int x, int y) {
        int col = x / BLOCK_SIZE;
        int row = y / BLOCK_SIZE;
        if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
            if (maze[row][col] == 2) {
                maze[row][col] = 0;
                pacman.addScore(10);
                totalDots--;
                if (totalDots == 0) {
                    nextLevel();
                }
            }
        }
    }
    
    private void nextLevel() {
        currentLevel++;
        if (currentLevel > MAX_LEVELS) {
            currentLevel = 1;
        }
        loadLevel(currentLevel);
        pacman.reset(180, 300);
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }
    }
    
    private void checkGhostCollision() {
        if (gameOver) {
            return;
        }
        
        int pacmanX = pacman.getX();
        int pacmanY = pacman.getY();
        
        for (Ghost ghost : ghosts) {
            int ghostX = ghost.getX();
            int ghostY = ghost.getY();
            
            // Calculate distance between pacman and ghost
            int dx = pacmanX - ghostX;
            int dy = pacmanY - ghostY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            // If distance is less than threshold, collision occurred
            if (distance < COLLISION_THRESHOLD) {
                handleLifeLoss();
                break;
            }
        }
    }
    
    private void handleLifeLoss() {
        pacman.loseLife();
        
        if (pacman.isGameOver()) {
            gameOver = true;
            timer.stop();
        } else {
            // Reset positions
            pacman.reset(180, 300);
            for (Ghost ghost : ghosts) {
                ghost.reset();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        pacman.draw(g);
        for (Ghost ghost : ghosts) {
            ghost.draw(g);
        }
    }

    private void drawBoard(Graphics g) {
        // Dibujar el laberinto
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                int x = col * BLOCK_SIZE;
                int y = row * BLOCK_SIZE;
                
                if (maze[row][col] == 1) {
                    // Dibujar pared
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.CYAN);
                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                } else if (maze[row][col] == 2) {
                    // Dibujar punto
                    g.setColor(Color.WHITE);
                    g.fillOval(x + 7, y + 7, 6, 6);
                }
            }
        }
        
        // Mostrar información del juego
        g.setColor(Color.YELLOW);
        g.drawString("Score: " + pacman.getScore(), 10, 395);
        g.drawString("Level: " + currentLevel, 180, 395);
        g.drawString("Lives: " + pacman.getLives(), 260, 395);
        g.drawString("Dots: " + totalDots, 320, 395);
        
        // Mostrar mensaje de game over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", 90, 200);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            pacman.move();
            for (Ghost ghost : ghosts) {
                ghost.move();
            }
            checkDotCollision(pacman.getX(), pacman.getY());
            checkGhostCollision();
        }
        repaint();
    }

    private class PacmanKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            pacman.keyPressed(e);
        }
    }
}