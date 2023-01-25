import java.awt.*;
import javax.swing.*;

public class GraphicsIntro extends JPanel {

    public GraphicsIntro() {
        setPreferredSize(new Dimension(800, 480));
        setBackground(new Color(252, 177, 3));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 255, 0));
        for (int i = 0; i < 10; i++) {
            g.drawLine(20 + i, 20, 500 + i, 360);
        }
        g.fillRect(200, 200, 100, 80);
        g.setColor(new Color(168, 115, 50));
        g.fillOval(5, 5, 100, 200);
        g.setColor(new Color(255, 0, 0));
        g.drawArc(20, 200, 100, 200, 45, 180);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphics Intro");
        GraphicsIntro panel = new GraphicsIntro();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
