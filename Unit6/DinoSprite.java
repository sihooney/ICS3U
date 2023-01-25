import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class DinoSprite extends JPanel implements KeyListener, Runnable {

    static BufferedImage[] tRexSprites = new BufferedImage[3];
    static int spriteNum = 0;
    static int xPos = 100;
    static int yPos = 205;

    static int dy = 0;
    static boolean jumping = false;
    static int gravity = 5;

    public DinoSprite() {
        setPreferredSize(new Dimension(500, 300));
        addKeyListener(this);
        this.setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();

        try {
            tRexSprites[0] = ImageIO.read(new File("trexleft.png"));
            tRexSprites[1] = ImageIO.read(new File("trexright.png"));
            tRexSprites[2] = ImageIO.read(new File("trexjump.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tRexSprites[spriteNum], xPos, yPos, null);
        tRexUpdate();
    }

    public static void tRexUpdate() {
        if (jumping) {
            spriteNum = 2;
            yPos += dy;
            dy += gravity;
            if (yPos == 205) {
                dy = 0;
                jumping = false;
            }
        } else {
            spriteNum = (spriteNum + 1) % 2;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            if (!jumping) {
                jumping = true;
                dy = -40;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sprite Animation using Chrome Dino");
        DinoSprite panel = new DinoSprite();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
