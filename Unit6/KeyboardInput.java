import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyboardInput extends JPanel implements KeyListener {

    static int ballX = 225;
    static int ballY = 225;
    static int ballD = 50;

    public KeyboardInput() {
        setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 0, 0));
        g.fillOval(ballX, ballY, ballD, ballD);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'w') {
            ballY -= 5;
        } else if (key == 'a') {
            ballX -= 5;
        } else if (key == 's') {
            ballY += 5;
        } else if (key == 'd') {
            ballX += 5;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("KeyListener demo");
        KeyboardInput panel = new KeyboardInput();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
