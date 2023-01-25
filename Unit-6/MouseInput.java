import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MouseInput extends JPanel implements MouseListener {

    static int xPos;
    static int yPos;
    static int gameState = 0;
    static int mouseClicks = 0;
    static BufferedImage gameOver;

    public MouseInput() {
        setPreferredSize(new Dimension(500, 500));
        addMouseListener(this);
        try {
            gameOver = ImageIO.read(new File("game_over.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == 0) {
            g.setColor(new Color(0, 255, 0));
            g.fillRect(150, 375, 200, 50);
        } else if (gameState == 1) {
            g.setColor(new Color(0, 0, 255));
            g.fillOval(xPos - 25, yPos - 25, 50, 50);
        } else if (gameState == 2) {
            g.drawImage(gameOver, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MouseInput panel = new MouseInput();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
        if (gameState == 0) {
            if (xPos >= 150 && xPos <= 250 && yPos >= 275 && yPos <= 425) {
                gameState = 1;
            }
        } else if (gameState == 1) {
            if (++mouseClicks == 5) {
                gameState = 2;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        gameState = 2;
        repaint();
    }
}
