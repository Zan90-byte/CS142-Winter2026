package Space_Destruction;
import Space_Destruction.Space_Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GUIProgram extends JPanel {

    private DestructionMap map;
    private ArrayList<backgroundStars> stars;

    public GUIProgram(DestructionMap map) {
        this.map = map;

        stars = new ArrayList<>();

        JFrame frame = new JFrame("Space Destruction Simulation");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

        Random rand = new Random();

        for (int i = 0; i < 200; i++) {
            stars.add(new backgroundStars(
                    rand.nextInt(800),
                    rand.nextInt(800),
                    rand.nextInt(3) + 1
            ));
        }

        map.start();

        Timer timer = new Timer(50, e -> {
            map.update();
            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint background black (grey by default)
        setBackground(Color.BLACK);

        // Draw stars
        for (backgroundStars s : stars) {
            s.draw(g);
        }

        // Move origin (0, 0) to center
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth() / 2, getHeight() / 2);

        // Draw simulation
        map.draw(g2);
    }
}