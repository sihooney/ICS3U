import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class MarioHighScore extends JPanel implements Runnable, KeyListener {

    static int gs = 0;
    static BufferedImage start;
    static BufferedImage highscreen;
    static BufferedImage[] sprites = new BufferedImage[8];
    static int frameCount = 0;
    static int imgNo = 0;
    static int xPos = 170;
    static int yPos = 175;
    static int score = 0;
    static int scoreFrameCnt = 0;
    static File topscores;
    static ArrayList<Integer> highscores = new ArrayList<>();

    public MarioHighScore() throws IOException {
        setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
        this.setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();
        for (int i = 0; i < 8; i++) {
            sprites[i] = ImageIO.read(new File("mario" + i + ".png"));
        }
        start = ImageIO.read(new File("start.png"));
        highscreen = ImageIO.read(new File("highscreen.png"));
        topscores = new File("highscores.txt");
        Scanner sc = new Scanner(topscores);
        while (sc.hasNextLine()) {
            highscores.add(Integer.parseInt(sc.nextLine()));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Courier New", Font.BOLD, 30));
        if (gs == 0) {
            g.drawImage(start, 0, 0, null);
            frameCount = 0;
            score = 0;
        } else if (gs == 1) {
            super.paintComponent(g);
            g.drawImage(sprites[imgNo], xPos, yPos, null);
            g.drawString("Score: " + score, 0, 30);
            updateMario();
        } else if (gs == 2) {
            g.drawImage(highscreen, 0, 0, null);
        } else if (gs == 3) {
            super.paintComponent(g);
            g.drawString("High Scores: ", 100, 250);
            if (highscores.size() >= 1) {
                g.drawString("1st: " + highscores.get(0), 100, 300);
            }
            if (highscores.size() >= 2) {
                g.drawString("2nd: " + highscores.get(1), 100, 350);
            }
            if (highscores.size() >= 3) {
                g.drawString("3rd: " + highscores.get(2), 100, 400);
            }
        }
    }

    boolean updateScores() throws IOException {
        highscores.add(score);
        highscores.sort(Collections.reverseOrder());
        PrintWriter pr = new PrintWriter(topscores);
        for (int s : highscores) {
            pr.println(s);
        }
        pr.close();

        return highscores.get(0) == score;
    }

    void updateMario() {
        scoreFrameCnt++;
        if (scoreFrameCnt == 50) {
            score++;
            scoreFrameCnt = 0;
        }
        if (frameCount % 2 == 0) {
            imgNo = (imgNo + 1) % 8;
        }
        frameCount++;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gs == 0) {
            if (e.getKeyChar() == ' ') {
                gs = 1;
            }
        } else if (gs == 1) {
            if (e.getKeyChar() == ' ') {
                try {
                    if (updateScores()) {
                        gs = 2;
                    } else {
                        gs = 3;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (gs == 2) {
            if (e.getKeyChar() == ' ') {
                gs = 3;
            }
        } else if (gs == 3) {
            if (e.getKeyChar() == ' ') {
                gs = 0;
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

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Mario Sprite Animation High Score");
        MarioHighScore panel = new MarioHighScore();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
