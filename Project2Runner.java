import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Name: Arham
 * Student ID: 501288044
 *
 * Project Title: Situationship Simulator+
 *
 * Project Description:
 * This program is a mood-themed drawing canvas using Swing and 2D graphics.
 * Users can click buttons to add shapes representing different emotions:
 *   - Red = Anger
 *   - Blue = Sadness
 *   - Yellow = Hope
 *   - Green = Envy
 *
 * Shapes are randomly placed and can be circles or squares.
 * You can:
 *   - Click and drag shapes with the mouse
 *   - Press keys (r, b, y, g) to change the color/mood of all shapes
 *   - See a live count of how many shapes of each emotion are on screen
 *
 * Technical Requirements Met:
 * ✅ Swing:
 *    - 4 JButtons for each emotion
 *    - 2 JLabels (instructions and mood count)
 * ✅ 2D Graphics:
 *    - Shapes drawn with Graphics in a custom JPanel
 * ✅ Event Listeners:
 *    - ActionListener (on buttons)
 *    - MouseListener + MouseMotionListener (dragging shapes)
 *    - KeyListener (change all moods with r, b, y, g)
 *
 * Instructions:
 * 1. Click buttons to add shapes.
 * 2. Click + drag shapes to move them.
 * 3. Press 'r', 'b', 'y', or 'g' to change the mood of all shapes.
 * 4. Mood count is shown at the bottom.
 */

public class Project2Runner {
    public static void main(String[] args) {
        new MoodApp();
    }
}

// Sets up the main window with buttons and canvas
class MoodApp extends JFrame {
    private DrawingPanel canvas;
    private JLabel stats;

    public MoodApp() {
        setTitle("Situationship Simulator+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JButton redBtn = new JButton("Anger");
        JButton blueBtn = new JButton("Sadness");
        JButton yellowBtn = new JButton("Hope");
        JButton greenBtn = new JButton("Envy");

        JLabel info = new JLabel("Press r/b/y/g to change mood. Drag shapes.");
        stats = new JLabel("No shapes yet.");

        canvas = new DrawingPanel(stats);

        JPanel top = new JPanel();
        top.add(redBtn); top.add(blueBtn);
        top.add(yellowBtn); top.add(greenBtn);
        top.add(info);

        add(top, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(stats, BorderLayout.SOUTH);

        redBtn.addActionListener(e -> canvas.addShape(Color.RED));
        blueBtn.addActionListener(e -> canvas.addShape(Color.BLUE));
        yellowBtn.addActionListener(e -> canvas.addShape(Color.YELLOW));
        greenBtn.addActionListener(e -> canvas.addShape(Color.GREEN));

        setVisible(true);
    }
}

// The canvas where shapes are drawn and interacted with
class DrawingPanel extends JPanel implements KeyListener {
    private ArrayList<MoodShape> shapes = new ArrayList<>();
    private JLabel stats;
    private MoodShape selected = null;

    public DrawingPanel(JLabel stats) {
        this.stats = stats;
        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                for (MoodShape shape : shapes) {
                    if (shape.contains(e.getX(), e.getY())) {
                        selected = shape;
                        repaint();
                        break;
                    }
                }
                requestFocusInWindow();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selected != null) {
                    selected.x = e.getX();
                    selected.y = e.getY();
                    repaint();
                }
            }
        });

        addKeyListener(this);
    }

    /**
     * Adds a shape to the canvas with a random position and shape type.
     * The color is based on the mood selected (red, blue, yellow, green).
     */
    public void addShape(Color color) {
        Random r = new Random();
        int x = r.nextInt(700), y = r.nextInt(400);
        boolean isCircle = r.nextBoolean();
        shapes.add(new MoodShape(x, y, 40, color, isCircle));
        updateStats();
        repaint();
    }

    /**
     * Draws all shapes on the panel using their current color and shape type.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MoodShape shape : shapes) shape.draw(g);
    }

    /**
     * Updates the mood count label (JLabel) based on the number of each color.
     */
    private void updateStats() {
        int r = 0, b = 0, y = 0, g = 0;
        for (MoodShape s : shapes) {
            if (s.color == Color.RED) r++;
            else if (s.color == Color.BLUE) b++;
            else if (s.color == Color.YELLOW) y++;
            else if (s.color == Color.GREEN) g++;
        }
        stats.setText("Anger: " + r + " | Sadness: " + b + " | Hope: " + y + " | Envy: " + g);
    }

    /**
     * When the user presses a key (r/b/y/g), all shapes update to that mood color.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'r' -> changeAll(Color.RED);
            case 'b' -> changeAll(Color.BLUE);
            case 'y' -> changeAll(Color.YELLOW);
            case 'g' -> changeAll(Color.GREEN);
        }
    }

    private void changeAll(Color c) {
        for (MoodShape s : shapes) s.color = c;
        updateStats();
        repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}

// A shape object with color, size, and type (circle or square)
class MoodShape {
    int x, y, size;
    Color color;
    boolean isCircle;

    public MoodShape(int x, int y, int size, Color color, boolean isCircle) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.isCircle = isCircle;
    }

    /**
     * Draws a single shape (either a circle or square) and its outline.
     */
    public void draw(Graphics g) {
        g.setColor(color);
        if (isCircle) {
            g.fillOval(x - size / 2, y - size / 2, size, size);
        } else {
            g.fillRect(x - size / 2, y - size / 2, size, size);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x - size / 2, y - size / 2, size, size);
    }

    /**
     * Returns true if the mouse click is inside this shape.
     */
    public boolean contains(int mx, int my) {
        return mx >= x - size / 2 && mx <= x + size / 2 &&
               my >= y - size / 2 && my <= y + size / 2;
    }
}