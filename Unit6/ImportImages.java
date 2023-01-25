import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;

public class ImportImages extends JPanel {

    public ImportImages() {
        setPreferredSize(new Dimension(700, 360));
    }

    public void paintComponent(Graphics g) {
        BufferedImage img;

        super.paintComponent(g);
        try {
            img = ImageIO.read(new File("img1.jpg"));
            g.drawImage(img, 100, 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImportImages panel = new ImportImages();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
